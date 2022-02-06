module Music {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.web;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
