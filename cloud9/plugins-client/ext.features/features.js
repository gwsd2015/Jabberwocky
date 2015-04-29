
define(function(require, exports, module) {






  //  var git_enabled = false;
    //var javac_enabled = false;
var features = {};

/*function git_status(){
    return git_enabled;

}
*/
exports.printFeatures = function(){
    console.log("---------------");
    for(i in features)
    {
        console.log(i,features[i]);
    }
    console.log("-----------------");
};





 //   exports.git_enabled= git_enabled;
   // exports.javac_enabled = javac_enabled;
    exports.feat = features;
});
//
//
