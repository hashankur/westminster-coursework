package com.example.w03;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Scanner;

public class ScannerController {
    @FXML
    private Label inputText;

    @FXML
    protected void onScannerPutButtonClick() {
        System.out.println(inputText);
    }

    @FXML
    protected void onScannerGetButtonClick() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input text: ");
        String lineOfText = input.next();
        inputText.setText(lineOfText);
    }
}