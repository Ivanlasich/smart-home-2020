package ru.sbt.mipt.oop;

public interface SmartSignalingStatus {
    public void activate(int code);
    public void deactivate(int code);
    public void signaling();
}
