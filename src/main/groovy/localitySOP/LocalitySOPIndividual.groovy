package localitySOP

import locality_model.LocalityIndividual

class LocalitySOPIndividual implements LocalityIndividual{

  /**
   * Any Individual using the Locality Model Engine uses this interface to specify
   * the methods an Individual class must implement.  The class will have the following properties:<br>
   *
   def fitness    // this can be any comparable type
   List chromosome
   int geneLength
   boolean converged

   * A constructor that creates an empty individual is required, where genelength is the number
   * of elements in a chromosome which should be a list that is set to the empty List<br>
   *
   * ExampleIndividual (int geneLength)<br>
   *
   * fitness is the current value of the fitness function applied to this individual
   * chromosome is the set of values that make up the individuals data points
   * geneLength is the number of elements in the chromosome
   * converged indicates whether a solution has converged after maxGenerations reached
    */
  int fitness
  List chromosome
  int geneLength
  boolean converged
  List <List> distances
  Map precedences
  boolean initialised = false

  LocalitySOPIndividual(int geneLength){
    this.geneLength = geneLength
    chromosome = []
    for ( i in 0 .. geneLength) chromosome << 0
  }


  /** The initialise method is used to create the values in each individual in the population

   * initialise property values in an individual.
   * @param rng a random number generator passed from a node; each node has a different rng
   */
  @Override
  void initialise(Random rng) {
    for ( i in 0 .. geneLength) chromosome[i] = 0
    chromosome[1] = 1
    chromosome[geneLength] = geneLength
    int cities = geneLength
    int place = rng.nextInt(cities) + 1   //1.. cities
    for ( i in 2 ..< cities ) {
      while ((place == 1) || (place == geneLength) ||
          (chromosome.contains(place))) place = rng.nextInt(cities) + 1
      chromosome[i] = place
    }
    chromosome[cities] = cities
//    println "initial unchecked value: $chromosome"
  }

  /**
   *
   * calculates the fitness value(s) of an individual
   *
   * @param evaluateData contains any data required to calculate the fitness function and is null if not required
   */
  @Override
  void evaluateFitness(List evaluateData) {
    if (!initialised){
      initialised = true
      distances = evaluateData[0]
      precedences = evaluateData[1]
    }
    // now ensure precedences are enforced
    boolean finished = false
    while (!finished){
      boolean swapped = false
      precedences.each{ Integer after, List befores ->
        int e = 2
        while (chromosome[e] != after) e = e + 1  // every node must be there so no size check
        int b = geneLength - 1 // last city is always fixed
        while ((b > e) && (! befores.contains(chromosome[b]))) b = b - 1
        if ( b > e){
          chromosome.swap(b, e)
          swapped = true
//        println "b: $b, e: $e, $iRoute"
        }
      }
      finished = ! swapped

    } //finished
    // now determine the fitness value
//    println "reordered: $chromosome"
    fitness = 0
//      println "EF: ${chromosome} "
    for ( int i in 2 .. geneLength){
//      println "EF: $i, ${i-1}, ${chromosome} "
      fitness = fitness + distances[chromosome[i-1]] [chromosome[i]]
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
    int place1 = rng.nextInt(geneLength - 3) + 2  //2..genelength-1
    int place2 = rng.nextInt(geneLength - 3) + 2
    while (place1 == place2) place2 = rng.nextInt(geneLength - 3) + 2
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
    return "$chromosome : $fitness"
  }

}
