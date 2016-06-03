package PokerDataParser.PokerDataParser;

import java.util.LinkedList;

public class PlayerState {




	private double countFold;
	private double countCall;
	private double countRaise;
	private double position;
	private LinkedList<Integer> pCards;
	private LinkedList<Integer> cCards;
	private String output;
	private String playerId;
	
	public PlayerState(double percFold, double percCall, double percRaise,
			double position, LinkedList<Integer> pCards, LinkedList<Integer> cCards, String playerId,String output) {
		this.countFold = percFold;
		this.countCall = percCall;
		this.countRaise = percRaise;
		this.position = position;
		this.pCards = pCards;
		this.cCards = cCards;
		this.output = output;
		this.playerId=playerId;
	}
	
	public PlayerState(){
		this.countCall=0;
		this.countFold=0;
		this.countRaise=0;
		this.cCards=new LinkedList<Integer>();
		this.pCards=new LinkedList<Integer>();
	}
	public double percCall(){
		double tot=this.countCall+this.countFold+this.countRaise;
		if(tot==0)
			return 0;
		return (this.countCall*100)/tot;
	}
	public void reset(){
		this.countCall=0;
		this.countFold=0;
		this.countRaise=0;
	}
	public void upContCall(){
		this.countCall++;
	}
	public void upContFold(){
		this.countFold++;
	}
	public void upContRaise(){
		this.countRaise++;
	}
    public void addcCard(int cCard){
    	this.cCards.add(cCard);
    }
    public void addpCard(int pCard){
    	this.pCards.add(pCard);
    }
	public double getContFold() {
		return countFold;
	}

	public void setPercFold(double percFold) {
		this.countFold = percFold;
	}

	public double getContCall() {
		return countCall;
	}

	public void setPercCall(double percCall) {
		this.countCall = percCall;
	}

	public double getContRaise() {
		return countRaise;
	}

	public void setPercRaise(double percRaise) {
		this.countRaise = percRaise;
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
