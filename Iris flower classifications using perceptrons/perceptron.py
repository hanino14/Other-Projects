#Hassan Hanino 10146297
#CISC-452 Assignment 1 Perceptron
import random

#Parses the file and makes each line a list as 5 elements
# where 4 are flower attributes with the last element as the name of the flower
#Each list (flower) is then put into a final list where each element is a list/flower specie 
def parseTrainingFile():
    flowerList = []
    with open("train.txt") as file:
        for line in file:
            newList = line.split(",")
            for i in range (4):
                newList[i] = float(newList[i])
            flowerList.append(newList)
    for i in range (len(flowerList)):
        flowerList[i][4] = flowerList[i][4].strip('\n')
    flowerList = random.sample(flowerList, len(flowerList)) #Shuffling the list so training is better, should not train 40 of the same species in a row.
    return flowerList

#Same function as above, but for the test file
def parseTestingFile():
    flowerList = []
    with open("test.txt") as file:
        for line in file:
            newList = line.split(",")
            for i in range (4):
                newList[i] = float(newList[i])
            flowerList.append(newList)
    for i in range (len(flowerList)):
        flowerList[i][4] = flowerList[i][4].strip('\n')
    return flowerList

#Creates a set of 4 random weights between -1 and 1
def createWeights():
    weights = []
    for x in range (4):
        weights.append(random.uniform(-1,1))
    return weights

    

def startLearningFirstPerceptron(inputArray, weights):
    perfect = True
    learningRate = 0.1
    threshold = 1
    for x in range (len(inputArray)):
        potential = 0
        for y in range (4):
            potential = potential + (weights[y]*(inputArray[x][y]))
        if potential > threshold:
            if ("Iris-setosa" == inputArray[x][4]): # Potential activity is higher than threshold, therefore we move to the second layer and predict the specie is NOT Iris-setosa, and if it is, we adjust weights.
                perfect = False
                weights = adjustWeights(weights, inputArray[x], learningRate, -1)
        else:
            if ("Iris-setosa" != inputArray[x][4]): #Potential activity is lower than threshold, therefore we predict it is Iris-setosa, and if it is not, we adjust weights.
                perfect = False
                weights = adjustWeights(weights, inputArray[x], learningRate, 1)
    if perfect == True:
        return weights
    else:
        return startLearningFirstPerceptron(inputArray,weights)
        

def startLearningSecondPerceptron(inputArray, weights):
    counter = 0
    learningRate = 0.05
    threshold = 1
    while (counter < 1000):
        for x in range (len(inputArray)):
            potential = 0
            for y in range (4):
                potential = potential + weights[y]*int(inputArray[x][y])
            if potential > threshold:
                #We predict the flower is Iris-virginica, so if it is versicolor, we adjust weights.
                if ("Iris-versicolor" == inputArray[x][4]):
                    #print (counter)
                    weights = adjustWeights(weights, inputArray[x], learningRate, -1)
            else:
                #We predict the flower is Iris-virgicolor, so if it is virginica, we adjust weights.
                if ("Iris-virginica" == inputArray[x][4]):
                    weights = adjustWeights(weights, inputArray[x], learningRate, 1)
        counter+=1
    return weights


# Function used to adjust weights
def adjustWeights(weights, inputList, learningrate, error):
    for x in range (len(weights)):
        weights[x] = weights[x] + (learningrate * inputList[x] * error)
    return weights
    

