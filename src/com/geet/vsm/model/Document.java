package com.geet.vsm.model;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Document  implements Comparable<Document>{
	
	protected String article="";
	Map<String, Term> hashTerms = null;
	public double value=0.0;
	public double dotProduct = 1.0;
	public Document(String article) {
		this.article = article;
	}
	public String getArticle() {
		return article;
	}
	public void getTerms(String delimiter, boolean isWithStopwordsRemoval, boolean isWithStemming) {
		hashTerms = new HashMap<String,Term>();
		StringTokenizer stringTokenizer = new StringTokenizer(getArticle(), delimiter, false);
		while (stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken().toLowerCase();
			
			if (isWithStemming) {
				// stem
			}
			if (isWithStopwordsRemoval && StopWords.isStopword(token)) {
				continue;	
			}
			if (hashTerms.containsKey(token)) {
				Term candidateTerm = hashTerms.get(token).toClone();
				candidateTerm.tf++;
				hashTerms.put(token, candidateTerm);
			} else {
				Term candidateTerm = new Term(token, 1);
				hashTerms.put(token, candidateTerm);
			}
		}
	}
	/**
	 * 
	 * @param document
	 * @return dotProduct dotProduct between two documents
	 */
	public void calculateDotProduct(Document document){
		double dotProduct = 0.0;
		for (String term : hashTerms.keySet()) {
			if (document.hashTerms.containsKey(term)) {
				dotProduct += hashTerms.get(term).getTF_IDF() *document.hashTerms.get(term).getTF_IDF();
			}
		}
		dotProduct = dotProduct/(getValue()*document.getValue());
	}
	
	public double getValue(){
		double value = 0.0;
		for (String term : hashTerms.keySet()) {
			Term tempTerm = hashTerms.get(term);
			value += tempTerm.getTF_IDF() * tempTerm.getTF_IDF();
		}
		return Math.sqrt(value);
	}
	@Override
	public String toString() {
		return "Ram and Sham are good friends.\nThey are good man also";
	}
	
	@Override
	public int compareTo(Document document) {
		if (dotProduct > document.dotProduct) {
			return 1;
		}
		return -1;
	}
	
	/**
	 * @return true when the two document is same 
	 */
	public boolean isSameDocument(Document document){
		boolean status = false;
		return status;
	}
}
