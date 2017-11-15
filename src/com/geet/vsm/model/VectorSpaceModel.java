/*package com.geet.vsm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VectorSpaceModel {

	public List<BugReport> documentsInVSM(List<BugReport>documents){
		// String of terms--> term
		// Double of terms--> document frequency
		Map<String,Double> terms = new HashMap<String,Double>();
		for (BugReport document : documents) {
			for (String key : document.hashTerms.keySet()) {
				if (terms.containsKey(key)) {
					terms.put(key, terms.get(key)+1.00);
				} else {
					terms.put(key, 1.00);
				}
			}
		}
		for (String key : terms.keySet()) {
			// IDF of a term "key"
			terms.put(key, Math.log10(1+(terms.get(key)/documents.size()))/Math.log10(2.0));
			for (BugReport document : documents) {
				if (document.hashTerms.containsKey(key)) {
					// calculate the TF*IDF
					document.hashTerms.put(key, document.hashTerms.get(key)*terms.get(key));
				}
			}
		}
		return documents;
	} 
}
*/