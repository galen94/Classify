/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - Composer class
 */

package finalproject_modelpackage;

/**
 * This class represents a composer who
 * created the music for each musical piece.
 * @author Galen Otten
 */
public class Composer{
	
	/**
	 * Name of composer
	 */
	private String name;
	
	/**
	 * This constructor creates a composer object
	 * with the given name, usually the last name.
	 * @param name, name of composer
	 */
	public Composer(String name) {
		this.name = name;
	}
	
	/**
	 * This method gets the name of the composer.
	 * @return string, name of composer
	 */
	public String getName() {
		return this.name;
	}
}