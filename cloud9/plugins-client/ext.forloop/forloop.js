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
var markup = require("text!ext/forloop/forloop.xml");

module.exports = ext.register("ext/forloop/forloop", {
    name     : "Auto For Loop",
    dev      : "Ajax.org",
    alone    : true,
    deps     : [],
    type     : ext.GENERAL,
    markup   : markup,

    nodes : [],

    init : function(){
        var _self = this;
        this.insert_for_loop = insert_for_loop;
        if (ide.features['afor'] == "True")
        {
        commands.addCommand({
            name: "forloop",
            hint: "I'll say something",
            msg: "Popping window!",
            bindKey: {mac: "Ctrl-f", win: "Ctrl-2"},
            isAvailable : function() {
                
                return true;    
            },
            exec: function() {
                //This calls the insertfor function.
                _self.insertfor();
                //_self.insert_for_loop.show()
            }
        });
        
        this.nodes.push(
            menus.addItemByPath("Insert/Insert For Loop", new apf.item({
                command : "forloop"
            }), 5400)
        ); 

       /* Just a plain menu...
        this.nodes.push(
            menus.addItemByPath("Edit/Extension Template", new apf.item({
                onclick : function(){
                    _self.insert_for_loop.show();
                }
            }), 5400)
        ); */
        }
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

     closeInsertForLoopWindow : function(){
        this.insert_for_loop.hide();
     },

     insertfor : function(){
    
       var page = ide.getActivePage();
       if(!page)
        return;
            if (!editors.currentEditor.amlEditor)
                return;
            //this gets the current editor for us to use.
            var editor = editors.currentEditor.amlEditor.$editor;
            //insert the code for a for statement.
            editor.insert("for(int i = 0; i<__; i++) \n\t{ \n\t}");
            
        
        this.insert_for_loop.hide();
     }
});

});
