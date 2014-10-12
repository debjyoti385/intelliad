import sys

if len(sys.argv) < 3:
	print 'Args: <tweetStreamFile> <tweetTextWithLabelFilePath>'
	sys.exit(1)
def getLabel(tweet):
	kwMap={
	'Business':set(['sell','sales','corporation','trade','stock','business','retail','merchant','merchandise','finance']),
	'Travel & Tourism':set(['travel','places','hiking','destination','holiday','resort','hotel','scenery','museum','beach']),
	'Games':set(['ps3','ps4','xbox','poker','computer game','mmorg','online game',' ea ','zynga','bioware']),
	'Sports':set(['football','arsenal','manu','chelsea','manc','runs','score','wicket','cricket','softball','indoor game','outdoor game']),
	'Fashion & Lifestyle':set(['haircut','hairstyle','footwear','makeup','clothes','dress','beer','jewellery','neclace','earring','trouser','wristwatch','shirts','shoes','apparel','floaters','sandals','saloon']),
	'Commodity':set(['gadget','electronic','iphone','lumia','device','commodit','product']),
	'Arts':set(['painting','classical','artist','singing','dancing','comed','magician','theatrical','band']),
	'Entertainment':set(['event','concert','orchestra','movie','tv','sunburn','carnival',' show ']),
	'Social Activism':set(['lgbt','activist','society','raise your voice','protest','awareness','democrat','republic','liberal','rights','promote','rural','urban','equality','racist','feminis','discrimination','crime']),
	'Religion':set(['god','almighty','lord','christ','bible','religion','religious']),
	'Health & Fitness':set(['fitness','gym','workout','muscle','yoga','aerobics','cardio','weight-training','benchpress','abs','disease','virus','bacteria','infection','epidemic','ebola','cancer','prescription','medical','pharma','medicine','exercise','cancer','aids']),
	'Food & Dining':set(['food','pizza','burger','dining','bar','brew','whiskey','wine','rum','vodka','restaurant']),
	'Jobs & Education':set(['job','office','profession','mba','education','graduate','degree','study'])
	}
	labels=set()
	for word in tweet.split():
		word=word.strip("#,;|").lower()
		for label in kwMap:
			if word in kwMap[label]:
				labels.add(label)
	return ','.join(labels)

tweetFile = open(sys.argv[1],"r")
labelledTweetFile = open(sys.argv[2],"w")
for line in tweetFile:
	sp=line.strip().split("\x01")
	if len(sp)<3:
		continue
	tweet=sp[1]
	labels=getLabel(tweet)
	if labels!='':
		labelledTweetFile.write(tweet+"\x01"+labels+"\x01")
		#[-73.939511, 40.830681]
                if 'None' not in sp[9]:
                        ll=sp[9].strip("][").split(",")
                        if len(ll)==2:
                                labelledTweetFile.write(ll[1].strip()+"\x01"+ll[0].strip()+"\n")
                        else:
                                labelledTweetFile.write("\x01\n")
                else:
                        labelledTweetFile.write("\x01\n")
labelledTweetFile.close()
tweetFile.close()
print 'Labelled tweet texts produced in',sys.argv[2]
	
