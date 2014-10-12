import nltk
from nltk.corpus import stopwords
import operator
from math import exp

############CONFIG#############
stops = set(stopwords.words('english'))
hashModuloPrime = 31

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
    for word in text.split():
        if not isStopWord(word):
            feature[getHashValue(word)]+=1
    return feature

# ignoring stopwords, calculate word frequency map of text
def getWordFrequencyFeatureIgnoringStopWords(text):
    freqFeat={}
    for word in text.split():
        wordLow=word.lower()
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
    def __init__(self,categorySet):
        self.categorySet=categorySet
        self.prior={}
        self.trainingPoints=0
        self.trainingData=[]
        self.classFeatureMean = {}
        self.classFeatureStd = {}
        self.classFeatureValues = {}
                
    def trainSingle(self,text,labelSet):
        self.trainingPoints+=1
        # update apriori probability
        for label in labelSet:
            if label not in self.prior:
                self.prior[label]=1.0
            else:
                self.prior[label]+=1.0
        # train model
        feature=getHashTrickHistogramFeatureIgnoringStopWords(text)
        self.trainingData.append((feature,labelSet))
    
    # call after training all the model
    def evaluateModelParameters(self):
        FeatLen=len(self.trainingData[0][0])
        TrainingSize=len(self.trainingData)
        #print 'TrainingSize:',TrainingSize
        #print self.trainingData[0][1]
        for trainingPoint in self.trainingData:
            #print "trainingPoint=",trainingPoint
            feature=trainingPoint[0]
            labelSet=trainingPoint[1]
            for label in labelSet:
                if label not in self.classFeatureValues:
                    self.classFeatureValues[label]=[]
                self.classFeatureValues[label].append(feature)
        #for label in self.classFeatureValues:
            #print 'Label:',label,',Values:',self.classFeatureValues[label]
        for label in self.classFeatureValues:
            for featureId in range(FeatLen):
                S=[]
                for trainingIndex in range(len(self.classFeatureValues[label])):
                    S.append(self.classFeatureValues[label][trainingIndex][featureId])
                #print label,S
                M = mean(S)
                STD = stdev(S)
                if label not in self.classFeatureMean:
                    self.classFeatureMean[label]=[]
                if label not in self.classFeatureStd:
                    self.classFeatureStd[label]=[]
                self.classFeatureMean[label].append(M)
                self.classFeatureStd[label].append(STD)
            #print 'Label:',label,',Mean & STD:'
            #print self.classFeatureMean[label]
            #print self.classFeatureStd[label]
                
    def getAprioriProb(self,label):
        if label not in self.prior:
            return 0
        #print label,'Apriori=',self.prior[label]/self.trainingPoints
        return self.prior[label]/self.trainingPoints
    
    def getLikelihood(self,label,attrValue,index):
        m=self.classFeatureMean[label][index]
        s=self.classFeatureStd[label][index]
        '''if s==0.0:
            return 1
        power = -(attrValue-m)**2/(2*(s**2))
        factor = (1.0/(2*3.142*s**2))**0.5
        return factor*exp(power)
        '''
        return exp(-(attrValue-m)**2)
        
    def getLabelsWithScores(self,text):
        feature=getHashTrickHistogramFeatureIgnoringStopWords(text)
        #print 'Test feature:',feature
        finalprob={}
        for category in self.categorySet:
            likelihoodProduct=1
            for i in range(len(feature)):
                Pi_C = self.getLikelihood(category,feature[i],i)
                likelihoodProduct*=Pi_C
            finalprob[category]=likelihoodProduct*self.getAprioriProb(category)
        return finalprob
    
    def getBestLabel(self,text):
        finalprob = self.getLabelsWithScores(text)
        sorted_prob = sorted(finalprob.items(), key=operator.itemgetter(1),reverse=True)
        return sorted_prob[0][0]
    
if __name__=='__main__':
    nbm = NaiveBayesModel(set(['Business','Sports']))
	
    nbm.trainSingle('i invest in football',set(['Business','Sports']))
    nbm.trainSingle('i invest in XYZ',set(['Business']))
    nbm.trainSingle('i love football',set(['Sports']))
    nbm.evaluateModelParameters()
    
    testText = 'i invest'
    print 'Query:',testText
    print 'Scores:',nbm.getLabelsWithScores(testText)
    print 'Best class:',nbm.getBestLabel(testText)
