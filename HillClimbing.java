import java.util.Arrays;
/**
 * @author David Moniz - FERI - University of Maribor
 * HillClimbing 
 */
public class HillClimbing {
    /**
     * This function uses the principle of Hill Climbing and uses the first array of the board to solve.
     * In case it doesn't it will generate a random board and solve again until it gets a solution
     * @param numberOfQueens
     * @param firstArray
     * @return
     */
    public static int[] solve(int numberOfQueens, int[] firstArray) {
        int[] queensArray = firstArray;//Saves the first array from the board to check if it can solve it
        int globalCost = NxNQueenHeuristic.getHeuristicCost(queensArray);//Gets the cost of the queens array
        
        while (globalCost>0) {//This loop will only stop until it has a solution, a heuristic cost of 0.
            boolean loopBreaker = true;//This boolean is to break from the column to pass to the next one.
            int localCost = globalCost;//local cost, to check the new state of the queens array
            for (int column = 0; column < numberOfQueens && loopBreaker; column++) {//Loops and expands the 2D array to all columns
                for (int row = 0; row < numberOfQueens; row++) {
                    if (row == queensArray[column])//Checks if the queen already exists in that position
                        continue;    
                    int[] temporaryArray = Arrays.copyOf(queensArray, numberOfQueens);//Temporary clone of the queens array move.
                    temporaryArray[column] = row;//Changes the queen to the free position
                    int cost = NxNQueenHeuristic.getHeuristicCost(temporaryArray);//Gets the cost of the temp array
	                    if (globalCost > cost) {              //if this new cost is lower than the current one then it found a better state
	                        queensArray[column] = row;        //sets the new row position on the column of the global array 
	                        globalCost = cost;                //temporary cost is the new cost
	                        loopBreaker = false;              //loop becomes false because it found a better state
	                        break;                            
	                    }}}
            if (localCost == globalCost)             //if it gets stuck at the local maxima
                queensArray = NxNQueenHeuristic.generateNewRandomMatrixState(numberOfQueens);   
        }
        return queensArray;//SOLUTION 
    }
}
