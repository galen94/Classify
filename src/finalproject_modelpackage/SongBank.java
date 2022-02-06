/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - SongBank class
 */

package finalproject_modelpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * This class contains the list
 * of songs read in from the file.
 * It can filter the songs based on user input.
 * @author Galen Otten
 *
 */
public class SongBank{
	
	/**
	 * This list contains all of the IMusicalPieces
	 * from the file.
	 */
	private List<IMusicalPiece> songBank;
	
	/**
	 * This list holds the current and temporary
	 * list of songs after a filtration.
	 */
	private List<IMusicalPiece> filteredSongList;
	
	/**
	 * This is a list of all the composers listed in
	 * the file.
	 */
	private List<String> composerList;	
	
	/**
	 * This is a list of all the languages listed in
	 * the file.
	 */
	private List<String> languageList;
	
	/**
	 * This is a list of all the emotional tones
	 * listed in the file.
	 */
	private List<String> toneList;
	
	/**
	 * This is a list of all the performers listed in
	 * the file.
	 */
	private List<String> performerList;
	
	/**
	 * This is a list of all the piece types
	 * listed in the file.
	 */
	private List<String> typeList;	
	
	/**
	 * This constructor creates an objects
	 * that will hold all of the songs from the file.
	 * Upon construction, it will read the file
	 * and create the list.
	 * @param filename, the name of the file holding the song
	 * information.
	 */
	public SongBank(String filename) {
		this.songBank = new ArrayList<IMusicalPiece>();
		this.filteredSongList = new ArrayList<IMusicalPiece>();
		this.readSongsFromFile(filename);
		this.copySongBankList();
		this.composerList = new ArrayList<String>();
		this.performerList = new ArrayList<String>();
		this.typeList = new ArrayList<String>();
		this.languageList = new ArrayList<String>();
		this.toneList = new ArrayList<String>();
	}
	
	/**
	 * This method creates the string representation
	 * of the SongBank.
	 * @return string, string of the SongBank list
	 */
	@Override
	public String toString() {
		String listString = "";
		for(IMusicalPiece piece : this.songBank) {
			listString = listString + piece.toString() + "\n";
		}
		return listString;
	}
	
	/**
	 * This method creates a list of the filters specified, for example,
	 * a list of all the composers, or a list of all of the languages.
	 * 
	 * @param userFilterChoice, the type of list needed 
	 * @return a list of strings based on the filter choice
	 */
	public List<String> applyUserFilterChoice(String userFilterChoice) {
		List<String> listToReturn = new ArrayList<String>();
	    switch (userFilterChoice) {
	         	case "Composer":
	         		this.makeComposerList();
	         		listToReturn = this.composerList;
	                     break;
	            case "Language":
	            	this.makeLanguageList();
	            	listToReturn = this.languageList;
	                     break;
	            case "Performer":
	            	this.makePerformerList();
	            	listToReturn = this.performerList;
	                     break;
	            case "Emotional Tone":
	            	this.makeToneList();
	            	listToReturn = this.toneList;
	                     break;
	            case "Piece Type":
	            	this.makeTypeList();
	            	listToReturn = this.typeList;
	                     break;
	            default: userFilterChoice = "";
	                     break;
	        }
	    return listToReturn;
	}
	
	/**
	 * This method gets the list of songs
	 * in the SongBank.
	 * @return list of pieces
	 */
	public List<IMusicalPiece> getSongBank(){
		return this.songBank;
	}
	
	/**
	 * This method sets the SongBank to the
	 * passed in list of IMusicalPieces.
	 * @param newSongBank, a list of IMusicalPieces
	 */
	public void setsongBank(List<IMusicalPiece> newSongBank){
		 this.songBank = newSongBank;
	}
	
	/**
	 * This method gets the filtered
	 * list of IMusicalPieces.
	 * @return filtered list of IMusicalPieces
	 */
	public List<IMusicalPiece> getFilteredSongBank(){
		return this.filteredSongList;
	}
	
	/**
	 * This method sets the filtered list
	 * variable to a passed in
	 * list of IMusicalPieces.
	 * @param newSongBank
	 */
	public void setFilteredsongBank(List<IMusicalPiece> newSongBank){
		 this.filteredSongList = newSongBank;
	}
	
