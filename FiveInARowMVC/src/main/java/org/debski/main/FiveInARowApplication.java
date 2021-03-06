package org.debski.main;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FiveInARowApplication extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		String fxmlFilePath = "/fxml/MenuWindow.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFilePath));
		String style = getClass().getResource("/styles/style.css").toExternalForm();
		Parent root;
		try {
			root = fxmlLoader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(style);
			primaryStage.setMinHeight(500);
			primaryStage.setMinWidth(500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		}
		
		
	}

}
