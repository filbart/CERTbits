package logonCERTsimpleParse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class toFileHttpTest {
	
	public static void main(String[] args) {
		
        String fileName = "/D:/r4.2/http.csv";

        String lineInput = null;
        simpleStringTest dummy = new simpleStringTest();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/r4.2/httpTestSample.json");
            int i=0;

            while((lineInput = bufferedReader.readLine()) != null) {
            	//System.out.println(dummy.verseConversion(lineInput).toString());
            	//fileOutput.write(dummy.verseConversion(lineInput).toJSONString());
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
	}

}
