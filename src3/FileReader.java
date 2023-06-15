import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	
	public static void fileReading(ArrayList<String[]> game, String a) throws FileNotFoundException{ 
	//simple method for read files...
	File txt1 = new File(a);
	Scanner txt = new Scanner(txt1);
	while (txt.hasNextLine()) {
	      String data = txt.nextLine();
	      String[] datarr;
	      datarr= data.split(" ");
	      game.add(datarr);
	    }  
		txt.close();
	

	
		
		
		
	
	
	}
}