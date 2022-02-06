/**
 * Edited by Galen Otten
 * CS5004, Summer 2021
 * Final project - SongTest class  
 */


package testpackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import finalproject_modelpackage.Song;

/**
 * This test class tests the model class Song.
 */
public class SongTest {
	
	/**
	 * An instance of Song for testing the symphony type
	 */
	private Song mySongSymphony;
	
	/**
	 * An instance of Song for testing other song types
	 */
	private Song mySong;
	
	  @Before
	  public void setUp() {
		  mySong = new Song("De_fortune_me_doi_pleindre_et_loer", "Ballade", "French", "Machaut", 
				  "Musica_Nova", "Love", "Medieval", "https://www.youtube.com/embed/hLxwycS-m7c");
		  mySongSymphony = new Song("Symphony_in_E-minor,_\"Gaelic\"", "Symphony", "Instrumental", "Beach", 
				  "Järvi", "Homesick", "Romantic", "https://www.youtube.com/embed/hLxwycS-m7c");

	  }

	@Test
	public void testToString() {
		//only display title, composer, and performer, and for symphony display title and composer
		assertEquals(mySong.toString(), "\"De fortune me doi pleindre et loer\", Machaut, Musica Nova");
		assertEquals(mySongSymphony.toString(), "\"Symphony in E-minor, \"Gaelic\"\", Beach, Järvi");
		
		assertNotEquals(mySong.toString(), "\"De fortune me doi pleindre et loer\"" + "Ballade" + "French" + "Machaut" + 
				  "Musica_Nova" + "Love" + "Medieval" + "https://www.youtube.com/embed/hLxwycS-m7c");
		assertNotEquals(mySongSymphony.toString(), "\"Symphony in E-minor, \"Gaelic\"\"" + "Symphony" + "Instrumental" + "Beach" + 
				  "Järvi" + "Homesick" + "Romantic" + "https://www.youtube.com/embed/hLxwycS-m7c");

	}

	@Test
	public void testCleanUpTitle() {
		assertEquals(this.mySong.getTitle(), "De fortune me doi pleindre et loer");
		assertNotEquals(this.mySong.getTitle(), "De_fortune_me_doi_pleindre_et_loer");
		
		assertEquals(this.mySongSymphony.getTitle(), "Symphony in E-minor, \"Gaelic\"");
		assertNotEquals(this.mySongSymphony.getTitle(), "Symphony_in_E-minor,_\"Gaelic\"");
	}
	
	@Test
	public void testCleanUpPerformer() {
		assertEquals(this.mySong.getPerformer(), "Musica Nova");
		assertNotEquals(this.mySong.getPerformer(), "Musica_Nova");
	}
	
	@Test
	public void testMakeTypeDescription() {
		
		//test make description on the song, ballade type
		 assertEquals(this.mySong.makeTypeDescription(), "A narrative poem set to music.");		 
		 assertNotEquals(this.mySong.makeTypeDescription(), "A theatrical piece entirely set to music with singers and an orchestra.");
		 assertNotEquals(this.mySong.makeTypeDescription(), "Originally a Spanish, courtly dance, usually with a serious tone.");
		 assertNotEquals(this.mySong.makeTypeDescription(), "A form of French poetry and song, originating from a dance form.");
		 
		 //test make description on the song, symphony type
		 assertEquals(this.mySongSymphony.makeTypeDescription(), "A lengthy form of music for an orchestra.");			
		 assertNotEquals(this.mySongSymphony.makeTypeDescription(), "A narrative poem set to music.");
		 assertNotEquals(this.mySongSymphony.makeTypeDescription(), "A religious song of praise.");
		 assertNotEquals(this.mySongSymphony.makeTypeDescription(), "German word for songs, one song is a Lied.");

	}
}
