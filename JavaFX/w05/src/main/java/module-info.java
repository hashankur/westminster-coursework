module com.example.w05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w05 to javafx.fxml;
    exports com.example.w05;
}