import MySQLdb


#connect
db = MySQLdb.connect(host="54.172.45.230",user="alice",passwd="iamadmin",db="Jabberwocky")

cursor = db.cursor()


def updateData(data):
	global db
	global cursor
	cmd = "update program_stats set sid="+str(data["sid"])+\
	", fname='"+data["fname"]+"',forloop="+\
	str(data["forloop"])+",switch="+str(data["switch"])+\
	",ifstate="+str(data["ifstate"])+",whilestate="+str(data["whilestate"])+",doloop="\
	+str(data["doloop"])+", numFunctions="+str(data["numFunctions"])+\
	",functionUsage="+str(data["func"])+" where sid="+str(data["sid"])+" and fname='"+data["fname"]+"'";
	print cmd
	cursor.execute(cmd)
	db.commit()


def insertData(data):
	global db
	global cursor
	cmd ="insert into program_stats(sid,fname,forloop,switch,ifstate,whilestate,doloop,numFunctions,functionUsage)"\
	"values("+str(data["sid"])+",'"+data["fname"]+"',"\
	+str(data["forloop"])+","+str(data["switch"])+","+str(data["ifstate"])+","+str(data["whilestate"])\
	+","+str(data["doloop"])+","+str(data["numFunctions"])+","+str(data["func"])+")"
	print cmd
	cursor.execute(cmd)
	db.commit()


def getCurrentData(sid,filename):
	global db
	global cursor
	cursor.execute("Select * from program_stats where sid="+sid+" and fname='"+filename+"'")
	#commit changes
	db.commit()
	#get number of rows in result
	#numrows = int(cursor.rowcount)
	#get and display one row at a time
	
	row = cursor.fetchone()
	if row is None:
		return (0,{})
	data = dict(sid=row[1],fname=row[2],forloop=row[3],switch=row[4],ifstate=row[5],whilestate=row[6]\
		,doloop=row[7],numFunctions=row[8],func=row[9])
	#data = dict(id=row[0],sid=row[1],compileT=row[2],compileF=row[3]\
		#,compileTE=row[4],compileP=row[5],EPC=row[6],bSR=row[7],pSR=row[8],level=row[9],LV=row[10])
	return (1,data)
	#for x in range(0,numrows):
	#	row = cursor.fetchone()
	#	print row[4]