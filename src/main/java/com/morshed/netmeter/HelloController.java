package com.morshed.netmeter;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import oshi.hardware.NetworkIF;
import oshi.hardware.platform.windows.WindowsNetworkIF;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class HelloController {
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