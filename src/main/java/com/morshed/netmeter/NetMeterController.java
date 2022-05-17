package com.morshed.netmeter;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class NetMeterController {
    @FXML
    public Label welcomeText;
    @FXML
    public Label inboundSpeed;
    @FXML
    public Label outboundSpeed;
    @FXML
    public ImageView inboundImageView;
    @FXML
    public ImageView outboundImageView;
    public Image inboundImage;
    public Image outboundImage;

    public NetMeterController() {
        inboundImage = new Image("/down.png");
        outboundImage = new Image("/up.png");
        inboundImageView = new ImageView();
        inboundImageView.setImage(inboundImage);
        outboundImageView = new ImageView();
        outboundImageView.setImage(outboundImage);
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

    @FXML
    public void onHelloButtonClick() throws Exception {
        System.out.println("In hello button click");
        calculateSpeed();
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void close(){
        Platform.exit();
    }
}