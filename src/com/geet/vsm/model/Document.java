package com.geet.vsm.model;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Document  implements Comparable<Document>{
	
	protected String article="";
	Map<String, Double> hashTerms = null;
	public double scalarValue = 0.0;
	public double dotProduct = 0.0;
	public Document(String article) {
		this.article = article;
		setTerms(" ", true, true);
	}
	public String getArticle() {
		return article;
	}
	
	
	// return the terms in a document with term-frequency included
	private void setTerms(String delimiter, boolean isWithStopwordsRemoval, boolean isWithStemming) {
		hashTerms = new HashMap<String,Double>();
		StringTokenizer stringTokenizer = new StringTokenizer(getArticle(), delimiter, false);
		while (stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken().toLowerCase();
			if (isWithStemming) {
				Stemmer stemmer = new Stemmer(token);
				stemmer.stem();
				token = stemmer.toString();
			}
			if (isWithStopwordsRemoval && StopWords.isStopword(token)) {
				continue;
			}
			if (hashTerms.containsKey(token)) {
				hashTerms.put(token, hashTerms.get(token)+1.00);
				System.out.println(hashTerms.get(token));
			} else {
				hashTerms.put(token, 1.00);		
				System.out.println(hashTerms.get(token));
			}
		}
	}
	
	// calculate the cosine similarity among two documents
	// set the cosine similarity value to its @dotProduct value
	public void calculateDotProduct(Document document){
		double dotProduct = 0.0;
		for (String key : hashTerms.keySet()) {
			if (document.hashTerms.containsKey(key)) {
			dotProduct += hashTerms.get(key)*document.hashTerms.get(key);
			}
		}
		dotProduct = dotProduct/(getValue()*document.getValue());
	}
	
	// the scalar value of a document vector
	private double getValue(){
		double value = 0.0;
		for (String key : hashTerms.keySet()) {
			value += hashTerms.get(key) * hashTerms.get(key);
		}
		return Math.sqrt(value);
	}
	
	@Override
	public String toString() {
		return article;
	}
	
	@Override
	public int compareTo(Document document) {
		if (dotProduct >= document.dotProduct) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * @return true when the two document's cosine similarity is zero 
	 */
	public boolean isSameDocument(Document document){
		// if the cosine similarity is less than 0.01 then the documents are same 
		calculateDotProduct(document);
		if (Math.abs(dotProduct-document.dotProduct)<=0.01) {
			return true;
		} else {
			return false;
		}
	}
	public String toStringWithTermsWeight(){
		String str ="";
		for (String key : hashTerms.keySet()) {
			str += key+","+hashTerms.get(key)+"\n";
		}
		return str;
	}
	
}
