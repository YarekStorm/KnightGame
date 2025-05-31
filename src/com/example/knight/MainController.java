package com.example.knight;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainMenuController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button startButton, loadButton, settingsButton, exitButton;

    private List<Image> frames = new ArrayList<>();
    private int currentFrame = 0;
    private Timeline timeline;
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        // Завантажуємо кадри
        for (int i = 1; i <= 5; i++) {
            Image img = new Image(getClass().getResource("/com/frames/Frame" + i + ".png").toExternalForm());
            frames.add(img);
        }

        // Анімація
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), e -> {
            backgroundImage.setImage(frames.get(currentFrame));
            currentFrame = (currentFrame + 1) % frames.size();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Музика
        File musicFile = new File("src/com/audio/menu_theme.mp3");
        Media media = new Media(musicFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @FXML
    private void startNewGame() {
        System.out.println("Нова гра");
        // TODO: перейти до вікна екіпірування
    }

    @FXML
    private void loadGame() {
        System.out.println("Завантаження гри...");
    }

    @FXML
    private void openSettings() {
        System.out.println("Налаштування...");
    }

    @FXML
    private void exitGame() {
        mediaPlayer.stop();
        System.exit(0);
    }
}
