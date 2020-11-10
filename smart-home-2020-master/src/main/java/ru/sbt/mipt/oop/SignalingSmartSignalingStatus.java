package ru.sbt.mipt.oop;

public class SignalingSmartSignalingStatus implements SmartSignalingStatus {
    private SmartSignaling smartSignaling;

    public SignalingSmartSignalingStatus(SmartSignaling smartSignaling) {
        this.smartSignaling = smartSignaling;
    }


    @Override
    public void activate(int code) {
        return;
    }

    @Override
    public void deactivate(int code) {
        if (smartSignaling.checkPassword(code)) {
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
