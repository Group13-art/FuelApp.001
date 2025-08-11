package fuelapp.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    public void handleLogin(ActionEvent event) {
        // Add login logic here
        goToPage(event, "/fxml/fuel_request.fxml");
    }

    public void goToRegister(ActionEvent event) {
        goToPage(event, "/fxml/register.fxml");
    }

    public void goToForgotPassword(ActionEvent event) {
        goToPage(event, "/fxml/forgot_password.fxml");
    }

    private void goToPage(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

