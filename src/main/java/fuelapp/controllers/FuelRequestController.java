package fuelapp.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
//import com.google.gson.JsonParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;

public class FuelRequestController {
    @FXML
    private ComboBox<String> carComboBox;
    @FXML
    private Label fuelPriceLabel;

    @FXML
    public void initialize() {
        loadCarList();
        fuelPriceLabel.setText("23.45"); // Placeholder
    }

    private void loadCarList() {
        try {
            File file = new File("src/main/resources/data/car_list.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            NodeList carNodes = doc.getElementsByTagName("car");

            for (int i = 0; i < carNodes.getLength(); i++) {
                carComboBox.getItems().add(carNodes.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFuelPrice() {
        try {
            File file = new File("src/main/resources/data/fuel_prices.json");
            ObjectMapper mapper = new ObjectMapper();

            // Parse the JSON file into a JsonNode
            JsonNode rootNode = mapper.readTree(file);

            // Getting the value of "petrol" and setting it to the label
            String petrolPrice = rootNode.get("petrol").asText();
            fuelPriceLabel.setText(petrolPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleRequest(ActionEvent event) {
        String selectedCar = carComboBox.getValue();
        String price = fuelPriceLabel.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fuel_summary.fxml"));
            Scene scene = new Scene(loader.load());

            FuelSummaryController controller = loader.getController();
            controller.setSummary(selectedCar, price);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


