package localitySOP

import locality_model.LocalityEngine
import locality_model.LocalityProblemSpecification

def sopProblem = new LocalityProblemSpecification()
sopProblem.nodes = 4
sopProblem.instances = 11
sopProblem.dataFileName = "./BR17-10.sop"
sopProblem.populationClass = LocalitySOPPopulation.getName()
sopProblem.individualClass = LocalitySOPIndividual.getName()
sopProblem.geneLength = 18
sopProblem.populationPerNode = 4
sopProblem.replaceInterval = 4
sopProblem.crossoverPoints = 2
sopProblem.maxGenerations = 8000
sopProblem.crossoverProbability = 1.0
sopProblem.mutationProbability = 0.1
sopProblem.convergenceLimit = 65
sopProblem.minOrMax = "MIN"
sopProblem.doSeedModify = true


String outFile = "./Locality17-10Single.csv"
def fw = new FileWriter(outFile, true)
def bw = new BufferedWriter(fw)
def printWriter = new PrintWriter(bw)

sopProblem.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                    1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                    10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                    12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]

def localityEngine = new LocalityEngine(problemSpecification: sopProblem,
    instances: sopProblem.instances,
    nodes: sopProblem.nodes,
    printWriter: printWriter)
localityEngine.run()


