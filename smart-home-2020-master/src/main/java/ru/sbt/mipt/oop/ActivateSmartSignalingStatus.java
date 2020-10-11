package ru.sbt.mipt.oop;

public class ActivateSmartSignalingStatus implements SmartSignalingStatus {
    private SmartSignaling smartSignaling;

    public ActivateSmartSignalingStatus(SmartSignaling smartAlarm) {
        this.smartSignaling = smartAlarm;
    }

    @Override
    public void activate(int code) {
        return;
    }

    @Override
    public void deactivate(int code) {
        if (smartSignaling.getCode()== code){
            SmartSignalingStatus deactivateSmartSignalingStatus = new DeactivateSmartSignalingStatus(smartSignaling);
            smartSignaling.newStatus(deactivateSmartSignalingStatus);
        }
        else {
            this.signaling();
        }

    }

    @Override
    public void signaling() {
        System.out.println("Sending sms");
        SmartSignalingStatus signalingSmartSignalingStatus = new SignalingSmartSignalingStatus(smartSignaling);
        smartSignaling.newStatus(signalingSmartSignalingStatus);
    }
}