	/**
	 * This method gets the list of composers
	 * from the file.
	 * @return a list of strings of composer names
	 */
	public List<String> getComposerList(){
		return this.composerList;
	}
	
	/**
	 * This method gets the list of languages
	 * from the file.
	 * @return, list of strings of languages
	 */
	public List<String> getLanguageList(){
		return this.languageList;
	}
	
	/**
	 * This method gets the list of performers
	 * from the file.
	 * @return a list of strings of performer names
	 */
	public List<String> getPerformerList(){
		return this.performerList;
	}
	
	/**
	 * This method gets the list of piece types
	 * from the file.
	 * @return a list of strings of piece types
	 */
	public List<String> getTypeList(){
		return this.typeList;
	}
	
	/**
	 * This method gets the list of emotions
	 * from the file.
	 * @return a list of strings of emotional tones 
	 */
	public List<String> getToneList(){
		return this.toneList;
	}

	/**
	 * This method make the list of all composers
	 * in the file,but doesn't allow duplicates.
	 * It then sorts the list alphabetically.
	 */
	public void makeComposerList() {
		for(IMusicalPiece piece : songBank) {
			if(composerList.contains(piece.getComposer()) == false) {
				composerList.add(piece.getComposer());
			}
		}
		Collections.sort(composerList,(composer1, composer2)->{return composer1.compareTo(composer2);});
	}
	
	/**
	 * This method make the list of all languages
	 * in the file,but doesn't allow duplicates.
	 * It then sorts the list alphabetically.
	 */
	public void makeLanguageList() {
		for(IMusicalPiece piece : songBank) {
			if(languageList.contains(piece.getLanguage()) == false) {
				languageList.add(piece.getLanguage());
			}
		}
		Collections.sort(languageList,(language1, language2)->{return language1.compareTo(language2);});
	}
	
	/**
	 * This method make the list of all performers
	 * in the file,but doesn't allow duplicates.
	 * It then sorts the list alphabetically.
	 */
	public void makePerformerList() {
		for(IMusicalPiece piece : songBank) {
			if(performerList.contains(piece.getPerformer()) == false) {
				performerList.add(piece.getPerformer());
			}
		}
		Collections.sort(performerList,(performer1, performer2)->{return performer1.compareTo(performer2);});
	}
	
	/**
	 * This method make the list of all emotions
	 * in the file,but doesn't allow duplicates.
	 * It then sorts the list alphabetically.
	 */
	public void makeToneList() {
		for(IMusicalPiece piece : songBank) {
			if(toneList.contains(piece.getEmotionalTone()) == false) {
				toneList.add(piece.getEmotionalTone());
			}
		}
		Collections.sort(toneList,(tone1, tone2)->{return tone1.compareTo(tone2);});
	}
	
	/**
	 * This method make the list of all piece types
	 * in the file,but doesn't allow duplicates.
	 * It then sorts the list alphabetically.
	 */
	public void makeTypeList() {
		for(IMusicalPiece piece : songBank) {
			if(typeList.contains(piece.getType()) == false) {
				typeList.add(piece.getType());
			}
		}
		Collections.sort(typeList,(type1, type2)->{return type1.compareTo(type2);});
	}
	
	/**
	 * This method finds and returns the hovering text
	 * description of the type of musical piece it is. 
	 * 
	 * @param type of musical piece
	 * @return string, the text description
	 */
	public String getTypeDescriptionsOfSongs(String type) {
		for(IMusicalPiece piece : songBank) {
			if(piece.getType().equals(type)) {
				return piece.makeTypeDescription();
			}
		}
		return "";
	}
	
