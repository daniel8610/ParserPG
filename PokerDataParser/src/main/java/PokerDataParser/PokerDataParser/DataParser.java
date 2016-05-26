package PokerDataParser.PokerDataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;


public class DataParser {
	public DataParser(){
		
	}
	public  String parse(String id,String path){
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(path));
		String line;
		br.readLine();
		int i=0;
		while ((line = br.readLine()) != null&&i<2) {
			String str =line.split(",", 3)[2];
			String json=str.substring(2, str.length()-5);
			this.jsonParser(json.replaceAll("\"\"","\""));
			i++;
			
		}
		br.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return id;
	
}
	private  void jsonParser(String json){
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject matchObject = reader.readObject();
		JsonArray eventsArray = matchObject.getJsonArray("events");
		for(JsonValue v : eventsArray){
			System.out.println(v.toString());
			JsonObject o=(JsonObject) v;
			//System.out.println(o.getJsonString("subclassType"));
			
		}
		
	}
}
