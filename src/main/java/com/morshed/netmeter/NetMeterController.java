package com.morshed.netmeter;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class NetMeterController implements Initializable {
    @FXML
    public Label inboundSpeed;
    @FXML
    public Label outboundSpeed;
    @FXML
    public ImageView inboundImageView;
    @FXML
    public ImageView outboundImageView;
    @FXML
    public Button closeButton;
    @FXML
    public HBox buttonHbox;
    @FXML
    public Image inboundImage;
    @FXML
    public Image outboundImage;

    public NetMeterController() throws InterruptedException {
        inboundImage = new Image(NetMeterController.class.getResourceAsStream("/down.png"));
        outboundImage = new Image(NetMeterController.class.getResourceAsStream("/up.png"));
        inboundImageView = new ImageView();
        inboundImageView.setImage(inboundImage);
        inboundImageView.setCache(true);
        outboundImageView = new ImageView();
        outboundImageView.setImage(outboundImage);
        outboundImageView.setCache(true);
        calculateSpeed();
    }

    public void calculateSpeed(){
        NetSpeedDetectorService netSpeedDetectorService = new NetSpeedDetectorService();
        netSpeedDetectorService.setPeriod(Duration.seconds(1));
        netSpeedDetectorService.setOnSucceeded(e->{
            SpeedModel speedModel = (SpeedModel) e.getSource().getValue();
            inboundSpeed.setText(speedModel.getInboundSpeed()+"");
            outboundSpeed.setText(speedModel.getOutboundSpeed()+"");
        });
        netSpeedDetectorService.start();
    }

    public void showButton(){
        buttonHbox.setVisible(true);
        buttonHbox.setManaged(true);
    }

    public void hideButton(){
        buttonHbox.setVisible(false);
        buttonHbox.setManaged(false);
    }

    @FXML
    public void close(){
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideButton();
    }
}