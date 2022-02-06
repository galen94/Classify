/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - MyController class
 */

package finalproject_controllerpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.VideoPlayer;
import finalproject_modelpackage.SongBank;

import finalproject_modelpackage.IMusicalPiece;
import finalproject_modelpackage.SongBank;
import finalproject_viewpackage.MyMusicView;

/**
 * This class contains a method that
 * when called upon starts the view and also
 * communicates with the model to ask it do 
 * certain tasks to then display back to the view.
 * @author Galen Otten
 *
 */
public class MyController {	
	
	/**
	 * This is a SongBank object, the model
	 */
	SongBank mySongbank;
	
	/**
	 * This is the filename to pass
	 * to the model.
	 */
	private String filename;
	
	/**
	 * This is the song queued up to 
	 * play.
	 */
	private ArrayList<IMusicalPiece> songToPlay;
	
	/**
	 * This is a list of musical pieces after being filtered.
	 */
	private List<IMusicalPiece> listByFilter;
	
	/**
	 * This constructor sets the filename,
	 * and instantiates the model object.
	 */
	public MyController() {
		this.filename = "songbank.txt";
		this.mySongbank = new SongBank(filename);
	    listByFilter = new ArrayList<IMusicalPiece>();
	    this.songToPlay = new ArrayList<IMusicalPiece>();
	}
	
	/**
	 * This method calls the main method of the MyMusicView class
	 * to set up the page.
	 */
	public void start() {
		MyMusicView.main(null);
	}
	
	/**
	 * This method launches the video
	 * of the song in songToPlay, but only if
	 * the size of songToPlay is 1 song.
	 */
	public void playVideo() {
		if(songToPlay.size() == 1) {
			try {
				new VideoPlayer();
				VideoPlayer.setURLAndTitle(songToPlay.get(0).getURL(), songToPlay.get(0).getTitle());
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
	}
	
	/**
	 * This method calls on the model to reset
	 * the filtered song list.
	 */
	public void resetFilterSongList() {
		this.mySongbank.resetSongList();
	}
	
	/**
	 * This method calls on the model
	 * to reset the filtered song list and
	 * clears the song queue.
	 */
	public void resetEraSongs() {
		resetFilterSongList();
		songToPlay.clear();
	}
	
	/**
	 * This method gets the playlist.
	 * @return a list of musical pieces
	 */
	public List<IMusicalPiece> getSongToPlay(){
		return this.songToPlay;
	}
	
	/**
	 * This method sets the name of the file
	 * to be passed to the model.
	 * @param filename, a string
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * This method gets the model's list of
	 * songs read in from the file.
	 * @return a list of musical pieces
	 */
	public List<IMusicalPiece> getSongBank() {
		return this.mySongbank.getSongBank();
	}
	
	/**
	 * This method gets the model object.
	 * @return a SongBank object
	 */
	public SongBank getSongBankObject() {
		return this.mySongbank;
	}
	
	/**
	 * This method tells the model to get a list of strings based 
	 * on the parameter such as composers, emotions, languages.
	 * @param userChoice, the filter choice
	 * @return a list of strings based on the filter
	 */
	public List<String> applyUserFilterChoice(String userChoice) {
		return this.mySongbank.applyUserFilterChoice(userChoice);		
	}
	
	/**
	 * This method tells the model to use the composer filter
	 * and apply the chosen composer passed in.
	 * @param userChoice, the composer chosen
	 * @return a list of pieces by that composer
	 */
	public List<IMusicalPiece> applyComposerFilter(String userChoice){
		return this.mySongbank.filterByComposer(userChoice);
	}
	
	/**
	 * This method tells the model to use the language filter
	 * and apply the chosen language passed in.
	 * @param userChoice, the language chosen
	 * @return a list of pieces in that language
	 */
	public List<IMusicalPiece> applyLanguageFilter(String userChoice){
		return this.mySongbank.filterByLanguage(userChoice);
	}
	
	/**
	 * This method tells the model to use the piece type filter
	 * and apply the chosen piece type passed in.
	 * @param userChoice, the piece type chosen
	 * @return a list of pieces that are that piece type
	 */
	public List<IMusicalPiece> applyTypeFilter(String userChoice){
		return this.mySongbank.filterByType(userChoice);
	}
	
	/**
	 * This method tells the model to use the performer filter
	 * and apply the chosen performer passed in.
	 * @param userChoice, the performer chosen
	 * @return a list of pieces that are performed by that performer
	 */
	public List<IMusicalPiece> applyPerformerFilter(String userChoice){
		return this.mySongbank.filterByPerformer(userChoice);
	}
	
	/**
	 * This method tells the model to use the emotional tone filter
	 * and apply the chosen emotional tone passed in.
	 * @param userChoice, the emotional tone chosen
	 * @return a list of pieces that have that emotional tone
	 */
	public List<IMusicalPiece> applyToneFilter(String userChoice){
		return this.mySongbank.filterByTone(userChoice);
	}
	
	/**
	 * This method tells the model to use the era filter
	 * and apply the chosen era passed in.
	 * @param userChoice, the era chosen
	 * @return a list of pieces that were written in that time period 
	 */
	public List<IMusicalPiece> applyEraFilter(String userChoice){
		return this.mySongbank.filterByEra(userChoice);
	}
	
	/**
	 * This method provides a better general use method 
	 * to use when deciding which filter the model should pick.
	 * @param columnName, the column displayed in the GUI
	 * @param radioButtonText, the text displayed on the radio button in the GUI column
	 * @return a list of musical pieces filtered
	 */
	public List<IMusicalPiece> chooseFilterType(String columnName, String radioButtonText) {
        switch (columnName) {
     	case "Composer":
     		listByFilter = applyComposerFilter(radioButtonText);		         	
                 break;
        case "Language":
        	listByFilter = applyLanguageFilter(radioButtonText);
                 break;
        case "Performer":
        	listByFilter = applyPerformerFilter(radioButtonText);	            	
                 break;
        case "Emotion":
        	listByFilter = applyToneFilter(radioButtonText);
                 break;
        case "Type of Piece":
        	listByFilter = applyTypeFilter(radioButtonText);	            	
                 break;		       
        }
        return listByFilter;
             		        
	}
	
	/**
	 * This method adds a song to the songToPlay
	 * playlist, but only of the size of the filtered list
	 * is 1.
	 * @return boolean, true if a song was added, false if not
	 */
	public boolean addSongToPlayList() {
		if(listByFilter.size() == 1 && songToPlay.size() == 0) {
        	songToPlay.add(listByFilter.get(0));
        	return true;
		}
		return false;
	}
	
	/**
	 * This method asks the model for the description of the type
	 * of musical piece.
	 * @param type, the type written on the GUI radio button
	 * @return the string description
	 */
	public String getTypeHoverDescription(String type){
		return this.mySongbank.getTypeDescriptionsOfSongs(type);
	}
	
	
}