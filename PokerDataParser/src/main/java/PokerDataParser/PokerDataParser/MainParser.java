package PokerDataParser.PokerDataParser;



public class MainParser {
	public static void main(String args[]){
		DataParser p=new DataParser("DatiRete.txt");
		p.parse("","dbexport.csv");
		
	}

}
