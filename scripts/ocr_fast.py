from subprocess import call
import os
import thread
from threading import Thread

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
#7da826e18d8841b2b4cb6a64cf9ea38e,ZP_iphone_FR_int Good SI,5980591,3,"{""url"":""f0dc3ca7db604a4d83e3ea0dac0b56fe.jpeg"",""altText"":"" ""}"

class Ocr(Thread):
	def __init__(self,img):
		Thread.__init__(self)
		self.img=img
	def run(self):
		call(['tesseract','ads/'+self.img,'ocr_fast/'+self.img.split(".")[0]])

threads=[]
maxT=100
nT=0
count=0
for imageName in imageNames:	
	count+=1
	if nT==maxT:
		for t in threads:
			t.join()
		threads=[]
		nT=0
	else:
		t=Ocr(imageName)
		t.start()
		threads.append(t)
		nT+=1
	if count%10000==0:
		print 'Images done:',count
for t in threads:
	t.join()
print 'Total Images processed:',count

