package localityMax

import locality_model.LocalityEngine
import locality_model.LocalityProblemSpecification

def problem = new LocalityProblemSpecification()


int instances = 11
boolean doSeedModify = true

problem.minOrMax = "MAX"
problem.instances = instances
problem.maxGenerations = 4000
problem.crossoverPoints = 1
problem.crossoverProbability = 1.0
problem.mutationProbability = 0.8
problem.dataFileName = null
problem.populationPerNode = 4
problem.doSeedModify = doSeedModify
problem.populationClass = LocalityMaxOnePopulation.getName()
problem.individualClass = LocalityMaxOneIndividual.getName()
problem.seeds = [3, 211, 419, 631, 839, 1039, 1249, 1451,
                 1657, 1861, 2063, 4073, 6079, 8081, 10091, 10301,
                 10487, 10687, 10883, 11083, 11273, 11471, 11689, 11867,
                 12043, 12241, 122412, 12583, 12763, 12959, 13147, 13331]

int geneLength = 2048
problem.geneLength = geneLength
problem.convergenceLimit = geneLength
for ( n in [2,4,6,8,10,12,14,16]) {
  problem.nodes = n
  for (cp in [1.0, 0.9, 0.8]) {
    problem.crossoverProbability = cp
    for (mp in [0.9, 0.8]) {
      problem.mutationProbability = mp
      for (ri in [4, 6, 8, 10]) {
        problem.replaceInterval = ri

        String outFile = "./localityMaxOnesMany${geneLength}.csv"
        def fw = new FileWriter(outFile, true)
        def bw = new BufferedWriter(fw)
        def printWriter = new PrintWriter(bw)

        def localityEngine = new LocalityEngine(problemSpecification: problem,
            nodes: problem.nodes,
            instances: instances,
            printWriter: printWriter)
        localityEngine.run()
      }
    }
  }
}


