import os
import sys
import subprocess
import string

import updateResults as updateDB

sid = -1

try:
	sid = sys.argv[1]
except IndexError:
	print "Need a sid as first argument"
	exit(0)



(feats,all_feats)=updateDB.getFeatureStatus(sid)


#print "Features enabled"
#print feats

#print "All Features"
#print all_feats


for feature in all_feats:
    if feature in feats:
        all_feats[feature] = True
    else:
        all_feats[feature] = False



#print "All Features"
#print all_feats


for feature in all_feats:
    print feature
    print all_feats[feature]

#print data_print
