package ru.sbt.mipt.oop;

public class SmartSignaling {
    private SmartSignalingStatus status;
    private int code;

    private int getCode() {
        return code;
    }

    public boolean checkPassword(int password){
        return password==this.code;
    }

    public SmartSignaling(int code) {
        this.status = new DeactivateSmartSignalingStatus(this);
        this.code = code;
    }

    private SmartSignalingStatus getStatus() {
        return status;
    }

    public void activate(int code){
        status.activate(code);
    }

    public void deactivate(int code){
        status.deactivate(code);
    }

    public void signaling(){
        status.signaling();
    }

    public void newStatus(SmartSignalingStatus status){
        this.status = status;
    }

    public boolean isAlarmed(){
        if(this.getStatus() instanceof SignalingSmartSignalingStatus){
            return true;
        }
        return false;
    }

}
