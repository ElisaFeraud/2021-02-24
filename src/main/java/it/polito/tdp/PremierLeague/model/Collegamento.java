package it.polito.tdp.PremierLeague.model;

public class Collegamento {
          Player origine;
          Player destinazione;
          double peso;
		public Collegamento(Player origine, Player destinazione, double peso) {
			this.origine = origine;
			this.destinazione = destinazione;
			this.peso = peso;
		}
		public Player getOrigine() {
			return origine;
		}
		public void setOrigine(Player origine) {
			this.origine = origine;
		}
		public Player getDestinazione() {
			return destinazione;
		}
		public void setDestinazione(Player destinazione) {
			this.destinazione = destinazione;
		}
		public double getPeso() {
			return peso;
		}
		public void setPeso(double peso) {
			this.peso = peso;
		}
          
}
