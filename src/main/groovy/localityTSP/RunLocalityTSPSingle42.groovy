package localityTSP

import locality_model.LocalityEngine
import locality_model.LocalityProblemSpecification

def tspProblem = new LocalityProblemSpecification()
tspProblem.nodes = 4
tspProblem.instances = 11
tspProblem.dataFileName = "./dantzig42.tsp"
tspProblem.populationClass = LocalityTSPPopulation.getName()
tspProblem.individualClass = LocalityTSPIndividual.getName()
tspProblem.geneLength = 43
tspProblem.populationPerNode = 8
tspProblem.replaceInterval = 4
tspProblem.crossoverPoints = 2
tspProblem.maxGenerations = 4000
tspProblem.crossoverProbability = 1.0
tspProblem.mutationProbability = 0.8
tspProblem.convergenceLimit = 850.0
tspProblem.minOrMax = "MIN"
tspProblem.doSeedModify = true
tspProblem.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                    1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                    10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                    12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]

String outFile = "./Locality42Single.csv"
def fw = new FileWriter(outFile, true)
def bw = new BufferedWriter(fw)
def printWriter = new PrintWriter(bw)

def mainlandEngine = new LocalityEngine(problemSpecification: tspProblem,
    instances: tspProblem.instances,
    nodes: tspProblem.nodes,
    printWriter: printWriter)
mainlandEngine.run()


