/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - IMusicalPiece interface
 */

package finalproject_modelpackage;

/**
 * This interface enforces all abilities
 * of a IMusicalPiece object.
 * 
 * @author Galen Otten
 *
 */
public interface IMusicalPiece{
	
	/**
	 * This method gets the title of the piece.
	 * 
	 * @return title, the title of the piece
	 */
	public String getTitle();
	
	/**
	 * This method removes all underscores
	 * from the title string.
	 */
	public void cleanUpTitle();
	
	/**
	 * This method removes all underscores
	 * from the performer title.
	 */
	public void cleanUpPerformer();
	
	/**
	 * This method gets the composer's
	 * name.
	 * 
	 * @return name, the composer's name 
	 */
	public String getComposer();
	
	/**
	 * This method gets the time period when the
	 * piece was written.
	 * 
	 * @return era, the time period
	 */
	public String getEra();
	
	/**
	 * This method first cleans, then 
	 * gets the performer's name.
	 * 
	 * @return name, the performer's name
	 */
	public String getPerformer();
	
	/**
	 * This method gets the type of
	 * the musical piece.
	 * 
	 * @return type, the type of the piece
	 */
	public String getType();
	
	/**
	 * This method gets the emotion/subject
	 * of the piece.
	 * 
	 * @return tone, the emotion of the piece
	 */
	public String getEmotionalTone();
	
	/**
	 * This method gets the language
	 * of the piece, what the singer is
	 * singing in or if it's an instrumental piece.
	 * 
	 * @return language, language of the piece
	 */
	public String getLanguage();
	
	/**
	 * This method gets to YouTube link
	 * of the song.
	 * 
	 * @return URL, the YouTube link
	 */
	public String getURL();
	
	/**
	 * This method gets the description
	 * of the type of piece. 
	 * 
	 * @return typeDescription, the explanation of the type
	 */
	public String makeTypeDescription();
	
	
	
}