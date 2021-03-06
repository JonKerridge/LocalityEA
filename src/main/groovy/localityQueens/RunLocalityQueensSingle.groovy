package localityQueens

import locality_model.LocalityEngine
import locality_model.LocalityProblemSpecification


def problem = new LocalityProblemSpecification()
problem.nodes = 4
problem.instances = 11
problem.dataFileName = null
problem.populationClass = LocalityQueensPopulation.getName()
problem.individualClass = LocalityQueensIndividual.getName()
problem.geneLength = 32
problem.populationPerNode = 4
problem.replaceInterval = 4
problem.crossoverPoints = 2
problem.maxGenerations = 2000
problem.crossoverProbability = 1.0
problem.mutationProbability = 0.8
problem.convergenceLimit = 0.0
problem.minOrMax = "MIN"
problem.doSeedModify = true
problem.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                             1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                             10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                             12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]

String outFile = "./LocalityQueensSingle.csv"
def fw = new FileWriter(outFile, true)
def bw = new BufferedWriter(fw)
def printWriter = new PrintWriter(bw)

def mainlandEngine = new LocalityEngine(problemSpecification: problem,
    instances: problem.instances,
    nodes: problem.nodes,
    printWriter: printWriter)
mainlandEngine.run()

