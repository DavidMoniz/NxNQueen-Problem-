
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author David Moniz
 * This class function is to create a new random matrix state and to calculate the heuristic of any matrix state of the NxNqueen problem.
 *
 */
public class NxNQueenHeuristic {
    /**
     * 
     * Creates new matrix state NxN with N queens, randomly placed, each in one column
     * @param numberOfQueens
     * @return
     */
    public static int[] generateNewRandomMatrixState(int numberOfQueens) {
    	int[] matrixState =  new int[numberOfQueens];//new matrix
    	for (int i = 0; i < matrixState.length; i++)
            matrixState[i] = (int) (Math.random() * matrixState.length);//Places a queen in each column randomly, from 0 to n
        return matrixState;
    }

    /**
     * 
     * Heuristic function to check how many times are the queens attacking each other queens.
     * @param matrixState
     * @return
     */
    public static int getHeuristicCost(int[] matrixState) {
        int heuristicCost = 0;
        for (int i = 0; i < matrixState.length; i++)
            for (int j = i + 1; j < matrixState.length; j++)
                if (matrixState[i] == matrixState[j] || Math.abs(matrixState[i] - matrixState[j]) == j - i)//Checks if there is two queens attacking each other.
                    heuristicCost += 1;//Increments the number of attacks
        return heuristicCost;
    }
}
