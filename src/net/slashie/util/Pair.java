package net.slashie.util;

public class Pair <K,L>{
	private K a;
	public Pair(K a_, L b_){
		a = a_;
		b = b_;
	}
	public K getA() {
		return a;
	}
	public void setA(K a) {
		this.a = a;
	}
	public L getB() {
		return b;
	}
	public void setB(L b) {
		this.b = b;
	}
	private L b;
}