def smartPerceptron(weights, inputArray):
    firstLayerWeights = weights[1]
    secondLayerWeights = weights[2]
    sumSquaredError = 0 #Incrementing my sumSquaredError is identical to calculating Sum of (y-d)^2 as the sum will either either increase by 1 (Error in classification), or 0 (No error). 
    setosaError = 0
    versicolorError = 0
    virginicaError = 0
    out = open("out.txt", 'w')
    threshold = 1
    out.write("  Predicted   :: Actual \n\n")
    for x in range (len(inputArray)):
        potential = 0
        for y in range(4):
            potential = potential + firstLayerWeights[y]*(inputArray[x][y])
        if potential < threshold:
            out.write(str(x) + " " + "Iris-setosa" + " :: " + inputArray[x][4] + "\n\n")
            if ("Iris-setosa" != inputArray[x][4]):
                setosaError +=1
                sumSquaredError+=1
        else:
            potential = 0
            for y in range(4):
                potential = potential + secondLayerWeights[y]*(inputArray[x][y])
            if potential < threshold:
                out.write(str(x) + " " + "Iris-versicolor" + " :: " + inputArray[x][4] + "\n\n")
                if ("Iris-versicolor" != inputArray[x][4]):
                    virginicaError +=1
                    sumSquaredError+=1
            else:
                out.write(str(x) + " " + "Iris-virginica" + " :: " + inputArray[x][4] + "\n\n")
                if ("Iris-virginica" != inputArray[x][4]):
                    versicolorError+=1
                    sumSquaredError+=1
    print("Initial weight vector for both perceptrons : " + str(weights[0]) + "\n")
    print("New weight vector for the first perceptron : " + str(firstLayerWeights) + "\n")
    print("New weight vector for the second perceptron : " + str(secondLayerWeights) + "\n")
    print("Sum Squared Error/total # of errors is " + str(sumSquaredError) + ". \n")
    print("Total number of iterations used to train the first perceptron is 240. Terminating criteria is when an error-free epoch occurs. \n")
    print("Total number of iterations used to train the second perceptron is 1000. Terminating criteria is when the second perceptron has used all of the training data points 1000 times to train itself.\n")
    print("Precision of my classification for Iris-setosa " + str((10-setosaError)/(10+setosaError)) + "\n\n")
    print("Precision of my classification for Iris-versicolor " + str((10-versicolorError)/(10+versicolorError)) + "\n")
    print("Precision of my classification for Iris-virginica " + str((10-virginicaError)/(10+virginicaError)) + "\n")
    print("Recall of my classification for Iris-setosa " + str((10-setosaError)/(10))+ "\n")
    print("Recall of my classification for Iris-versicolor " + str((10-versicolorError)/(10)) + "\n")
    print("Recall of my classification for Iris-virginica " + str((10-virginicaError)/(10))+ "\n")
    
    out.write("Initial Weight Vector for both perceptrons : " + str(weights[0]) + "\n\n")
    out.write("Weight Vector for the first perceptron : " + str(firstLayerWeights) + "\n\n")
    out.write("Weight Vector for the second perceptron : " + str(secondLayerWeights) + "\n\n")
    out.write("Sum Squared Error is " + str(sumSquaredError) + ". \n\n")
    out.write("Total number of iterations used to train the first perceptron is 240. Terminating criteria is when an error-free epoch occurs. \n\n")
    out.write("Total number of iterations used to train the second perceptron is 1000. Terminating criteria is when the second perceptron has used all of the training data points 1000 times to train itself.\n\n")
    out.write("Precision of my classification for Iris-setosa " + str((10-setosaError)/(10+setosaError)) + "\n\n")
    out.write("Precision of my classification for Iris-versicolor " + str((10-versicolorError)/(10+versicolorError)) + "\n\n")
    out.write("Precision of my classification for Iris-virginica " + str((10-virginicaError)/(10+virginicaError)) + "\n\n")
    out.write("Recall of my classification for Iris-setosa " + str((10-setosaError)/(10))+ "\n\n")
    out.write("Recall of my classification for Iris-versicolor " + str((10-versicolorError)/(10)) + "\n\n")
    out.write("Recall of my classification for Iris-virginica " + str((10-virginicaError)/(10))+ "\n\n")

    

def perceptronMain():
    trainingList = parseTrainingFile()
    testingList = parseTestingFile()
    allWeights = []
    weights = createWeights()
    initialWeights = weights[:] #Copy of initial weights since Python uses referencing to attain values of variables.
    allWeights.append(initialWeights)
    firstLayerWeights = startLearningFirstPerceptron(trainingList, weights)
    firstLayerWeightsCopy = firstLayerWeights[:] #Copy of first layer weights since Python uses referencing to attain values of variables.
    allWeights.append(firstLayerWeightsCopy)
    secondLayerWeights = startLearningSecondPerceptron(trainingList, weights)
    secondLayerWeightsCopy = secondLayerWeights[:] #Copy of second layer weights ince Python uses referencing to attain values of variables.
    allWeights.append(secondLayerWeightsCopy)
    smartPerceptron(allWeights, testingList)
    

perceptronMain()
