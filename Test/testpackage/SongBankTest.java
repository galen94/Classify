/**
 * Edited by Galen Otten
 * CS5004, Summer 2021
 * Final project - SongBankTest class  
 */


package testpackage;


import org.junit.Test;

import finalproject_modelpackage.IMusicalPiece;
import finalproject_modelpackage.MusicalPiece;
import finalproject_modelpackage.SongBank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;

/**
 * This test class tests the model class SongBank.
 */
public class SongBankTest {

  /**
   * An instance of the SongBank for testing
   */
  private SongBank mySongBank;
  
  @Before
  public void setUp() {
	  mySongBank = new SongBank("songbank.txt");
  }

  @Test
  public void testFileReadIn() {
	  assertEquals(mySongBank.getSongBank().size(), 22);
	  assertTrue(mySongBank.getSongBank().get(0) instanceof MusicalPiece);
	  assertTrue(mySongBank.getSongBank().get(8) instanceof MusicalPiece);
	  assertTrue(mySongBank.getSongBank().get(18) instanceof MusicalPiece);
	  assertTrue(mySongBank.getSongBank().get(21) instanceof MusicalPiece);
	  
	  //test all attributes of the first song in the list
	  assertEquals(mySongBank.getSongBank().get(0).getTitle(), "Je vivroie liement");
	  assertEquals(mySongBank.getSongBank().get(0).getComposer(), "Machaut");
	  assertEquals(mySongBank.getSongBank().get(0).getEra(), "Medieval");
	  assertEquals(mySongBank.getSongBank().get(0).getEmotionalTone(), "Love");
	  assertEquals(mySongBank.getSongBank().get(0).getLanguage(), "French");
	  assertEquals(mySongBank.getSongBank().get(0).getType(), "Virelai");
	  assertEquals(mySongBank.getSongBank().get(0).getPerformer(), "Falsobordone");
	  assertEquals(mySongBank.getSongBank().get(0).getURL(), "https://www.youtube.com/embed/9ti59NdbG1c");
	  
	  //test all attributes of the last song in the list
	  assertEquals(mySongBank.getSongBank().get(21).getTitle(), "Symphony in E-minor, \"Gaelic\"");
	  assertEquals(mySongBank.getSongBank().get(21).getComposer(), "Beach");
	  assertEquals(mySongBank.getSongBank().get(21).getEra(), "Romantic");
	  assertEquals(mySongBank.getSongBank().get(21).getEmotionalTone(), "Homesick");
	  assertEquals(mySongBank.getSongBank().get(21).getLanguage(), "Instrumental");
	  assertEquals(mySongBank.getSongBank().get(21).getType(), "Symphony");
	  assertEquals(mySongBank.getSongBank().get(21).getPerformer(), "Järvi");
	  assertEquals(mySongBank.getSongBank().get(21).getURL(), "https://www.youtube.com/embed/VmLU1CfHcJw");
  }
  
