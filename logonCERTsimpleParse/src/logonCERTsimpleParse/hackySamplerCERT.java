package logonCERTsimpleParse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class hackySamplerCERT {
public static void main(String[] args) {
		
        String fileName = "/D:/r6.2/logon.csv";
        String lineInput = null;
        ZFCERTr62Parser dummy = new ZFCERTr62Parser();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/r6.2/logonShort.csv");
            int i=0;

            while((lineInput = bufferedReader.readLine()) != null) {
            	fileOutput.write(lineInput+"\n");
            	fileOutput.flush();
            	i++;
            	if(i==101) {break;}
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
        /*
        fileName = "/D:/r6.2/device.csv";
        lineInput = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/r6.2/devicer62Sample.json");
            int i=0;

            while((lineInput = bufferedReader.readLine()) != null) {
            	fileOutput.write(dummy.verseDeviceConversion(lineInput).toString()+"\n");
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
        
        fileName = "/D:/r6.2/email.csv";
        lineInput = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/r6.2/emailr62Sample.json");
            int i=0;

            while((lineInput = bufferedReader.readLine()) != null) {
            	fileOutput.write(dummy.verseMailConversion(lineInput).toString()+"\n");
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
        
        fileName = "/D:/r6.2/file.csv";
        lineInput = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/r6.2/filer62Sample.json");
            int i=0;

            while((lineInput = bufferedReader.readLine()) != null) {
            	fileOutput.write(dummy.verseFileConversion(lineInput).toString()+"\n");
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
        
        fileName = "/D:/r6.2/logon.csv";
        lineInput = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/r6.2/logonr62Sample.json");
            int i=0;

            while((lineInput = bufferedReader.readLine()) != null) {
            	fileOutput.write(dummy.wrappedConversion(lineInput).toString()+"\n");
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
	

}
