/**
 * @author David Moniz - FERI - University of Maribor
 * 
 */
public class SimulatedAnnealing {

	/**
	 * Solve by being very flexible in the beggining 
	 * @param numberOfQueens
	 * @param temperature
	 * @param coolingFactor
	 * @param firstArray
	 * @return
	 */
    public static int[] solve(int numberOfQueens, double temperature, double coolingFactor, int[] firstArray) { 
        int[] r = firstArray; 
        int costToBeat = NxNQueenHeuristic.getHeuristicCost(r);//gets the cost for this setup
       
        
        for (int x = 0; x < 1000 && costToBeat > 0; x++) {//for every iteration
            r = makeMove(r, costToBeat, temperature);
            costToBeat = NxNQueenHeuristic.getHeuristicCost(r);              //gets a new cost to beat
            temperature = Math.max(temperature * coolingFactor, 0.01); //gets a new temperature. Lowest temperature = 0.01
        }
        return costToBeat == 0 ? r : null; // return solution if solved
    }

    /**
     * Uses hillclimbing with a random row or column but doesn't mean it will accept that choice
     * @param queensArray
     * @param costToBeat
     * @param temp
     * @return
     */
    private static int[] makeMove(int queensArray[], int costToBeat, double temp) { //inputs board setup, current cost to beat and temperature
        int n = queensArray.length; //gets the lenght of board
        while (true) {
            int column = (int) (Math.random()*n);//Chooses a random column number
            int row = (int) (Math.random()*n);//Chooses a random row number
            int temporaryRow = queensArray[column];//Saves the queen row position at that column.
            queensArray[column] = row;                       //sets real row at random column to a random row

            int cost = NxNQueenHeuristic.getHeuristicCost(queensArray); //gets the cost of new setup
            if (cost < costToBeat)                      //if this new setup is better
                return queensArray;                               //return this

            int dE = costToBeat - cost;            //else difference = previous (better cost) - this cost
            double acceptProb = Math.min(1, Math.exp(dE / temp)); //gets the probability for e^(de/temp)

            if (Math.random() < acceptProb)  //the better the probability, the closer it is to 1
                return queensArray;                    //if the probability is really good (closing to 1) return the board setup
            queensArray[column] = temporaryRow;                //else return the same board setup as before and start random change again
        }
    }
}
