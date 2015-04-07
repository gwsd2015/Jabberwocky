import os
import sys
import subprocess
import string

import updateResults as updateDB

modifiers = ["public","private","protected","static","final","native","synchronized","abstract","threadsafe","transient"]
method_table = []
method_table_count = []
data = {}
#thresholds = dict(forloop=30,switch=30,ifstate=30,whilestate=30,doloop=30,numFunctions=75,func=100,git=30,java=30) 
thresholds = dict(forloop=5,switch=5,ifstate=5,whilestate=5,doloop=5,numFunctions=5,func=5,git=5,java=5) 
keys = ["forloop","switch","func","numFunctions","whilestate","doloop","ifstate"]
#Open file
#file = "/Users/lucasch/Desktop/cs1112/lucasch-out11/WordMorph.java"
#file = "/home/lucasch/Documents/Code_drop/DigitSum.java"
try:
	file = sys.argv[1]
except IndexError:
	print "Need a filename"
	exit(0)


try:
	data["sid"] = sys.argv[2]
except IndexError:
	print "Need a sid argument: python "+sys.argv[0]+" test.java 1234"
	exit(0)

data["fname"] = sys.argv[1]

#file = raw_input("Enter a file name:  ")
try:
	f = open(file,"r")
except IOError:
	print "File not found"
	exit(0)


program = []
for line in f:
	program.append(line)







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


class Construct:
	def __init__(self,line,index):
		self.line = "".join(line.split())
		self.children = []
		self.index = index
		
	def printConstruct(self):
		print self.line





def ExpType(x):
	return {
		'for(exp)': 1,
		'if(exp)': 2,
		'while(exp)': 3,
		'do{':4,
	}[x]

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





def checkForMethods(code):
	global modifiers
	global method_table
	#print code
	program = code.split()
	index = 0
	prev_value = False
	not_found = True
	#locate ( in  for line, 
	for line in program:
		if ";" in line:
			return False
		if "(" in line:
			paren_index = string.find(line,"(")
			not_found = False
			if paren_index == 0:
				prev_value = True
				index -= 1
			break
		index +=1
	if not_found:
		#print "Parenthesis not found"
		return False
	#print index
	if(index<0 or index >= len(program)):
		return False
	method_name = program[index]
	
	index -=1
	if(index <0):
		return
	return_type = program[index]
	for i in range(index):
		if(program[i] not in modifiers):
			return False
	p_index= string.find(method_name,"(")
	if p_index >0:
		method_name = method_name[0:p_index]
	if method_name in method_table:
		pass
	else:
		method_table.append(method_name)
	#print "Method Found:"
	#print "\tMethod Name: " + method_name
	#print code
	return True




#print "Now printing method table:"
#for item in method_table:
#	print item

def findMethodUsage(program):
	global method_table
	global method_table_count
	for item in method_table:
		item_list=  filter(lambda x: item in x, program)
		method_table_count.append((item,len(item_list)-1))
	#print "Now Printing Method Table Count:"
	#for item in method_table_count:
		#print item

	#for i in range(len(program)):
		#program[i] = program[i].replace(" ","") 


def computeAverageMethodUsage():
	global method_table_count
	average = 0
	count = 0
	for item in method_table_count:
		average += item[1]
		count += 1
	averageb = average / (float(count))
	return average
	#print "average method usage is: "+str(average)

#This function compares against values in table and updates table
def updateData(current_data,old_data,sid,fname):
	global keys
	print "In update data function"
	print current_data
	print old_data
	db_data = {}
	#db_data['sid'] =  data['sid']
	#db_data['fname'] = data['fname']
	for i in range(len(keys)):
		data_key = keys[i]
		if current_data[data_key] > old_data[data_key]:
			print data_key+": current_data is bigger than old_data"
			print str(current_data[data_key])+">"+str(old_data[data_key])
			db_data[data_key] = current_data[data_key]
		elif current_data[data_key] < old_data[data_key]:
			print data_key+": old_data is bigger than current_data"
			print str(current_data[data_key])+"<"+str(old_data[data_key])
		else: 
			print data_key+": old_data is equal to current_data"
			print str(current_data[data_key])+"="+str(old_data[data_key])
	updateDB.updateData(db_data,sid,fname)

#This function performs actuall analysis
def SinglePrint():
	global method_table_count
	global data
	(data_type,data) = updateDB.getCurrentData(data["sid"],data["fname"])
	method_count = 0
	for i in range(len(program)):
		if checkForMethods(program[i]):
			method_count +=1
	print "Found "+str(method_count)+" methods"
	findMethodUsage(program)
	for item in method_table_count:
		print item

	constructList = []
	constructList.append(Construct("for(exp)",4))
	constructList.append(Construct("if(exp)",3))
	constructList.append(Construct("while(exp)",6))
	constructList.append(Construct("do{",3))
	res = identifyConstructs(program,constructList)
	print "Total Constructs:"
	for i in range(len(constructList)):
		print "\t"+constructList[i].line+": "+str(res[i+1])
	print "Average method usage: "+str(computeAverageMethodUsage())
	data["forloop"] = res[1]
	data["switch"] = 0
	data["ifstate"] = res[2]
	data["whilestate"] = res[3]
	data["doloop"] = res[4]
	data["numFunctions"] = method_count
	data["func"] = computeAverageMethodUsage()  
	#print data
	#print "data type is "+str(data_type)
	data_totals = updateDB.getOverallTotals(data["sid"])
	if data_type == 0:
		updateDB.insertData(data)
	elif data_type == 1:
		#updateDB.getProgramTotals(data["sid"],data["fname"])
		(ex,data_program_totals) = updateDB.getCurrentData(data["sid"],data["fname"])
		updateData(data,data_program_totals,data["sid"],data["fname"])
		#updateDB.makeQuery(db_data,data["sid"],data["fname"])
		
		#updateDB.updateData(db_data)




def GroupPrint():
	method_count = 0
	printer_values = []
	for i in range(len(program)):
		if checkForMethods(program[i]):
			method_count +=1
	printer_values.append(method_count)
	#print method_count
	findMethodUsage(program)
	constructList = []
	constructList.append(Construct("for(exp)",4))
	constructList.append(Construct("if(exp)",3))
	constructList.append(Construct("while(exp)",6))
	constructList.append(Construct("do{",3))
	res = identifyConstructs(program,constructList)
	printer_values = printer_values + res
	#for i in range(1,len(res)):
		#print str(res[i])
	printer_values.append(computeAverageMethodUsage())
	for x in printer_values:
		print x


SinglePrint()
#GroupPrint()
f.close()