  @Test
  public void testMakeComposerList() {	  
	  mySongBank.makeComposerList();
	  assertEquals(mySongBank.getComposerList().size(), 14);
	  String[] testBadComposerList = {"Machaut", "Byrd", "Machaut", "Purcell", "Purcell", "Palestrina", "Vivaldi",
			  "Mozart", "Puccini", "Delibes", "Schubert", "Puccini", "Wagner", "Monteverdi", "Schubert", "Verdi", "Mozart", 
		   	   "Puccini", "Handel", "Puccini", "Puccini", "Beach"};
	  
	  String[] testBadComposerList2 = {"Machaut", "Byrd", "Purcell", "Palestrina", "Vivaldi", "Mozart", "Puccini", "Delibes",
		   	   "Schubert", "Wagner", "Monteverdi", "Verdi", "Handel", "Beach"};

	  String[] testGoodComposerList = {"Beach", "Byrd", "Delibes", "Handel", "Machaut", "Monteverdi", 
			  "Mozart", "Palestrina", "Puccini", "Purcell", "Schubert", "Verdi", "Vivaldi", "Wagner"};
	  
	  //test that the composer list doesn't have duplicates
	  assertNotEquals(mySongBank.getComposerList().get(0), testBadComposerList[0]);
	  assertNotEquals(mySongBank.getComposerList().get(3), testBadComposerList[3]);
	  assertNotEquals(mySongBank.getComposerList().get(7), testBadComposerList[7]);
	  assertNotEquals(mySongBank.getComposerList().get(13), testBadComposerList[13]);

	  //test that the composer list sorted alphabetically
	  assertNotEquals(mySongBank.getComposerList().get(2), testBadComposerList2[2]);
	  assertNotEquals(mySongBank.getComposerList().get(6), testBadComposerList2[6]);
	  assertNotEquals(mySongBank.getComposerList().get(8), testBadComposerList2[8]);
	  assertNotEquals(mySongBank.getComposerList().get(12), testBadComposerList2[12]);

	  //test that the composer list is alphabetical and without duplicates
	  assertEquals(mySongBank.getComposerList().get(0), testGoodComposerList[0]);
	  assertEquals(mySongBank.getComposerList().get(1), testGoodComposerList[1]);
	  assertEquals(mySongBank.getComposerList().get(2), testGoodComposerList[2]);
	  assertEquals(mySongBank.getComposerList().get(3), testGoodComposerList[3]);
	  assertEquals(mySongBank.getComposerList().get(4), testGoodComposerList[4]);
	  assertEquals(mySongBank.getComposerList().get(5), testGoodComposerList[5]);
	  assertEquals(mySongBank.getComposerList().get(6), testGoodComposerList[6]);
	  assertEquals(mySongBank.getComposerList().get(7), testGoodComposerList[7]);
	  assertEquals(mySongBank.getComposerList().get(8), testGoodComposerList[8]);
	  assertEquals(mySongBank.getComposerList().get(9), testGoodComposerList[9]);
	  assertEquals(mySongBank.getComposerList().get(10), testGoodComposerList[10]);
	  assertEquals(mySongBank.getComposerList().get(11), testGoodComposerList[11]);
	  assertEquals(mySongBank.getComposerList().get(12), testGoodComposerList[12]);
	  assertEquals(mySongBank.getComposerList().get(13), testGoodComposerList[13]);
  }
  
  @Test
  public void testMakeLanguageList() {
	  mySongBank.makeLanguageList();
	  assertEquals(mySongBank.getLanguageList().size(), 6);
	  String[] testBadLanguageList = {"French", "Latin", "French", "English", "English", "Latin", "Italian", "Italian", "Italian", 
			  "French", "German", "Italian", "German", "Italian", "Schubert", "Italian", 
			  "Instrumental", "Italian", "Italian", "Italian", "Italian", "Instrumental"};
	  
	  String[] testBadLanguageList2 = {"French", "Latin", "English", "Latin", "Italian", "Instrumental", "Italian", "French",
		   	   "German", "German", "Italian", "Italian", "Italian", "Instrumental"};

	  String[] testGoodLanguageList = {"English", "French", "German", "Instrumental", "Italian", "Latin"};
	  
	  //test that the composer list doesn't have duplicates
	  assertNotEquals(mySongBank.getLanguageList().get(0), testBadLanguageList[0]);
	  assertNotEquals(mySongBank.getLanguageList().get(1), testBadLanguageList[1]);
	  assertNotEquals(mySongBank.getLanguageList().get(2), testBadLanguageList[2]);
	  assertNotEquals(mySongBank.getLanguageList().get(3), testBadLanguageList[3]);

	  //test that the composer list sorted alphabetically
	  assertNotEquals(mySongBank.getLanguageList().get(0), testBadLanguageList2[0]);
	  assertNotEquals(mySongBank.getLanguageList().get(1), testBadLanguageList2[1]);
	  assertNotEquals(mySongBank.getLanguageList().get(2), testBadLanguageList2[2]);
	  assertNotEquals(mySongBank.getLanguageList().get(3), testBadLanguageList2[3]);

	  //test that the composer list is alphabetical and without duplicates
	  assertEquals(mySongBank.getLanguageList().get(0), testGoodLanguageList[0]);
	  assertEquals(mySongBank.getLanguageList().get(1), testGoodLanguageList[1]);
	  assertEquals(mySongBank.getLanguageList().get(2), testGoodLanguageList[2]);
	  assertEquals(mySongBank.getLanguageList().get(3), testGoodLanguageList[3]);
	  assertEquals(mySongBank.getLanguageList().get(4), testGoodLanguageList[4]);
	  assertEquals(mySongBank.getLanguageList().get(5), testGoodLanguageList[5]);
  }
  
