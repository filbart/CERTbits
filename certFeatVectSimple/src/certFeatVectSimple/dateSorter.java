package certFeatVectSimple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.StringTokenizer;

public class dateSorter {
	
	public static void main(String[] args) throws ParseException  {
		
		ArrayList<Date> dater=new ArrayList<>();
		SimpleDateFormat formatCert=new SimpleDateFormat("MM/dd/yyyy");
		String lineInput = null;
		String[] dateParts = null;
		String dateInput = null;
		
		/* reference
		Date date00=formatCert.parse("03/12/2001");
		Date date01=formatCert.parse("02/12/2001");
		Date date02=formatCert.parse("12/09/1999");
		Date date03=formatCert.parse("13/11/2016");
		
		dater.add(date00);
		dater.add(date01);
		dater.add(date02);
		dater.add(date03);
		Collections.sort(dater);
		dater.forEach(action-> System.out.println(formatCert.format(action)));
		*/
		
		try {
	        FileReader fileReader = new FileReader("/D:/r6.2/trialFVectShav.csv");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        FileWriter fileOutput = new FileWriter("/D:/r6.2/trialFVectShavSorted.csv");
	        
	        while((lineInput = bufferedReader.readLine()) != null) {
	        	dateParts = lineInput.split(",");
	        	dateInput = dateParts[0];
	        	Date date0 = formatCert.parse(dateInput);
	        	dater.add(date0);
	        }   

	        bufferedReader.close();
	        
	        Collections.sort(dater);
	        dater.forEach(action-> System.out.println(formatCert.format(action)));
	        dater.forEach(action-> {
				try {
					fileOutput.write(formatCert.format(action)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	        
				
	        
	        //fileOutput.write(shaveLine(lineInput,"http")+"\n");
	    }
	    catch(FileNotFoundException e) {  
	    	System.out.println("File not found!");
	    }
	    catch(IOException e) {
	        e.printStackTrace();
	    }
		
		
	}

}
