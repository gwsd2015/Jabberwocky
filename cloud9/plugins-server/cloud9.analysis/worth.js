"use strict";

//var util = require("util");

//var Plugin = require("../cloud9.core/plugin");
//var c9util = require("../cloud9.core/util");
var plug = require("../../configs/default");
var PythonShell = require('python-shell');
var name = "worth";


//This function takes in the working directory and runs the script on it.
    exports.callAlgorithm = function(directory){
        console.log("Inside of callAlgorithm");
        var dir = directory;
        var options = {
            mode: 'text',
            pythonPath: '/usr/bin/python',
            pythonOptions: ['-u'],
            scriptPath: 'plugins-server/cloud9.analysis',
            args: [dir]
        };

        PythonShell.run('round1.py', options, function (err, results) {
            if (err) throw err;
            // results is an array consisting of messages collected during execution
            console.log('results: %j', results);
            //return plug.UnlockL(results[0]);
            //return results[0]
        });

       

    }



