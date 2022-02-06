/**
 * Edited by Galen Otten
 * CS5004, Summer 2021
 * Final project - MyControllerTest class
 */

package testpackage;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import finalproject_controllerpackage.MyController;
import finalproject_modelpackage.IMusicalPiece;

/**
 * This test class tests the controller class MyController.
 */
public class MyControllerTest {
	
	/**
	 * A controller object for testing
	 */
	private MyController myController;
	  
	@Before
    public void setUp() {
		myController = new MyController();
	}

	@Test
	public void testResetFilterSongList() {
		assertEquals(this.myController.getSongBankObject().getFilteredSongBank().size(), 22);//before filtering anything
		this.myController.getSongBankObject().filterByComposer("Puccini");
		assertEquals(this.myController.getSongBankObject().getFilteredSongBank().size(), 5);//after Puccini filter
		
		this.myController.resetFilterSongList();
		assertEquals(this.myController.getSongBankObject().getFilteredSongBank().size(), 22);//reset to all songs
	}
	
	@Test
	  public void testApplyUserFilterChoiceComposerController() {	  
		  List<String> filteredList =  this.myController.getSongBankObject().applyUserFilterChoice("Composer");
		  assertEquals(filteredList.get(0), "Beach");
		  assertNotEquals(filteredList.get(0), "Machaut");
		  assertNotEquals(filteredList.get(0), "French");
		  assertNotEquals(filteredList.get(0), "Aria");
		  assertNotEquals(filteredList.get(0), "Bostridge");
		  assertNotEquals(filteredList.get(0), "Anxious");
	  }
	  
	  @Test
	  public void testApplyUserFilterChoicePerformerController() {	  
		  List<String> filteredList =  this.myController.getSongBankObject().applyUserFilterChoice("Performer");
		  assertEquals(filteredList.get(0), "Bostridge");
		  assertNotEquals(filteredList.get(0), "Falsobordone");
		  assertNotEquals(filteredList.get(0), "French");
		  assertNotEquals(filteredList.get(0), "Aria");
		  assertNotEquals(filteredList.get(0), "Beach");
		  assertNotEquals(filteredList.get(0), "Anxious");
	  }
	  
	  @Test
	  public void testApplyUserFilterChoiceLanguageController() {	  
		  List<String> filteredList =  this.myController.getSongBankObject().applyUserFilterChoice("Language");
		  assertEquals(filteredList.get(0), "English");
		  assertNotEquals(filteredList.get(0), "French");
		  assertNotEquals(filteredList.get(0), "Machaut");
		  assertNotEquals(filteredList.get(0), "Aria");
		  assertNotEquals(filteredList.get(0), "Bostridge");
		  assertNotEquals(filteredList.get(0), "Anxious");
	  }
	  
	  @Test
	  public void testApplyUserFilterChoiceToneController() {	  
		  List<String> filteredList =  this.myController.getSongBankObject().applyUserFilterChoice("Emotional Tone");
		  assertEquals(filteredList.get(0), "Anxious");
		  assertNotEquals(filteredList.get(0), "Love");
		  assertNotEquals(filteredList.get(0), "French");
		  assertNotEquals(filteredList.get(0), "Aria");
		  assertNotEquals(filteredList.get(0), "Bostridge");
		  assertNotEquals(filteredList.get(0), "Machaut");
	  }
	  
	  @Test
	  public void testApplyUserFilterChoiceTypeController() {	  
		  List<String> filteredList =  this.myController.getSongBankObject().applyUserFilterChoice("Piece Type");
		  assertEquals(filteredList.get(0), "Aria");
		  assertNotEquals(filteredList.get(0), "Virelai");
		  assertNotEquals(filteredList.get(0), "French");
		  assertNotEquals(filteredList.get(0), "Beach");
		  assertNotEquals(filteredList.get(0), "Bostridge");
		  assertNotEquals(filteredList.get(0), "Anxious");
	  }

	  @Test
	  public void testGetTypeHoverDescriptionController() {
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Aria"), "A solo vocal piece, usually in an opera.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Mass"), "A sacred musical composition sung by a church choir.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Opera"), "A theatrical piece entirely set to music with singers and an orchestra.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Passacaglia"), "Originally a Spanish, courtly dance, usually with a serious tone.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Hymn"), "A religious song of praise.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Virelai"), "A form of French poetry and song, originating from a dance form.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Ballade"), "A narrative poem set to music.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Symphony"), "A lengthy form of music for an orchestra.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs("Lieder"), "German word for songs, one song is a Lied.");
		  assertEquals(this.myController.getSongBankObject().getTypeDescriptionsOfSongs(""), "");
	  }
	 

	  @Test
	  public void testChooseFilterType() {
		  List<IMusicalPiece> filteredList = this.myController.chooseFilterType("Performer", "Orlinski");
		  assertEquals(filteredList.get(0).getLanguage(), "Italian");
		  assertEquals(filteredList.get(0).getType(), "Aria");
		  assertEquals(filteredList.size(), 1);
		  
		  this.myController.resetFilterSongList();
		  List<IMusicalPiece> filteredList2 = this.myController.chooseFilterType("Type of Piece", "Mass");
		  assertEquals(filteredList2.get(0).getLanguage(), "Latin");
		  assertEquals(filteredList2.get(0).getComposer(), "Palestrina");
		  assertEquals(filteredList2.size(), 1);
		  
		  this.myController.resetFilterSongList();
		  List<IMusicalPiece> filteredList3 = this.myController.chooseFilterType("Composer", "Handel");
		  assertEquals(filteredList3.get(0).getLanguage(), "Italian");
		  assertEquals(filteredList3.get(0).getPerformer(), "Lezhneva");
		  assertEquals(filteredList3.size(), 1);
		  
		  this.myController.resetFilterSongList();
		  List<IMusicalPiece> filteredList4 = this.myController.chooseFilterType("Emotion", "Sad");
		  assertEquals(filteredList4.get(0).getLanguage(), "Italian");
		  assertEquals(filteredList4.get(0).getTitle(), "Vissi d'Arte");
		  assertEquals(filteredList4.size(), 2);
		  
		  this.myController.resetFilterSongList();
		  List<IMusicalPiece> filteredList5 = this.myController.chooseFilterType("Language", "Latin");
		  assertEquals(filteredList5.get(0).getTitle(), "Ave verum corpus");
		  assertEquals(filteredList5.get(0).getType(), "Hymn");
		  assertEquals(filteredList5.size(), 2);		 
	  }
}
