package logonCERTsimpleParse;

import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;



public class simpleStringTest {

	// id,date,user,pc,activity
	//String input ="{X1D9-S0ES98JV-5357PWMI},01/02/2010 06:49:00,NGF0157,PC-6056,Logon";
	String output;
	
	// {"_index":"CERT_r4.2","_type":"event","_id":"grabFromInput","_score":1,"_source":{"ext":"","mn":null,"ac":"user logged on","d":"2018-03-02T13:01:47.962Z","m":"uqP","ap":"","r":"","folder":[],"file":"","u":"acmeltd__sales1"}}
	String indexName = "CERT_r4.2";
	String typeName ="event";
	String idName; //id VAR
	String scoreName ="1";
	String ext =""; //extension
	String mn = "null";
	String ac; //user logged on OR user logged off VAR
	String d; //date VAR
	String m; //machine VAR
	String ap =""; //application
	String r="";
	String folder ="[]";
	String file ="";
	String u; //user VAR
	
	public simpleStringTest() {
		indexName = "CERT_r4.2";
		typeName ="event";
		idName = ""; //id VAR
		scoreName ="1";
		ext =""; //extension
		mn = "null";
		ac=""; //user logged on OR user logged off VAR
		d=""; //date VAR
		m=""; //machine VAR
		ap =""; //application
		r="";
		folder ="[]";
		file ="";
		u =""; //user VAR
	}
	
	public void resetVariables() {
		this.setIdName("");
		this.setExt("");
		this.setAc("");
		this.setAp("");
		this.setD("");
		this.setM("");
		this.setFile("");
		this.setU("");
		// can add more, should suffice for now
	}
			
    public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getAp() {
		return ap;
	}