	/**
	 * This method reads the strings in the
	 * file passed in. It uses the strings
	 * to set up the song objects and add them
	 * to the SongBank object.
	 * @param filename, name of file to be read
	 */
	public void readSongsFromFile(String filename) {
		try {
		    File file = new File(filename);
			Scanner input = new Scanner(file);
			
			String title;
			String language;
			String type; 
			String performerName;
			String composerName;
			String emotionalTone;
			String era;
			
			while(input.hasNext()) {
				title = input.next();
				composerName = input.next();
				language = input.next();
				type = input.next();
				performerName = input.next();
				emotionalTone = input.next();
				era = input.next();
				String url = input.next();
				input.nextLine();
				if(language.equals("Instrumental")) {
					this.songBank.add(new Symphony(title, type, language, composerName, performerName, emotionalTone, era, url));
				}
				else {
					this.songBank.add(new Song(title, type, language, composerName, performerName, emotionalTone, era, url));	
				}
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * This method makes a copy of the original SongBank
	 * list of IMusicalPieces so that it can be filtered
	 * without altering the original list.
	 */
	private void copySongBankList() {
		for(int i = 0; i < songBank.size(); i++) {
			this.filteredSongList.add(songBank.get(i));
		}
	}
	
	/**
	 * This method resets the filtered list variable to 
	 * contain all pieces in the original SongBank list again.
	 */
	public void resetSongList() {
		this.filteredSongList = new ArrayList<IMusicalPiece>();
		for(int i = 0; i < songBank.size(); i++) {
			this.filteredSongList.add(songBank.get(i));
		}
	}
	
	/**
	 * This helper method takes in a predicate and creates
	 * a list of IMusicalPieces that pass that predicate test.
	 * @param predicate, test to pass the filter
	 * @return, the filtered list of musical pieces
	 */
	private List<IMusicalPiece> filterHelper(Predicate<IMusicalPiece> predicate){
		List<IMusicalPiece> filteredList = 
				filteredSongList.stream().filter(predicate).collect(Collectors.toList());
		this.filteredSongList = filteredList;
		return filteredList;
	}

	/**
	 * This method passes in a composer name to create
	 * a predicate that will be passed to the filterHelper method.
	 * @param composer, the person who wrote the music
	 * @return list of IMusicalPieces that were all composed by the
	 * passed in composer.
	 */
	public List<IMusicalPiece> filterByComposer(String composer) {
		if(this.songBank == null) {
			return null;
		}
		Predicate<IMusicalPiece> predicate = s -> {return s.getComposer().equals(composer);};
		return this.filterHelper(predicate);
	}
	
	/**
	 * This method passes in a language to create
	 * a predicate that will be passed to the filterHelper method.
	 * @param language, the language of the song
	 * @return list of IMusicalPieces that were all in the same
	 * language that was passed into the method.
	 */
	public List<IMusicalPiece> filterByLanguage(String language) {
		if(this.songBank == null) {
			return null;
		}
		Predicate<IMusicalPiece> predicate = s -> {return s.getLanguage().equals(language);};
		return this.filterHelper(predicate);	
	}
	
	/**
	 * This method passes in an era to create
	 * a predicate that will be passed to the filterHelper method.
	 * @param era, the time period of the song
	 * @return list of IMusicalPieces that were all in the same
	 * time period that was passed into the method.
	 */
	public List<IMusicalPiece> filterByEra(String era) {
		if(this.songBank == null) {
			return null;
		}
		Predicate<IMusicalPiece> predicate = s -> {return s.getEra().equals(era);};
		return this.filterHelper(predicate);
	}
	
	/**
	 * This method passes in an emotional tone to create
	 * a predicate that will be passed to the filterHelper method.
	 * @param tone, the emotion tone of the song
	 * @return list of IMusicalPieces that all had the same
	 * emotional tone that was passed into the method.
	 */
	public List<IMusicalPiece> filterByTone(String tone) {
		if(this.songBank == null) {
			return null;
		}
		Predicate<IMusicalPiece> predicate = s -> {return s.getEmotionalTone().equals(tone);};
		return this.filterHelper(predicate);
	}
	
	/**
	 * This method passes in a type of piece to create
	 * a predicate that will be passed to the filterHelper method.
	 * @param type, the type of the song
	 * @return list of IMusicalPieces that are all the same type
	 * of classical music piece.
	 */
	public List<IMusicalPiece> filterByType(String type) {
		if(this.songBank == null) {
			return null;
		}
		Predicate<IMusicalPiece> predicate = s -> {return s.getType().equals(type);};
		return this.filterHelper(predicate);
	}
	
	/**
	 * This method passes in a performer to create
	 * a predicate that will be passed to the filterHelper method.
	 * @param performer, the artist of the song
	 * @return list of IMusicalPieces that were all performed
	 * by the same person that was passed into the method.
	 */
	public List<IMusicalPiece> filterByPerformer(String performer) {
		if(this.songBank == null) {
			return null;
		}
		Predicate<IMusicalPiece> predicate = s -> {return s.getPerformer().equals(performer);};
		return this.filterHelper(predicate);
	}
	
}