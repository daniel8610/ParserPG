package PokerDataParser.PokerDataParser;



public class MainParser {
	public static void main(String args[]){
		DataParser p=new DataParser("DatiPoker.txt");
		p.parse("6550:0000017427","dbexport.csv");
		
	}

}
