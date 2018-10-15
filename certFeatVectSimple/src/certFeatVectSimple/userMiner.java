package certFeatVectSimple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class userMiner {
	
	public static void main(String[] args) {
		
		String lineInput = null;
		try {
			FileReader fileReader = new FileReader("/D:/r6.2/psychometric.csv");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			FileWriter fileOutput = new FileWriter("/D:/r6.2/usersTest.csv");
			//initial readline to get rid of schema line
			bufferedReader.readLine();
        
			 while((lineInput = bufferedReader.readLine()) != null) {
		        	// magic happens
		        	StringTokenizer line = new StringTokenizer(lineInput,",");
		        	line.nextToken(); // name
		        	fileOutput.write(line.nextToken()+"\n"); // userId
	        		fileOutput.flush();
			 }

        	
        }
		catch(FileNotFoundException e) {  
	    	System.out.println("File not found!");
	    }
		catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
		
	}

}

