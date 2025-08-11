package fuelapp.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ForgotPasswordController {
    @FXML private TextField emailField;
    @FXML private PasswordField newPasswordField;

    public void handleReset(ActionEvent event) {
        String email = emailField.getText();
        String newPassword = newPasswordField.getText();
        System.out.println("Password reset for " + email + " to: " + newPassword);
        //  logic to update password in storage
    }
}

