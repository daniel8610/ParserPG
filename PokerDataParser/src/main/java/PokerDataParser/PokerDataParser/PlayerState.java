package PokerDataParser.PokerDataParser;

import java.util.LinkedList;

public class PlayerState {




	private double contFold;
	private double contCall;
	private double contRaise;
	private double position;
	private LinkedList<Integer> pCards;
	private LinkedList<Integer> cCards;
	private String output;
	private String playerId;
	
	public PlayerState(double percFold, double percCall, double percRaise,
			double position, LinkedList<Integer> pCards, LinkedList<Integer> cCards, String playerId,String output) {
		this.contFold = percFold;
		this.contCall = percCall;
		this.contRaise = percRaise;
		this.position = position;
		this.pCards = pCards;
		this.cCards = cCards;
		this.output = output;
		this.playerId=playerId;
	}
	
	public PlayerState(){
		this.contCall=0;
		this.contFold=0;
		this.contRaise=0;
		this.cCards=new LinkedList<Integer>();
		this.pCards=new LinkedList<Integer>();
	}
	public void upContCall(){
		this.contCall++;
	}
    public void addcCard(int cCard){
    	this.cCards.add(cCard);
    }
    public void addpCard(int pCard){
    	this.pCards.add(pCard);
    }
	public double getPercFold() {
		return contFold;
	}

	public void setPercFold(double percFold) {
		this.contFold = percFold;
	}

	public double getPercCall() {
		return contCall;
	}

	public void setPercCall(double percCall) {
		this.contCall = percCall;
	}

	public double getPercRaise() {
		return contRaise;
	}

	public void setPercRaise(double percRaise) {
		this.contRaise = percRaise;
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public LinkedList<Integer> getpCards() {
		return pCards;
	}

	public void setpCards(LinkedList<Integer> pCards) {
		this.pCards = pCards;
	}

	public LinkedList<Integer> getcCards() {
		return cCards;
	}

	public void setcCards(LinkedList<Integer> cCards) {
		this.cCards = cCards;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	

}
