import urllib2
import sys
import thread
import time

bannercreativedata=open('bannercreativedata.csv')

baseUrl='http://r.edge.inmobicdn.net/FileData/'

def download(imgname):
	global baseUrl
	output=open(imgname,'wb')
	output.write(urllib2.urlopen(baseUrl+imgname).read())
	output.close()

activeTh=0
for line in bannercreativedata:
	sp=line.split('"url"":""')
	if len(sp)<2:
		continue
	imgname=sp[1].split('"",')[0]
	if activeTh>=50:
		time.sleep(2)
		activeTh=0
	thread.start_new_thread(download,(imgname,))
	activeTh+=1

bannercreativedata.close()
