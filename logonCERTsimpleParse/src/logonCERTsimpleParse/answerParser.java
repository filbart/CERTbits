package logonCERTsimpleParse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class answerParser {

	
	public static void main(String[] args) {
		
		String lineInput = null;
		ZFCERTr62Parser dummy = new ZFCERTr62Parser();
        lineInput = "logon,{G3R8-G0BG91SZ-1111ZKPB},08/18/2010 21:47:42,ACM2278,PC-8431,Logon";
        //dummy.choiceWrap(lineInput);
        System.out.println(dummy.choiceWrap(lineInput).toString()+"\n");
        // had to chenge slashes due to invalid escape, how to deal with this?
        lineInput = "device,{F0N3-C5GS70QE-4575NRWF},08/18/2010 22:59:20,ACM2278,PC-8431,R:/;R:/52G6677;R:/782bxm8;R:/ACM2278,Connect";
        System.out.println(dummy.choiceWrap(lineInput).toString()+"\n");
        lineInput = "file,{L8U4-E1KC17HI-2295QTAV},08/19/2010 01:34:19,ACM2278,PC-8431,R:/52G6677/B7RGYJZC.jpg,File Open,False,True,The_Real_Story_About_DTAA,FF-D8 ";
        System.out.println(dummy.choiceWrap(lineInput).toString()+"\n");
        lineInput = "http,{E9K0-T0KL12CS-9706ONHN},08/19/2010 01:34:19,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/B7RGYJZC.jpg,WWW Upload,The_Real_Story_About_DTAA,FF-D8";
        System.out.println(dummy.choiceWrap(lineInput).toString()+"\n");
        lineInput = "email,{D1Z4-D4GW77FH-8911CRHT},02/07/2011 12:28:06,CMP2946,PC-9447,Ashely_L_Green@boeing.com,,,Chandler.Martin.Page@dtaa.com,Send,22529,,jobsearch,Ability to create innovative textures: Diffuse Spec, Normal, AO and Displacement Mapping. Have you ever described a bad date as having awkward user experience? CACI Named for Second Consecutive Year to CivilianJobs.com Most Valuable Employers (MVE) for Military This position requires, at minimum, a secret clearance, though some will require a top secret clearance."; 
        System.out.println(dummy.choiceWrap(lineInput).toString()+"\n");	
        
        String fileName = "/D:/answers/r6.2-1.csv";

        lineInput = null;
        

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            FileWriter fileOutput = new FileWriter("/D:/answers/answerTest.json");

            while((lineInput = bufferedReader.readLine()) != null) {
            	//System.out.println(dummy.verseConversion(lineInput).toString());
            	//fileOutput.write(dummy.verseConversion(lineInput).toJSONString());
            	fileOutput.write(dummy.choiceWrap(lineInput.replaceAll("\"", "")).toString()+"\n");
            	fileOutput.flush();

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
//device,"{F0N3-C5GS70QE-4575NRWF}","08/18/2010 22:59:20","ACM2278","PC-8431","R:\;R:\52G6677;R:\782bxm8;R:\ACM2278","Connect"
//file,"{L8U4-E1KC17HI-2295QTAV}","08/19/2010 01:34:19","ACM2278","PC-8431","R:\52G6677\B7RGYJZC.jpg","File Open","False","True","The_Real_Story_About_DTAA","FF-D8 "
//http,{E9K0-T0KL12CS-9706ONHN},08/19/2010 01:34:19,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/B7RGYJZC.jpg,WWW Upload,The_Real_Story_About_DTAA,FF-D8 
//email,"{D1Z4-D4GW77FH-8911CRHT}","02/07/2011 12:28:06","CMP2946","PC-9447","Ashely_L_Green@boeing.com","","","Chandler.Martin.Page@dtaa.com","Send","22529","","jobsearch","Ability to create innovative textures: Diffuse Spec, Normal, AO and Displacement Mapping. Have you ever described a bad date as having awkward user experience? CACI Named for Second Consecutive Year to CivilianJobs.com Most Valuable Employers (MVE) for Military This position requires, at minimum, a secret clearance, though some will require a top secret clearance."

/*
{"_index":"ai_alerts",
"_type":"ai_alert",
"_id":"AWOS3vR0Bzyi3BjvqJyh",
"_score":1,

"_source":{"confidence":0.25,
"explanation":"Why isn't this NORMAL? ",
"activityAlertKey":"1527177475050:ai:hragzyunii",
"triggerTime":"2018-05-24T15:57:55Z",
"severity":"Critical",
"ruleName":"AI",
"name":"filip_example_alerts",
"tags":["risky_filepath","new_user","risky_filetype"],
"fact":"",
"ftr":"",
"context":"",
"alertTime":"2018-02-24T18:47:04Z",
"events":[{"agentId":"1bV","userName":"zonefox-hq__j.graves","application":"evernote.exe","activity":"file downloaded","resource":"https://dfw25s13-in-f8.1e100.net -> d:\\evernote\\logs\\applog_2018-02-21.txt","time":"2018-02-24T18:47:04Z"}]}}
*/

/*
{"_index":"ai_alerts_event_relationship",
"_type":"alertrelationship",
"_id":"AWOS4b2KBzyi3BjvqKfv",
"_score":1,

"_source":{"activityAlertKey":"1527177657724:ai:gpauqfzmoy",
"triggerTime":"2018-05-24T16:00:57Z",
"agentId":"zW9",
"userName":"zonefox-hq__d.cronshaw",
"application":"",
"resource":"",
"activity":"user logged off"}}
 
*/

/*
logon,"{G3R8-G0BG91SZ-1111ZKPB}","08/18/2010 21:47:42","ACM2278","PC-8431","Logon"
device,"{F0N3-C5GS70QE-4575NRWF}","08/18/2010 22:59:20","ACM2278","PC-8431","R:\;R:\52G6677;R:\782bxm8;R:\ACM2278","Connect"
file,"{L8U4-E1KC17HI-2295QTAV}","08/19/2010 01:34:19","ACM2278","PC-8431","R:\52G6677\B7RGYJZC.jpg","File Open","False","True","The_Real_Story_About_DTAA","FF-D8 "
file,"{Z8S0-I9WK82HT-2702OXHW}","08/19/2010 01:37:20","ACM2278","PC-8431","R:\52G6677\B7RGYJZC.jpg","File Open","False","True","The_Real_Story_About_DTAA","FF-D8 "
file,"{M3U7-F9ZW51CX-5408MNTW}","08/19/2010 01:38:10","ACM2278","PC-8431","R:\52G6677\7QO6RIRR.txt","File Open","False","True","The_Real_Story_About_DTAA","33-4E-42-56 Samantabhadra (2nd century CE) and Siddhasena Divakara (3rd century CE) further fine-tuned Jain epistemology and logic by expounding on the concepts of anekantavada in proper form and structure. The importance and antiquity of anekantavada are also demonstrated by the fact that it formed the subject matter of Astinasti Pravada, the fourth part of the lost Purva that contained teachings of the {IAST|Tirtha?karas} prior to Mahavira. This ecumenical and irenical attitude, engendered by anekantavada, allowed modern Jain monks such as Vijayadharmasuri to declare: ""I am neither a Jain nor a Buddhist, a Vaisnava nor a Saivite, a Hindu nor a Muslim, but a traveler on the path of peace shown by the supreme soul, the God who is free from passion."" In recent times, Jainism influenced Gandhi, who advocated {IAST|ahi?sa} and satyagraha."
file,"{I1R8-Z2OT23MQ-3906SEYL}","08/19/2010 01:46:04","ACM2278","PC-8431","R:\ACM2278\QP8YHH52.zip","File Open","False","True","The_Real_Story_About_DTAA","50-4B-03-04-14 This does not deny the other attributes, qualities, modes and other aspects; they are just irrelevant from a particular perspective. He proclaimed that the duty of every individual is to determine what is personally true and act on that relative perception of truth."
device,"{E7T1-Z4BN74BJ-2108LJAD}","08/19/2010 05:23:05","ACM2278","PC-8431","","Disconnect"
logon,"{C0F6-J3LV50LO-8992SRMS}","08/19/2010 06:10:59","ACM2278","PC-8431","Logoff"
logon,"{N8G8-A3UJ42OL-2334MCCV}","08/24/2010 01:02:58","ACM2278","PC-8431","Logon"
device,"{R4V5-J1JK66OF-2014WDEN}","08/24/2010 03:24:16","ACM2278","PC-8431","R:\;R:\52G6677;R:\782bxm8;R:\ACM2278","Connect"
file,"{D2X2-X4AE75SO-9681RGTG}","08/24/2010 03:34:21","ACM2278","PC-8431","R:\52G6677\JMR2V1HC.txt","File Open","False","True","The_Real_Story_About_DTAA","30-51-46-4A In response to a friend's query on religious tolerance, he responded in the journal ""Young India - 21 Jan 1926"": The principle of anekantavada is the foundation of many Jain philosophical concepts. All the men claimed to understand and explain the true appearance of the elephant, but could only partly succeed, due to their limited perspectives. Just as a right-acting person respects the life of all beings, so a right-thinking person acknowledges the validity of all judgments."
file,"{S3M9-M7FP50TI-7818PRJO}","08/24/2010 03:43:48","ACM2278","PC-8431","R:\ACM2278\JGLCVL46.doc","File Open","False","True","The_Real_Story_About_DTAA","D0-CF-11-E0-A1-B1-1A-E1 The epistemology of anekantavada and syadvada also had a profound impact on the development of ancient Indian logic and philosophy. The origins of anekantavada can be traced back to the teachings of Mahavira (599-527 BCE), the 24th Jain {IAST|Tirthankara}. Mallisena uses the parable to argue that immature people deny various aspects of truth; deluded by the aspects they do understand, they deny the aspects they don't understand. Nayavada is a compound of two Sanskrit words-naya (""partial viewpoint"") and vada (""school of thought or debate""). This means recognizing all aspects of reality, not merely one or some aspects, as is done in non-Jain philosophies."""
file,"{F9N3-T3ZH14TZ-0696YIFM}","08/24/2010 03:48:51","ACM2278","PC-8431","R:\ACM2278\PXZQFYVF.jpg","File Open","False","True","The_Real_Story_About_DTAA","FF-D8 "
device,"{X2R4-W6PP65ON-1448BKTE}","08/24/2010 04:15:32","ACM2278","PC-8431","","Disconnect"
logon,"{D3U1-X0MN79ZE-0404OETN}","08/24/2010 04:20:39","ACM2278","PC-8431","Logoff"
http,{E9K0-T0KL12CS-9706ONHN},08/19/2010 01:34:19,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/B7RGYJZC.jpg,WWW Upload,The_Real_Story_About_DTAA,FF-D8 
http,{T2S1-D4NO90IT-7131JYCF},08/19/2010 01:37:20,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/B7RGYJZC.jpg,WWW Upload,The_Real_Story_About_DTAA,FF-D8 
http,{H1P7-O4WO11SJ-6718RXPK},08/19/2010 01:38:10,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/7QO6RIRR.txt,WWW Upload,The_Real_Story_About_DTAA,"33-4E-42-56 Samantabhadra (2nd century CE) and Siddhasena Divakara (3rd century CE) further fine-tuned Jain epistemology and logic by expounding on the concepts of anekantavada in proper form and structure. The importance and antiquity of anekantavada are also demonstrated by the fact that it formed the subject matter of Astinasti Pravada, the fourth part of the lost Purva that contained teachings of the {IAST|Tirtha?karas} prior to Mahavira. This ecumenical and irenical attitude, engendered by anekantavada, allowed modern Jain monks such as Vijayadharmasuri to declare: ""I am neither a Jain nor a Buddhist, a Vaisnava nor a Saivite, a Hindu nor a Muslim, but a traveler on the path of peace shown by the supreme soul, the God who is free from passion."" In recent times, Jainism influenced Gandhi, who advocated {IAST|ahi?sa} and satyagraha."
http,{S2J0-W5II68BI-5010XUHO},08/19/2010 01:46:04,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/QP8YHH52.zip,WWW Upload,The_Real_Story_About_DTAA,"50-4B-03-04-14 This does not deny the other attributes, qualities, modes and other aspects; they are just irrelevant from a particular perspective. He proclaimed that the duty of every individual is to determine what is personally true and act on that relative perception of truth."
http,{Q8M9-O4HT66UU-1449YTME},08/24/2010 03:34:21,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/JMR2V1HC.txt,WWW Upload,The_Real_Story_About_DTAA,"30-51-46-4A In response to a friend's query on religious tolerance, he responded in the journal ""Young India - 21 Jan 1926"": The principle of anekantavada is the foundation of many Jain philosophical concepts. All the men claimed to understand and explain the true appearance of the elephant, but could only partly succeed, due to their limited perspectives. Just as a right-acting person respects the life of all beings, so a right-thinking person acknowledges the validity of all judgments."
http,{N3Z9-Y2XU99QN-9915ELWN},08/24/2010 03:43:48,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/JGLCVL46.doc,WWW Upload,The_Real_Story_About_DTAA,"D0-CF-11-E0-A1-B1-1A-E1 The epistemology of anekantavada and syadvada also had a profound impact on the development of ancient Indian logic and philosophy. The origins of anekantavada can be traced back to the teachings of Mahavira (599-527 BCE), the 24th Jain {IAST|Tirthankara}. Mallisena uses the parable to argue that immature people deny various aspects of truth; deluded by the aspects they do understand, they deny the aspects they don't understand. Nayavada is a compound of two Sanskrit words-naya (""partial viewpoint"") and vada (""school of thought or debate""). This means recognizing all aspects of reality, not merely one or some aspects, as is done in non-Jain philosophies."""
http,{R7J1-H9MP31HE-0422QHDW},08/24/2010 03:48:51,ACM2278,PC-8431,http://wikileaks.org/Julian_Assange/assange/The_Real_Story_About_DTAA/Gur_Erny_Fgbel_Nobhg_QGNN1528513805.php/PXZQFYVF.jpg,WWW Upload,The_Real_Story_About_DTAA,FF-D8 

*/