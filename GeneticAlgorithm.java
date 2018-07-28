import java.util.Arrays;
import java.util.Comparator;

/**
 * @author David Moniz - FERI - University of Maribor
 */
public class GeneticAlgorithm {


	/**
	 * Generates an array of states and gets a solution state by changing proprities within each other like genetic mutation 
	 * @param numberOfQueens
	 * @param sizeSingleGeneration
	 * @param mutationProbability
	 * @param numOfGenerations
	 * @param crossOverProbability
	 * @param elitismPercentage
	 * @return
	 */
    public int[] solve(int numberOfQueens, int sizeSingleGeneration, double mutationProbability, int numOfGenerations, double crossOverProbability, double elitismPercentage) {

    	//Number of chromosomes
        sizeSingleGeneration = sizeSingleGeneration - (sizeSingleGeneration % 2);//Corrects the number to a pair number because it's pairs.                                   
        int[][] generation = generateGeneration(numberOfQueens, sizeSingleGeneration);//generates specified amount of random setups
        int maxFitness = getMaxFitness(numberOfQueens);  //gets max fitness per specified generation
        int elitismSize = (int) (sizeSingleGeneration*elitismPercentage);
        
        for (int x = 0; x < numOfGenerations; x++) {//for all generations

            generation = getSelectedgeneration(generation);//get the best generation out of all generations
            generation = handleCrossovers(generation, numberOfQueens, crossOverProbability);//crossover random columns with each other (by pairs of 2)

            /*
             * Checks if there's any state with max fitness, with heuristic 0
             */
            for (int i = 0; i < sizeSingleGeneration; i++) {//for all the generation size
                if (getFitness(generation[i]) == maxFitness)  
                    return generation[i];//if we achieved max possible fitness return this board setup
            } 
            
            /*
             * This will mutate all chromossomes execpt the best ones
             */
            for (int i = 0; i < sizeSingleGeneration-elitismSize; i++) {
                generation[i] = tryToMutate(generation[i], mutationProbability);//mutates
                if (getFitness(generation[i]) == maxFitness) //If it has max fitness then it found a solution
                    return generation[i];
            }
        }
        
        return null;
    }

    /**
     * The crossover will apply an exchnage of proprieties between two choromosomes
     * Which means that between two states it will exchange queen positions from one colmun only 
     * @param generation
     * @param n
     * @param crossOverProbability
     * @return
     */
    private int[][] handleCrossovers(int[][] generation, int n, double crossOverProbability) {
        for (int i = 0; i < generation.length; i += 2) {  //every 2nd board setup
        	if (satisfyProb(crossOverProbability)) {
	            int crossoverPos = (int) (Math.random() * n); //get random column form 0 to n
	
	            for (int j = 0; j < crossoverPos; j++) {   //crosses over one random column setup with next
	                int tmp = generation[i][j];            //Saves queen position A of state A'
	                generation[i][j] = generation[i+1][j]; //Puts queen position A to state B'
	                generation[i+1][j] = tmp;				//Puts queen position B to state A
	            }
        	}
        }
        return generation;
    }

    /**
     * Sorts the generation by fitness, so the first ones are actually the worst because the have the less fitness
     * @param generation
     * @return
     */
    private int[][] getSelectedgeneration(int[][] generation) {
        Arrays.sort(generation, Comparator.comparingInt(this::getFitness)); 
        return generation;   //returns the generation with best fitness
    }

    /**
     * 
     * Mutates from a state a random column the position of the queen
     * @param r
     * @param mutationProbability
     * @return
     */
    private int[] tryToMutate(int[] r, double mutationProbability) {
        if (satisfyProb(mutationProbability))
            r[(int)(Math.random()*r.length)] = (int)(Math.random()*r.length); //Chooses a random column and change the position of the queen randomly.
        return r;
    }

    /**
     * This will be true or false by random probability against the inputs probability
     * @param prob
     * @return
     */
    private boolean satisfyProb(double prob) {
        return prob >= Math.random();  
    }                                  

    /**
     * gets the fitness of a choromossome
     * @param r
     * @return
     */
    private int getFitness(int[] r) {
        return getMaxFitness(r.length) - NxNQueenHeuristic.getHeuristicCost(r);  //gets the fitness by subtracting best possible by current
    }

    /**
     * Get the maximum fitness possible of a chromossome, which is our goal to find in a state
     * @param n
     * @return
     */
    private int getMaxFitness(int n) {
        return n*(n-1)/2;  //almost linear function for getting the max fitness according to generation size
    }                      //the bigger the generation, the better max fitness can be

   
    /**
     * Generates a generation of chromosomes 
     * @param n
     * @param sizeSingleGeneration
     * @return
     */
    private int[][] generateGeneration(int n, int sizeSingleGeneration) {
        int[][] generation = new int[sizeSingleGeneration][];
        for (int i = 0; i < sizeSingleGeneration; i++)//loops through the population size
            generation[i] = NxNQueenHeuristic.generateNewRandomMatrixState(n);//Insert a chromosome in it
        return generation;
    }
}
