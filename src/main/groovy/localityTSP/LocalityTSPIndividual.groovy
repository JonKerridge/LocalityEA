package localityTSP

import locality_model.LocalityIndividual

class LocalityTSPIndividual implements LocalityIndividual{

  /**
   * Any Individual using the Locality Model Engine uses this interface to specify
   * the methods an Individual class must implement.  The class will have the following properties:<br>
   *
   def fitness    // this can be any comparable type
   List chromosome
   int geneLength

   * A constructor that creates an empty individual is required, where genelength is the number
   * of elements in a chromosome which should be a list that is set to the empty List<br>
   *
   * ExampleIndividual (int geneLength)<br>
   *
   * fitness is the current value of the fitness function applied to this individual
   * chromosome is the set of values that make up the individuals data points
   * geneLength is the number of elements in the chromosome
   */
  int fitness
  List chromosome
  int geneLength
  boolean converged

  LocalityTSPIndividual(int geneLength){
    this.geneLength = geneLength
    chromosome = []
    for ( i in 0 ..< geneLength) chromosome << 0
  }


  /** The initialise method is used to create the values in each individual in the population

   * initialise property values in an individual.
   * @param rng a random number generator passed from a node; each node has a different rng
   */
  @Override
  void initialise(Random rng) {
    for ( i in 0 ..< geneLength) chromosome[i] = 0
    chromosome[0] = 1
    chromosome[geneLength-1] = 1
    int cities = geneLength -1
    int place = rng.nextInt(cities) + 1   //1.. cities
    for ( i in 1 ..< cities ) {
      while ((place == 1) || (chromosome.contains(place))) place = rng.nextInt(cities) + 1
      chromosome[i] = place
    }
  }

  /**
   *
   * calculates the fitness value(s) of an individual
   *
   * @param evaluateData contains any data required to calculate the fitness function and is null if not required
   */
  @Override
  void evaluateFitness(List evaluateData) {
    fitness = 0
    for ( int i in 1 ..< geneLength){
//      println "EF: $i, ${i-1}, ${chromosome} "
      fitness = fitness + evaluateData[chromosome[i-1]] [chromosome[i]]
    }
  }

  /**
   * undertakes the mutation operation of this individual
   *
   * @param rng the Random number generator used by the node to which
   * this individual belongs
   */
  @Override
  void mutate(Random rng) {
    int place1 = rng.nextInt(geneLength - 2) + 1  //1..genelength-1
    int place2 = rng.nextInt(geneLength - 2) + 1
    while (place1 == place2) place2 = rng.nextInt(geneLength - 2) + 1
    chromosome.swap(place1, place2)
  }

  /**
   *  used to obtain the chromosome from an Individual, typically when no
   *  convergence has been found and the best solution so far is required
   *
   * @return the chromosome part of a solution
   */
  @Override
  Object getSolution() {
    return [ chromosome, fitness]
  }

  /**
   * @return the fitness value of an Individual
   */
  @Override
  Object getFitness() {
    return fitness
  }

  String toString(){
    return "$fitness"
  }

}
