import MySQLdb


#connect
db = MySQLdb.connect(host="54.172.45.230",user="alice",passwd="iamadmin",db="Jabberwocky")

cursor = db.cursor()


def getAllFeatures():
    global db
    global cursor
    feats = {}
    query = "select keyword from features"
    cursor.execute(query)
    db.commit()
    for row in cursor.fetchall():
        feats[row] = False
    return feats




def setFeatures(sid,fid):
    global db
    global cursor
    query = "select * from student_features where sid="+str(sid)+" and fid="+str(fid)
    cursor.execute(query)
    db.commit()
    row = cursor.fetchone()
    if row is None:
        query = "insert into student_features(SID,FID,CID) values("+str(sid)+","+str(fid)+",123)"
        cursor.execute(query)
        db.commit()
    

def getFeatureStatus(sid):
    global db
    global cursor
    feats = []
    all_feats = {} 
    query = "select keyword from student_features inner join features on student_features.fid=features.fid where sid = "+str(sid)
    cursor.execute(query)
    db.commit()
    for row in cursor.fetchall() :
        #features[row[1]]=row[0]
        feats.append(row[0])
    query = "select fid,featureName,keyword from features"
    cursor.execute(query)
    db.commit()
    for row in cursor.fetchall():
        all_feats[row[2]] = row[0]
    return (feats,all_feats)


def updateCommandStatus(command,sid,value):
    global db
    global cursor
    query = "update learning_data set "+command+" = "+str(value)+" where sid = "+str(sid)
    cursor.execute(query)
    db.commit()


def getCommandStatus(command,sid):
    global db
    global cursor
    query = "select "+command+" from learning_data where sid = "+str(sid)
    cursor.execute(query)
    db.commit()
    row = cursor.fetchone()
    if row is None:
        return -1
    return row[0]


def updateCompile():
    global db
    global cursor
    query = ""

def makeQuery(data,sid,fname):
	query = "update program_stats set sid="+str(sid)
	for key,value in data.iteritems():
		query = query+", "+key+"="+str(value)
	query += " where sid="+str(sid)+" and fname='"+fname+"'"
	return query
		

def updateData(data,sid,fname):
	global db
	global cursor
	#cmd = "update program_stats set sid="+str(data["sid"])+\
	#", fname='"+data["fname"]+"',forloop="+\
	#str(data["forloop"])+",switch="+str(data["switch"])+\
	#",ifstate="+str(data["ifstate"])+",whilestate="+str(data["whilestate"])+",doloop="\
	#+str(data["doloop"])+", numFunctions="+str(data["numFunctions"])+\
	#",functionUsage="+str(data["func"])+" where sid="+str(data["sid"])+" and fname='"+data["fname"]+"'";
	cmd = makeQuery(data,sid,fname)
	cursor.execute(cmd)
	db.commit()
        

def insertData(data):
	global db
	global cursor
	cmd ="insert into program_stats(sid,fname,forloop,switch,ifstate,whilestate,doloop,numFunctions,functionUsage)"\
	"values("+str(data["sid"])+",'"+data["fname"]+"',"\
	+str(data["forloop"])+","+str(data["switch"])+","+str(data["ifstate"])+","+str(data["whilestate"])\
	+","+str(data["doloop"])+","+str(data["numFunctions"])+","+str(data["functionUsage"])+")"
	print cmd
	cursor.execute(cmd)
	db.commit()


def getCurrentData(sid,filename):
	global db
	global cursor
	cursor.execute("Select * from program_stats where sid="+str(sid)+" and fname='"+filename+"'")
	#commit changes
	db.commit()
	#get number of rows in result
	#numrows = int(cursor.rowcount)
	#get and display one row at a time
	
	row = cursor.fetchone()
	if row is None:
		sid_temp = sid
		data = dict(sid=sid,fname=filename,forloop=0,switch=0,ifstate=0,whilestate=0\
		,doloop=0,numFunctions=0,functionUsage=0)
		return (0,data)
	data = dict(sid=row[1],fname=row[2],forloop=row[3],switch=row[4],ifstate=row[5],whilestate=row[6]\
		,doloop=row[7],numFunctions=row[8],functionUsage=row[9])
	#data = dict(id=row[0],sid=row[1],compileT=row[2],compileF=row[3]\
		#,compileTE=row[4],compileP=row[5],EPC=row[6],bSR=row[7],pSR=row[8],level=row[9],LV=row[10])
	return (1,data)
	#for x in range(0,numrows):
	#	row = cursor.fetchone()
	#	print row[4]

def getProgramTotals(sid,fname):
	global db
	global cursor
	cursor.execute("Select forloop,switch,ifstate,whilestate,doloop,numFunctions,functionUsage from program_stats where sid="+str(sid)+" and fname='"+fname+"'")
	db.commit()
	row = cursor.fetchone()
	if row is None:
		return 0
	print "Row is "
	data = dict(forloop=row[0],switch=row[1],ifstate=row[2],whilestate=row[3],doloop=row[4],numFunctions=row[5],functionUsage=row[6])
	return data

def getOverallTotals(sid):
	global db
	global cursor
	cursor.execute("SELECT SUM(forloop), SUM(switch),SUM(ifstate), SUM(whilestate), SUM(doloop), SUM(numFunctions),SUM(functionUsage) FROM program_stats where sid="+str(sid))
	db.commit()
	row = cursor.fetchone()
	if row is None:
		return 0
	data = dict(forloop=row[0],switch=row[1],ifstate=row[2],whilestate=row[3],doloop=row[4],numFunctions=row[5],functionUsage=row[6])
	print data
	return data
