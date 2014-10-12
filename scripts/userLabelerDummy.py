import sys

if len(sys.argv) < 3:
	print 'Args: <tweetStreamFile> <userDescWithLabelFilePath>'
	sys.exit(1)
def getLabel(userDesc):
	kwMap={
	'Organisation':set(['job','weather','charity','department','official','artist','producer','actor','painter']),
	'Business':set(['business','buy','sell','ceo','cto','retail','trade']),
	'Social':set(['friends','activist','social','promote','campaign','protest']),
	'Working':set(['professional','engineer','software','accountant','working','employed','techie','programm']),
	'Casual':set(['life','cool','chill','sleep','tired']),
	'Retired':set(['retired','former']),
	'Fitness':set(['gym','fitness','abs','yoga','run']),
	'Student':set(['student','study','university','college','school']),
	'Religious':set(['god','almighty','spiritual','muslim','christ','pray','lord']),
	'Sports':set(['fan','football','manu','arsenal','liverpool','chelsea','games','manc'])
	}
	labels=set()
	for word in userDesc.split():
		word=word.strip("#,;|").lower()
		for label in kwMap:
			if word in kwMap[label]:
				labels.add(label)
	return ','.join(labels)

tweetFile = open(sys.argv[1],"r")
labelledUserFile = open(sys.argv[2],"w")
for line in tweetFile:
	sp=line.strip().split("\x01")
	if len(sp)<7:
		continue
	userDesc=sp[6]
	labels=getLabel(userDesc)
	if labels!='':
		labelledUserFile.write(userDesc+"\x01"+labels+"\x01"+sp[9]+"\x01"+sp[10]+"\n")

labelledUserFile.close()
tweetFile.close()
print 'Labelled user descriptions produced in',sys.argv[2]
	