  @Test
  public void testMakePerformerList() {	  
	  mySongBank.makePerformerList();
	  assertEquals(mySongBank.getPerformerList().size(), 18);
	  String[] testBadPerformerList = {"Falsobordone", "The Sixteen", "Musica Nova", "Gens", "Gardiner", "Tallis", "Orlinski", 
			  "Popp", "Gheorghiu", "Dessay", "Prey", "Pavarotti", "Flagstad", "Gardiner", 
			  "Bostridge", "Pavarotti", "SCO", "Pavarotti", "Lezhneva", "Tebaldi", "Pavarotti", "Järvi"};
	  
	  String[] testBadPerformerList2 = {"Falsobordone", "The Sixteen", "Musica Nova", "Gens", "Gardiner", "Tallis", "Orlinski", 
			  "Popp", "Gheorghiu", "Dessay", "Prey", "Pavarotti", "Flagstad", 
			  "Bostridge", "SCO", "Lezhneva", "Tebaldi", "Järvi"};

	  String[] testGoodPerformerList = {"Bostridge", "Dessay", "Falsobordone", "Flagstad", "Gardiner", "Gens", "Gheorghiu", "Järvi",
			  "Lezhneva", "Musica Nova", "Orlinski", "Pavarotti", "Popp", "Prey", "SCO", "The Sixteen", "Tallis", "Tebaldi"};
		
	  
	  //test that the composer list doesn't have duplicates
	  assertNotEquals(mySongBank.getPerformerList().get(0), testBadPerformerList[0]);
	  assertNotEquals(mySongBank.getPerformerList().get(3), testBadPerformerList[3]);
	  assertNotEquals(mySongBank.getPerformerList().get(7), testBadPerformerList[7]);
	  assertNotEquals(mySongBank.getPerformerList().get(13), testBadPerformerList[13]);

	  //test that the composer list sorted alphabetically
	  assertNotEquals(mySongBank.getPerformerList().get(2), testBadPerformerList2[2]);
	  assertNotEquals(mySongBank.getPerformerList().get(6), testBadPerformerList2[6]);
	  assertNotEquals(mySongBank.getPerformerList().get(8), testBadPerformerList2[8]);
	  assertNotEquals(mySongBank.getPerformerList().get(12), testBadPerformerList2[12]);

	  //test that the composer list is alphabetical and without duplicates
	  assertEquals(mySongBank.getPerformerList().get(0), testGoodPerformerList[0]);
	  assertEquals(mySongBank.getPerformerList().get(1), testGoodPerformerList[1]);
	  assertEquals(mySongBank.getPerformerList().get(2), testGoodPerformerList[2]);
	  assertEquals(mySongBank.getPerformerList().get(3), testGoodPerformerList[3]);
	  assertEquals(mySongBank.getPerformerList().get(4), testGoodPerformerList[4]);
	  assertEquals(mySongBank.getPerformerList().get(5), testGoodPerformerList[5]);
	  assertEquals(mySongBank.getPerformerList().get(6), testGoodPerformerList[6]);
	  assertEquals(mySongBank.getPerformerList().get(7), testGoodPerformerList[7]);
	  assertEquals(mySongBank.getPerformerList().get(8), testGoodPerformerList[8]);
	  assertEquals(mySongBank.getPerformerList().get(9), testGoodPerformerList[9]);
	  assertEquals(mySongBank.getPerformerList().get(10), testGoodPerformerList[10]);
	  assertEquals(mySongBank.getPerformerList().get(11), testGoodPerformerList[11]);
	  assertEquals(mySongBank.getPerformerList().get(12), testGoodPerformerList[12]);
	  assertEquals(mySongBank.getPerformerList().get(13), testGoodPerformerList[13]);
  }
  
