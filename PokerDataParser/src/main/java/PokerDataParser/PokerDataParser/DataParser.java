package PokerDataParser.PokerDataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

//idea crea un reader che si lavora le string come in riga 36-40 poi sposta il codice metodo priv in publico e 
//crea un metodo privato per estrarti solo le carte publiche e uno per le carte private
public class DataParser {
	private HashMap<String, String> eventsMap;
	private StateWriter writer;
	public DataParser(String path){
		this.writer=new StateWriter(path);
		this.eventsMap=new HashMap<String,String>();
		this.eventsMap.put("ExternalRaiseEvent","raise");
		this.eventsMap.put("ExternalCallEvent","call");
		this.eventsMap.put("ExternalFoldEvent","fold");
		this.eventsMap.put("ExternalBetEvent","raise");
		this.eventsMap.put("ExternalCheckEvent","call");
	}
	public  String parse(String id,String path){
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(path));
		String line;
		br.readLine();
		int i=0;
		while ((line = br.readLine()) != null&&i<100000000) {
			String str =line.split(",", 3)[2];
			String json=str.substring(2, str.length()-5);
			this.jsonParser(json.replaceAll("\"\"","\""),id);
			i++;
			
		}
		br.close();
		this.writer.closeWriter();
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
			System.out.println(v.toString());
			//migliora posizione
			if(o.getString("subclassType").equals("ExternalCardsDealtToPlayersEvent")){
				JsonArray cards=o.getJsonObject("externalCardsDealtToPlayersEvent").getJsonArray("dealtCards");
				if(id.equals(""))
					ps.setPlayerId(cards.getJsonObject(new Random().nextInt(cards.size()-1)).getString("playerId"));
				for(int i=0;i<cards.size();i++){
					if(cards.getJsonObject(i).getString("playerId").equals(ps.getPlayerId())){
						ps.setPosition((double)(i+1)/cards.size());
						ps.addpCard(cards.getJsonObject(i).getJsonArray("cards").getJsonObject(0).getJsonObject("card").getInt("value"));
						ps.addpCard(cards.getJsonObject(i).getJsonArray("cards").getJsonObject(1).getJsonObject("card").getInt("value"));
						System.out.println(ps.getpCards().get(0)+"   "+ps.getpCards().get(1));
					}
				}
			}
			if(this.eventsMap.containsKey(o.getString("subclassType"))&&!ps.getPlayerId().equals(o.getJsonObject(o.getString("subclassType").replaceFirst("E", "e")).getString("playerId"))  ){
				ps.upCount(this.eventsMap.get(o.getString("subclassType")));
			}
			
			if(this.eventsMap.containsKey(o.getString("subclassType"))&&ps.getPlayerId().equals(o.getJsonObject(o.getString("subclassType").replaceFirst("E", "e")).getString("playerId"))){
				ps.setOutput(this.eventsMap.get(o.getString("subclassType")));
				System.out.println("contatori del player id:"+ps.getOutput()+"  "+ps.getPosition()+"  "+ps.getPlayerId()+"  "+ps.getCountCall()+" "+ps.getCountFold()+" "+ps.getCountRaise()+" "+ps.percCall());
				this.writer.write(ps);
				ps.reset();
				//fai reset e genera output della scelta e mandalo sul writer 
				}
			if(o.getString("subclassType").equals("ExternalCardsDealtToTableEvent")&&o.getJsonObject("externalCardsDealtToTableEvent").containsKey("communityCards")){
				for(JsonValue k:o.getJsonObject("externalCardsDealtToTableEvent").getJsonArray("communityCards")){
					JsonObject c=(JsonObject)k;
					ps.addcCard(c.getInt("value"));
				}
				
				ps.reset();
			}
			
			
		}
	}
}
