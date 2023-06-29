package it.polito.tdp.nyc.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
	private Double pesoMedio;
	
	
	
	public Model() {
		this.dao = new NYCDao();	
	}


	public List<String> getAllBoroughs(){
		return this.dao.getAllBoroughs();
	}
	
	public List<String> getVertexes(String borough){
		return this.dao.getVertexes(borough);
	}
	
	public void creaGrafo(String borough) {
		this.pesoMedio = (double) 0;
		this.graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.graph, this.getVertexes(borough));
		for (String v1 : this.graph.vertexSet()) {
			for (String v2: this.graph.vertexSet()) {
				if (v1.compareTo(v2) < 0) {
					Set<String> s1 = this.getDistinctSSID(v1);
					Set<String> s2 = this.getDistinctSSID(v2);
					Set<String> sum = new HashSet<String>(s1);
					sum.addAll(s2);
					if (s1.size() < s2.size()) {
						Graphs.addEdgeWithVertices(this.graph, v2, v1, s1.size() + s2.size());
					}
					else {
						Graphs.addEdgeWithVertices(this.graph, v1, v2, s1.size() + s2.size());
					}
				}
			}
		}
		
		for (DefaultWeightedEdge dfe : this.graph.edgeSet()) {
			this.pesoMedio += this.graph.getEdgeWeight(dfe);
		}
		this.pesoMedio = this.pesoMedio / this.graph.edgeSet().size();
		System.out.println(this.pesoMedio);
	}
	
	public Set<String> getDistinctSSID(String NTAName){
		return this.dao.getDistinctSSID(NTAName);
	}
	
	public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> getGraph() {
		return graph;
	}

	public List<Arco> archiAnalizzati(){
		List<Arco> result = new LinkedList<Arco>();
		for (DefaultWeightedEdge dfe : this.graph.edgeSet()) {
			String NTA1 = this.graph.getEdgeSource(dfe);
			String NTA2 = this.graph.getEdgeTarget(dfe);
			Double peso = this.graph.getEdgeWeight(dfe);
			if (peso < this.pesoMedio) {
				result.add(new Arco(NTA1, NTA2, peso));
			}
		}
		Collections.sort(result);
		return result;
	}
}
