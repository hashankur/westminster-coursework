package com.example.w05;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private void handleExitAction(final ActionEvent event) {
        handleExitFunctionality();

    }

    private void handleExitFunctionality() {
        Platform.exit();
    }
}