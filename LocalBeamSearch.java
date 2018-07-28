import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author David Moniz - FERI - University of Maribor
 * 
 * 
 */
public class LocalBeamSearch {

	
	/**
	 * Creates a multi array of numbOfStates, and multiply by the number of queens.
	 * @param n
	 * @param numOfStates
	 * @return
	 */
    public static int[] solve(int n, int numOfStates) {
        int[][] states = new int[numOfStates][];//Creates empty multiarray with numOfStates arrays

        for (int i = 0; i < numOfStates; i++)
            states[i] = NxNQueenHeuristic.generateNewRandomMatrixState(n);//Generate each a random queen state for the array


        for (int x = 0; x < 1000; x++) { //loop

        	/**
        	 * for each state, it will create N states that to improve each one by one COLUMN only
        	 */
            int[][] newStates = new int[n*numOfStates][];//Create the empty spaces
            
            for (int i = 0; i < numOfStates; i++) {//for every number of state 
                int costToBeat = NxNQueenHeuristic.getHeuristicCost(states[i]);//get heuristic cost for states

                // if solved
                if (costToBeat == 0)   //if the heuristic cost is 0
                    return states[i];  //return the state

                for (int col = 0; col < n; col++) {   //for each column
                    newStates[i*n + col] = changeColumnCost(states[i], col, costToBeat);//change one queen in a column of the state to a better state
                                                                                 
                    if (newStates[i*n + col] == null) //In case one of the states gets null from the hill climbing effect
                        newStates[i*n + col] = NxNQueenHeuristic.generateNewRandomMatrixState(n); //generate a new state
                }

            }
            Arrays.sort(newStates, Comparator.comparingInt(NxNQueenHeuristic::getHeuristicCost)); //sort by heuristic cost
            states = Arrays.copyOfRange(newStates, 0, numOfStates); //Gets the best ones 
        }
        return null;
    }

    /**
     * This is a hill climbing algorithm used to choose the best move of a state by changing only one queen of a column
     * @param queensArray
     * @param column
     * @param costToBeat
     * @return
     */
    private static int[] changeColumnCost(int queensArray[], int column, int costToBeat) {
        int n = queensArray.length;  //sets n as length of array´
        
        for (int row = 0; row < n; row++) {  //loops through all rows
            
            if (row == queensArray[column])  //if the queen is in this position, cycle one more of the loop
                continue;      
            int tmpRow = queensArray[column];  //temporary row is the searchable row (just in case the heuristic cost is the same or less)
            queensArray[column] = row;         //Choose the free move
            int cost = NxNQueenHeuristic.getHeuristicCost(queensArray);  //gets the heuristic cost
            if (costToBeat > cost) {  //if the new cost is lower than existing one
                queensArray[column] = row;   //Change the better move to the queens state
                return queensArray;       //return the board new setup
            }
            queensArray[column] = tmpRow;    //else, return the same board setum
        }
        return null;
    }


}
