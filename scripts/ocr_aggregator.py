from subprocess import call
import os,sys
import thread
from threading import Thread

if len(sys.argv) <2 :
	print 'Usage: <ImageName To ContentData FileName>'
	sys.exit(1)

bannerdatafile = open('bannercreativedata.csv','r')
imageNames=[]
imCount=0
for line in bannerdatafile:
        sp=line.split('url"":""')
        if len(sp)<2 or  (not os.path.isfile('ads/'+sp[1].split('"')[0])):
                continue
        imCount+=1
        if(imCount%10000==0):
                print 'Creatives counted=',imCount
        imageNames.append(sp[1].split('"')[0])
bannerdatafile.close()

print 'Total creatives=',len(imageNames)

out=open(sys.argv[1],"w")
blanks=0
nonblanks=0
imCount=0
for imageName in imageNames:
	contentPath='ocr_fast/'+imageName.split(".")[0]+".txt"
	imCount+=1
	if(imCount%10000==0):
                print 'Creatives processed=',imCount
	if os.path.isfile(contentPath):
		content=open(contentPath).read().replace("\n"," ").strip()
		if(content==""):
			blanks+=1
			continue
		nonblanks+=1
		out.write(imageName+"\x01"+content+"\n")
out.close()
print 'Total records written(images with valid content)=',nonblanks
