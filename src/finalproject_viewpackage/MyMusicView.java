/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - MyMusicView class
 */

package finalproject_viewpackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import finalproject_controllerpackage.MyController;
import finalproject_modelpackage.IMusicalPiece;


import javax.swing.JRadioButton;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * This class extends JFrame to set
 * up a GUI so that the user can use the program
 * and see the displayed information and results.
 * @author Galen Otten
 *
 */
public class MyMusicView extends JFrame{
	
	/**
	 * A controller object used to decide what
	 * happens in the event handlers.
	 */
	private MyController myController;	
	
	/**
	 * The JPanel object for the GUI
	 */
	private JPanel contentPane;
	
	//lists sent from the controller to make the columns
	private List<String> composerList;
	private List<String> performerList;
	private List<String> pieceTypeList;
	private List<String> languageList;
	private List<String> toneList;
	
	//lists of radio buttons in columns
	private ArrayList<JRadioButton> composerButtonsList;
	private ArrayList<JRadioButton> performerButtonsList;	
    private ArrayList<JRadioButton> typeButtonsList;
    private ArrayList<JRadioButton> languageButtonsList;
	private ArrayList<JRadioButton> toneButtonsList;	
    private ArrayList<JButton> eraButtonsList;
    
    //labels of columns
    private JLabel composerColumnLabel;		
    private JLabel performerColumnLabel;	
    private JLabel typeColumnLabel;
    private JLabel languageColumnLabel;
    private JLabel toneColumnLabel;
    
    //timeline labels
    private JLabel medievalTimeLine;	
    private JLabel classicalTimeLine;	
    private JLabel renaissanceTimeLine;	
    private JLabel romanticTimeLine;	
    private JLabel modernTimeLine;	
    private JLabel baroqueTimeLine;
    
	//era buttons
    private JButton allErasButton;
    private JButton medievalButton;
    private JButton renaissanceButton;
    private JButton baroqueButton;
    private JButton classicalButton;
    private JButton romanticButton;
    private JButton modernButton;
    
    //control buttons
    private JButton listenButton;
    private JButton addButton;
    private JButton resetButton;
    
    //favorites menu
    protected JComboBox<IMusicalPiece> favoritesComboBox;
    
    //radio button groups to bind the radio buttons together in each column
    private ButtonGroup composerButtonGroup;	
    private ButtonGroup performerButtonGroup;	
    private ButtonGroup typeButtonGroup;	
    private ButtonGroup languageButtonGroup;
    private ButtonGroup toneButtonGroup;


	/**
	 * This method launches the GUI.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMusicView frame = new MyMusicView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	/**
	 * This method keeps the radio button
	 * in a column visible and enabled after
	 * the user clicks on it.
	 * @param counter, to make sure that a button doesn't get
	 * grayed out if it had already passed the relevance test.
	 * @param i, the location in the buttons list
	 * @param buttonsList, the column of buttons
	 * @return
	 */
	private int keepUserRadioButtonChoiceHighlighted(int counter, int i, List<JRadioButton> buttonsList) {
		counter++;
		buttonsList.get(i).setEnabled(true);
		buttonsList.get(i).setForeground(Color.BLACK);
		buttonsList.get(i).setVisible(true);
		return counter;
	}
	
	/**
	 * This method grays out the choices that
	 * the user didn't pick and disables the button.
	 * @param i, the location in the buttons list
	 * @param buttonsList, the column of buttons
	 */
	private void greyOutIrrelevantChoices(int i, List<JRadioButton> buttonsList) {
		buttonsList.get(i).setEnabled(false);
		buttonsList.get(i).setForeground(Color.GRAY);
	}
	
	/**
	 * This method disables and makes the buttons completely 
	 * invisible if the user chooses to see an individual era without
	 * the others grayed out.
	 * @param buttonsList, the column of buttons
	 * @param i, the location in the buttons list
	 */
	private void setOtherEraFiltersToInvisible(List<JRadioButton> buttonsList, int i) {
		buttonsList.get(i).setEnabled(false);				     				
		buttonsList.get(i).setVisible(false);
	}
	
	/**
	 * This method chooses whether to make irrelevant buttons visible, but
	 * grayed out or just invisible.
	 * @param visibility, status of buttons
	 * @param i, the location in the buttons list
	 * @param buttonsList, the column of buttons
	 */
	private void chooseVisibleOrInvisibleFilter(String visibility, int i, List<JRadioButton> buttonsList) {
		if(visibility.equals("visible")) {
				greyOutIrrelevantChoices(i, buttonsList);			     		            	
 		}
		else if (visibility.equals("invisible")) {
				setOtherEraFiltersToInvisible(buttonsList, i);
		}
	}
	
	/**
	 * This method goes through the specified column and 
	 * either grays out or makes invisible all of the no longer
	 * relevant filters that aren't on the path anymore.
	 * @param listByFilter, the current filtered list
	 * @param buttonsList, the column of buttons
	 * @param columnName, name of column of buttons
	 * @param visibility, status of buttons
	 */
	public void eliminateIrrelevantButtonsInColumn(List<IMusicalPiece> listByFilter, 
			List<JRadioButton> buttonsList, String columnName, String visibility) {
		int counter;		 
		 for(int i = 0; i < buttonsList.size(); i++) {	  
		     counter = 0;		
		     for(int j = 0; j < listByFilter.size(); j++) {
		    	 switch (columnName) {
		         	case "Composer":
		         		if(listByFilter.get(j).getComposer().equals(buttonsList.get(i).getText()) == false && counter == 0) {	
		         			chooseVisibleOrInvisibleFilter(visibility, i, buttonsList);		         			
		         		}
				     	else if (listByFilter.get(j).getComposer().equals(buttonsList.get(i).getText())) {
				     		counter = keepUserRadioButtonChoiceHighlighted(counter, i, composerButtonsList);
				     	}	         				         	
		                     break;
		            case "Language":
		            	if(listByFilter.get(j).getLanguage().equals(buttonsList.get(i).getText()) == false && counter == 0) {	
		            		chooseVisibleOrInvisibleFilter(visibility, i, buttonsList);		            		
		            	}
				     	else if (listByFilter.get(j).getLanguage().equals(buttonsList.get(i).getText())) {
				     		counter = keepUserRadioButtonChoiceHighlighted(counter, i, languageButtonsList);
				     	}
		                     break;
		            case "Performer":
		            	if(listByFilter.get(j).getPerformer().equals(buttonsList.get(i).getText()) == false && counter == 0) {		   
		            		chooseVisibleOrInvisibleFilter(visibility, i, buttonsList);		            		
		            	}
				     	else if (listByFilter.get(j).getPerformer().equals(buttonsList.get(i).getText())) {
				     		counter = keepUserRadioButtonChoiceHighlighted(counter, i, performerButtonsList);
				     	}          	
		                     break;
		            case "Emotion":
		            	if(listByFilter.get(j).getEmotionalTone().equals(buttonsList.get(i).getText()) == false && counter == 0) {
		            		chooseVisibleOrInvisibleFilter(visibility, i, buttonsList);		            		
				     	}
				     	else if (listByFilter.get(j).getEmotionalTone().equals(buttonsList.get(i).getText())) {
				     		counter = keepUserRadioButtonChoiceHighlighted(counter, i, toneButtonsList);
				     	}
		                     break;
		            case "Type of Piece":
		            	if(listByFilter.get(j).getType().equals(buttonsList.get(i).getText()) == false && counter == 0) {	
		            		chooseVisibleOrInvisibleFilter(visibility, i, buttonsList);		            		
				     	}
				     	else if (listByFilter.get(j).getType().equals(buttonsList.get(i).getText())) {
				     		counter = keepUserRadioButtonChoiceHighlighted(counter, i, typeButtonsList);
				     	}	            	
		                     break;		       
		            }		     	
		     }		            	
		 }			
	}
	
	/**
	 * This method goes through each column to eliminate the
	 * no longer relevant words/filters based on the previous filter chosen.
	 * @param listByFilter, the list after being filtered by the previos word chosen
	 */
	public void eliminateIrrelevantFilters(List<IMusicalPiece> listByFilter) {
		eliminateIrrelevantButtonsInColumn(listByFilter, composerButtonsList, "Composer", "visible");
		eliminateIrrelevantButtonsInColumn(listByFilter, performerButtonsList, "Performer", "visible");
		eliminateIrrelevantButtonsInColumn(listByFilter, languageButtonsList, "Language", "visible");
		eliminateIrrelevantButtonsInColumn(listByFilter, typeButtonsList, "Type of Piece", "visible");
		eliminateIrrelevantButtonsInColumn(listByFilter, toneButtonsList, "Emotion", "visible");		 		            		 	     	    
		}
			
