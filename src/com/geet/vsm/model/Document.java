package com.geet.vsm.model;

import java.util.HashMap;
import java.util.Map;

public class Document {
	long ID;
	public Map<String, Term> terms;
	int Surprising, Dormant, Blocker, Security, Performance, Breakage;

	public Document(long ID, String corpus) {
		this.ID=ID;
		terms = new HashMap<String, Term>();
		setTerms(corpus);
	}
	
	public Document(long ID, String corpus, int sur, int dor, int blo, int sec, int per, int bre) {
		this(ID, corpus);
		this.Surprising=sur;
		this.Dormant = dor;
		this.Blocker = blo;
		this.Security = sec;
		this.Performance = per;
		this.Breakage = bre;
	}
	private void setTerms(String corpus) {
		String [] tokens  = corpus.split("\\s");
		for (int i=0;i<tokens.length;i++) {
			String token = tokens[i];
			if (token.equals("\\s")) {
				continue;
			}
			if (terms.containsKey(token)) {
				Term term = terms.get(token);
				term.setWeight(term.getWeight()+1);
				terms.put(token, term);
			} else {
				Term term = new Term(token, 1);
				terms.put(token, term);
			}
		}
	}
	@Override
	public String toString() {
		String toString=ID+" ";
		for (String key : terms.keySet()) {
			toString += terms.get(key)+" ";
		}
		return toString;
	}
}
