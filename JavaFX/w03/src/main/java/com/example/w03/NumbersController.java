package com.example.w03;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Scanner;

public class NumbersController {
    @FXML
    private Label totalText;
    @FXML
    private TextField num1;
    @FXML
    private TextField num2;

    @FXML
    protected void onTotalButtonClick() {
        double n1 = Double.parseDouble(num1.getText());
        double n2 = Double.parseDouble(num2.getText());
        double total = n1 + n2;
        totalText.setText(String.valueOf(total));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.valueOf(total));
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK);
    }
}