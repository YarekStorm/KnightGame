package com.example.knight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Comparator;
import java.util.Optional;

public class InventoryController {

    @FXML
    private ComboBox<Knight> knightComboBox; // ✅ лише один раз

    @FXML
    private TableView<Ammunition> tableView;

    @FXML
    private TableColumn<Ammunition, String> nameColumn;

    @FXML
    private TableColumn<Ammunition, String> typeColumn;

    @FXML
    private TableColumn<Ammunition, Double> weightColumn;

    @FXML
    private TableColumn<Ammunition, Double> priceColumn;

    private final ObservableList<Ammunition> inventory = FXCollections.observableArrayList();
    private final ObservableList<Knight> knights = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        // ⚔️ Додаємо лицарів
        knights.addAll(
                new Knight("Сір Артур"),
                new Knight("Дейм Гвіневра")
        );
        knightComboBox.setItems(knights);
        knightComboBox.getSelectionModel().selectFirst();

        tableView.setItems(inventory);
    }

    @FXML
    private void handleGiveToKnight() {
        Ammunition selected = tableView.getSelectionModel().getSelectedItem();
        Knight knight = knightComboBox.getSelectionModel().getSelectedItem();

        if (selected != null && knight != null) {
            knight.addAmmunition(selected);
            inventory.remove(selected);
            showAlert("Успіх", "Предмет видано лицарю: " + knight.getName());
        } else {
            showAlert("Помилка", "Оберіть амуніцію та лицаря.");
        }
    }

    @FXML
    private void handleAdd() {
        inventory.add(new Ammunition("Шолом", "Голова", 1.5, 120));
    }

    @FXML
    private void handleDelete() {
        Ammunition selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            inventory.remove(selected);
        } else {
            showAlert("Помилка", "Оберіть предмет для видалення.");
        }
    }

    @FXML
    private void handleSort() {
        inventory.sort(Comparator.comparingDouble(Ammunition::getWeight));
    }

    @FXML
    private void handleSearch() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Пошук");
        dialog.setHeaderText("Введіть максимальну ціну:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            try {
                double max = Double.parseDouble(input);
                ObservableList<Ammunition> filtered = inventory.filtered(item -> item.getPrice() <= max);
                tableView.setItems(filtered);
            } catch (NumberFormatException e) {
                showAlert("Помилка", "Ціна має бути числом.");
            }
        });
    }

    @FXML
    private void handleTotal() {
        double total = inventory.stream().mapToDouble(Ammunition::getPrice).sum();
        showAlert("Загальна вартість", "Усього: " + total + " золота.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
