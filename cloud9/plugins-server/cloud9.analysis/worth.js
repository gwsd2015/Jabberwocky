"use strict";

//var util = require("util");
//var Plugin = require("../cloud9.core/plugin");
//var c9util = require("../cloud9.core/util");
var config = require("../../configs/default");
//var features = require("../../plugins-client/cloud9.core/www/core/features");
var features = require("../ext.features/features");
var PythonShell = require('python-shell');
var name = "worth";
    

   // exports.getCurrentStatus = function(sid){
   function getCurrentStatus(sid,callback){
     var options = {
            mode: 'text',
            pythonPath: '/usr/bin/python',
            pythonOptions: ['-u'],
            scriptPath: 'plugins-server/cloud9.analysis',
            args: [sid]
        };
        PythonShell.run('featureStatus.py',options,function(err,results){
            if (err) throw err;
            console.log("Update Status function");
            console.log("Results: %j",results);
            for(var i=0, len=results.length; i < len; i+=2){
               features.feat[results[i]]= results[i+1]; 
            }

            console.log("GLOBAL VARIABLE: "+features.feat['gpush']);
        });
        callback(features.feat);
       

    }

    exports.getCurrentStatus = getCurrentStatus;

    exports.updateCmdLine = function(dir,cmd_array,callback){
        console.log("Inside of updateCompile");
        var cmd = cmd_array[0];
        cmd = cmd.toLowerCase();
        try{
            var cmd_opt = cmd_array[1];
            cmd_opt = cmd_opt.toLowerCase();
        }catch(err){
            if (cmd === "git")
            {
                return;
            }
        }
        var pyCMD = "";
        if (cmd === "git") {
            switch(cmd_opt){
                case "push": pyCMD = "gpush"; break;
                case "pull": pyCMD = "gpull"; break;
                case "commit": pyCMD = "gcommit"; break;
                case "checkout": pyCMD = "gcheckout"; break;
                case "status": pyCMD = "gstatus"; break;
                default: return;
            }
        }
        if (cmd === "javac")
        {
            var pyCMD = cmd;
        }

        
        var options = {
            mode: 'text',
            pythonPath: '/usr/bin/python',
            pythonOptions: ['-u'],
            scriptPath: 'plugins-server/cloud9.analysis',
            args: [dir,pyCMD,"1234"]
        };
        PythonShell.run('updateCMD.py',options,function(err,results){
            if (err) throw err;
            console.log("Results: %j",results);
            console.log("status of git_enabled before: "+features.git_enabled);
            if ( results[1].indexOf("gpush") != -1  && results[1].indexOf("unlock")!= -1)
            {
                console.log("Unlocked gpush");
                features.git_enabled = true;
            }
            console.log("status of git_enabled after: "+features.git_enabled);
            
        });
        getCurrentStatus(1234,callback);
        //callback(features.feat);

    }

//This function takes in the working directory and runs the script on it.
    exports.callAlgorithm = function(directory,level,callback){
        console.log("Inside of callAlgorithm");
        var dir = directory;
        var options = {
            mode: 'text',
            pythonPath: '/usr/bin/python',
            pythonOptions: ['-u'],
            scriptPath: 'plugins-server/cloud9.analysis',
            args: [dir,"1234"]
        };

        PythonShell.run('findMethods.py', options, function (err, results) {
            if (err) throw err;
             
            console.log("First");
            // results is an array consisting of messages collected during execution
            console.log('results2: %j', results);
                        //UpdateLevel(results[0],callback);
            console.log("Worth callAlgorithm level is "+level);
            callback(level);
            //UnlockL(results[0],level,callback);
            //callback(results);
            //return plug.UnlockL(results[0]);
            //return results[0]
        });

       

    }

    var UpdateLevel = function(result,callback)
    {
        //console.log("Your score was");
        console.log("The configs are "+config[13])
    }




/* var UnlockL = function(score,level,callback)
{
    console.log("Entered the UNlock Function");
    score = parseFloat(score);
    var posScore = Math.abs(score);
    console.log("Worth UnlockL level is "+level);
    /*var debug = -1 - score;
    score = score - debug;
    level.score = score;*/
/*
    level.score = score;
    console.log("level.score is "+level.score+" score is "+score+" level is "+level.level);
    if(level.score <= -2 && level.level < 2 )
    {
        config[13].clientPlugins.push("ext/codecomplete/codecomplete"); 
        config[13].clientPlugins.push("ext/searchinfiles/searchinfiles");
        config[13].clientPlugins.push("ext/vim/vim");
        level.f1 = "Code Complete";
        level.f2 = "Search in Files";
        level.f3 = "Vim Mode";
        level.level = 2;
        level.message = "You have reached level "+level.level+" You have unlocked "+level.f1+", "+level.f2+", "+level.f3+" Please reload the page to use the new features";
    }
    else if(level.score <= -3 && level.level < 3)
    {
        config[13].clientPlugins.push("ext/clipboard/clipboard");
        config[13].clientPlugins.push("ext/undo/undo");
        level.f1 = "ClipBoard";
        level.f2 = "Undo";
        level.f3 = "";
        level.level = 3;
        level.message = "You have reached level "+level.level+" You have unlocked "+level.f1+", "+level.f2+", "+level.f3+" Please reload the page to use the new features";
    }
    else if(level.score <= -4 && level.level < 4)
    {
        config[13].clientPlugins.push("ext/keybindings/keybindings");
        config[13].clientPlugins.push("ext/extmgr/extmgr");
        config[13].clientPlugins.push("ext/revisions/revisions");
        level.f1 = "Keybindings";
        level.f2 = "Extension Manager";
        level.f3 = "Revisions";
        level.level = 4;
        level.message = "You have reached level "+level.level+" You have unlocked "+level.f1+", "+level.f2+", "+level.f3+" Please reload the page to use the new features";
    }
    else if(level.score <= -5 && level.level < 5)
    {
        config[13].clientPlugins.push("ext/gotoline/gotoline");
        level.f1 = " Go to Line";
        level.f2 = "";
        level.level=5;
        level.message = "You have reached level "+level.level+" You have unlocked "+level.f1+", "+level.f2+", "+level.f3+" Please reload the page to use the new features";
    }
    else 
    {
        level.message = "Not yet. Your score is too low. Your current score is "+score;
    }
    callback(level);
}*/
