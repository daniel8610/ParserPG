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
		while ((line = br.readLine()) != null&&i<1) {
			String str =line.split(",", 3)[2];
			String json=str.substring(2, str.length()-5);
			this.jsonParser(json.replaceAll("\"\"","\""),id);
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
	private  void jsonParser(String json ,String id){
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject matchObject = reader.readObject();
		JsonArray eventsArray = matchObject.getJsonArray("events");
		PlayerState ps=new PlayerState();
		ps.setPlayerId(id);
		for(JsonValue v : eventsArray){
			JsonObject o=(JsonObject) v;
			//serve per prendere una posizione dell'array System.out.println(eventsArray.getJsonObject(1).toString());
			//if(id!=o.getJsonString(""))
			System.out.println(v.toString());
			//questo if lo fai anche per Raise Fold Bet e .....in questo modo conteggi per la % poi fai un altro if che racchiude id== e li invii al writer e resetti %
			if(o.getString("subclassType").equals("ExternalCallEvent")&&!"6550:0000017758".equals(o.getJsonObject("externalCallEvent").getString("playerId"))){
			System.out.println(o.getJsonObject("externalCallEvent").getString("playerId"));
			ps.upContCall();
			}
			
		}
	}
}
