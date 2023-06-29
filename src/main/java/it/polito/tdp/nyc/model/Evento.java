package it.polito.tdp.nyc.model;

public class Evento implements Comparable<Evento>{
	
	public enum Tipologia{
		CONDIVISIONE,
		CANCELLAZIONE
	}
	
	private Integer tempo;
	private Tipologia tipologia;
	private String NTA;
	private Integer idFile;
	

	public Evento(Integer tempo, Tipologia tipologia, String NTA, Integer idFile) {
		super();
		this.tempo = tempo;
		this.tipologia = tipologia;
		this.NTA = NTA;
		this.idFile = idFile;
	}



	@Override
	public int compareTo(Evento o) {
		return this.tempo.compareTo(o.tempo);
	}



	public Integer getTempo() {
		return tempo;
	}



	public Tipologia getTipologia() {
		return tipologia;
	}



	public String getNTA() {
		return NTA;
	}



	public Integer getIdFile() {
		return idFile;
	}
	
}
