package com.morshed.netmeter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class NetMeterApplication extends Application {
    String inboundSpeed, outboundSpeed;

    private double xOffset = 0;
    private double yOffset = 0;
    private static class Delta {
        double x, y;
    }

    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NetMeterApplication.class.getResource("netmeter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 130, 30);
        StageStyle stageStyle= StageStyle.UNDECORATED;
        stage.initStyle(stageStyle);


        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = stage.getX() - event.getScreenX();
                dragDelta.y = stage.getY() - event.getScreenY();
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                double x = stage.getX();
                double y = stage.getY();

                if(y<0){
                    Timer timer =new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            stage.setY(0);
                            timer.cancel();
                        }
                    },500);
                }else{
                    stage.setY(event.getScreenY() + dragDelta.y);
                }

                stage.setX(event.getScreenX() + dragDelta.x);
                stage.getWidth();
                stage.getHeight();
                stage.getX();
                stage.getY();
                inDrag.set(true);

            }
        });

        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setMaxHeight(15);
        stage.setMaxWidth(scene.getWidth());
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stage.setX(screenBounds.getWidth()/2);
        stage.setY(0);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}