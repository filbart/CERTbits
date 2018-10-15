package logonCERTsimpleParse;

import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;



public class ZFCERTr62Parser {

	String output;
	String indexName = "CERT_r6.2";
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
	
	public ZFCERTr62Parser() {
		indexName = "CERT_r6.2";
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
		this.setR("");
		// can add more, should suffice for now
	}
	
	//auto-generated getters and setters
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
		// 01/02/2010 06:49:00 what we get
		// 2018-03-02T13:01:47.962Z what we want
	}
	
	//possibly redundant, changing names of some actions to fit with overlapping activities in ZF samples where possible
	public String actionConversion(String action) {
		if(action.equals("Logon"))return "user logged on";
		else if(action.equals("Logoff"))return "user logged off";
		else if(action.equals("Connect"))return "new drive mounted";
		else if(action.equals("Disconnect"))return "drive disconnected";
		else return "action unknown";
	}
	
	//might need an update, not always makes sense!
	public String fileDestinationCheck(boolean toDrive, boolean fromDrive) {
		if(toDrive && !fromDrive)return "machine -> removable";
		else if(!toDrive && fromDrive)return "removable -> machine";
		else if(!toDrive && !fromDrive)return "machine -> machine";
		else return null;
	}
	
	public JSONObject logEntryWrap(JSONObject logEntry) {
		logEntry.put("_index", getIndexName());
    	logEntry.put("_type", getTypeName());
    	logEntry.put("_id", getIdName());
    	logEntry.put("_score", getScoreName());
    	return logEntry;
	}
	
	public JSONObject entrySourceWrap(JSONObject entrySource) {
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
    	return entrySource;
	}
	
	public JSONObject verseFileConversion(String input) {
		//setup and variable reset
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
    	
		//event unique line parsing
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	String fileInfo = line.nextToken();
    	setAc(line.nextToken());
    	setExt("."+fileInfo.split("\\.")[1]);
    	setAp("explorer.exe"); // added as a placeholder, no info in the set but present in ZF data
    	setFile(fileInfo.split("\\\\")[fileInfo.split("\\\\").length-1]);
    	boolean toDrive = Boolean.parseBoolean(line.nextToken());
    	boolean fromDrive = Boolean.parseBoolean(line.nextToken());
    	setR(fileDestinationCheck(toDrive, fromDrive));
    	
    	//creating JSON object
    	logEntryWrap(logEntry);
    	logEntry.put("_source", entrySourceWrap(new JSONObject()));    	
		return logEntry;
	}
	
	public JSONObject verseHttpConversion(String input) {
		//setup and variable reset
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
    	
		//event unique line parsing
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	String url = line.nextToken();
    	setAc(line.nextToken());
    	setR(url);
    	
    	//creating JSON object
    	logEntryWrap(logEntry);
    	logEntry.put("_source", entrySourceWrap(new JSONObject()));    	
		return logEntry;
	}
	
	public JSONObject verseMailConversion(String input) {
		//setup and variable reset
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",",true);
		
		//event unique line parsing
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
    	//here we have the difference between r6.2 and r4.2 formats
    	String ac = line.nextToken();
    	if(ac.equals(",")) {
    		ac="unknown";
    	}else {
    		line.nextToken();
    	}
    	setAc(ac);
    	//disregarding size, attachments and content
    	setR(mailFrom+" -> "+"To:"+mailTo+" CC:"+mailCc+" Bcc:"+mailBcc);
    
    	//creating JSON object
    	logEntryWrap(logEntry);
    	logEntry.put("_source", entrySourceWrap(new JSONObject()));    	
		return logEntry;
	}
	
	public JSONObject verseDeviceConversion(String input) {
		//setup and variable reset
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
		
		//event unique line parsing
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	String fileInfo = line.nextToken(); //this contains the drive file tree, ignoring this information for now!
    	if(fileInfo.equals("Connect"))setAc(actionConversion(fileInfo));
    	else if(fileInfo.equals("Disconnect"))setAc(actionConversion(fileInfo));
    	else setAc(actionConversion(line.nextToken()));
    	
    	//creating JSON object
    	logEntryWrap(logEntry);
    	logEntry.put("_source", entrySourceWrap(new JSONObject()));    	
		return logEntry;
	}
	
	public JSONObject wrappedConversion(String input) {
		//setup and variable reset
		JSONObject logEntry = new JSONObject();
		resetVariables();
		StringTokenizer line = new StringTokenizer(input,",");
		
		//event unique line parsing
    	setIdName(line.nextToken());
    	setD(dateConversion(line.nextToken()));
    	setU(line.nextToken());
    	setM(line.nextToken());
    	setAc(actionConversion(line.nextToken()));
    	
    	//creating JSON object
    	logEntryWrap(logEntry);
    	logEntry.put("_source", entrySourceWrap(new JSONObject()));    	
		return logEntry;
	}
	
	public JSONObject choiceWrap(String input) {
		String content[]=input.split(",", 2);
		System.out.println(content[0]);
		System.out.println(content[1]);
		return choiceConversion(content[1],content[0]);
	}
	
	public JSONObject choiceConversion(String input, String type) {
	if(type.equals("logon"))return wrappedConversion(input);
	else if(type.equals("device"))return verseDeviceConversion(input);
	else if(type.equals("file"))return verseFileConversion(input);
	else if(type.equals("email"))return verseMailConversion(input);
	else if(type.equals("http"))return verseHttpConversion(input);
	else {
		System.out.println("Wrong type passed as a parameter! Within a string");
		return null;}
	}
	
}


// String result = yourString.replaceAll("[\\-\\+\\.\\^:,]","");