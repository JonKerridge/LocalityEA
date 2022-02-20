package localityQueens

import locality_model.LocalityIndividual

class LocalityQueensIndividual implements LocalityIndividual{

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

  double fitness
  List <Integer> chromosome
  int geneLength
  boolean converged

  LocalityQueensIndividual(int geneLength){
    this.geneLength = geneLength
    chromosome = []
  }

  /** The initialise method is used to create the values in each individual in the population

   * initialise property values in an individual.
   * @param rng a random number generator passed from a node; each node has a different rng
   */
  @Override
  void initialise(Random rng) {
    chromosome = [null]

    for (g in 1 .. geneLength) chromosome << g
    // now swap queensIsland to mix up initial locations
    for (q1 in 1 .. geneLength) {
      int q2 = rng.nextInt(geneLength) + 1
      chromosome.swap(q1, q2)
    }
    evaluateFitness(null)
  }

  /**
   *
   * calculates the fitness value(s) of an individual
   *
   * @param evaluateData contains any data required to calculate the fitness function and is null if not required
   */
  @Override
  void evaluateFitness(List evaluateData) {
    // loops start at 1 because chromosome[0] is null
    List <Integer> leftDiagonal = []
    List <Integer> rightDiagonal = []
    double sum = 0.0D

    for ( i in 1 .. 2*geneLength) {
      leftDiagonal[i] = 0
      rightDiagonal[i] = 0
    }
    for (int i in 1 .. geneLength) {
      int idxL = i + chromosome[i] - 1
//      println "idxl = $idxL"
      leftDiagonal[idxL]++
      int idxR = geneLength - i + chromosome[i]
      rightDiagonal[idxR]++
//            rightDiagonal[N-i+board[i]]++
    }
    for ( i in 1 .. ((2*geneLength) - 1)) {
      int counter = 0
      if ( leftDiagonal[i] > 1)
        counter += leftDiagonal[i] - 1
      if ( rightDiagonal[i] > 1)
        counter += rightDiagonal[i] - 1
      sum += counter / (geneLength - Math.abs(i-geneLength))
    }
    // target fitness is 0.0
    // sum can be negative so return absolute value
    fitness = Math.abs(sum)
  }

  /**
   * undertakes the mutation operation of this individual
   *
   * @param rng the Random number generator used by the node to which
   * this individual belongs
   */
  @Override
  void mutate(Random rng) {
    // only move ONE queen first element is null so add 1
    int column1 = rng.nextInt(geneLength) + 1
    int column2 = rng.nextInt(geneLength) + 1
    while (column1 == column2) column2 = rng.nextInt(geneLength) + 1
    chromosome.swap(column1, column2)
  }

  /**
   *  used to obtain the chromosome from an Individual, typically when no
   *  convergence has been found and the best solution so far is required
   *
   * @return the chromosome part of a solution
   */
  @Override
  Object getSolution() {
    return chromosome
  }

  /**
   * @return the fitness value of an Individual
   */
  @Override
  Object getFitness() {
    return fitness
  }

  String toString(){
    return "$chromosome"
  }
}
