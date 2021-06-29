package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;



public class Model {
	PremierLeagueDAO dao;
	Map<Integer,Player> idMap;
	Graph<Player,DefaultWeightedEdge> grafo;
	public Model(){
		dao = new PremierLeagueDAO();
	}
	public List<Match> listAllMatches(){
		return dao.listAllMatches();
	}
	
	public void creaGrafo(Match match) {
		 idMap = new HashMap<>();
		 this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		 int id = match.getMatchID();
		 dao.getVertici(idMap, id);
		 Graphs.addAllVertices(this.grafo, idMap.values());
		 for(PlayerEfficienza p1: dao.getEfficienza(idMap, id)) {
			//System.out.println(""+p1.getTeamID());
			 for(PlayerEfficienza p2 : dao.getEfficienza(idMap, id)) {

				 if( !p1.equals(p2)   && this.grafo.containsVertex(p1.getPlayer()) && this.grafo.containsVertex(p2.getPlayer())) {
					// System.out.println(""+ p1+ "--- "+p2);
					 if(p1.getTeamID()!=p2.getTeamID()) {
					 if((p1.getEfficienza()>p2.getEfficienza()) || p1.getEfficienza()==p2.getEfficienza()) {
					 
					 Player origine = p1.getPlayer();
					 Player destinazione = p2.getPlayer();
					 double peso = p1.getEfficienza()-p2.getEfficienza();
					DefaultWeightedEdge d = this.grafo.getEdge(origine, destinazione);
					 if(d==null)
						 Graphs.addEdgeWithVertices(this.grafo,origine,destinazione,peso);
					 
				 }
				 else if((p1.getEfficienza()<p2.getEfficienza())) {
					 Player origine = p2.getPlayer();
					 Player destinazione = p1.getPlayer();
					 double peso = p2.getEfficienza()-p1.getEfficienza();
					 DefaultWeightedEdge d = this.grafo.getEdge(origine, destinazione);
					 if(d==null)
						 Graphs.addEdgeWithVertices(this.grafo,origine,destinazione,peso);
					 
				 }
				 }
			 }}
		 }
	    		
		 
	 }
	 public String infoGrafo() {
		 return "Grafo creato con "+ this.grafo.vertexSet().size()+ " vertici e " + this.grafo.edgeSet().size()+" archi\n";
	 }
	public PlayerEfficienza getTopPlayer() {
		double max=-1;
		Player top = null;
			 for( Player p: this.grafo.vertexSet()) {
				 double sumI=0;
				 double sumU =0;
				 double differenza =0;
				for(DefaultWeightedEdge c1: this.grafo.incomingEdgesOf(p)) {
				     sumI = sumI+ this.grafo.getEdgeWeight(c1);
				}
				for(DefaultWeightedEdge c2: this.grafo.outgoingEdgesOf(p)) {
				     sumU = sumU+ this.grafo.getEdgeWeight(c2);
				
				}
				differenza = sumU-sumI;
				
				if(differenza>0 && differenza>max) {
					max=differenza;
					 top = p ;
				}
		}
		PlayerEfficienza pe = new PlayerEfficienza(top,max);
		return pe;
	}
}
