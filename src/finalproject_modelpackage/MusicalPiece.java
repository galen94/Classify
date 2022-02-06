/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - MusicalPiece
 * abstract class
 */

package finalproject_modelpackage;


/**
 * This class implements the common functions
 * between all IMusicalPiece objects.
 * @author Galen Otten
 *
 */
public abstract class MusicalPiece implements IMusicalPiece{

	/**
	 * The title of the piece
	 */
	protected String title;
	
	/**
	 * The genre of the piece
	 */
	protected String genre;
	
	/**
	 * The emotional tone/subject of the piece
	 */
	protected String tone;
	
	/**
	 * The time period when the piece was composed/written
	 */
	protected String era;
	
	/**
	 * The type of musical piece it is
	 */
	protected String type;
	
	/**
	 * The string description of the type
	 */
	protected String typeDescription;
	
	/**
	 * The language of the piece, either 
	 * a sung musical piece which uses a spoken language
	 * or an instrumental piece
	 */
	protected String language;
	
	/**
	 * The composer of the piece, the person
	 * who wrote the music, but not always the words
	 */
	protected Composer composer;
	
	/**
	 * The artist who performs the piece
	 */
	protected String performer;
	
	/**
	 * The YouTube link to the piece
	 */
	protected String URL;
	
	/**
	 * This indicates whether or not this musical piece
	 * has been filtered out of the list of songs to play.
	 */
	protected boolean filteredOut;
	
	/**
	 * This constructor is used by the subclass, Song,
	 * to construct the MusicalPiece.
	 * 
	 * @param title, the title of the piece
	 * @param type, the type of the piece
	 * @param language, the language used in the piece
	 * @param composerName, the name of the person who wrote the piece
	 * @param performerName, the name of the artist who performs the piece
	 * @param tone, the emotional tone of the piece
	 * @param era, the time period when the piece was written
	 * @param url, the YouTube link to the piece
	 */
	protected MusicalPiece(String title, String type, String language, String composerName, 
			String performerName, String tone, String era, String url) {
		this.title = title;
		this.tone = tone;
		this.type = type;
		this.language = language;
		this.composer = new Composer(composerName);
		this.performer = performerName;		
		this.era = era;
		this.URL = url;
		this.filteredOut = false;
	}

	/**
	 * This method gets the title of the piece.
	 * 
	 * @return title, the title of the piece
	 */
	@Override
	public String getTitle() {
		this.cleanUpTitle();
		return this.title;
	}

	/**
	 * This method gets the composer's
	 * name.
	 * 
	 * @return name, the composer's name 
	 */
	@Override
	public String getComposer() {
		return this.composer.getName();
	}

	/**
	 * This method gets the time period when the
	 * piece was written.
	 * 
	 * @return era, the time period
	 */
	@Override
	public String getEra() {
		return this.era;
	}

	/**
	 * This method first cleans, then
	 * gets the performer's name.
	 * 
	 * @return name, the performer's name
	 */
	@Override
	public String getPerformer() {
		this.cleanUpPerformer();
		return this.performer;
	}

	/**
	 * This method gets the type of
	 * the musical piece.
	 * 
	 * @return type, the type of the piece
	 */
	@Override
	public String getType() {
		return this.type;
	}

	/**
	 * This method gets the emotion/subject
	 * of the piece.
	 * 
	 * @return tone, the emotion of the piece
	 */
	@Override
	public String getEmotionalTone() {
		return this.tone;
	}

	/**
	 * This method gets the language
	 * of the piece, what the singer is
	 * singing in or if it's an instrumental piece.
	 * 
	 * @return language, language of the piece
	 */
	@Override
	public String getLanguage() {
		return this.language;
	}

	/**
	 * This method gets to YouTube link
	 * of the song.
	 * 
	 * @return URL, the YouTube link
	 */
	@Override
	public String getURL() {
		return this.URL;
	}
	
	/**
	 * This method removes all underscores
	 * from the title string.
	 */
	@Override
	public void cleanUpTitle() {
		String[] wordsInTitle = this.title.split("_");		
		this.title = String.join(" ", wordsInTitle); 
	}
	
	/**
	 * This method removes all underscores
	 * from the performer title.
	 */
	@Override
	public void cleanUpPerformer() {
		String[] wordsInTitle = this.performer.split("_");		
		this.performer = String.join(" ", wordsInTitle); 
	}
	
	/**
	 * This method creates the string representation
	 * of the piece including information about 
	 * the title, composer, and performer.
	 * @return string, string of the piece
	 */
	@Override
	public String toString() {
		this.cleanUpTitle();
		return "\"" + this.title + "\", " + this.getComposer() + ", " + this.getPerformer();	
	}
	
	/**
	 * This method gets the description
	 * of the type of piece. 
	 * 
	 * @return typeDescription, the explanation of the type
	 */
	@Override
	public String makeTypeDescription() {
		switch (this.type) {
     	case "Aria":
     		this.typeDescription = "A solo vocal piece, usually in an opera.";    		
                 break;
        case "Mass":
        	this.typeDescription = "A sacred musical composition sung by a church choir.";        	
                 break;
        case "Opera":
        	this.typeDescription = "A theatrical piece entirely set to music with singers and an orchestra.";
                 break;
        case "Passacaglia":
        	this.typeDescription = "Originally a Spanish, courtly dance, usually with a serious tone.";
                 break;
        case "Hymn":
        	this.typeDescription = "A religious song of praise.";
                 break;
        case "Virelai":
        	this.typeDescription = "A form of French poetry and song, originating from a dance form.";
                 break;
        case "Ballade":
        	this.typeDescription = "A narrative poem set to music.";
                 break;
        case "Symphony":
        	this.typeDescription = "A lengthy form of music for an orchestra.";
                 break;
        case "Lieder":
        	this.typeDescription = "German word for songs, one song is a Lied.";
                 break;
        default: this.type = "";
                 break;
		}
                 
		return this.typeDescription;
	}
	
}