package certFeatVectSimple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class singleVectSubstringTest {

	//attempt to make feature vectors out of single days
	
	//first thing we're trying to do is make a feature vector, single day, let's say we look over only a 1000 records per file for now
	
	// Id to work on:
	// DNS1758
	// ANC1950
	// an insider ACM2278
	// tested for initial fvects SDH2394
	/*
		Creation of the sequences of actions
		actions=["logon","device","http","email","file"]
	0 logon
 	1 logoff
	2 connect
	3 disconnect
	4 WWW Download
	5 WWW Upload
	6 WWW Visit
	7 Send
	8 View
	9 open
	10 write
	11 copy
	12 delete
	 */
	
	//check this static stuff!
	private static Integer[] vectorUpdate(String userId, String fName, String[] fileNames, String lineInput, HashMap<String, Integer[]> featureVectors) {
		Integer[] fVect = featureVectors.get(userId);
		String activity = null;
		if (fVect == null) {
			fVect = new Integer[13];
			Arrays.fill(fVect, 0);
		}
		
		StringTokenizer line = new StringTokenizer(lineInput,",");
		// dirty logon parsing
		if( fName.equals(fileNames[0])) {
			if (lineInput.contains("Logon"))fVect[0]+=1;
			else fVect[1]+=1;
			return fVect;
		}
		//dirty device parsing, super hacky with activity choice, find a fix!
		else if( fName.equals(fileNames[1])) {
			if (lineInput.contains("Disconnect"))fVect[3]+=1;
			else fVect[2]+=1;
			return fVect;
		}
		// dirty http parsing
		else if( fName.equals(fileNames[2])) {
			if (lineInput.contains("WWW Download"))fVect[4]+=1;
			else if (lineInput.contains("WWW Upload"))fVect[5]+=1;
			else fVect[6]+=1;
			return fVect;
		}
		// dirty email parsing, to solve later
		else if( fName.equals(fileNames[3])) {
			if (lineInput.contains("Send"))fVect[7]+=1;
			else if (lineInput.contains("View"))fVect[8]+=1;
			return fVect;
		}
		// dirty file parsing
		else if( fName.equals(fileNames[4])) {
			if (lineInput.contains("File Open"))fVect[9]+=1;
			else if (lineInput.contains("File Write"))fVect[10]+=1;
			else if (lineInput.contains("File Copy"))fVect[11]+=1;
			else if (lineInput.contains("File Delete"))fVect[12]+=1;
			return fVect;
		}
		else	return null;
	}
	
	private static String printfVect(Integer[] fVect) {
		String output ="";
		for (Integer i : fVect) {
			output += i +",";
		}
		output = output.substring(0, output.length() - 1);
		//output+="]";
		return output;
	}
	
	
	
	public static void main(String[] args) {
		
		String[] fileNames = {"logon","device","http","email","file"};
		String filep1 = "/D:/r6.2/";
		//String filep2 = ".csv";
		String filep2 = "Shaved.csv";
		String lineInput = null;
		String filename = null;
		String testId = "ACM2278";
		String sampleId = null;
		String timestamp = null;
		String userId = null;
		String machine = null;
		String dateInput = "01/02/2010";
		String[] dateParts = null;
		
		
		HashMap<String, Integer[]> featureVectors = new HashMap<String, Integer[]>();
		
		for (String fName : fileNames)
		{
			try {
		        FileReader fileReader = new FileReader(filep1+fName+filep2);
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        //initial readline to get rid of schema line
		        bufferedReader.readLine();
		        //FileWriter fileOutput = new FileWriter("/D:/r6.2/httpr62Sample.json");
		        //commented out i break, might take some time. started 10:54
		        //int i=0;

		        while((lineInput = bufferedReader.readLine()) != null) {
		        	// magic happens
		        	StringTokenizer line = new StringTokenizer(lineInput,",");
		        	sampleId = line.nextToken();
		        	timestamp = line.nextToken();
		        	userId = line.nextToken();
		        	
		        	//new bits
		        	dateParts = timestamp.split(" ");
		        	dateInput = dateParts[0];
		        	
		        	
		        	if( userId.equals(testId)) {
		        		featureVectors.put(dateInput,vectorUpdate(dateInput,fName,fileNames,lineInput,featureVectors));
		        		//System.out.println(""+dateParts[0]+" "+printfVect(featureVectors.get(dateInput)));
		        	}
		        	
		        	/*
		        	if( userId.equals(testId) && dateParts[0].equals(dateInput)) {
		        		featureVectors.put(dateInput,vectorUpdate(userId,fName,fileNames,lineInput,featureVectors));
		        	}
		        	else if ( userId.equals(testId) && !dateParts[0].equals(dateInput)) {
		        		
		        		//one line for debugging purposes
		        		//System.out.println(""+dateInput+" "+printfVect(featureVectors.get(dateInput)));
		        		
		        		dateInput = dateParts[0];
		        		featureVectors.put(dateInput,vectorUpdate(userId,fName,fileNames,lineInput,featureVectors));
		        	}
		        	*/
		        	//i++;
		        	//if(i==100000) {break;}
		        }   

		        bufferedReader.close();
		    }
		    catch(FileNotFoundException e) {  
		    	System.out.println("File not found!");
		    }
		    catch(IOException e) {
		        e.printStackTrace();
		    }
		}
		
		for (String name: featureVectors.keySet()){
			System.out.println(""+name+" "+printfVect(featureVectors.get(name)));
		}
		
		//file print bit
		try {
			FileWriter fileOutput = new FileWriter("/D:/r6.2/trialFVectShavSubstring.csv");
			for (String name: featureVectors.keySet()){
				fileOutput.write(""+name+","+printfVect(featureVectors.get(name))+"\n");
				fileOutput.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//System.out.println(printfVect(featureVectors.get(testId)));
		
	}
	//hacky copypaste for reference
	/*
	String fileName = "/D:/r6.2/http.csv";
    String lineInput = null;
    ZFCERTr62Parser dummy = new ZFCERTr62Parser();

    try {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();
        FileWriter fileOutput = new FileWriter("/D:/r6.2/httpr62Sample.json");
        int i=0;

        while((lineInput = bufferedReader.readLine()) != null) {
        	fileOutput.write(dummy.verseHttpConversion(lineInput).toString()+"\n");
        	fileOutput.flush();
        	i++;
        	if(i==100) {break;}
        }   

        bufferedReader.close();
        fileOutput.close();
    }
    catch(FileNotFoundException e) {  
    	System.out.println("File not found!");
    }
    catch(IOException e) {
        e.printStackTrace();
    }
	*/
}
