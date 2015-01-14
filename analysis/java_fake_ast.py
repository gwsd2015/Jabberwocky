
# coding: utf-8

# In[199]:

import re


# In[200]:

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


# In[201]:

#Open us all of information about file
f = open("HelloWorld.java","r")
program = []
for line in f:
    program.append(line)
print program


# In[202]:




# In[203]:




# 
#     

# In[219]:

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


# In[220]:

tree = makeTree(Node("START",None),program)


# In[221]:

for i in range(len(tree.children)):
    print tree.children[i].data
print "Children:"
for i in range(len(tree.children[2].children)):
    print tree.children[2].children[i].data


# In[222]:




# In[223]:

def printTree(tree):
    for i in range(len(tree.children)):
        print tree.children[i].data.strip()+ "---> Parent is "+tree.children[i].parent.data
    for i in range(len(tree.children)):
        printTree(tree.children[i])
    return


# In[223]:




# In[224]:

printTree(tree)


# In[210]:




# In[210]:



