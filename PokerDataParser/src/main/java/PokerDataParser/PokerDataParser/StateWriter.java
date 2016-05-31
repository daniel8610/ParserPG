package PokerDataParser.PokerDataParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StateWriter {
	private BufferedWriter bw;
public StateWriter(String path){
	File fout = new File(path);
	FileOutputStream fos;
	try {
		fos = new FileOutputStream(fout);
		this.bw = new BufferedWriter(new OutputStreamWriter((fos), "UTF-8"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public void write(PlayerState ps){
	
	try {
		//qui fai scrivere la linea separata da ","
		this.bw.newLine();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public void closeWriter(){
	try {
		this.bw.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
