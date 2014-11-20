
define(function(require, exports, module) {

    //var spawn = require("child_process").spawn;
    var ide = require("core/ide");
    exports.callAlgorithm = function(name,wrkspace){
    	console.log("Called callAlgorithm Function");
    	/*var buildDebug = spawn("python","/home/lucasch/SD/Jabberwocky/cloud9/scripts/test.java");

    	buildDebug.stderr.setEncoding("utf8");
           buildDebug.stderr.on('data', function (data) {
              console.error(data);
           });

           buildDebug.on('exit', function (code) {
              if (code !== 0) {
                console.error('build-debug process exited with code ' + code);
                process.exit(code);
              }
              boot();
           });*/
		if(!ide.onLine){
			console.log("You are offline!");
		}
		else{
			var file = wrkspace.split("/");
			//console.log(file);
			var data = {
				command: "worth",
				wksp : name+"/"+file[2],
				id : 1
			};
			/*console.log("begin breakdown of items:");
			for(var x in data)
			{
				console.log(x+" is "+data[x]);
			}
			console.log("Name is "+data);
*/
			ide.send(data);
		}

    }

});
