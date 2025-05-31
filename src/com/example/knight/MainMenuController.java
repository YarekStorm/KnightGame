package com.example.knight;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController {

    @FXML
    private void startNewGame() {
        // TODO: тут відкриємо нове вікно (таблиця амуніції)
        System.out.println("Start new game...");
    }

    @FXML
    private void loadGame() {
        new Alert(Alert.AlertType.INFORMATION, "Функція завантаження буде пізніше").showAndWait();
    }

    @FXML
    private void openSettings() {
        new Alert(Alert.AlertType.INFORMATION, "Налаштування в розробці").showAndWait();
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }
}
