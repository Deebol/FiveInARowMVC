package org.debski.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuWindowController {

    @FXML
    private MenuItem newMenuItem;

    @FXML
    private MenuItem quitMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem rulesMenuItem;
    
    @FXML
    private AnchorPane anchorPaneCenter;

    @FXML
    void aboutMenuItemClicked(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("About");
    	alert.setHeaderText(null);
    	alert.setContentText("Gomoku Standard by Adam Dębski");

    	alert.showAndWait();
    }

    @FXML
    void newMenuItemClicked(ActionEvent event) {
    	anchorPaneCenter.getChildren().clear();
    	anchorPaneCenter.setStyle("-fx-background-image: none;");
        try {
        	GridPane board = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/Board.fxml"));
			anchorPaneCenter.getChildren().add(board);
			AnchorPane.setTopAnchor(board, 0.0);
			AnchorPane.setLeftAnchor(board, 0.0);
			AnchorPane.setBottomAnchor(board, 0.0);
			AnchorPane.setRightAnchor(board, 0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void quitMenuItemClicked(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void rulesMenuItemClicked(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION) {
    		
    	};
    	alert.setTitle("Rules");
    	alert.setHeaderText("Gumoku Standard rules:");
    	Label label = new Label("Celem każdego z graczy jest ułożenie nieprzerwanego łańcucha\n"
    						  + "dokładnie pięciu kamieni własnego koloru (w poziomie, pionie\n"
    						  + "lub po przekątnej). Ustawienie więcej niż 5 kamieni w linii,\n"
    						  + "zwane overline, nie daje wygranej.");
    	label.setWrapText(true);
    	alert.getDialogPane().setContent(label);
    	
    	alert.showAndWait();
    }

}
