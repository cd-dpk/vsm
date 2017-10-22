package com.geet.vsm.model;
import java.util.ArrayList;
import java.util.List;

public class Term {
	
	public String term;
	public int tf = 0;
	public int df = 0;
	public double idf = 0.0; 
	
	public Term(String term) {
		this.term = term;
	}
	public Term(String term, int tf) {
		super();
		this.term = term;
		this.tf = tf;
	} 
	public Term(String term, int tf,
			int df) {
		super();
		this.term = term;
		this.tf = tf;
		this.df = df;
	}
	public boolean isSame(Term term){
		if (term.term.equals(term)) {
			return true;
		} 
		return false;
	}
	
	public void setdfAndidf(List<Document>documents){
		for (Document document : documents) {
			if (document.hashTerms.containsKey(term)) {
				Term candidateTerm = document.hashTerms.get(term);
				candidateTerm.df++;
				document.hashTerms.put(term, candidateTerm);
			} 
		}
		idf = 1+Math.log10((double)documents.size()/(double)df);
	}
	
	public double getTF_IDF(){
		return  (double) tf * idf;
	}
	public double getTF(){
		return  (double) tf;
	}
	@Override
	public String toString() {
		return "[ "+term+","+tf+","+idf+"] ";
	}

	public Term toClone() {
		return new Term(term, tf, df);
	}
}
