import MySQLdb


#connect
db = MySQLdb.connect(host="54.172.45.230",user="alice",passwd="iamadmin",db="Jabberwocky")

cursor = db.cursor()


def updateLDScore(data):
	global db
	global cursor
	cmd = "update learning_data set  compileTotal="+str(data["compileT"])+\
	", compileFails="+str(data["compileF"])+",compileTotalErrors="+\
	str(data["compileTE"])+",compilePass="+str(data["compileP"])+\
	",EPC="+str(data["EPC"])+",bracketRate="+str(data["bSR"])+",parenRate="\
	+str(data["pSR"])+" where sid="+str(data["sid"]);
	cursor.execute(cmd)
	db.commit()


def getCurrentLDValues():
	global db
	global cursor
	cursor.execute("Select * from learning_data where sid=1111")
	#commit changes
	db.commit()
	#get number of rows in result
	numrows = int(cursor.rowcount)
	#get and display one row at a time
	row = cursor.fetchone()
	data = dict(id=row[0],sid=row[1],compileT=row[2],compileF=row[3]\
		,compileTE=row[4],compileP=row[5],EPC=row[6],bSR=row[7],pSR=row[8],level=row[9],LV=row[10])
	return data
	#for x in range(0,numrows):
	#	row = cursor.fetchone()
	#	print row[4]
