module com.example.geographyquizgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.geographyquizgui to javafx.fxml;
    exports com.example.geographyquizgui;
}