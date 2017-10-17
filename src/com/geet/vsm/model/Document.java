package com.geet.vsm.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
public class Document  implements Comparable<Document>{
	
	protected String article="";
	
	public Document(String article) {
		this.article = article;
	}
	public String getArticle() {
		return article;
	}
	
	public List<Term> getTerms() {
		List<Term> terms = new ArrayList<Term>();
		StringTokenizer stringTokenizer = new StringTokenizer(getArticle(), "", false);
		while (stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken();
			// stem the token if token is a word
			/*if (StringUtils.isWord(token)) {
				token = new Stemmer(token.toLowerCase()).toString();
			}*/
			// if stop word then continue
			if (StopWords.isStopword(token)) {
				continue;
			}
			Term candidateTerm = new Term(token.toLowerCase(), 1);
			int pass = -1;
			for (int i = 0; i < terms.size(); i++) {
				if (terms.get(i).isSame(candidateTerm)) {
					pass = i;
					terms.get(i).termFrequency++;
					continue;
				}
			}
			if (pass == -1) {
				terms.add(candidateTerm);
			}
		}
		return terms;
	}
	public boolean hasTerm(Term term){
		for (Term trm : getTerms()) {
			if (trm.isSame(term)) {
				return true;
			}
		}
		return false;
	}
	private List<String> getTermsFromDocument(){
		Set<String> terms = new HashSet<String>();
		return new ArrayList<String>(terms);
	}
	/**
	 * 
	 * @param document
	 * @return dotProduct dotProduct between two documents
	 */
	public double getDotProduct(Document document){
		double dotProduct = 1.0;
		double scalarValue = 0.0;
		for (Term term : getTerms()) {
			for (Term trm : document.getTerms()) {
				if (term.isSame(trm)) {
					dotProduct *= term.getTF_IDF() * term.getTF_IDF();
					scalarValue += term.getTF_IDF() * term.getTF_IDF();
					break;
				}
			}
		}
		// cosine similarity
		return dotProduct;
	}
	@Override
	public String toString() {
		return "Ram and Sham are good friends.\nThey are good man also";
	}
	public double dotProduct=0.0;
	@Override
	public int compareTo(Document document) {
		if (dotProduct > document.dotProduct) {
			return 1;
		}
		return -1;
	}
	public double getTF_IDF(String termString){
		double tf_idf = 0.0;
		for (Term term : getTerms()) {
			if (term.isSame(new Term(termString))) {
				return term.getTF_IDF();
			}
		}
		return tf_idf;
	}
	public double getTF(String termString){
		double tf = 0.0;
		for (Term term : getTerms()) {
			if (term.isSame(new Term(termString))) {
				return term.termFrequency;
			}
		}
		return tf;
	}
	public List<String> getTermsInString(){
		List<String> termsInString = new ArrayList<String>();
		for (Term term : getTerms()) {
			termsInString.add(term.termString);
		}
		return termsInString;
	}
	/**
	 * @return true when the two document is same 
	 */
	public boolean isSameDocument(Document document){
		boolean status = false;
		return status;
	}
}
