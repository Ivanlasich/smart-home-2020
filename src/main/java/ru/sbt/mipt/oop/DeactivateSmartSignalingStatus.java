package ru.sbt.mipt.oop;
public class DeactivateSmartSignalingStatus implements SmartSignalingStatus{
    private SmartSignaling smartSignaling;

    public DeactivateSmartSignalingStatus(SmartSignaling smartAlarm) {
        this.smartSignaling = smartAlarm;
    }

    @Override
    public void activate(int code) {
        if (smartSignaling.checkPassword(code)){
            SmartSignalingStatus activateSmartSignalingStatus = new ActivateSmartSignalingStatus(smartSignaling);
            smartSignaling.newStatus(activateSmartSignalingStatus);
        }
        else {
            this.signaling();
        }

    }

    @Override
    public void deactivate(int code) {
        return;
    }

    @Override
    public void signaling() {
        System.out.println("Sending sms");
        SmartSignalingStatus signalingSmartSignalingStatus = new SignalingSmartSignalingStatus(smartSignaling);
        smartSignaling.newStatus(signalingSmartSignalingStatus);

    }


}
