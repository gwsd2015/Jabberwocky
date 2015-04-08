import os
import sys
import subprocess
 


def processFile(fileName,sid):
	print "File: "+fileName
	cmd = "python findMethods.py "+fileName+" "+sid#samples/"+fileName
		#print cmd
	process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
	output, error = process.communicate()

	parsed_ips = []

	if not error:
		print output
	else:
		print "There was an error during processing!"
		print error

try:
	fname = sys.argv[1]
	fname = fname.rstrip()
	sid = sys.argv[2]
	sid = sid.rstrip()
	processFile(fname,sid)

except IndexError:
	print "Need to provide a filename argument"
	exit(0)







