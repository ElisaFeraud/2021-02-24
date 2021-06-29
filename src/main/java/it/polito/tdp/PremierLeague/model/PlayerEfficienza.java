package it.polito.tdp.PremierLeague.model;

public class PlayerEfficienza {
      Player player;
      int teamID;
      double passaggi;
      double assist;
      double tempo;
      double efficienza;
	public PlayerEfficienza(Player player, int teamID,double passaggi, double assist, double tempo) {
		this.player = player;
		this.passaggi = passaggi;
		this.assist = assist;
		this.tempo = tempo;
		this.teamID= teamID;
		this.efficienza=(passaggi+assist)/tempo;
	}
	public PlayerEfficienza(Player player, double efficienza) {
		this.player=player;
		this.efficienza=efficienza;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public double getPassaggi() {
		return passaggi;
	}
	public void setPassaggi(double passaggi) {
		this.passaggi = passaggi;
	}
	public double getAssist() {
		return assist;
	}
	public void setAssist(double assist) {
		this.assist = assist;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	public double getEfficienza() {
		return efficienza;
	}
	public void setEfficienza(double efficienza) {
		this.efficienza = efficienza;
	}
	
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	@Override
	public String toString() {
		return player + "--" + efficienza;
	}
    
}
