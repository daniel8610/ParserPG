package PokerDataParser.PokerDataParser;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonReader;

public class MainParser {
	public static void main(String args[]){
		DataParser p=new DataParser();
		p.parse("id","dbexport.csv");
		
	}

}