	public void setAp(String ap) {
		this.ap = ap;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String dateConversion(String inDate) {
		String outDate;
		String day,month,year,hour,minute,sec;
		String[] dateParts = inDate.split(" ");
		String[] dayParts = dateParts[0].split("/");
		String[] hourParts = dateParts[1].split(":");
		day = dayParts[1];
		month = dayParts[0];
		year = dayParts[2];
		hour = hourParts[0];
		minute = hourParts[1];
		sec = hourParts[2];
		outDate=""+year+"-"+month+"-"+day+"T"+hour+":"+minute+":"+sec+".000Z";
		return outDate;
		// 01/02/2010 06:49:00
		// 2018-03-02T13:01:47.962Z
	}
	
	public String actionConversion(String action) {
		if(action.equals("Logon"))return "user logged on";
		else if(action.equals("Logoff"))return "user logged off";
		else if(action.equals("Connect"))return "new drive mounted";
		else if(action.equals("Disconnect"))return "drive disconnected";
		else return "action unknown";
	}
	
	//{"_index":"ev.usr.2018.03","_type":"event","_id":"Km5J6AdbBsHAhFqFX5DZfC","_score":1,"_source":{"ext":".doc","mn":null,"ac":"file written","d":"2018-03-02T13:08:39.170Z","num":5,"m":"uqP","ap":"explorer.exe","dt":["2018-03-02T13:08:39.170Z","2018-03-04T13:08:40.048Z","2018-03-05T09:08:44.693Z","2018-03-06T13:08:39.878Z","2018-03-07T09:08:46.335Z"],"r":"c:\\users\\engineer3\\dropbox\\plan1.doc","folder":["c:","users","engineer3","dropbox"],"file":"plan1.doc","u":"acmeltd__engineer3","id":"Km5J6AdbBsHAhFqFX5DZfC"}}
	//id,date,user,pc,filename,content
	//{L9G8-J9QE34VM-2834VDPB},01/02/2010 07:23:14,MOH0273,PC-6699,EYPC9Y08.doc,D0-CF-11-E0-A1-B1-1A-E1 during difficulty
	
	//represents copying information to a drive only!!!
	public JSONObject verseFileConversion(String input) {
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
    	
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	String fileInfo = line.nextToken();
    	setAc("file written");
    	setExt("."+fileInfo.split("\\.")[1]);
    	setAp("explorer.exe"); // added as a placeholder, no info in the set but present in ZF data
    	// no info about r
    	// no info about folder
    	setFile(fileInfo);
    	
    	//different token read for each event type
    	/*
    	System.out.println("id= "+getIdName());
    	System.out.println("date= "+getD());
    	System.out.println("user= "+getU());
    	System.out.println("machine= "+getM());
    	System.out.println("action= "+getAc());
    	*/
    	
    	//{"_index":"CERT_r4.2","_type":"event","_id":"grabFromInput","_score":1,"_source":{"ext":"","mn":null,"ac":"user logged on","d":"2018-03-02T13:01:47.962Z","m":"uqP","ap":"","r":"","folder":[],"file":"","u":"acmeltd__sales1"}}
    	
    	logEntry.put("_index", getIndexName());
    	logEntry.put("_type", getTypeName());
    	logEntry.put("_id", getIdName());
    	logEntry.put("_score", getScoreName());
    	
    	JSONObject entrySource = new JSONObject();
    	entrySource.put("ext",getExt());
    	entrySource.put("mn",getMn());
    	entrySource.put("ac",getAc());
    	entrySource.put("d",getD());
    	entrySource.put("m",getM());
    	entrySource.put("ap",getAp());
    	entrySource.put("r",getR());
    	entrySource.put("folder",getFolder());
    	entrySource.put("file",getFile());
    	entrySource.put("u",getU());
		
    	logEntry.put("_source", entrySource);
    	
		return logEntry;
	}
	
	public JSONObject verseHttpConversion(String input) {
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
    	
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	setAc("website access");
    	String url = line.nextToken();
    	
    	//different token read for each event type
    	/*
    	System.out.println("id= "+getIdName());
    	System.out.println("date= "+getD());
    	System.out.println("user= "+getU());
    	System.out.println("machine= "+getM());
    	System.out.println("action= "+getAc());
    	*/
    	
    	//{"_index":"CERT_r4.2","_type":"event","_id":"grabFromInput","_score":1,"_source":{"ext":"","mn":null,"ac":"user logged on","d":"2018-03-02T13:01:47.962Z","m":"uqP","ap":"","r":"","folder":[],"file":"","u":"acmeltd__sales1"}}
    	
    	logEntry.put("_index", getIndexName());
    	logEntry.put("_type", getTypeName());
    	logEntry.put("_id", getIdName());
    	logEntry.put("_score", getScoreName());
    	
    	JSONObject entrySource = new JSONObject();
    	entrySource.put("ext",getExt());
    	entrySource.put("mn",getMn());
    	entrySource.put("ac",getAc());
    	entrySource.put("d",getD());
    	entrySource.put("m",getM());
    	entrySource.put("ap",getAp());
    	entrySource.put("r",getR());
    	entrySource.put("folder",getFolder());
    	entrySource.put("file",getFile());
    	entrySource.put("u",getU());
    	entrySource.put("url",url);
		
    	logEntry.put("_source", entrySource);
    	
		return logEntry;
	}
	
	//id,date,user,pc,to,cc,bcc,from,size,attachments,content
	//{R3I7-S4TX96FG-8219JWFF},01/02/2010 07:11:45,LAP0338,PC-5758,Dean.Flynn.Hines@dtaa.com;Wade_Harrison@lockheedmartin.com,Nathaniel.Hunter.Heath@dtaa.com,,Lynn.Adena.Pratt@dtaa.com,25830,0,middle f2 systems 4 july techniques powerful destroyed who larger speeds plains part paul hold like followed over decrease actual training international addition geographically side able 34 29 such some appear prairies still 2009 succession yet 23 months mid america could most especially 34 off descend 2010 thus officially southward slope pass finland needed 2009 gulf stick possibility hall 49 montreal kick gulf
	
	public JSONObject verseMailConversion(String input) {
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",",true);
    	//all the line.nextToken are added to deal with adding delimiters to the token list, had to deal with empty entries somehow 
    	setIdName(line.nextToken());
    	line.nextToken();
    	setD(dateConversion(line.nextToken()));
    	line.nextToken();
    	setU(line.nextToken());
    	line.nextToken();
    	setM(line.nextToken());
    	
    	line.nextToken();
    	
    	String mailTo = line.nextToken();
    	if(mailTo.equals(",")) {
    		mailTo="";
    	}else {
    		line.nextToken();
    	}
    	String mailCc = line.nextToken();
    	if(mailCc.equals(",")) {
    		mailCc="";
    	}else {
    		line.nextToken();
    	}
    	String mailBcc = line.nextToken();
    	if(mailBcc.equals(",")) {
    		mailBcc="";
    	}else {
    		line.nextToken();
    	}
    	String mailFrom = line.nextToken();
    	if(mailFrom.equals(",")) {
    		mailFrom="";
    	}else {
    		line.nextToken();
    	}
    	String mailSize = line.nextToken();
    	if(mailSize.equals(",")) {
    		mailSize="";
    	}else {
    		line.nextToken();
    	}
    	String mailAttach = line.nextToken();
    	setAc("email");
    	
    	//different token read for each event type
    	/*
    	System.out.println("id= "+getIdName());
    	System.out.println("date= "+getD());
    	System.out.println("user= "+getU());
    	System.out.println("machine= "+getM());
    	System.out.println("action= "+getAc());
    	*/
    	
    	//{"_index":"CERT_r4.2","_type":"event","_id":"grabFromInput","_score":1,"_source":{"ext":"","mn":null,"ac":"user logged on","d":"2018-03-02T13:01:47.962Z","m":"uqP","ap":"","r":"","folder":[],"file":"","u":"acmeltd__sales1"}}
    	
    	logEntry.put("_index", getIndexName());
    	logEntry.put("_type", getTypeName());
    	logEntry.put("_id", getIdName());
    	logEntry.put("_score", getScoreName());
    	
    	JSONObject entrySource = new JSONObject();
    	entrySource.put("ext",getExt());
    	entrySource.put("mn",getMn());
    	entrySource.put("ac",getAc());
    	entrySource.put("d",getD());
    	entrySource.put("m",getM());
    	entrySource.put("ap",getAp());
    	entrySource.put("r",getR());
    	entrySource.put("folder",getFolder());
    	entrySource.put("file",getFile());
    	entrySource.put("u",getU());
    	entrySource.put("mailTo",mailTo);
    	entrySource.put("mailCc",mailCc);
    	entrySource.put("mailBcc",mailBcc);
    	entrySource.put("mailFrom",mailFrom);
    	entrySource.put("mailSize",mailSize);
    	entrySource.put("mailAttach",mailAttach);
		
    	logEntry.put("_source", entrySource);
    	
		return logEntry;
	}
	
	
	public JSONObject verseConversion(String input) {
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
		
    	
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	setAc(actionConversion(line.nextToken()));
    	
    	//different token read for each event type
    	/*
    	System.out.println("id= "+getIdName());
    	System.out.println("date= "+getD());
    	System.out.println("user= "+getU());
    	System.out.println("machine= "+getM());
    	System.out.println("action= "+getAc());
    	*/
    	
    	//{"_index":"CERT_r4.2","_type":"event","_id":"grabFromInput","_score":1,"_source":{"ext":"","mn":null,"ac":"user logged on","d":"2018-03-02T13:01:47.962Z","m":"uqP","ap":"","r":"","folder":[],"file":"","u":"acmeltd__sales1"}}
    	
    	logEntry.put("_index", getIndexName());
    	logEntry.put("_type", getTypeName());
    	logEntry.put("_id", getIdName());
    	logEntry.put("_score", getScoreName());
    	
    	JSONObject entrySource = new JSONObject();
    	entrySource.put("ext",getExt());
    	entrySource.put("mn",getMn());
    	entrySource.put("ac",getAc());
    	entrySource.put("d",getD());
    	entrySource.put("m",getM());
    	entrySource.put("ap",getAp());
    	entrySource.put("r",getR());
    	entrySource.put("folder",getFolder());
    	entrySource.put("file",getFile());
    	entrySource.put("u",getU());
		
    	logEntry.put("_source", entrySource);
    	
		return logEntry;
	}
	
