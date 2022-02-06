/**
 * Galen Otten
 * CS5004, Summer 2021
 * Final Project - VideoPlayer class
 */

package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * This class makes it possible to launch and 
 * play multiple youtube videos.
 * @author Galen Otten, with citations in the 
 * comments as needed
 */
public class VideoPlayer extends Application {   
	
	/**
	 * The YouTube link to play
	 * the video.
	 */
	public static String urlToPlay;
	
	/**
	 * The title of the song in the video
	 */
	public static String titleVideo;
	
	/**
	 * The args to be passed into the main method
	 */
	private static String[] args;
	
	/**
	 * A variable to flag if one video has already been
	 * launched or not.
	 */
	private static boolean alreadyLaunched = false;
	
	/**
	 * This main method allows the VideoPlayer to launch.
	 * @param args
	 */
	public static void main(String[] args) {
		multipleLaunch(VideoPlayer.class);
	} 
	
	/**
	 * This method allows a unique YouTube link
	 * and title to be passed into the VideoPlayer.
	 * @param URL, YouTube link
	 * @param title, title of the song
	 */
	public static void setURLAndTitle(String URL, String title) {
		urlToPlay = URL;
		titleVideo = title;
		main(args);
	}

	/**
	 * This method sets up the stage for the
	 * video to be played on and searches using
	 * the given link so it can display the video.
	 * 
	 * This was a very new concept for me. I was able 
	 * to get this going with the help of the video at
	 * this address:
	 * https://www.youtube.com/watch?v=1cGmWPI5TaI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
	      primaryStage.setTitle(titleVideo);
	      WebView webView = new WebView();
	      //Searches the web with the given url
	      webView.getEngine().load(urlToPlay);
	      
	      VBox vBox = new VBox(webView);
	      Scene scene = new Scene(vBox, 670, 400);
	      primaryStage.setX(760);
	      primaryStage.setY(360);
	      primaryStage.setScene(scene);
	      primaryStage.show();
	 }
	
	/**
	 * This method is a custom launch enabling multiple videos to be played 
	 * in a single run of the program. It was heavily inspired by the discussion
	 * at this address:
	 * https://stackoverflow.com/questions/24320014/how-to-call-launch-more-than-once-in-java
	 * The application cannot be launched more than once, so we have to specify that
	 * we don't want to exit, and then with a new thread, launch it again.
	 * This was, again, an entirely new concept for me, so I am citing my references 
	 * in this comment.
	 * @param myApplication, VideoPlayer is the application class to
	 * be launched
	 */
	public static void multipleLaunch(Class<? extends Application> myApplication) {
	    if (alreadyLaunched == false) { // First launch
	        Platform.setImplicitExit(false);//don't allow it to exit
	        //use original launch method, start() causes this thread to execute
	        new Thread(()->Application.launch(myApplication)).start();  
	        alreadyLaunched = true;
	    } else { // Any launch after the first launch will come here
	        Platform.runLater(()->{
	            try {
	            	//make a new instance of the VideoPlayer class
	                Application application = myApplication.getDeclaredConstructor().newInstance();
	                Stage primaryStage = new Stage();
	                application.start(primaryStage);//call the start method on the new VideoPlayer object
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	    }
	}
}