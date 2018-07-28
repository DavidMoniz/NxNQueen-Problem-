import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Label;

/**
 * 
 * 
 * @author David Moniz
 *
 */
public class MainGUI extends JFrame {

	
	private JPanel contentPane;
	private JTextField sAStartTemp;
	private JTextField sACoolTemp;
	private JTextField lnumOfStates;
	private JTextField gElitism;
	private JTextField gMutation;
	private JTextField gCrossover;
	private JTextField gNumGen;
	private JTextField gSingleGen;
//Above are the input value !!	
	private int numberOfQueensNxN = 4;
	private int[] queenArray;
	private JPanel[] elements = new JPanel[numberOfQueensNxN*numberOfQueensNxN];
	private JTextField numQueens;
	private int numberOfButtonOption = 2;//This will save the current buton id
	private String heuristicValue;//This will save the heuristic value of a NxN array
	
	
	
	/**
	 * Launch application of the GUI 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 
	 * Constructor that will draw the basics of the GUI Panel
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel Interface = new JPanel();
		contentPane.add(Interface);
		Interface.setLayout(null);
		
		queenArray = NxNQueenHeuristic.generateNewRandomMatrixState(numberOfQueensNxN);//Creates a new queen array
		heuristicValue=Integer.toString(NxNQueenHeuristic.getHeuristicCost(queenArray));//Gets the heuristic value of the array 
		
		mainInfo(Interface);//Draws the main Interface GUI once
		JPanel board = new JPanel();
		board.setBounds(10, 11, 400, 400);
		
		Interface.add(drawChessBoard(queenArray));//Creates a new matrix state for the queens and draws them on the interface board 
		
		board.revalidate();
		board.repaint();
	}
	
	
	/**
	 * This is the main Panel that has all the GUI of the buttons and inputs of the Game
	 * @param Interface
	 * @return
	 */
	public JPanel mainInfo(JPanel Interface) {
		
		JPanel panel = new JPanel();
		panel.setBounds(420, 0, 349, 428);
		Interface.add(panel);
		panel.setLayout(null);
		
		JLabel lblNxnQueens = new JLabel("NxN Queens");
		lblNxnQueens.setBounds(21, 11, 168, 41);
		panel.add(lblNxnQueens);
		lblNxnQueens.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setBounds(10, 120, 329, 61);
		panel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JPanel InputPanel = new JPanel();
		InputPanel.setBounds(10, 192, 329, 191);
		panel.add(InputPanel);
		InputPanel.setLayout(new CardLayout(0, 0));
		
		JPanel hillClimbingInputPanel = new JPanel();
		InputPanel.add(hillClimbingInputPanel, "name_2135647586");
		hillClimbingInputPanel.setLayout(null);
		
		JLabel lblHillClimbing = new JLabel("Hill Climbing");
		lblHillClimbing.setBounds(137, 5, 91, 14);
		hillClimbingInputPanel.add(lblHillClimbing);
		
		JPanel simulatedAnnealingInputPanel = new JPanel();
		InputPanel.add(simulatedAnnealingInputPanel, "name_7867543322435");
		simulatedAnnealingInputPanel.setLayout(null);
		
		JLabel lblSimulatedAnnealing = new JLabel("Simulated Annealing");
		lblSimulatedAnnealing.setBounds(116, 5, 137, 14);
		simulatedAnnealingInputPanel.add(lblSimulatedAnnealing);
		
		JLabel lblStartingTemp = new JLabel("Starting temperature:");
		lblStartingTemp.setBounds(10, 30, 165, 14);
		simulatedAnnealingInputPanel.add(lblStartingTemp);
		
		sAStartTemp = new JTextField();
		sAStartTemp.setBounds(10, 50, 148, 20);
		simulatedAnnealingInputPanel.add(sAStartTemp);
		sAStartTemp.setColumns(10);
		
		sACoolTemp = new JTextField();
		sACoolTemp.setColumns(10);
		sACoolTemp.setBounds(10, 101, 148, 20);
		simulatedAnnealingInputPanel.add(sACoolTemp);
		
		JLabel lblCoolingTemp = new JLabel("Cooling temperature:");
		lblCoolingTemp.setBounds(10, 81, 165, 14);
		simulatedAnnealingInputPanel.add(lblCoolingTemp);
		
		JPanel localBeamInputPanel = new JPanel();
		InputPanel.add(localBeamInputPanel, "name_24578346534534");
		localBeamInputPanel.setLayout(null);
		
		JLabel lblLocalBeam = new JLabel("Local Beam");
		lblLocalBeam.setBounds(138, 5, 103, 14);
		localBeamInputPanel.add(lblLocalBeam);
		
		JLabel lblNumberOf = new JLabel("Number of States:");
		lblNumberOf.setBounds(10, 30, 165, 14);
		localBeamInputPanel.add(lblNumberOf);
		
		lnumOfStates = new JTextField();
		lnumOfStates.setColumns(10);
		lnumOfStates.setBounds(10, 50, 148, 20);
		localBeamInputPanel.add(lnumOfStates);
		
		JPanel geneticAlgorithmPanel = new JPanel();
		InputPanel.add(geneticAlgorithmPanel, "name_325443541243");
		geneticAlgorithmPanel.setLayout(null);
		
		JLabel lblGeneticAlgorithm = new JLabel("Genetic Algorithm");
		lblGeneticAlgorithm.setBounds(122, 5, 125, 14);
		geneticAlgorithmPanel.add(lblGeneticAlgorithm);
		
		JLabel lblPercentOf = new JLabel("Percent of Elitism:");
		lblPercentOf.setBounds(10, 89, 165, 14);
		geneticAlgorithmPanel.add(lblPercentOf);
		
		gElitism = new JTextField();
		gElitism.setColumns(10);
		gElitism.setBounds(10, 109, 148, 20);
		geneticAlgorithmPanel.add(gElitism);
		
		JLabel lblMutationProbability = new JLabel("Mutation Probability:");
		lblMutationProbability.setBounds(10, 140, 165, 14);
		geneticAlgorithmPanel.add(lblMutationProbability);
		
		gMutation = new JTextField();
		gMutation.setColumns(10);
		gMutation.setBounds(10, 160, 148, 20);
		geneticAlgorithmPanel.add(gMutation);
		
		JLabel lblCrossing = new JLabel("Crossover Probability:");
		lblCrossing.setBounds(168, 89, 165, 14);
		geneticAlgorithmPanel.add(lblCrossing);
		
		gCrossover = new JTextField();
		gCrossover.setColumns(10);
		gCrossover.setBounds(168, 109, 148, 20);
		geneticAlgorithmPanel.add(gCrossover);
		
		JLabel lblNumberOfGenerations = new JLabel("Number of Generations:");
		lblNumberOfGenerations.setBounds(168, 140, 165, 14);
		geneticAlgorithmPanel.add(lblNumberOfGenerations);
		
		gNumGen = new JTextField();
		gNumGen.setColumns(10);
		gNumGen.setBounds(168, 160, 148, 20);
		geneticAlgorithmPanel.add(gNumGen);
		
		JLabel lblSizeOfA = new JLabel("Size of a Single Generation:");
		lblSizeOfA.setBounds(10, 38, 178, 14);
		geneticAlgorithmPanel.add(lblSizeOfA);
		
		gSingleGen = new JTextField();
		gSingleGen.setColumns(10);
		gSingleGen.setBounds(10, 58, 148, 20);
		geneticAlgorithmPanel.add(gSingleGen);
		
//RADIO BUTTONS to get the input 		
		JRadioButton btnHillClimbing = new JRadioButton("Hill Climbing");
		JRadioButton btnSimulated = new JRadioButton("Simulated Annealing");
		JRadioButton btnGenetic = new JRadioButton("Genetic Algorithm");
		btnGenetic.setForeground(new Color(0, 51, 255));
		JRadioButton btnLocalBeam = new JRadioButton("Local Beam");
		btnLocalBeam.setForeground(new Color(0, 51, 255));
		
		btnHillClimbing.setBackground(Color.LIGHT_GRAY);
		btnHillClimbing.setBounds(6, 7, 150, 23);
		/**
		 * 
		 * When button of Hill Climbing gets clicked all the others deactivate
		 * 
		 * 
		 */
		btnHillClimbing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numQueens.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				btnSimulated.setSelected(false);
				btnGenetic.setSelected(false);
				btnLocalBeam.setSelected(false);
				numberOfButtonOption = 1;
				InputPanel.removeAll();
				InputPanel.add(hillClimbingInputPanel);
				InputPanel.revalidate();
				InputPanel.repaint();
			}
		});
		buttonPanel.add(btnHillClimbing);
		btnSimulated.setBackground(Color.LIGHT_GRAY);
		btnSimulated.setBounds(6, 33, 150, 23);
		/**
		 * 
		 * When button of Simulated Anealing gets clicked all the others deactivate
		 * 
		 * 
		 */
		btnSimulated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numQueens.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				btnHillClimbing.setSelected(false);
				btnGenetic.setSelected(false);
				btnLocalBeam.setSelected(false);
				numberOfButtonOption = 2;
				InputPanel.removeAll();
				InputPanel.add(simulatedAnnealingInputPanel);
				InputPanel.revalidate();
				InputPanel.repaint();
			}
		});
		buttonPanel.add(btnSimulated);
		
		btnLocalBeam.setBackground(Color.LIGHT_GRAY);
		btnLocalBeam.setBounds(180, 7, 143, 23);
		/**
		 * 
		 * When button of Local Beam gets clicked all the others deactivate
		 * And the number of queens input gets BLUE
		 * 
		 * 
		 */
		btnLocalBeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numQueens.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
				btnSimulated.setSelected(false);
				btnGenetic.setSelected(false);
				btnHillClimbing.setSelected(false);
				numberOfButtonOption = 3;
				InputPanel.removeAll();
				InputPanel.add(localBeamInputPanel);
				InputPanel.revalidate();
				InputPanel.repaint();
			}
		});
		buttonPanel.add(btnLocalBeam);
		
		btnGenetic.setBackground(Color.LIGHT_GRAY);
		btnGenetic.setBounds(180, 33, 143, 23);
		/**
		 * 
		 * When button of Genetics Algorithm gets clicked all the others deactivate
		 * And the number of queens input gets BLUE
		 * 
		 * 
		 */
		btnGenetic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numQueens.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
				btnSimulated.setSelected(false);
				btnHillClimbing.setSelected(false);
				btnLocalBeam.setSelected(false);
				numberOfButtonOption = 4;
				InputPanel.removeAll();
				InputPanel.add(geneticAlgorithmPanel);
				InputPanel.revalidate();
				InputPanel.repaint();
			}
		});
		buttonPanel.add(btnGenetic);
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Solve");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * 
			 * Solve Button - Solves the matrix and draws it GUI
			 * 
			 * Will collect all inputs
			 * Checks if the inputs are empty
			 * Update the number of queens
			 * Uses the correct algorithm by number
			 * Removes all components and redraws them again with refreshed values
			 * 
			 * 
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(!numQueens.getText().isEmpty()){
					  numberOfQueensNxN = Integer.parseInt(numQueens.getText());//Saves the number of queens input
				  }
				  
				switch(numberOfButtonOption) {//Switch case to get the button value
				   case 1 :
					   if(btnHillClimbing.isSelected()) { 
						   queenArray = HillClimbing.solve(numberOfQueensNxN,queenArray);
					   }
				      break; 
				   case 2 :
					   if(btnSimulated.isSelected() && !sAStartTemp.getText().isEmpty() && !sACoolTemp.getText().isEmpty()) {   
						   try {
							double t1 =  (double) Double.parseDouble(sAStartTemp.getText());
							   double t2 = (double) Double.parseDouble(sACoolTemp.getText());
							   queenArray = SimulatedAnnealing.solve(numberOfQueensNxN,t1,t2,queenArray);
						} catch (NumberFormatException e) {
							System.out.println("Digits Only");
						}
					   }
				      break;
				   case 3:
					   if(btnLocalBeam.isSelected() && !lnumOfStates.getText().isEmpty() && !numQueens.getText().isEmpty()) {
						   try {
							queenArray = LocalBeamSearch.solve(numberOfQueensNxN, Integer.parseInt(lnumOfStates.getText()));
						} catch (NumberFormatException e) {
							System.out.println("Digits Only");
						}
					   }
				      break; 
				   case 4 :
					   if(btnGenetic.isSelected() && !gSingleGen.getText().isEmpty() && !gMutation.getText().isEmpty() && !gCrossover.getText().isEmpty() && !gNumGen.getText().isEmpty() && !numQueens.getText().isEmpty()) {
						   try {
							   GeneticAlgorithm g = new GeneticAlgorithm();
							   queenArray = g.solve(numberOfQueensNxN,
									   Integer.parseInt(gSingleGen.getText()), 
									   Double.parseDouble(gMutation.getText()),
									   Integer.parseInt(gNumGen.getText()),
									   Double.parseDouble(gCrossover.getText()),
									   Double.parseDouble(gElitism.getText()));
						} catch (NumberFormatException e) {
							System.out.println("Digits Only");
						}
					   }
				      break; // optional
				}
				
				heuristicValue=Integer.toString(NxNQueenHeuristic.getHeuristicCost(queenArray));
				Interface.removeAll();
				Interface.add(mainInfo(Interface));
				Interface.add(drawChessBoard(queenArray));
				Interface.revalidate();
				Interface.repaint();
			}
		});
		btnNewButton.setBounds(140, 394, 89, 23);
		panel.add(btnNewButton);

		JLabel heuristic = new JLabel();
		heuristic.setForeground(new Color(0, 0, 51));
		heuristic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		heuristic.setBounds(156, 87, 47, 31);
		heuristic.setText(heuristicValue);
		panel.add(heuristic);
		
		JButton createTable = new JButton("Create Table");
		createTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!numQueens.getText().isEmpty()) {
					numberOfQueensNxN = Integer.parseInt(numQueens.getText());
					queenArray = generateRandomQueenArray(numberOfQueensNxN);
					heuristicValue=Integer.toString(NxNQueenHeuristic.getHeuristicCost(queenArray) );
					Interface.removeAll();
					Interface.add(mainInfo(Interface));
					Interface.add(drawChessBoard(queenArray));
					Interface.revalidate();
					Interface.repaint();
				}
			}
		});
		createTable.setBounds(208, 63, 114, 23);
		panel.add(createTable);
		
		numQueens = new JTextField();
		numQueens.setColumns(10);
		numQueens.setBounds(140, 64, 56, 20);
		numQueens.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panel.add(numQueens);
		
		JLabel label = new JLabel("Number of Queens:");
		label.setBounds(21, 67, 109, 14);
		panel.add(label);
		
		JLabel lblCurrentHeuristicCost = new JLabel("Current heuristic cost:");
		lblCurrentHeuristicCost.setBounds(21, 95, 129, 14);
		panel.add(lblCurrentHeuristicCost);
	
		return panel;
	}
	
	
	
	public JLabel drawQueens(int x, int y,int elementSize) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\PC\\eclipse-workspace\\AI - assignment 1\\src\\queen.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(elementSize, elementSize, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		JLabel image = new JLabel();
		image.setIcon(imageIcon);
		image.setBounds(x*elementSize,y*elementSize, elementSize, elementSize);
		
		return image;
	}
	
	
	
	public int[] generateRandomQueenArray(int n) {
		
		int[] arrayBoard = new int[n];
	         
	    for(int i=0;i<n;i++) {
	    	arrayBoard[i] =  0 + (int)(Math.random() * n);
	    }

	    System.out.println(Arrays.toString(arrayBoard));
	    return arrayBoard;
	}
	
	
	
	public JPanel drawChessBoard(int[] queenPositionArray) {
		
		
		elements = new JPanel[numberOfQueensNxN*numberOfQueensNxN];
		
		JPanel board = new JPanel();
		board.setBounds(159, 11, 400, 400);
		int elementSize = (int) board.getHeight()/numberOfQueensNxN;
		board.setBounds(10, 11, elementSize*numberOfQueensNxN, elementSize*numberOfQueensNxN);//This is to remove any defect on the end of the board due to loss of decimals.
		
		board.setBackground(Color.WHITE);
		board.setLayout(null);
		

		/*
		for(int z=0;z<n;z++) {
			queensXY[z] = drawQueens(z*elementSize,queenPositionArray[z],elementSize);
			board.add(queensXY[z]);
			board.revalidate();
			board.repaint();
		}
		*/
		
		for(int z=0;z<numberOfQueensNxN;z++) {
			board.add(drawQueens(z,queenPositionArray[z],elementSize));
		}
		
		boolean w = false;
		Color c;
		
		for(int i = 0; i<numberOfQueensNxN;i++) {
			for(int j = 0; j < numberOfQueensNxN;j++) {
			elements[i] = new JPanel();
			elements[i].setBounds(i*elementSize, j*elementSize, elementSize, elementSize);
			
			if(w ) {
				c = Color.LIGHT_GRAY;
				w = false;
			}
			else {
				c = Color.WHITE;
				w = true;
			}
			elements[i].setBackground(c);
			board.add(elements[i]);
			}
			if(w && (numberOfQueensNxN%2)==0) {
				w=false;
				
			}
			else if(!w && (numberOfQueensNxN%2)==0){
				w=true;
			}
		}
		board.revalidate();
		board.repaint();
		return board;
	}
	
}
