/**
 * Extension Template for Cloud9 IDE
 * 
 * Inserts a context menu item under the "Edit" menu, which, when
 * clicked, displays a simple window with a "Close" button.
 * 
 * This file is stripped of comments in order to provide a quick template for 
 * future extensions. Please reference our documentation for a list of what's
 * going on.
 *
 * @copyright 2012, Ajax.org B.V.
 * @license GPLv3 <http://www.gnu.org/licenses/gpl.txt>
 */
 
define(function(require, exports, module) {

var ext = require("core/ext");
var ide = require("core/ide");
var script = require("core/worthy");
//var worth = require("core/")
//var spawn = require("child_process").spawn;
//var exec = require("child_process").exec;
//var net = require("net");
var menus = require("ext/menus/menus");
var commands = require("ext/commands/commands");
var markup = require("text!ext/compile/compile.xml");

module.exports = ext.register("ext/compile/compile", {
    name     : "Compile Java",
    dev      : "Ajax.org",
    alone    : true,
    deps     : [],
    type     : ext.GENERAL,
    markup   : markup,

    nodes : [],

    init : function(){
        var _self = this;
        this.winExtensionTemplate = winExtensionTemplate;
        
        commands.addCommand({
            name: "sayhello",
            hint: "I'll say something",
            msg: "Popping window!",
            bindKey: {mac: "Shift-1", win: "Ctrl-1"},
            isAvailable : function() {
                return true;    
            },
            exec: function() {
                _self.winExtensionTemplate.show()
            }
        });
        
        this.nodes.push(
            menus.addItemByPath("Edit/Compile Java", new apf.item({
                command : "sayhello"
            }), 5400)
        ); 

       /* Just a plain menu...
        this.nodes.push(
            menus.addItemByPath("Edit/Extension Template", new apf.item({
                onclick : function(){
                    _self.winExtensionTemplate.show();
                }
            }), 5400)
        ); */
    },

    hook : function(){
        var _self = this;
        ext.initExtension(this);
    },

    enable : function(){
        this.nodes.each(function(item){
            item.enable();
        });
    },

    disable : function(){
        this.nodes.each(function(item){
            item.disable();
        });
    },

    destroy : function(){
        this.nodes.each(function(item){
            item.destroy(true, true);
        });
        this.nodes = [];
    },

     closeExtensionTemplateWindow : function(){
        this.winExtensionTemplate.hide();
     },
     compileJava : function(){
        // script.js
        /*exec('python round1.py test.java', function(error, stdout, stderr) {
            console.log('stdout: ', stdout);
            console.log('stderr: ', stderr);
            if (error !== null) {
                console.log('exec error: ', error);
            }
        });*/
       // console.log("Workspace directory is "+ide.workspaceDir);
        var page = ide.getActivePage();
        if(!page)
            return;
       //console.log("page is "+page.id)
        script.callAlgorithm(ide.workspaceDir,page.id);
        
        //console.log("Current File is "+ide.ide.getActivePage());
        this.winExtensionTemplate.hide();
     }
});

});