/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - Song class
 */

package finalproject_modelpackage;

/**
 * This class represents a song
 * or a piece of music. It extends
 * the abstract class, MusicalPiece,
 * which implements the interface, IMusicalPiece.
 * This is a general class that I'm using for now.
 * In the future, I will create a variety of individualized
 * classes that all extend MusicalPiece that have a couple unique
 * functionalities.
 * @author Galen Otten
 *
 */
public class Song extends MusicalPiece{

	/**
	 * This constructor passes in the attributes
	 * and calls to the super class, MusicalPiece.
	 * @param title, the title of the piece
	 * @param type, the type of the piece
	 * @param language, the language used in the piece
	 * @param composerName, the name of the person who wrote the piece
	 * @param performerName, the name of the artist who performs the piece
	 * @param tone, the emotional tone of the piece
	 * @param era, the time period when the piece was written
	 * @param url, the YouTube link to the piece
	 */
	public Song(String title, String type, String language, String composerName,
			String performerName, String tone, String era, String url) {
		super(title, type, language, composerName, performerName, tone, era, url);
	}		
}