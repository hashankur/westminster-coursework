module com.example.w03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w03 to javafx.fxml;
    exports com.example.w03;
}