package com.example.knight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("main_menu.css").toExternalForm());

        primaryStage.setTitle("Knight Equipment Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
