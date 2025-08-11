package fuelapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField carMakeField;
    @FXML private TextField carModelField;
    @FXML private TextField carYearField;



    public void handleSubmit(ActionEvent event) {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all required fields.");
            alert.show();
            return;
        }

        String data = String.format("Name: %s\nEmail: %s\nPhone: %s\nCar: %s %s (%s)\n\n",
                nameField.getText(), emailField.getText(), phoneField.getText(),
                carMakeField.getText(), carModelField.getText(), carYearField.getText());

        try (FileWriter writer = new FileWriter("src/main/resources/data/registrations.txt", true)) {
            writer.write(data);
            System.out.println("Registration saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


