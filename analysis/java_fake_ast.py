
# coding: utf-8

# In[128]:

import re
import os
import sys
import subprocess
errorString = ""
numIfs = 0
numFor = 0


# In[129]:

class Node:
    def __init__(self,data,parent):
        self.data = data
        self.children = []
        self.parent = parent
    def addChild(self,node):
        self.children.append(node)
    
    def printChildren(self):
        for x in range(len(self.children)):
            print self.children[x]


# In[130]:

#Open us all of information about file
f = open("HelloWorld.java","r")
program = []
for line in f:
    program.append(line)
print program


# In[130]:




# In[130]:




# 
#     

# In[131]:

##Iterative tree builder
def makeTree(tree,program):
    tempTree= tree
    currentParent = tree
    cont = False
    for i in range(len(program)):
        #print "i is "+str(i)
        if cont == True:
            cont = False
            continue
        if i == len(program)-1:
           print "end" 
        elif "{" in program[i]:
            #print "start1"
            currentParent.children.append(Node(program[i],currentParent))
            currentParent = currentParent.children[len(currentParent.children)-1]
        elif "{" in program[i+1] and program[i+1].strip().find("{")==0:
            #print "start2"
            currentParent.children.append(Node(program[i],currentParent))
            currentParent = currentParent.children[len(currentParent.children)-1]
            cont = True
        elif "}" in program[i]:
            #print "start3"
            currentParent = currentParent.children[len(currentParent.children)-1].parent.parent
        else:
            #print "start4"
            if(program[i].strip() != ""):
                currentParent.children.append(Node(program[i],currentParent))
    return tempTree


# In[132]:

tree = makeTree(Node("START",None),program)


# In[133]:

for i in range(len(tree.children)):
    print tree.children[i].data
print "Children:"
for i in range(len(tree.children[2].children)):
    print tree.children[2].children[i].data


# In[133]:




# In[134]:

#print tree.
def printTree(tree):
    for i in range(len(tree.children)):
        print tree.children[i].data.strip()+ "---> Parent is "+tree.children[i].parent.data
        print " "
    for i in range(len(tree.children)):
        printTree(tree.children[i])
    return


# In[134]:




# In[135]:

printTree(tree)


# In[136]:

def detectElements(program):
    runCompile()
    for i in range(len(program)):
        #print program[i]
        detectFor(program[i])
        detectIf(program[i])


# In[137]:

#function to determine if there are for statements in code
def detectFor(line):
    global errorString
    global numFor
    reg = r"(for)+(\s?)+(\()+(.)+(.)+(.?)+(\))"
    ret = re.search(reg,line)
    if ret:
        if ret.string in errorString:
            print "BAD LINE"
        else: 
            numFor = numFor + 1
        print ret.string
        


# In[138]:

detectElements(program)


# In[139]:

# compile the code to get the error messages.
def runCompile():
    global errorString
    cmd = "javac HelloWorld.java"
    process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
    output, error = process.communicate()
    if not output and not error:
        print "No Errors"
    if error:
        errorString = error
        
        


# In[140]:

# function to determine if there are if statements in the code.
def detectIf(line):
    global errorString
    global numIfs
    reg = r"(if)+(\s?)+(\()+(((\s?)+(.)+(\s?)+(==|!=|<=|>=)+(\s?)+(.))|((\s?)+(\w)+(\s?)))+(\))"
    ret = re.search(reg,line)
    if ret:
        if ret.string in errorString:
            print "BAD LINE"
        else:
            numIfs = numIfs + 1
        print ret.string


# In[141]:

runCompile()


# In[142]:

print "Total number of Ifs"
print numIfs

print "Total number of Fors"
print numFor


# In[142]:



