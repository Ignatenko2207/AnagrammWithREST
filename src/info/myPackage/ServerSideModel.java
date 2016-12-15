package info.myPackage;

import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("anagram")
public class ServerSideModel {
	//////////////////////////////////////////
	@GET
	@Path("/{inputPhrase}")
	@Produces(MediaType.TEXT_PLAIN)
	public String answer(@QueryParam("inputPhrase") String inputPhrase){
		if (inputPhrase.isEmpty()) {
			return "You didn't input anything";
		}		
		String[] wordsOfPhrase = inputPhrase.split(" ");
		char[][] allSymbolsOfPhrase = new char[wordsOfPhrase.length][];		
		for (int i=0; i < wordsOfPhrase.length; i++){
			allSymbolsOfPhrase[i] = new char [wordsOfPhrase[i].length()];
			for(int k = 0; k < wordsOfPhrase[i].length(); k++){
				allSymbolsOfPhrase[i][k] = wordsOfPhrase[i].charAt(k);		
			}
		}
		char [][] replacedSymbols = new char [allSymbolsOfPhrase.length][];	
		for (int i = 0; i < allSymbolsOfPhrase.length; i++){
			replacedSymbols[i] = new char [allSymbolsOfPhrase[i].length];		
			ArrayList<Integer> positionOfLetters = new ArrayList<Integer>();
			for(int k = 0; k < allSymbolsOfPhrase[i].length; k++){
				if(Character.isLetter(allSymbolsOfPhrase[i][k]) == false){
				replacedSymbols[i][k] = allSymbolsOfPhrase[i][k];			
				}
				else{
					positionOfLetters.add(k);						
				}
			}
			int lastIndexOfpositionInList = positionOfLetters.size() - 1;
			for(int x = 0; x < allSymbolsOfPhrase[i].length; x++){				
				if(Character.isLetter(allSymbolsOfPhrase[i][x])){
					replacedSymbols[i][x] = allSymbolsOfPhrase[i][positionOfLetters.get(lastIndexOfpositionInList)];
					lastIndexOfpositionInList = lastIndexOfpositionInList - 1;	
				}
			}
		}
		String changedPhrase = ""; 
		for (char word[] : replacedSymbols){ 					
			for( char symbol: word){
				changedPhrase += String.valueOf(symbol);
			}
			changedPhrase += " ";
		}
		changedPhrase.trim();
		return changedPhrase;
	}
}
	

