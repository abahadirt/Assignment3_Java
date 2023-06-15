
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) throws IOException {
		//all txt files converted arraylist of arrays.
	ArrayList<String[]> grid = new ArrayList<String[]>();           
	ArrayList<String[]> command = new ArrayList<String[]>();
	ArrayList<String[]> leaderboard = new ArrayList<String[]>();
	FileReader.fileReading(grid,args[0]);
	FileReader.fileReading(command,args[1]);
	FileReader.fileReading(leaderboard,"leaderboard.txt");
	
	PrintWriter writer = new PrintWriter("monitoring.txt", "UTF-8");
	FileWriter fw = new FileWriter("leaderboard.txt",true);
	PrintWriter pw = new PrintWriter(fw);
	
	String notice= "\nSelect coordinate or enter E to end the game: ";

	writer.println("Game grid:\n");
	writer.print(Game.gridPrinter(grid));
	
	for(int i=0; i<command.size();i++) {
		
		if(command.get(i).length==2) {			//checking for coordinates
			int row= Integer.parseInt(command.get(i)[0]);
			int col= Integer.parseInt(command.get(i)[1]);
			String str = grid.get(row)[col];
			writer.println(notice+row+" "+col+"\n");
			switch(str) {
			case "D":
				if(Game.leftDiagonalChecker(grid,row,col)) {      //direction checker methods returns true if can NOT find any triple match
					Game.rightDiagonalChecker(grid, row, col); 	
				}
				break;
			case "S":
				Game.horizontalChecker(grid, row, col);
				break;
			case "T":	
				Game.verticalChecker(grid, row, col);
				break;
			case "W":
				if(Game.verticalChecker(grid, row, col)) {
					if(Game.horizontalChecker(grid, row, col)) {
						if(Game.leftDiagonalChecker(grid,row,col)) {
							Game.rightDiagonalChecker(grid, row, col);	
						}
					}				
				}
				break;
			case "/":
				if(Game.rightDiagonalChecker(grid, row, col)) {	
				}
				break;
			case "-":
				Game.horizontalChecker(grid, row, col);
				break;
			case "+":
				if(Game.horizontalChecker(grid, row, col)) {
					Game.verticalChecker(grid, row, col);
				}
				break;
			case "\\":
				Game.leftDiagonalChecker(grid,row,col);
				break;
			case "|":
				Game.verticalChecker(grid, row, col);
				break;
		
			}
			if(str.equals(" ")) {                 
				writer.println("Please enter a valid coordinate");
			}
			else {            //dislocation and printing for grid
				Game.emptyChecker(grid);
				writer.print( Game.gridPrinter(grid));
				writer.println("\nScore: "+Game.partpoint+" points");
			}	
			
		}
		else if(command.get(i)[0].equals("E")) {      //ending the game
			writer.println(notice+"E");
			writer.println("\nTotal score: "+Game.point+" points");
			writer.println("\nEnter name: "+command.get(i+1)[0]);
			writer.println("\n"+Game.whoWins(leaderboard));
			writer.println("\nGood bye!");
			pw.print("\n"+command.get(i+1)[0]+" "+Game.point);
		}
	}
	
	
	pw.close();
	writer.close();
	}
}