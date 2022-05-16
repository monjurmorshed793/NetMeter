package com.morshed.netmeter;

public class SpeedModel {
    private String inboundSpeed;
    private String outboundSpeed;

    public SpeedModel(String inboundSpeed, String outboundSpeed) {
        this.inboundSpeed = inboundSpeed;
        this.outboundSpeed = outboundSpeed;
    }

    public String getInboundSpeed() {
        return inboundSpeed;
    }

    public String getOutboundSpeed() {
        return outboundSpeed;
    }
}
