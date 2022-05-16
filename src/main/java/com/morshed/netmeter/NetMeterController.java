package com.morshed.netmeter;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class NetMeterController {
    @FXML
    public Label welcomeText;
    @FXML
    public Label inboundSpeed;
    @FXML
    public Label outboundSpeed;

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
}