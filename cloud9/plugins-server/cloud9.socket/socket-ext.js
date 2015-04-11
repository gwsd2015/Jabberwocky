/*global require module*/
"use strict";

var assert = require("assert");
var ERROR = require("http-error");
var worthy = require("../cloud9.analysis/worth");
//Q = require('q');
module.exports = function setup(options, imports, register) {

    assert(options.socketPath, "option 'socketPath' is required");

    var IDE = imports.ide.getServer();
    var SESSION = imports.session;
    var PERMISSIONS = imports["workspace-permissions"];
    var smithIde = imports["smith.io.ide"];



    smithIde.addConnectionHook(options.socketPath, function(connection) {

        connection.on("message", function(message) {
            //debugger;
            console.log("--------------Break-----------------");
            for( var propName in message){
                console.log(propName+" is "+message[propName]);
            }
            console.log(message.command+" <-----");
            console.log("--------------Break------------------");
            if (message.command === "attach" && typeof message.workspaceId !== "undefined") {

                getSession(message.sessionId, function(err, session) {
                    if (err) {
                        return connection.send({
                            "type": "error",
                            "code": err.code,
                            "message": err.message
                        });
                    }

                    message.session = session;
                    var uid = session.uid || session.anonid;
                    PERMISSIONS.getPermissions(uid, message.workspaceId, "cloud9.socket", function(err, userPermissions) {
                        if (err) {
                            connection.send(err.toJSON ? err.toJSON() : {
                                "type": "error",
                                "code": err.code || 500,
                                "message": err.message || err
                            });
                        }

                        IDE.addUser(uid, userPermissions);
                        IDE.addClientConnection(uid, connection, message);
                    });
                });
            }
		if(message.command === "javac" || message.command === "git" || message.command === "GIT"){
            console.log("Compilation was called!!!");
            var result = worthy.updateCmdLine(message['wksp'],message['argv']);
            //debugger;
        }
            //checks if the worth command was given. Calls the worthiness algorithm on it..
        if(message.command === "worth") {//&& typeof message.workspaceId !== "undefinded"){
            //console.log("It worked!!!!");
                    //debugger;
                    console.log("socket-ext level is "+message.ld);
                    var result = worthy.callAlgorithm(message['wksp'],message['ld'], function(score){

                        //console.log("result is "+score[0]);
                        var data = {
                            "type": "level",
                            command: "worth",
                            "message" : score.message,
                            id : 1,
                            ld : score
                        };
                        //console.log("Second");
                        /*This sends data back to the client side received in cloud9.core/www/ide.js*/
                        connection.send(data);

                    })
                }
            });
    });

function getSession(sessionId, callback) {
        //debugger;
        SESSION.get(sessionId, function(err, session) {
            if (err)
                return callback(new ERROR.InternalServerError(err));

            if (!session || !(session.uid || session.anonid))
                return callback(new ERROR.Unauthorized("Session ID missing"));

            return callback(null, session);
        });
    }


    register(null, {
        "workspace-socket": {}
    });
};
