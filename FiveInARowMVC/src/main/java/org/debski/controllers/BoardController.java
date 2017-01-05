package org.debski.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.debski.models.FiveInARowModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BoardController implements Initializable{
	
	@FXML
	private GridPane gridPane;
	
	private FiveInARowModel model = new FiveInARowModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				fillGridWithButton(i, j);
			}
		}
	}

	private void fillGridWithButton(int row, int column) {
		Button button = new Button("   ");
		button.getStyleClass().add("grid-buttons");
		button.setOnAction(e -> {
			try {
				model.play(row, column);
				if (model.getCurrentPlayer() == 'X') 
					button.getStyleClass().add("x-buttons");
				else button.getStyleClass().add("o-buttons");
			} catch (RuntimeException ex) {
				System.out.println(ex.getMessage());
			}
			
		});
		button.setMaxHeight(Double.MAX_VALUE);
		button.setMaxWidth(Double.MAX_VALUE);
		gridPane.add(button, column, row);
		GridPane.setFillWidth(button, true);
		GridPane.setFillHeight(button, true);
	}
}
