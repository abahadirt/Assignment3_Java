import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	static int point=0;  // total point 
	static int partpoint=0;   // stage point

	
	
	
	public static void emptyChecker(ArrayList<String[]> grid) {    //grid dislocation 
		for(int i=grid.size()-2; -1<i; i--) {         
			for(int i2=0; i2<grid.get(i).length; i2++) {
				if(grid.get(i+1)[i2].equals(" ")) {
					for(int i3=i+1; 0<i3; i3--) {
						grid.get(i3)[i2] = grid.get(i3-1)[i2];
						grid.get(i3-1)[i2] = " ";
					}
				}
			}
		}
		for(int i=grid.size()-2; -1<i; i--) {         
			for(int i2=0; i2<grid.get(i).length; i2++) {
				if(grid.get(i+1)[i2].equals(" ")) {
					for(int i3=i+1; 0<i3; i3--) {
						grid.get(i3)[i2] = grid.get(i3-1)[i2];
						grid.get(i3-1)[i2] = " ";
					}
				}
			}
		}

	}
	
	
	public static String gridPrinter(ArrayList<String[]> grid) {      
		String str="";
		for(int i=0; i<grid.size(); i++) {         
			for(int i2=0; i2<grid.get(i).length; i2++) {
				str+=grid.get(i)[i2]+" ";
				if(i2==grid.get(i).length-1) {
					str+="\n";
				}
			}
		}
		return str;
	}
	
	
	
	
	
	public static boolean shapeChecker(String[] arr) {  //checking for matches... returns true if jewels matches.
		try {
			boolean math1 = (arr[1].equals("+")||arr[1].equals("/")||arr[1].equals("-")||arr[1].equals("\\")||arr[1].equals("|"));
			boolean math2 = (arr[2].equals("+")||arr[2].equals("/")||arr[2].equals("-")||arr[2].equals("\\")||arr[2].equals("|"));
			if(arr[0].equals("D")) {
				if(arr[1].equals("D")&& arr[2].equals("D")) {

					return true;
				}
			}
			else if(arr[0].equals("S")) {
				if(arr[1].equals("S")&& arr[2].equals("S")) {

					return true;
				}
			}
			else if(arr[0].equals("T")) {
				if(arr[1].equals("T") && arr[2].equals("T")) {

					return true;
				}
			}
			else if(arr[0].equals("W")) {

				if(arr[1].equals("W") && !(arr[2].equals(" ")||math2)) {  //ex: wwt
					return true;
				}
				else if(arr[1].equals(arr[2]) && !(arr[2].equals(" ")||math2)) {  //ex: www, wtt
					return true;
				}
				else if(arr[2].equals("W") && !(arr[1].equals(" ")||math1)){
					return true;
				}			
			}
			
			else if((arr[0].equals("+")||arr[0].equals("/")||arr[0].equals("-")||arr[0].equals("\\")||arr[0].equals("|"))) {
				if((math1 && math2)) {
					return true;
				}
			}

			
			return false;
			
		}
		catch(Exception e) {return false; }
		
	}
	
	
	
	
	/* 
	All the ..Checker methods in the below search for matches in the specified direction 
	and send them to the shapeChecker method and process them according to the jewel type.
	they returns false if jewel or symbol gets deleted.
	*/
		
	public static boolean horizontalChecker(ArrayList<String[]> grid,int row, int col) {
		partpoint=0;
		String[] match = new String[3];
		try { //4 direction
			match[0] = grid.get(row)[col];
			match[1] = grid.get(row)[col-1];
			match[2] = grid.get(row)[col-2];
			if(shapeChecker(match)) {
				pointCalculate(match);
				deleter(grid,row,col,row,col-1,row,col-2);
				return false;
			}
		}catch(Exception e){			}
		
		if(!shapeChecker(match)) {
			try { //6 direction
				match[0] = grid.get(row)[col];
				match[1] = grid.get(row)[col+1];
				match[2] = grid.get(row)[col+2];
				if(shapeChecker(match)) {
					pointCalculate(match);
					deleter(grid,row,col,row,col+1,row,col+2);
					return false;
				}
			}catch(Exception e){	
				
			}	
		}
		
		return true;
	}
	
	
	
	public static boolean leftDiagonalChecker(ArrayList<String[]> grid,int row, int col) {
		partpoint=0;
		String[] match = new String[3];
		try {     //1 direction
			match[0] = grid.get(row)[col];
			match[1] = grid.get(row-1)[col-1];
			match[2] = grid.get(row-2)[col-2];

			if(shapeChecker(match)) {
				pointCalculate(match);
				deleter(grid,row,col,row-1,col-1,row-2,col-2);
				return false;
			}
		}catch(Exception e){

		}
		if(!shapeChecker(match)) {
			try { //9 direction
				match[0] = grid.get(row)[col];
				match[1] = grid.get(row+1)[col+1];
				match[2] = grid.get(row+2)[col+2];

				if(shapeChecker(match)) {
					pointCalculate(match);
					deleter(grid,row,col,row+1,col+1,row+2,col+2);
					return false;
				}
			}catch(Exception e){	
				
			}	
		}		
		return true;
	}
	
	
	
	public static boolean rightDiagonalChecker(ArrayList<String[]> grid,int row, int col) {
		partpoint=0;
		String[] match = new String[3];
			try { //3 direction
				match[0] = grid.get(row)[col];
				match[1] = grid.get(row-1)[col+1];
				match[2] = grid.get(row-2)[col+2];

				if(shapeChecker(match)) {
					pointCalculate(match);
					deleter(grid,row,col,row-1,col+1,row-2,col+2);
					return false;
				}
			}catch(Exception e){	
				
			}		
		
		if(!shapeChecker(match)) {
			try { //7 direction
				match[0] = grid.get(row)[col];
				match[1] = grid.get(row+1)[col-1];
				match[2] = grid.get(row+2)[col-2];

				if(shapeChecker(match)) {
					pointCalculate(match);
					deleter(grid,row,col,row+1,col-1,row+2,col-2);
					return false;
				}
			}catch(Exception e){	
				
			}	
		}
		return true;
	}
		

	
	public static boolean verticalChecker(ArrayList<String[]> grid,int row, int col) {
		partpoint=0;
		String[] match = new String[3];
		try { //2 direction
			match[0] = grid.get(row)[col];
			match[1] = grid.get(row-1)[col];
			match[2] = grid.get(row-2)[col];
			if(shapeChecker(match)) {
				pointCalculate(match);
				deleter(grid,row,col,row-1,col,row-2,col);
				return false;
			}
		}catch(Exception e){			}
		
		if(!shapeChecker(match)) {
			try { //8 direction
				match[0] = grid.get(row)[col];
				match[1] = grid.get(row+1)[col];
				match[2] = grid.get(row+2)[col];
				if(shapeChecker(match)) {
					pointCalculate(match);
					deleter(grid,row,col,row+1,col,row+2,col);
					return false;
				}
			}catch(Exception e){	
				
			}	
		}
		
		
		return true;
	}
	
	
	
	
	
	
	public static void deleter(ArrayList<String[]> grid, int cor1, int cor2, int cor3, int cor4, int cor5, int cor6) {
		grid.get(cor1)[cor2] = " ";
		grid.get(cor3)[cor4] = " ";
		grid.get(cor5)[cor6] = " ";
	}
	
	public static void pointCalculate(String[] arr) {
		for(int i =0; i<arr.length;i++) {
			if(arr[i].equals("D")) {
				point+=30;
				partpoint+=30;
			}
			else if(arr[i].equals("S")) {
				point+=15;
				partpoint+=15;
			}
			else if(arr[i].equals("T")) {
				point+=15;
				partpoint+=15;
			}
			else if(arr[i].equals("W")) {
				point+=10;
				partpoint+=10;
			}
			else if(!arr[i].equals(" ")) {     //for math symbols
				point+=20;
				partpoint+=20;
			}
			
		}
		
	}
	
	public static String whoWins(ArrayList<String[]> leader) {
		int i;
		int temp1=0,temp2=0;
		String str= "Your rank is ";
		String[] tempArr = {"name", Integer.toString(point)};
		
		int[] pointsArr = new int[leader.size()+1];
		leader.add(tempArr);
		
		for(i=0; i<leader.size()-1;i++) {
			pointsArr[i] =Integer.parseInt( leader.get(i)[1]);
		}
		pointsArr[i]= point;
		Arrays.sort(pointsArr);

		try {         //prevents: if player ranked first or last, i+1 or i-1 index gives error.
			for(i=0; i<leader.size();i++) {
				if(pointsArr[i]==point) {      //finding new player's index
					for(int i2=0; i2<leader.size();i2++) {
						if(pointsArr[i+1]== Integer.parseInt(leader.get(i2)[1])) {       
							temp1= i2;
						}
					}
				}		
			}
		}
		catch(Exception e) {}
		
		try {
			for(i=0; i<leader.size();i++) {
				if(pointsArr[i]==point) {      
					for(int i2=0; i2<leader.size();i2++) {
						if(pointsArr[i-1]== Integer.parseInt(leader.get(i2)[1])) {
							temp2= i2;
						}
					}
				}		
			}
		}
		catch(Exception e) {}
		
		
		
		if(pointsArr[leader.size()-1]==point) {   //player ranked first
			str += "1/"+(pointsArr.length)+", your score is "+ (point-Integer.parseInt(leader.get(temp2)[1]))+
					" points higher than "+leader.get(temp2)[0];

		}
		else if(pointsArr[0]==point) {            //player ranked last
			str += (pointsArr.length-1)+"/"+(pointsArr.length)+", your score is "+ +(Integer.parseInt(leader.get(temp1)[1])-point)+
					" points lower than "+leader.get(temp1)[0];
		}
		else {                                    //player's rank is in the middle
		str+= (i-1)+"/"+(pointsArr.length)+", your score is "+(Integer.parseInt(leader.get(temp1)[1])-point)+
				" points lower than "+ leader.get(temp1)[0]+" and "+(point-Integer.parseInt(leader.get(temp2)[1]))+
				" points higher than "+leader.get(temp2)[0];
	}
		
		
		return str;
	}
	
	
	




}