	public static void main(String[] args) {
		//initialize test input, starting with login CERT entry
    	String input ="{X1D9-S0ES98JV-5357PWMI},01/02/2010 06:49:00,NGF0157,PC-6056,Logon";
    	simpleStringTest dummy = new simpleStringTest(); 
    	
    	System.out.println(dummy.verseConversion(input).toString());
    	System.out.println("{\"_index\":\"CERT_r4.2\",\"_type\":\"event\",\"_id\":\"grabFromInput\",\"_score\":1,\"_source\":{\"ext\":\"\",\"mn\":null,\"ac\":\"user logged on\",\"d\":\"2018-03-02T13:01:47.962Z\",\"m\":\"uqP\",\"ap\":\"\",\"r\":\"\",\"folder\":[],\"file\":\"\",\"u\":\"acmeltd__sales1\"}}"+"\n");
    	
    	//test printout for device mounting entry
    	input = "{J1S3-L9UU75BQ-7790ATPL},01/02/2010 07:21:06,MOH0273,PC-6699,Connect";
    	System.out.println(dummy.verseConversion(input).toString()+"\n");
    	//lack of "file" and "r" information in the CERT set, [resent in ZF format
    	
    	//test printout for file entry
    	input = "{L9G8-J9QE34VM-2834VDPB},01/02/2010 07:23:14,MOH0273,PC-6699,EYPC9Y08.doc,D0-CF-11-E0-A1-B1-1A-E1 during difficulty";
    	System.out.println(dummy.verseFileConversion(input).toString()+"\n");
    	// no information about "r" or "folder", and app is written as "explorer.exe" despite lack of info, just to fit ZF samples
    	// also assumed that that copying files to a drive are "file written"
    	// no info about num or dt 
    	
    	//html test, problems with a biiig file, but we are discarding the descriptions of websites anyway. ommitted last part of the line from csv, we don't want the description!
    	// {U7D0-K8FF04MI-4691ZHYP},01/02/2010  07:05:55,IRM0931,PC-7188,http://skype.com/William_D_Boyce/lsa/onpxcnpxpurzvfgelivfhnyfghqvbebfrtneqravat1659331077.jsp
    	input = "{U7D0-K8FF04MI-4691ZHYP},01/02/2010 07:05:55,IRM0931,PC-7188,http://skype.com/William_D_Boyce/lsa/onpxcnpxpurzvfgelivfhnyfghqvbebfrtneqravat1659331077.jsp";
    	System.out.println(dummy.verseHttpConversion(input).toString()+"\n");
    	    	
    	// email test
    	input = "{R3I7-S4TX96FG-8219JWFF},01/02/2010 07:11:45,LAP0338,PC-5758,Dean.Flynn.Hines@dtaa.com;Wade_Harrison@lockheedmartin.com,Nathaniel.Hunter.Heath@dtaa.com,,Lynn.Adena.Pratt@dtaa.com,25830,0,middle f2 systems 4 july techniques powerful destroyed who larger speeds plains part paul hold like followed";
    	System.out.println(dummy.verseMailConversion(input).toString()+"\n");
    	// add file read writes
    	
	}
}


