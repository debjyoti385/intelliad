import nltk
from nltk.corpus import stopwords
import operator
from math import exp
import sys

############CONFIG#############
stops = set(stopwords.words('english'))
hashModuloPrime = 1007

################################

#############UTILITY FUNCTIONS#########
def isStopWord(word):
    return (word.lower() in stops)

def getHashValue(word):
    return hash(word.lower()) % hashModuloPrime

# ignoring stopwords, calculate hash modulo prime and increase bucket counter to get
# constant size feature vector, like a histogram
def getHashTrickHistogramFeatureIgnoringStopWords(text):
    feature=[0]*hashModuloPrime
    for word in text.lower().strip("#,;|").split():
        if not isStopWord(word):
            feature[getHashValue(word)]+=1
    return feature

# ignoring stopwords, calculate word frequency map of text
def getWordFrequencyFeatureIgnoringStopWords(text):
    freqFeat={}
    for word in text.split():
        wordLow=word.lower().strip("#,;|")
        if not isStopWord(wordLow):
            if wordLow not in freqFeat:
                freqFeat[wordLow]=0
            freqFeat[wordLow]+=1
    return freqFeat

def mean(V):
    return sum(V)*1.0/len(V)
    
def stdev(V):
    m=mean(V)
    s=0
    for i in range(len(V)):
        s+=((V[i]-m)**2)
    return (s/len(V))**0.5
    
#############MODEL#####################

class NaiveBayesModel:
    def __init__(self,labelSet):
        self.labelSet=labelSet
        self.labelPrior={}
        self.labelFeatureMean = {}
        self.labelFeatureStd = {}
    
    # call after training all the model
    def evaluateModelParameters(self,trainingData):
        FeatLen=len(trainingData[0][0])
        TrainingSize=len(trainingData)
        #print 'TrainingSize:',TrainingSize
        #print trainingData[0][1]
        labelFeatureValues={}
        for trainingPoint in trainingData:
            #print "trainingPoint=",trainingPoint
            feature=trainingPoint[0]
            labelSet=trainingPoint[1]
            for label in labelSet:
                if label not in labelFeatureValues:
                    labelFeatureValues[label]=[]
                labelFeatureValues[label].append(feature)
        #for label in labelFeatureValues:
            #print 'Label:',label,',Values:',labelFeatureValues[label]
        for label in labelFeatureValues:
            for featureId in range(FeatLen):
                S=[]
                for trainingIndex in range(len(labelFeatureValues[label])):
                    S.append(labelFeatureValues[label][trainingIndex][featureId])
                #print label,S
                M = mean(S)
                STD = stdev(S)
                if label not in self.labelFeatureMean:
                    self.labelFeatureMean[label]=[]
                if label not in self.labelFeatureStd:
                    self.labelFeatureStd[label]=[]
                self.labelFeatureMean[label].append(M)
                self.labelFeatureStd[label].append(STD)
            #print 'Label:',label,',Mean & STD:'
            #print self.labelFeatureMean[label]
            #print self.labelFeatureStd[label]
        for label in self.labelFeatureMean:
            print 'Label:',label,',Mean:',self.labelFeatureMean[label]
            print 'Label:',label,',Apriori:',self.labelPrior[label]
            
    def readRecord(self,textlabelSetFp,delim, labelSep):
        line=textlabelSetFp.readline()
        if line=='':
            return (-1,-1)
        lineSplit=line.strip().split(delim)
        text=lineSplit[0]
        labelSet=set()
        for label in lineSplit[1].split(labelSep):
            labelSet.add(label)
        return (text,labelSet)
            
    def trainMulti(self,textlabelSetFile,delim, labelSep):
        textlabelSetFp=open(textlabelSetFile,"r")
        record=self.readRecord(textlabelSetFp,delim, labelSep)
        trainingPoints=0
        trainingData=[]
        print 'Starting record reading using',textlabelSetFile,'for feature calculations'
        while(record!=(-1,-1)):
            trainingPoints+=1
            text=record[0]
            labelSet=record[1]
            if not labelSet:
                continue
            # update aPriori probability
            for label in labelSet:
                if label not in self.labelPrior:
                    self.labelPrior[label]=1.0
                else:
                    self.labelPrior[label]+=1.0
            # train model
            feature=getHashTrickHistogramFeatureIgnoringStopWords(text)
            trainingData.append((feature,labelSet))
            record=self.readRecord(textlabelSetFp,delim, labelSep)
        
        textlabelSetFp.close()
        print 'Feature calculation for all records done. Evaluating model parameters...'
        
        for label in self.labelPrior:
            self.labelPrior[label]/=trainingPoints
        
        self.evaluateModelParameters(trainingData)
        print 'Model evaluation done!'
        
                
    def getAPrioriProb(self,label):
        if label not in self.labelPrior:
            return 0
        #print label,'AlabelPriori=',self.labelPrior[label]/self.trainingPoints
        return self.labelPrior[label]
    
    def getLikelihood(self,label,attrValue,index):
        #print label, index
        #print self.labelFeatureMean
        m=self.labelFeatureMean[label][index]
        s=self.labelFeatureStd[label][index]
        '''if s==0.0:
            return 1
        power = -(attrValue-m)**2/(2*(s**2))
        factor = (1.0/(2*3.142*s**2))**0.5
        return factor*exp(power)
        '''
        return exp(-(attrValue-m)**2)
        
    def classifyTextWithLabelScores(self,text):
        feature=getHashTrickHistogramFeatureIgnoringStopWords(text)
        print 'Test feature:',feature
        finalprob={}
        for label in self.labelSet:
            likelihoodProduct=1
            for i in range(len(feature)):
                Pi_C = self.getLikelihood(label,feature[i],i)
                likelihoodProduct*=Pi_C
            print 'Likelihood for label:',label,':',likelihoodProduct
            #TODO: ignoring apriori for now
            #finalprob[label]=likelihoodProduct*self.getAPrioriProb(label)
            finalprob[label]=likelihoodProduct
        print 'Scores:',finalprob
        return finalprob
    
    def getClassificationLabel(self,text):
        finalprob = self.classifyTextWithLabelScores(text)
        sorted_prob = sorted(finalprob.items(), key=operator.itemgetter(1),reverse=True)
        return sorted_prob[0][0]
    
if __name__=='__main__':
    if len(sys.argv) <2:
        print 'Arguments: <trainingFile in text,labelSet format>'
        sys.exit(1)
    nbm = NaiveBayesModel(set(map(str,range(10))))
	
    nbm.trainMulti(sys.argv[1],"\x01",",")
    
    print 'Enter query text:',
    testText=raw_input().strip()
    #testText = 'almighty'
    print 'Query:',testText
    print 'Best class:',nbm.getClassificationLabel(testText)