  @Test
  public void testMakeToneList() {	  
	  mySongBank.makeToneList();
	  assertEquals(mySongBank.getToneList().size(), 10);
	  
	  String[] testBadToneList = {"Love", "Spiritual", "Love", "Love", "Nature", "Spiritual", "Love", 
			  "Seductive", "Sad", "Magical", "Happy", "Sad", "Triumphant", "Magical", 
			  "Anxious", "Love", "Triumphant", "Love", "Triumphant", "Love", "Triumphant", "Homesick"};
	  
	  String[] testBadToneList2 = {"Love", "Spiritual", "Nature", "Seductive", "Sad", "Magical", 
			  "Happy", "Triumphant", "Anxious", "Homesick"};

	  String[] testGoodToneList = {"Anxious", "Happy", "Homesick", "Love", "Magical", "Nature", "Sad", "Seductive",
			  "Spiritual", "Triumphant"};
	  
		  
	  //test that the composer list doesn't have duplicates
	  assertNotEquals(mySongBank.getToneList().get(0), testBadToneList[0]);
	  assertNotEquals(mySongBank.getToneList().get(1), testBadToneList[1]);
	  assertNotEquals(mySongBank.getToneList().get(2), testBadToneList[2]);

	  //test that the composer list sorted alphabetically
	  assertNotEquals(mySongBank.getToneList().get(0), testBadToneList2[0]);
	  assertNotEquals(mySongBank.getToneList().get(1), testBadToneList2[1]);
	  assertNotEquals(mySongBank.getToneList().get(2), testBadToneList2[2]);
	  assertNotEquals(mySongBank.getToneList().get(3), testBadToneList2[3]);

	  //test that the composer list is alphabetical and without duplicates
	  assertEquals(mySongBank.getToneList().get(0), testGoodToneList[0]);
	  assertEquals(mySongBank.getToneList().get(1), testGoodToneList[1]);
	  assertEquals(mySongBank.getToneList().get(2), testGoodToneList[2]);
	  assertEquals(mySongBank.getToneList().get(3), testGoodToneList[3]);
	  assertEquals(mySongBank.getToneList().get(4), testGoodToneList[4]);
	  assertEquals(mySongBank.getToneList().get(5), testGoodToneList[5]);
	  assertEquals(mySongBank.getToneList().get(6), testGoodToneList[6]);
	  assertEquals(mySongBank.getToneList().get(7), testGoodToneList[7]);
	  assertEquals(mySongBank.getToneList().get(8), testGoodToneList[8]);
	  assertEquals(mySongBank.getToneList().get(9), testGoodToneList[9]);
  }

  @Test
  public void testMakeTypeList() {	  
	  mySongBank.makeTypeList();
	  assertEquals(mySongBank.getTypeList().size(), 9);
	  
	  String[] testBadTypeList = {"Virelai", "Hymn", "Ballade", "Passacaglia", "Aria", "Mass", "Aria", 
			  "Aria", "Aria", "Aria", "Lieder", "Opera", "Aria", "Opera", 
			  "Lieder", "Aria", "Symphony", "Aria", "Aria", "Aria", "Aria", "Symphony"};
	  
	  String[] testBadTypeList2 = {"Virelai", "Hymn", "Ballade", "Passacaglia", "Aria", "Mass", "Lieder", 
			  "Opera", "Symphony"};

	  String[] testGoodTypeList = {"Aria", "Ballade", "Hymn", "Lieder", "Mass", "Opera", "Passacaglia", "Symphony", "Virelai"};
	  
		  
	  //test that the composer list doesn't have duplicates
	  assertNotEquals(mySongBank.getTypeList().get(0), testBadTypeList[0]);
	  assertNotEquals(mySongBank.getTypeList().get(1), testBadTypeList[1]);
	  assertNotEquals(mySongBank.getTypeList().get(2), testBadTypeList[2]);

	  //test that the composer list sorted alphabetically
	  assertNotEquals(mySongBank.getTypeList().get(0), testBadTypeList2[0]);
	  assertNotEquals(mySongBank.getTypeList().get(1), testBadTypeList2[1]);
	  assertNotEquals(mySongBank.getTypeList().get(2), testBadTypeList2[2]);
	  assertNotEquals(mySongBank.getTypeList().get(3), testBadTypeList2[3]);

	  //test that the composer list is alphabetical and without duplicates
	  assertEquals(mySongBank.getTypeList().get(0), testGoodTypeList[0]);
	  assertEquals(mySongBank.getTypeList().get(1), testGoodTypeList[1]);
	  assertEquals(mySongBank.getTypeList().get(2), testGoodTypeList[2]);
	  assertEquals(mySongBank.getTypeList().get(3), testGoodTypeList[3]);
	  assertEquals(mySongBank.getTypeList().get(4), testGoodTypeList[4]);
	  assertEquals(mySongBank.getTypeList().get(5), testGoodTypeList[5]);
	  assertEquals(mySongBank.getTypeList().get(6), testGoodTypeList[6]);
	  assertEquals(mySongBank.getTypeList().get(7), testGoodTypeList[7]);
	  assertEquals(mySongBank.getTypeList().get(8), testGoodTypeList[8]);
  }
  
  @Test
  public void testApplyUserFilterChoiceComposer() {	  
	  List<String> filteredList =  mySongBank.applyUserFilterChoice("Composer");
	  assertEquals(filteredList.get(0), "Beach");
	  assertNotEquals(filteredList.get(0), "Machaut");
	  assertNotEquals(filteredList.get(0), "French");
	  assertNotEquals(filteredList.get(0), "Aria");
	  assertNotEquals(filteredList.get(0), "Bostridge");
	  assertNotEquals(filteredList.get(0), "Anxious");
  }
  
