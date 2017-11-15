package com.geet.vsm.model;

public class Term {

	private String term;
	private double weight=0.0;
	
	
	public Term(String term, double weight) {
		this.term = term;
		this.weight = weight;
	}
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "["+term+","+weight+"]";
	}
}
