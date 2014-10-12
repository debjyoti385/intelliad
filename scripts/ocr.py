from subprocess import call
import os

bannerdatafile = open('bannercreativedata.csv','r')
imageNames=[]
for line in bannerdatafile:
	sp=line.split('url"":""')
	if(len(sp)<2):
		continue
	imageNames.append(sp[1].split('"')[0])
bannerdatafile.close()

#7da826e18d8841b2b4cb6a64cf9ea38e,ZP_iphone_FR_int Good SI,5980591,3,"{""url"":""f0dc3ca7db604a4d83e3ea0dac0b56fe.jpeg"",""altText"":"" ""}"

for imageName in imageNames:
	count=0
	if os.path.isfile('ads/'+imageName):
		call(['tesseract','ads/'+imageName,'ocr/'+imageName.split(".")[0]])
		count+=1
	if count%1000==0:
		print 'Images done:',count
print 'Images done:',count
