package com.geet.vsm.model;
public class BugReport{
	long ID;
	String summary = "";
	String description ="";
	int Surprising, Dormant, Blocker, Security, Performance, Breakage;
	
	protected String corpus="";
	
	public static String delimiter ="\\s|\\W";
	public BugReport(long ID, String summary,String description,int Surprising, int Dormant, int Blocker, int Security, int Performance, int Breakage) {
		this.ID = ID;
		this.summary = summary;
		this.description = description;
		this.Surprising=Surprising;
		this.Dormant = Dormant;
		this.Blocker = Blocker;
		this.Security = Security;
		this.Performance = Performance;
		this.Breakage = Breakage;
		setCorpus(true, true);
	}

	
	public long getID() {
		return ID;
	}


	// return the terms in a document with term-frequency included
	private void setCorpus(boolean isWithStopwordsRemoval, boolean isWithStemming) {
		String [] tokens = (summary+" "+description).split(delimiter);
		for (int i=0;i<tokens.length;i++) {
			String token = tokens[i];
			if (isWithStopwordsRemoval && StopWords.isStopword(token.toLowerCase())) {
				continue;
			}
			if (isWithStemming) {
				Stemmer stemmer = new Stemmer(token.toLowerCase());
				stemmer.stem();
				token = stemmer.toString();
			}
			corpus += token+" ";
		}
	}
	
	public String toCorpus(){
		return ID+","+corpus;
	}
	
	@Override
	public String toString() {
		return ID+","+summary+","+description;
	}
	
	public String toXML(){
		String xmlString ="";
		xmlString+="<report id=\""+ID+"\">\n";
		xmlString+="<corpus>";
		xmlString+=corpus;
		xmlString+="</corpus>\n";	
		xmlString+="<Surprising>"+Surprising+"</Surprising>\n"; 
		xmlString+="<Dormant>"+Dormant+"</Dormant>\n"; 
		xmlString+="<Blocker>"+Blocker+"</Blocker>\n";
		xmlString+="<Security>"+Security+"</Security>\n";
		xmlString+="<Performance>"+Performance+"</Performance>\n";
		xmlString+="<Breakage>"+Breakage+"</Breakage>\n";
		xmlString+="</report>\n";
		return xmlString;
	}
	
}
