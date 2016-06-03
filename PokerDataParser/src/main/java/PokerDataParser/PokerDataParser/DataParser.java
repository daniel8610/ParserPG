package PokerDataParser.PokerDataParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

//idea crea un reader che si lavora le string come in riga 36-40 poi sposta il codice metodo priv in publico e 
//crea un metodo privato per estrarti solo le carte publiche e uno per le carte private
public class DataParser {
	private LinkedList<String> eventi;
	private StateWriter writer;
	public DataParser(String path){
		this.writer=new StateWriter(path);
		this.eventi=new LinkedList<String>();
		eventi.add("ExternalRaiseEvent");
		eventi.add("ExternalCallEvent");
		eventi.add("ExternalFoldEvent");
		eventi.add("ExternalBetEvent");
		eventi.add("ExternalCheckEvent");
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
			//if(id!=o.getJsonString(""))
			System.out.println(v.toString());
			if(o.getString("subclassType").equals("ExternalCardsDealtToPlayersEvent")){
				JsonArray cards=o.getJsonObject("externalCardsDealtToPlayersEvent").getJsonArray("dealtCards");
				for(int i=0;i<cards.size();i++){
					if(cards.getJsonObject(i).getString("playerId").equals(ps.getPlayerId())){
						ps.addpCard(cards.getJsonObject(i).getJsonArray("cards").getJsonObject(0).getJsonObject("card").getInt("value"));
						ps.addpCard(cards.getJsonObject(i).getJsonArray("cards").getJsonObject(1).getJsonObject("card").getInt("value"));
						System.out.println(ps.getpCards().get(0)+"   "+ps.getpCards().get(1));
					}
				}
			}
			//Unificare i 3 casi ID!= e aggiungi Bet e Check
			if(o.getString("subclassType").equals("ExternalCallEvent")&&!ps.getPlayerId().equals(o.getJsonObject("externalCallEvent").getString("playerId"))){
			System.out.println(o.getJsonObject("externalCallEvent").getString("playerId"));
			ps.upContCall();
			}
			if(o.getString("subclassType").equals("ExternalFoldEvent")&&!ps.getPlayerId().equals(o.getJsonObject("externalFoldEvent").getString("playerId"))){
				System.out.println(o.getJsonObject("externalFoldEvent").getString("playerId"));
				ps.upContFold(); 
				}
			if(o.getString("subclassType").equals("ExternalRaiseEvent")&&!ps.getPlayerId().equals(o.getJsonObject("externalRaiseEvent").getString("playerId"))){
				System.out.println(o.getJsonObject("externalRaiseEvent").getString("playerId"));
				ps.upContRaise();
				}
			//Caso giocatore da analizzare
			if(this.eventi.contains(o.getString("subclassType"))&&ps.getPlayerId().equals(o.getJsonObject(o.getString("subclassType").replaceFirst("E", "e")).getString("playerId"))){
				System.out.println("contatori del player id:"+ps.getPlayerId()+"  "+ps.getContCall()+" "+ps.getContFold()+" "+ps.getContRaise()+" "+ps.percCall());
				ps.reset();
				//fai reset e genera output della scelta e mandalo sul writer 
				}
			//resetta contatori dopo che si passa in un altra fase di gioco basta fare un if con externalMoveToPotEvent e reset
			
			
		}
	}
}
