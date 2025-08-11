package fuelapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<String> carMakeCombo;
    @FXML private ComboBox<String> carModelCombo;
    @FXML private TextField carYearField;
    @FXML private TextField carRegField;



    @FXML
    public void initialize() {
        carMakeCombo.getItems().addAll("Toyota", "Ford", "Volkswagen", "BMW", "Mercedes");

        // This will set default models of cars which the user can pick from(optional)
        carModelCombo.getItems().addAll("Corolla", "Fiesta", "Golf", "3 Series", "C-Class");

        // A dynamic model loading based on selected make by the user.
        carMakeCombo.setOnAction(e -> {
            String selectedMake = carMakeCombo.getValue();
            carModelCombo.getItems().clear();

            switch (selectedMake) {
                case "Toyota":
                    carModelCombo.getItems().addAll("Corolla", "Camry", "Hilux");
                    break;
                case "Ford":
                    carModelCombo.getItems().addAll("Fiesta", "Focus", "Ranger");
                    break;
                case "Volkswagen":
                    carModelCombo.getItems().addAll("Golf", "Polo", "Passat");
                    break;
                case "BMW":
                    carModelCombo.getItems().addAll("3 Series", "X5", "M4");
                    break;
                case "Mercedes":
                    carModelCombo.getItems().addAll("C-Class", "E-Class", "GLA");
                    break;
            }
        });
    }


    public void handleSubmit(ActionEvent event) {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || carRegField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all required fields.");
            alert.show();
            return;
        }
        String data = String.format(
                "Name: %s\nEmail: %s\nPhone: %s\nCar: %s %s (%s)\nRegistration: %s\n\n",
                nameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                carMakeCombo.getValue(),
                carModelCombo.getValue(),
                carYearField.getText(),
                carRegField.getText()
        );

        // This will save the user details to file so they can log in again and find their details.
        try (FileWriter writer = new FileWriter("src/main/resources/data/registrations.txt", true)) {
            writer.write(data);
            System.out.println("Registration saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





