import os
import sys
import subprocess
 


def processFile(fileName):
	print "File: "+fileName
	cmd = "python findMethods.py samples/"+fileName
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
	list_of_files = sys.argv[1]
except IndexError:
	print "Need to provide a filename argument"
	exit(0)


try:
	f = open(list_of_files,"r")
except IOError:
	print "File "+list_of_files+" not found"
	exit(0)

for line in f:
	line = line.rstrip()
	processFile(line)





