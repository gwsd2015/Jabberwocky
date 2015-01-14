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
var editors = require("ext/editors/editors")
var menus = require("ext/menus/menus");
var commands = require("ext/commands/commands");
var markup = require("text!ext/insertIf/insertIf.xml");

module.exports = ext.register("ext/insertIf/insertIf", {
    name     : "Auto If Statement",
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
            name: "insertIf",
            hint: "I'll say something",
            msg: "Popping window!",
            bindKey: {mac: "Ctrl-i", win: "Ctrl-2"},
            isAvailable : function() {
                
                return true;    
            },
            exec: function() {
                //This calls the insertIf function.
                _self.insertIf();
                //_self.winExtensionTemplate.show()
            }
        });
        
        this.nodes.push(
            menus.addItemByPath("Edit/Insert If Statement", new apf.item({
                command : "insertIf"
            }), 5400)
        ); 
        commands.addCommand({
            name: "insertIfElse",
            hint: "I'll say something",
            msg: "Popping window!",
            bindKey: {mac: "Ctrl-Shift+i", win: "Ctrl-2"},
            isAvailable : function() {
                
                return true;    
            },
            exec: function() {
                //This calls the insertIfElse function.
                _self.insertIfElse();
                //_self.winExtensionTemplate.show()
            }
        });
        
        this.nodes.push(
            menus.addItemByPath("Edit/Insert If Else Statement", new apf.item({
                command : "insertIfElse"
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

     insertIf : function(){
    
       var page = ide.getActivePage();
       if(!page)
        return;
            if (!editors.currentEditor.amlEditor)
                return;
            //this gets the current editor for us to use.
            var editor = editors.currentEditor.amlEditor.$editor;
            //insert the code for an if statement.
            editor.insert("if( == )\n\t{\n\n\t}");
            
        
        this.winExtensionTemplate.hide();
     },
     insertIfElse : function(){
    
       var page = ide.getActivePage();
       if(!page)
        return;
            if (!editors.currentEditor.amlEditor)
                return;
            //this gets the current editor for us to use.
            var editor = editors.currentEditor.amlEditor.$editor;
            //insert the code for an if else statement.
            editor.insert("if( == ){\n\n\t} \n \telse{     \n\t}");
            
            
        
        this.winExtensionTemplate.hide();
     }
});

});