import os
import sys
import subprocess
import string

import updateResults as updateDB

data = {}
command = ""
sid = ""
filename = ""
#Thresholds for when to unlock features
thresholds = dict(javac=5,gpull=5,gpush=5,gcommit=5,gcheckout=5,gstatus=5) 
#Keys for thresholds
dbData = dict(gpush="git_push",gpull="git_pull",gcommit="git_commit",gcheckout="git_branch",gstatus="git_status",javac="compile") 


keys = ["compileJ","gitPull","gitPush","gitCommit","gitBranch","gitStatus","gitAdd"]
#Open file
#file = "/Users/lucasch/Desktop/cs1112/lucasch-out11/WordMorph.java"
#file = "/home/lucasch/Documents/Code_drop/DigitSum.java"
try:
	filename = sys.argv[1]
except IndexError:
	print "Need a filename as first argument"
	exit(0)


try:
	command = sys.argv[2]
except IndexError:
        print "Need a command as a second argument"
	exit(0)


try:
        sid = sys.argv[3]
except IndexError:
        print "Need a sid argument as third argument"
        exit(0)

#print "command is "+command
#print "filename is "+filename 
#print "sid is "+sid



temp = updateDB.getFeatureStatus(sid)

compileResult = updateDB.getCommandStatus(dbData[command],sid)

# increase result by one
compileResult += 1

print "Data is "+str(compileResult)+" threshold "+str(thresholds[command])
if(compileResult >= thresholds[command]):
    #print "Unlocked a feature" 
    (feat,all_feats) = updateDB.getFeatureStatus(sid)
    #print feat
    #print all_feats
    if command not in feat:
        fid = all_feats[command] 
        updateDB.setFeatures(sid,fid)
    print "unlock "+command
#update the database to reflect.
print "Locked"
updateDB.updateCommandStatus(dbData[command],sid,compileResult)
#print "Compile result is "+str(compileResult)
