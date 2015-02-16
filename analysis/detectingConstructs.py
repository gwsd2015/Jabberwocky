
# coding: utf-8

# In[206]:

import os
import sys
import subprocess
rule = "for(expression)"


# In[207]:

#Open us all of information about file
f = open("HelloWorld.java","r")
program = []
for line in f:
    program.append(line)
#print program[14]
identifyFor(program[14],rule)


# In[208]:

def identifyFor(line,rule):
    print line


                This data structure is used to store lines and detect whether an item is a construct.
                
# In[209]:

class CodeLine:
    def __init__(self,line):
        self.line = "".join(line.split())
        self.children = []
        self.expType=""
        
    def identify(self,exp):
        #for i in range(exp.index):
            #if self.line[i] != exp.line[i]:
                #return 0
        for i in range(len(exp)):
            index = exp[i].index
            if( self.line[0:index] == exp[i].line[0:index]):
                self.expType = exp[i].line
        #print self.expType
        if(self.expType == ""):
            return 0
        return 1
      
    def printCode(self):
        return self.line
        #print 1


# In[210]:

class Construct:
    def __init__(self,line,index):
        self.line = "".join(line.split())
        self.children = []
        self.index = index
        
    def printConstruct(self):
        print self.line
        


#### s = "\tVery cool Man \n" x = "".join(s.split()) print x

# In[211]:

line1 = CodeLine("for(int i = 0 ; i < hello.length(); i ++ )")
forLoop = Construct("for(exp)",4)
ifExp = Construct("if(exp)",3)
whileExp = Construct("while(exp)",6)
doExp = Construct("do{",3)

constructList = []
constructList.append(Construct("for(exp)",4))
constructList.append(Construct("if(exp)",3))
constructList.append(Construct("while(exp)",6))
constructList.append(Construct("do{",3))


# In[212]:

line1.printCode()
forLoop.printConstruct()


# In[213]:

print line1.identify(constructList)
print line1.expType
#line1.identify(ifExp)
#line1.identify(doExp)


# In[214]:

def identifyConstructs(code,constructs):
    program = []
    counters = [0,0,0,0,0]
    for i in range(len(code)):
        if("".join(code[i].split()) == ""):
            continue
        program.append(CodeLine(code[i]))
    #for i in range(len(program)):
    for i in range(len(program)):
        ret = program[i].identify(constructs)
        if(ret == 1):
            val = ExpType(program[i].expType)
            counters[val] = counters[val]+1
    return counters  


# In[215]:

#print program
res = identifyConstructs(program,constructList)
print "Totals:"
for i in range(len(constructList)):
    print "\t"+constructList[i].line+": "+str(res[i+1])


# In[216]:

def ExpType(x):
    return {
        'for(exp)': 1,
        'if(exp)': 2,
        'while(exp)': 3,
        'do{':4,
    }[x]


# In[216]:




# In[ ]:



