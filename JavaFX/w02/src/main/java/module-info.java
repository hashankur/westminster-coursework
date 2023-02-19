module com.example.w02 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w02 to javafx.fxml;
    exports com.example.w02;
}