	/**
	 * This method sets up the listen button and
	 * how it will react when clicked. It will ask the controller
	 * to launch the VideoPlayer application.
	 */
	public void addListenButton() {
		listenButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		listenButton.setForeground(Color.BLACK);
		listenButton.setBackground(Color.LIGHT_GRAY);
		listenButton.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				myController.playVideo();				
			}
		});
	}
	
	/**
	 * This method goes through a column of buttons
	 * and enables, sets visible, and sets the color of them
	 * all back to black. It also clears the selection from the button group.
	 * @param buttonsList, the specific column of buttons
	 * @param buttonGroup, the group that binds the buttons together
	 */
	private void showAllFilterChoices(List<JRadioButton> buttonsList, ButtonGroup buttonGroup) {
		for(JRadioButton temp : buttonsList) {
			temp.setEnabled(true);
    		temp.setForeground(Color.BLACK);	
    		temp.setVisible(true);
		}
		buttonGroup.clearSelection();

	}
	
	/**
	 * This method sets up the button for "All Eras" that
	 * will display all of the pieces from every era. It includes
	 * a listener to handle a click. When pressed it will display all
	 * the pieces.
	 */
	public void setUpAllErasButton() {
		allErasButton.setBackground(new Color(143, 188, 143));
		allErasButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		eraButtonsList.add(allErasButton);
		allErasButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkIfEraButtonIsHighlighted(allErasButton.getText());
				allErasButton.setBackground(new Color(143, 188, 143));
				myController.resetEraSongs();
				showAllFilterChoices(composerButtonsList, composerButtonGroup);
				showAllFilterChoices(performerButtonsList, performerButtonGroup);
				showAllFilterChoices(toneButtonsList, toneButtonGroup);
				showAllFilterChoices(languageButtonsList, languageButtonGroup);
				showAllFilterChoices(typeButtonsList, typeButtonGroup);		
			}
		});
	}
	
	/**
	 * This method sets up the add button which, when clicked,
	 * adds a single song to the favorites list at one time.
	 */
	public void setUpAddButton() {
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.setForeground(Color.BLACK);
		addButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(myController.getSongToPlay().size() == 1) {							
					int numFavorites = favoritesComboBox.getItemCount();	
					int counterForUniqueSong = 0;		
					for (int i = 0; i < numFavorites; i++) {					
					    IMusicalPiece songInFavorites = favoritesComboBox.getItemAt(i);										 				
					    if(songInFavorites.getTitle().equals(myController.getSongToPlay().get(0).getTitle())) {	
					    	counterForUniqueSong++;
					    }				
					}
					if(counterForUniqueSong == 0) {						
						favoritesComboBox.addItem(myController.getSongToPlay().get(0));
					}
				}
			}
		});
	}
	
	/**
	 * This method sets up the reset button which, when clicked,
	 * resets all buttons in the columns to enabled, visible, and black color.
	 */
	public void setUpResetButton() {
		resetButton.setForeground(Color.BLACK);
		resetButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		resetButton.setBackground(Color.LIGHT_GRAY);
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				myController.resetEraSongs();
				resetEraButtonsHighlighting();				
				favoritesComboBox.setSelectedItem("                   Your Favorites");
				allErasButton.setBackground(new Color(143, 188, 143));
				showAllFilterChoices(composerButtonsList, composerButtonGroup);
				showAllFilterChoices(performerButtonsList, performerButtonGroup);
				showAllFilterChoices(toneButtonsList, toneButtonGroup);
				showAllFilterChoices(languageButtonsList, languageButtonGroup);
				showAllFilterChoices(typeButtonsList, typeButtonGroup);									
			}
		});		
	}
	
	/**
	 * This method gets the favorites menu box.
	 * @return the favorites box object
	 */
	public JComboBox<IMusicalPiece> getFavoritesBox() {
		return this.favoritesComboBox;
	}
	
	/**
	 * This method sets up the favorites combo box which
	 * can display all songs in a drop down and scrollable menu.
	 * One song can be clicked and added to the song queue.
	 */
	public void setUpFavoritesComboBox() {
		favoritesComboBox.setForeground(Color.BLACK);
		favoritesComboBox.setEditable(true);
		favoritesComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		favoritesComboBox.setSelectedItem("                   Your Favorites");				
		favoritesComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {							
				myController.getSongToPlay().clear();				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					checkIfEraButtonIsHighlighted(favoritesComboBox.getSelectedItem().toString());	
					for(IMusicalPiece temp : myController.getSongBank()) {
						if(favoritesComboBox.getSelectedItem().toString().equals(temp.toString())) {
							myController.getSongToPlay().add(temp);
							highlightRelevantEraButton();
							allErasButton.setBackground(new Color(143, 188, 143));
							showAllFilterChoices(composerButtonsList, composerButtonGroup);
							showAllFilterChoices(performerButtonsList, performerButtonGroup);
							showAllFilterChoices(toneButtonsList, toneButtonGroup);
							showAllFilterChoices(languageButtonsList, languageButtonGroup);
							showAllFilterChoices(typeButtonsList, typeButtonGroup);															
							eliminateIrrelevantFilters(myController.getSongToPlay());							
						}
					}
				}
								
			}
		});
	}
	
	/**
	 * This method sets all of era buttons to white so
	 * that it is clear that an individual era is not being
	 * displayed. It sets the "All Eras" button to green to show
	 * that every era of songs is being displayed.
	 */
	private void resetEraButtonsHighlighting() {
		for(JButton temp : eraButtonsList) {
			if(!temp.getText().equals("All Eras")) {
				temp.setBackground(Color.WHITE);
			}
			else {
				temp.setBackground(new Color(143, 188, 143));
			}
		}	
	}
	
	/**
	 * This method compares the era buttons text to the
	 * string passed in. If it isn't the same text, then the button
	 * will be set to white.
	 * @param buttonText, the title of the button
	 */
	private void checkIfEraButtonIsHighlighted(String buttonText) {
		for(JButton temp : eraButtonsList) {
			if(!temp.getText().equals(buttonText)) {
				temp.setBackground(Color.WHITE);
			}
		}
	}

	/**
	 * This method turns the appropriate era button
	 * green if it matches the song in the playlist queue.
	 */
	public void highlightRelevantEraButton() {
		for(JButton temp : eraButtonsList) {
			if(temp.getText().equals(myController.getSongToPlay().get(0).getEra())) {
				temp.setBackground(new Color(143, 188, 143));
			}
		}
	}
	
	/**
	 * This method sets up each column of radio buttons. If the 
	 * column name is "Type of Piece", the radio buttons will get hovering
	 * text descriptions to display to the user. The action listener added
	 * allows the irrelevant buttons to be grayed out and disabled, and the relevant
	 * buttons to stay put as they were.  
	 * @param columnX, the x location of the button column
	 * @param columnList, the y location of the button that will change in the for loop
	 * @param columnButtonsList, the list of radio buttons 
	 * @param buttonGroup, the group that binds the radio buttons in the specified list together
	 * @param columnName, the name of the column
	 */
	public void setUpColumn(int columnX, List<String> columnList, 
			List<JRadioButton> columnButtonsList, ButtonGroup buttonGroup, String columnName) {	
		int columnY = 40;
		int columnWidth = 120;
		int columnEachWordHeight = 40;
		for(int i = 0; i < columnList.size(); i++) {		  
			JRadioButton columnRadioButton = new JRadioButton();	  
			columnButtonsList.add(columnRadioButton);
			columnButtonsList.get(i).setText(columnList.get(i));
			columnButtonsList.get(i).setBounds(columnX, columnY, columnWidth, columnEachWordHeight);
			columnButtonsList.get(i).setBackground(SystemColor.menu);
			columnButtonsList.get(i).setFont(new Font("Times New Roman", Font.PLAIN, 16));
			if(columnName.equals("Type of Piece")) {
				columnButtonsList.get(i).setToolTipText(myController.getTypeHoverDescription(pieceTypeList.get(i)));
			}
			getContentPane().add(columnButtonsList.get(i));
			buttonGroup.add(columnButtonsList.get(i));	
			columnY += 40;//adding to the y so the buttons don't sit on top of each other

			//add action listener to each button
			columnButtonsList.get(i).addActionListener(new ActionListener() {
				@Override
		        public void actionPerformed(ActionEvent e) {
		            JRadioButton columnRadioButton = (JRadioButton) e.getSource();
		            List<IMusicalPiece> listByFilter = myController.chooseFilterType(columnName, columnRadioButton.getText());		                 		        		     
		            for(int i = 0; i < columnButtonsList.size(); i++) {
		            	if(columnButtonsList.get(i).getText() != columnRadioButton.getText()) {
		            		columnButtonsList.get(i).setEnabled(false);
		            		columnButtonsList.get(i).setForeground(Color.GRAY);
		            	}
		            }	         
		            eliminateIrrelevantFilters(listByFilter);	
		            boolean result = myController.addSongToPlayList();
		            if(result == true) {
		            	highlightRelevantEraButton();//if only one song is in the play list
					}
		        }
			});
		}
	}
	
	/**
	 * This method clears all previously selected radio buttons
	 * from each column.
	 */
	private void clearAllFilterSelections() {
		composerButtonGroup.clearSelection();
		performerButtonGroup.clearSelection();
		toneButtonGroup.clearSelection();
		languageButtonGroup.clearSelection();
		typeButtonGroup.clearSelection();
	}
	
	/**
	 * This method sets up an individual era button. It
	 * adds an action listener that gets all of the song only
	 * in that era and displays only the relevant filters. It sets all other
	 * filters to invisible.
	 */
	public void setUpEraButton(JButton eraButton) {		
		eraButton.setBackground(Color.WHITE);
		eraButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		eraButtonsList.add(eraButton);
		eraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfEraButtonIsHighlighted(eraButton.getText());
				eraButton.setBackground(new Color(143, 188, 143));	
				myController.resetEraSongs();
				clearAllFilterSelections();				
				JButton eraButton = (JButton) e.getSource();
				List<IMusicalPiece> songBankByEra = myController.applyEraFilter(eraButton.getText());						     			
		     	eliminateIrrelevantButtonsInColumn(songBankByEra, composerButtonsList, "Composer", "invisible");
		     	eliminateIrrelevantButtonsInColumn(songBankByEra, performerButtonsList, "Performer", "invisible");
		     	eliminateIrrelevantButtonsInColumn(songBankByEra, typeButtonsList, "Type of Piece", "invisible");
		     	eliminateIrrelevantButtonsInColumn(songBankByEra, languageButtonsList, "Language", "invisible");
		     	eliminateIrrelevantButtonsInColumn(songBankByEra, toneButtonsList, "Emotion", "invisible");				     
				}
			});
	}		 
	
	/**
	 * This constructor creates the frame. It adds and sets up
	 * all of the buttons, labels, radio buttons, and the comboBox.
	 */
	public MyMusicView() {
		myController = new MyController();
		
		//set up the column labels
		composerColumnLabel = new JLabel("Composer");		
	    performerColumnLabel = new JLabel("Performer");	
	    typeColumnLabel = new JLabel("    Type of Piece");
	    languageColumnLabel = new JLabel(" Language");
	    toneColumnLabel = new JLabel("Emotion");
	    
	    //set up the timeline labels
	    medievalTimeLine = new JLabel("500 - 1400");	
		classicalTimeLine = new JLabel("1750 - 1820");	
		renaissanceTimeLine = new JLabel("1400 - 1600");	
		romanticTimeLine = new JLabel("1820 - 1910");	
		modernTimeLine = new JLabel("1910 - present");	
		baroqueTimeLine = new JLabel("1600 - 1750");
		
		//set up the era buttons
		allErasButton = new JButton("All Eras");
	    medievalButton = new JButton("Medieval");
	    renaissanceButton = new JButton("Renaissance");
	    baroqueButton = new JButton("Baroque");
	    classicalButton = new JButton("Classical");
	    romanticButton = new JButton("Romantic");
	    modernButton = new JButton("Modern");
	    
	    //set up the control buttons
	    listenButton = new JButton("Listen");
	    addButton = new JButton("Add");
	    resetButton = new JButton("Reset");
	    
	    //set up the column radio button group binders
	    composerButtonGroup = new ButtonGroup();	
	    performerButtonGroup = new ButtonGroup();	
	    typeButtonGroup = new ButtonGroup();	
	    languageButtonGroup = new ButtonGroup();
	    toneButtonGroup = new ButtonGroup();
	    
	    //set up the favorites list comboBox
	    favoritesComboBox = new JComboBox<IMusicalPiece>();
		
		//set up the window of the GUI
		setTitle("La Musica!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 20, 1400, 800);		
		contentPane = new JPanel();	
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		//get lists from the controller to make the columns
		composerList = myController.applyUserFilterChoice("Composer");
		performerList = myController.applyUserFilterChoice("Performer");	
		pieceTypeList = myController.applyUserFilterChoice("Piece Type");
		languageList = myController.applyUserFilterChoice("Language");
		toneList = myController.applyUserFilterChoice("Emotional Tone");
		
		//initiate the radio buttons lists of filters
		composerButtonsList = new ArrayList<JRadioButton>();		
		performerButtonsList = new ArrayList<JRadioButton>();		
		typeButtonsList = new ArrayList<JRadioButton>();		
		languageButtonsList = new ArrayList<JRadioButton>();
		toneButtonsList = new ArrayList<JRadioButton>();	
		eraButtonsList  = new ArrayList<JButton>();
		
		//set the fonts for the column labels
		composerColumnLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		performerColumnLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));		
		typeColumnLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));		
		languageColumnLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toneColumnLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		//set up the columns of filters: composer, performer, emotion, language, type of piece
		setUpColumn(15, composerList, composerButtonsList, composerButtonGroup, "Composer"); 
		setUpColumn(140, performerList, performerButtonsList, performerButtonGroup, "Performer"); 
		setUpColumn(270, toneList, toneButtonsList, toneButtonGroup, "Emotion"); 
		setUpColumn(410, languageList, languageButtonsList, languageButtonGroup, "Language"); 
		setUpColumn(550, pieceTypeList, typeButtonsList, typeButtonGroup, "Type of Piece"); 

		//set up add, listen, reset buttons, and favorites menu
		setUpAddButton();
		addListenButton();
		setUpResetButton();		
		setUpFavoritesComboBox();
		
		//set up all era buttons in the era timeline 
		setUpAllErasButton();
		setUpEraButton(medievalButton);
		setUpEraButton(renaissanceButton);
		setUpEraButton(baroqueButton);
		setUpEraButton(classicalButton);
		setUpEraButton(romanticButton);
		setUpEraButton(modernButton);						

		//set the fonts for the dates of the eras
		medievalTimeLine.setFont(new Font("Times New Roman", Font.PLAIN, 14));		
		classicalTimeLine.setFont(new Font("Times New Roman", Font.PLAIN, 14));		
		renaissanceTimeLine.setFont(new Font("Times New Roman", Font.PLAIN, 14));		
		romanticTimeLine.setFont(new Font("Times New Roman", Font.PLAIN, 14));	
		modernTimeLine.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		baroqueTimeLine.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		//set up the instructions text
		JLabel instructionsLabel = new JLabel("Choose one from each column to listen.");
		instructionsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		//code generated by Windows Builder		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(classicalTimeLine, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(82)
									.addComponent(romanticTimeLine, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(75)
									.addComponent(modernTimeLine, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addGap(60))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(composerColumnLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
											.addGap(30)
											.addComponent(performerColumnLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
											.addGap(29)
											.addComponent(toneColumnLabel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(languageColumnLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(373)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(medievalButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
													.addComponent(classicalButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(renaissanceButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(baroqueButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(romanticButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(modernButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED))))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(18)
												.addComponent(typeColumnLabel)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
												.addGap(34)
												.addComponent(favoritesComboBox, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(listenButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
									.addGap(28))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(allErasButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addGap(55)
									.addComponent(medievalTimeLine, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(80)
									.addComponent(renaissanceTimeLine, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(84)
									.addComponent(baroqueTimeLine, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(61))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(355)
							.addComponent(instructionsLabel)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(166)
					.addComponent(medievalTimeLine, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(572, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(favoritesComboBox, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
								.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(baroqueButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(renaissanceButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(medievalButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(romanticButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(modernButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(classicalButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(languageColumnLabel)
								.addComponent(composerColumnLabel)
								.addComponent(performerColumnLabel)
								.addComponent(toneColumnLabel)
								.addComponent(typeColumnLabel))
							.addGap(132)
							.addComponent(renaissanceTimeLine, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(166)
							.addComponent(baroqueTimeLine, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(modernTimeLine, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(classicalTimeLine, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(romanticTimeLine, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(476))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(instructionsLabel)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(listenButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
							.addGap(72))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addComponent(allErasButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(548, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

