package com.morshed.netmeter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NetMeterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NetMeterApplication.class.getResource("netmeter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        StageStyle stageStyle= StageStyle.TRANSPARENT;
        stage.initStyle(stageStyle);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}