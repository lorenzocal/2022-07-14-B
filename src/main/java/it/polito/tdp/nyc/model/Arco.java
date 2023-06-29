package it.polito.tdp.nyc.model;

public class Arco implements Comparable<Arco>{
	
	private String NTA1;
	private String NTA2;
	private Double peso;
	
	public Arco(String nTA1, String nTA2, Double peso) {
		super();
		NTA1 = nTA1;
		NTA2 = nTA2;
		this.peso = peso;
	}

	@Override
	public int compareTo(Arco o) {
		return this.peso.compareTo(o.peso);
	}

	@Override
	public String toString() {
		return "Arco da " + NTA1 + " a " + NTA2 + " con peso " + peso + ".";
	}
	
	
	
	
	
}
