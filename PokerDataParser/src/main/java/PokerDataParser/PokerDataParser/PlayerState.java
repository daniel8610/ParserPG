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
	public double getTotBet(){
		return this.countCall+this.countFold+this.countRaise;
	}
	public void reset(){
		this.countCall=0;
		this.countFold=0;
		this.countRaise=0;
	}
	public void upCount(String s){
		if(s.equals("call"))
			this.upCountCall();
		if(s.equals("fold"))
			this.upCountFold();
		if(s.equals("raise"))
			this.upCountRaise();
	}
	public void upCountCall(){
		this.countCall++;
	}
	public void upCountFold(){
		this.countFold++;
	}
	public void upCountRaise(){
		this.countRaise++;
	}
    public void addcCard(int cCard){
    	this.cCards.add(cCard);
    }
    public void addpCard(int pCard){
    	this.pCards.add(pCard);
    }
	public double getCountFold() {
		return countFold;
	}

	public void setPercFold(double percFold) {
		this.countFold = percFold;
	}

	public double getCountCall() {
		return countCall;
	}

	public void setPercCall(double percCall) {
		this.countCall = percCall;
	}

	public double getCountRaise() {
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
	public String toString(){
		String s= this.getPlayerId()+","+this.getCountCall()*100/this.getTotBet()+","+this.getCountRaise()*100/this.getTotBet()+","+this.getCountFold()*100/this.getTotBet()
				+","+this.getpCards().get(0)+","+this.getpCards().get(1)+","+this.output;
		return s;
	}
	

}