  @Test
  public void testApplyUserFilterChoicePerformer() {	  
	  List<String> filteredList =  mySongBank.applyUserFilterChoice("Performer");
	  assertEquals(filteredList.get(0), "Bostridge");
	  assertNotEquals(filteredList.get(0), "Falsobordone");
	  assertNotEquals(filteredList.get(0), "French");
	  assertNotEquals(filteredList.get(0), "Aria");
	  assertNotEquals(filteredList.get(0), "Beach");
	  assertNotEquals(filteredList.get(0), "Anxious");
  }
  
  @Test
  public void testApplyUserFilterChoiceLanguage() {	  
	  List<String> filteredList =  mySongBank.applyUserFilterChoice("Language");
	  assertEquals(filteredList.get(0), "English");
	  assertNotEquals(filteredList.get(0), "French");
	  assertNotEquals(filteredList.get(0), "Machaut");
	  assertNotEquals(filteredList.get(0), "Aria");
	  assertNotEquals(filteredList.get(0), "Bostridge");
	  assertNotEquals(filteredList.get(0), "Anxious");
  }
  
  @Test
  public void testApplyUserFilterChoiceTone() {	  
	  List<String> filteredList =  mySongBank.applyUserFilterChoice("Emotional Tone");
	  assertEquals(filteredList.get(0), "Anxious");
	  assertNotEquals(filteredList.get(0), "Love");
	  assertNotEquals(filteredList.get(0), "French");
	  assertNotEquals(filteredList.get(0), "Aria");
	  assertNotEquals(filteredList.get(0), "Bostridge");
	  assertNotEquals(filteredList.get(0), "Machaut");
  }
  
  @Test
  public void testApplyUserFilterChoiceType() {	  
	  List<String> filteredList =  mySongBank.applyUserFilterChoice("Piece Type");
	  assertEquals(filteredList.get(0), "Aria");
	  assertNotEquals(filteredList.get(0), "Virelai");
	  assertNotEquals(filteredList.get(0), "French");
	  assertNotEquals(filteredList.get(0), "Beach");
	  assertNotEquals(filteredList.get(0), "Bostridge");
	  assertNotEquals(filteredList.get(0), "Anxious");
  }

