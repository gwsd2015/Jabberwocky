


var git_enabled = false;
var javac_enabled = false;


var feat = {};
/*function git_status(){
    return git_enabled;

}
*/
/*exports.git_status = function(){
        return git_enabled;
};*/


exports.printFeatures = function(){
    for(i in feat){
        console.log(i,feat[i]);
    }

};


  //  exports.git_enabled=false;
  //  exports.javac_enabled = false;
    exports.feat = feat;
