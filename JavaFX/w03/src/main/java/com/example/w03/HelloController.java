package com.example.w03;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField name;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello " + name.getText());
    }

    @FXML
    protected void onClearButtonClick() {
        welcomeText.setText("");
        name.setText("");
    }
}