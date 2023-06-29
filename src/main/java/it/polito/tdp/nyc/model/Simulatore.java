package it.polito.tdp.nyc.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.nyc.model.Evento.Tipologia;

public class Simulatore {

	//Parametri di input 
	private Double probabilita;
	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
	private Integer d;
	
	//Stato del sistema
	private PriorityQueue<Evento> queue;
	private Map<String, Integer> registroCondivisioni;
	private Integer idFile = 0;
	
	public Simulatore(Double probabilita, SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph, Integer d,
			PriorityQueue<Evento> queue) {
		this.probabilita = probabilita;
		this.graph = graph;
		this.d = d;
		this.queue = queue;
		this.registroCondivisioni = new HashMap<String, Integer>();
		for (String vertex : graph.vertexSet()) {
			this.registroCondivisioni.put(vertex, 0);
		}
		
	}
	
	public void inizializza() {
		for (int i=1; i <= 100; i++) {
			if (Math.random() < this.probabilita) {
				Integer random = (int)(Math.random()*this.graph.vertexSet().size() - 1);
				List<String> allNTA = new LinkedList<String>(this.graph.vertexSet());
				String primoNTA = allNTA.get(random);
				this.queue.add(new Evento(i, Tipologia.CONDIVISONE, primoNTA, this.idFile));
				this.idFile++;
				this.queue.add(new Evento(i + this.d, Tipologia.CANCELLAZIONE, null, this.idFile));
			}
		}
	}
	
	public void run() {
		while (!this.queue.isEmpty()) {
			
			Evento e = this.queue.poll();
			Integer tempo = e.getTempo();
			Tipologia tipologia = e.getTipologia();
			String NTA = e.getNTA();
			Integer idFile = e.getIdFile();
			
			switch(tipologia) {
			case CONDIVISIONE:
				break;
			case CANCELLAZIONE:
				break;
			}
		}
	}
	
}