  @Test
  public void testGetTypeDescriptionsOfSongs() {
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Aria"), "A solo vocal piece, usually in an opera.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Mass"), "A sacred musical composition sung by a church choir.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Opera"), "A theatrical piece entirely set to music with singers and an orchestra.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Passacaglia"), "Originally a Spanish, courtly dance, usually with a serious tone.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Hymn"), "A religious song of praise.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Virelai"), "A form of French poetry and song, originating from a dance form.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Ballade"), "A narrative poem set to music.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Symphony"), "A lengthy form of music for an orchestra.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs("Lieder"), "German word for songs, one song is a Lied.");
	  assertEquals(mySongBank.getTypeDescriptionsOfSongs(""), "");
  }
 
  @Test
  public void testComposerFilter() {
	  List<IMusicalPiece> listByMozart = this.mySongBank.filterByComposer("Mozart");
	  
	  assertEquals(listByMozart.size(), 2);
	  assertFalse(listByMozart.size() == 22);
	  
	  assertEquals(listByMozart.get(0).getTitle(), "Vedrai Carino");
	  assertEquals(listByMozart.get(1).getTitle(), "Symphony 41, \"Jupiter\"");
	  assertEquals(listByMozart.get(0).getComposer(), "Mozart");
	  assertEquals(listByMozart.get(1).getComposer(), "Mozart");
  }
  
  @Test
  public void testPerformerFilter() {
	  List<IMusicalPiece> listByPavarotti = this.mySongBank.filterByPerformer("Pavarotti");
	  
	  assertEquals(listByPavarotti.size(), 4);
	  assertFalse(listByPavarotti.size() == 22);
	  
	  assertEquals(listByPavarotti.get(0).getTitle(), "La Boheme");
	  assertEquals(listByPavarotti.get(1).getTitle(), "La donna e mobile");
	  assertEquals(listByPavarotti.get(2).getTitle(), "Che gelida manina");
	  assertEquals(listByPavarotti.get(3).getTitle(), "Nessun Dorma");
	  
	  assertEquals(listByPavarotti.get(0).getPerformer(), "Pavarotti");
	  assertEquals(listByPavarotti.get(1).getPerformer(), "Pavarotti");
	  assertEquals(listByPavarotti.get(2).getPerformer(), "Pavarotti");
	  assertEquals(listByPavarotti.get(3).getPerformer(), "Pavarotti");
	  
	  assertNotEquals(listByPavarotti.get(0).getComposer(), "Mozart");
	  assertNotEquals(listByPavarotti.get(1).getComposer(), "Mozart");
  }
  
  @Test
  public void testToneFilter() {
	  List<IMusicalPiece> listByLove = this.mySongBank.filterByTone("Love");
	  
	  assertEquals(listByLove.size(),7);
	  assertFalse(listByLove.size() == 22);
	  
	  assertEquals(listByLove.get(0).getTitle(), "Je vivroie liement");
	  assertEquals(listByLove.get(1).getTitle(), "De fortune me doi pleindre et loer");
	  assertEquals(listByLove.get(2).getTitle(), "How happy the lover");
	  assertEquals(listByLove.get(3).getTitle(), "Vedro con mio diletto");
	  assertEquals(listByLove.get(4).getTitle(), "La donna e mobile");
	  assertEquals(listByLove.get(5).getTitle(), "Che gelida manina");
	  assertEquals(listByLove.get(6).getTitle(), "Un bel di vedremo");
	  
	  assertEquals(listByLove.get(0).getEmotionalTone(), "Love");
	  assertEquals(listByLove.get(1).getEmotionalTone(), "Love");
	  assertEquals(listByLove.get(2).getEmotionalTone(), "Love");
	  assertEquals(listByLove.get(3).getEmotionalTone(), "Love");
	  assertEquals(listByLove.get(4).getEmotionalTone(), "Love");
	  assertEquals(listByLove.get(5).getEmotionalTone(), "Love");
	  assertEquals(listByLove.get(6).getEmotionalTone(), "Love");
  }
  
  @Test
  public void testLanguageFilter() {
	  List<IMusicalPiece> listByFrench = this.mySongBank.filterByLanguage("French");
	  
	  assertEquals(listByFrench.size(), 3);
	  assertFalse(listByFrench.size() == 22);
	  
	  assertEquals(listByFrench.get(0).getLanguage(), "French");
	  assertEquals(listByFrench.get(1).getLanguage(), "French");
	  assertEquals(listByFrench.get(2).getLanguage(), "French");
	  
	  assertEquals(listByFrench.get(0).getTitle(), "Je vivroie liement");
	  assertEquals(listByFrench.get(1).getTitle(), "De fortune me doi pleindre et loer");
	  assertEquals(listByFrench.get(2).getTitle(), "Ou va la jeune hindou");
  }
  
  @Test
  public void testTypeFilter() {
	  List<IMusicalPiece> listByLieder = this.mySongBank.filterByType("Lieder");
	  
	  assertEquals(listByLieder.size(), 2);
	  assertFalse(listByLieder.size() == 22);
	  
	  assertEquals(listByLieder.get(0).getType(), "Lieder");
	  assertEquals(listByLieder.get(1).getType(), "Lieder");
	  	  
	  assertEquals(listByLieder.get(0).getTitle(), "Das Wandern");
	  assertEquals(listByLieder.get(1).getTitle(), "Erlkoenig");
	  
	  assertEquals(listByLieder.get(0).getComposer(), "Schubert");
	  assertEquals(listByLieder.get(1).getComposer(), "Schubert");
  }
  
  @Test
  public void testEraFilter() {
	  List<IMusicalPiece> listByBaroque = this.mySongBank.filterByEra("Baroque");
	  
	  assertEquals(listByBaroque.size(), 4);
	  assertFalse(listByBaroque.size() == 22);
	  
	  assertEquals(listByBaroque.get(0).getEra(), "Baroque");
	  assertEquals(listByBaroque.get(1).getEra(), "Baroque");
	  assertEquals(listByBaroque.get(2).getEra(), "Baroque");
	  assertEquals(listByBaroque.get(3).getEra(), "Baroque");
	  	  
	  assertEquals(listByBaroque.get(0).getTitle(), "How happy the lover");
	  assertEquals(listByBaroque.get(1).getTitle(), "Thus the ever Grateful Spring");
	  assertEquals(listByBaroque.get(2).getTitle(), "Vedro con mio diletto");
	  assertEquals(listByBaroque.get(3).getTitle(), "L'Orfeo");
  }
  
  

 
}
