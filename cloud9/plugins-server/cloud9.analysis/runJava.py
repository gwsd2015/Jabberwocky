import os
import sys
import subprocess
import string

filename = ""

try:
	filename = sys.argv[1]
except IndexError:
	print "Need a filename as first argument"
	exit(0)

print filename


def parseError(error,filename):
    data = error.splitlines()
    error_list = []
    for line in data:
        if filename in line:
            error_list.append(line.replace(filename,""))
    print len(error_list)
    for line in error_list:
        print line
    if len(error_list) == 0:
        print error

def checkCompile(filename):
	cmd = "javac "+filename
	compileScore = 0
	process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
	output, error = process.communicate()
	if not output and not error:
            #print "Success: Compile was Successful."
            print "success"
	if error:
            #print "Failure : Compile was unsuccessful."
            #print "Error was :",error
            print "fail"
            errorVal = error
            parseError(error,filename)


checkCompile(filename)
