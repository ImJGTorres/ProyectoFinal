/**
 * 
 */
/**
 * 
 */
module views {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens views to javafx.fxml;
    opens controllers to javafx.fxml;
//    opens objects to javafx.fxml;
    exports views;
    exports controllers;
//    exports objects;
}