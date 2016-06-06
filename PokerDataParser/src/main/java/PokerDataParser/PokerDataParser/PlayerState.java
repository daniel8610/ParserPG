package PokerDataParser.PokerDataParser;

import java.util.LinkedList;

public class PlayerState {




	private float countFold;
	private float countCall;
	private float countRaise;
	private float position;
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
	public float getTotBet(){
		return this.countCall+this.countFold+this.countRaise;
	}
	public void reset(){
		this.countCall=0;
		this.countFold=0;
		this.countRaise=0;
	}
	public float[] getPerc(){
		float[] p={0,0,0};
		
		if(this.getTotBet()!=0){
			p[0]=(this.countCall*100)/this.getTotBet();
			p[1]=(this.countRaise*100)/this.getTotBet();
			p[2]=(this.countFold*100)/this.getTotBet();
			
		}
			
		
		
		
		
		return p;
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
	public float getCountFold() {
		return countFold;
	}

	public void setCountFold(float countFold) {
		this.countFold = countFold;
	}

	public float getCountCall() {
		return countCall;
	}

	public void setCountCall(float countcCall) {
		this.countCall = countcCall;
	}

	public float getCountRaise() {
		return countRaise;
	}

	public void setCountRaise(float countRaise) {
		this.countRaise = countRaise;
		
	}

	public float getPosition() {
		return position;
	}

	public void setPosition(float position) {
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
