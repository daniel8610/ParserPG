package PokerDataParser.PokerDataParser;



public class MainParser {
	public static void main(String args[]){
		DataParser p=new DataParser("cartella dove scrivere");
		p.parse("6550:0000017758","dbexport.csv");
		
	}

}
