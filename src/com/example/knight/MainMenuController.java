package com.example.knight;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class MainMenuController {

    @FXML
    private ImageView backgroundImage1;

    @FXML
    private ImageView backgroundImage2;

    private final List<Image> frames = new ArrayList<>();
    private int currentFrameIndex = 0;
    private boolean showingFirst = true;

    @FXML
    public void initialize() {
        loadFrames();
        startSlideshow();
    }

    private void loadFrames() {
        for (int i = 1; i <= 6; i++) {
            String path = "/com/frames/Frame" + i + ".png";
            Image image = new Image(getClass().getResourceAsStream(path));
            frames.add(image);
        }
        backgroundImage1.setImage(frames.get(0));
    }

    private void startSlideshow() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> crossfade())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void crossfade() {
        Image nextImage = frames.get((currentFrameIndex + 1) % frames.size());

        ImageView fadingIn = showingFirst ? backgroundImage2 : backgroundImage1;
        ImageView fadingOut = showingFirst ? backgroundImage1 : backgroundImage2;

        fadingIn.setImage(nextImage);
        fadingIn.setOpacity(0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), fadingIn);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        currentFrameIndex = (currentFrameIndex + 1) % frames.size();
        showingFirst = !showingFirst;
    }

    // 🎮 Обробка кнопок
    @FXML
    private void startNewGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Інвентар Лицаря");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Не вдалося завантажити інвентар").showAndWait();
        }
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
