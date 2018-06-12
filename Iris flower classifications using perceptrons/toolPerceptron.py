#Hassan Hanino 10146297
#CISC-452 Assignment 1 Perceptron
# To install these, go on terminal (mac) and type :
#"python3 -m pip install pandas"
#"python 3 -m pip install pandas scikit-learn"

import pandas
from sklearn.metrics import classification_report
from sklearn.linear_model import Perceptron

#This will read the file and split into 2 lists consisting of the training data and training answers
trainingFile = "train.txt"
trainingData = pandas.read_csv(trainingFile, sep=',')
trainingList = trainingData.values
trainingInputs = trainingList[:, 0:4]
trainingAnswers = trainingList[:, 4]

toolPerceptron = Perceptron(None, 0.05,True, 500)
toolPerceptron.fit(trainingInputs,trainingAnswers)

#This will read the file and split into 2 lists consisting of the test data and test answers
testFile = "test.txt"
testData = pandas.read_csv(testFile, sep=',')
testList = testData.values
testInputs = testList[:, 0:4]
testAnswers = testList[:, 4]

#The perceptron has a method called predictions which will return a list of answers on the test data
predictions = toolPerceptron.predict(testInputs)

print(classification_report(testAnswers, predictions))
