/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - Symphony class
 */

package finalproject_modelpackage;

/**
 * This class represents a symphony played by 
 * an orchestra without any singers. It extends
 * the abstract class, MusicalPiece,
 * which implements the interface, IMusicalPiece.
 * @author Galen Otten
 *
 */
public class Symphony extends MusicalPiece{

	/**
	 * This constructor passes in the attributes
	 * and calls to the super class, MusicalPiece.
	 * This class represents a piece of music
	 * played by an orchestra without any singers.
	 * @param title, the title of the piece
	 * @param type, the type of the piece
	 * @param language, the language used in the piece
	 * @param composerName, the name of the person who wrote the piece
	 * @param performerName, the name of the artist who performs the piece, orchestra name or conductor
	 * @param tone, the emotional tone of the piece
	 * @param era, the time period when the piece was written
	 * @param url, the YouTube link to the piece
	 */
	public Symphony(String title, String type, String composerNationality,
			String composerName, String performerName, String tone, String era, String url) {
		super(title, type, composerNationality, composerName, performerName, tone, era, url);
	}
	
	/**
	 * This method creates the string representation
	 * of the symhpony including information about 
	 * the title and composer.
	 * @return string, string of the piece
	 */
	@Override
	public String toString() {
		this.cleanUpTitle();
		return this.title + ",  " + this.getComposer();	
	}
}