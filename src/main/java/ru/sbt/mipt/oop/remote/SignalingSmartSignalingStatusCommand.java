package ru.sbt.mipt.oop.remote;

import ru.sbt.mipt.oop.SmartHome;

public class SignalingSmartSignalingStatusCommand implements Command {
    private final SmartHome smartHome;

    public SignalingSmartSignalingStatusCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSmartSignaling().signaling();
    }
}
