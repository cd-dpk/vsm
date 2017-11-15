package com.geet.vsm.model;
public class BugReport{
	long ID;
	String summary = "";
	String description ="";
	protected String corpus="";
	
	public static String delimiter ="\\s";
	public BugReport(long ID, String summary,String description) {
		this.ID = ID;
		this.summary = summary;
		this.description = description;
		setCorpus(true, true);
	}
	
	public String getCorpus() {
		return corpus;
	}
	
	// return the terms in a document with term-frequency included
	private void setCorpus(boolean isWithStopwordsRemoval, boolean isWithStemming) {
		String [] tokens = (summary+" "+description).split(delimiter);
		for (int i=0;i<tokens.length;i++) {
			String token = tokens[i].toLowerCase();
			if (isWithStemming) {
				Stemmer stemmer = new Stemmer(token);
				stemmer.stem();
				token = stemmer.toString();
			}
			if (isWithStopwordsRemoval && StopWords.isStopword(token)) {
				continue;
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
		xmlString+="<report>";
		xmlString+="<id>";
		xmlString+=ID;
		xmlString+="</id>";	
		xmlString+="<corpus>";
		xmlString+=corpus;
		xmlString+="</corpus>";	
		xmlString+="</report>";
		return xmlString;
	}
	
}
