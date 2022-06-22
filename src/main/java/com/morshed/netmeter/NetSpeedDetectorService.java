package com.morshed.netmeter;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import oshi.hardware.NetworkIF;
import oshi.hardware.platform.windows.WindowsNetworkIF;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class NetSpeedDetectorService extends ScheduledService<SpeedModel> {
    NetworkIF networkIF;
    SpeedModel speedModel;
    public NetSpeedDetectorService() {
        try{
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface networkInterface: Collections.list(networkInterfaces)){
                if(networkInterface.isLoopback())
                    continue;
                if(networkInterface.isUp()){

                    if(networkInterface.getName().equalsIgnoreCase("wlan0")){
                        networkIF = new WindowsNetworkIF(networkInterface);
                        break;
                    }
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void calculateSpeed() {
        try{
            networkIF.updateAttributes();
            long  bytesReceivedStart = networkIF.getBytesRecv();
            long bytesSentStart = networkIF.getBytesSent();
            long startTimestamp = networkIF.getTimeStamp();
            Thread.sleep(1000);
            networkIF.updateAttributes();
            long bytesReceivedEnd = networkIF.getBytesRecv();
            long bytesSentEnd = networkIF.getBytesSent();
            long endTimestamp = networkIF.getTimeStamp();
            long inbound = (bytesReceivedEnd-bytesReceivedStart)/(endTimestamp- startTimestamp);
            long outbound = (bytesSentEnd-bytesSentStart)/(endTimestamp- startTimestamp);
            String bytesReceivedInKb = NetMeterUtils.formatBytes(inbound);
            String bytesSentInKb = NetMeterUtils.formatBytes(outbound);
            speedModel = new SpeedModel(bytesReceivedInKb, bytesSentInKb);
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }

    }

    @Override
    protected Task<SpeedModel> createTask() {
        return new Task<SpeedModel>() {
            @Override
            protected SpeedModel call() throws Exception {
                calculateSpeed();
                return speedModel;
            }
        };
    }
}
