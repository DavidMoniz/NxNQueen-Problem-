import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		GeneticAlgorithm g = new GeneticAlgorithm();
		System.out.println(Arrays.toString(g.solve(8, 1000, 0.8, 100,0.6,1)));
		
		LocalBeamSearch l = new LocalBeamSearch();
		System.out.println(Arrays.toString(l.solve(8, 1000)));
	}

}
