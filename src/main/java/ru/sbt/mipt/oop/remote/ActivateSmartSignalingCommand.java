package ru.sbt.mipt.oop.remote;

import ru.sbt.mipt.oop.SmartHome;

public class ActivateSmartSignalingCommand implements Command{
    private final SmartHome smartHome;
    private final int code;

    public ActivateSmartSignalingCommand(SmartHome smartHome, int code) {
        this.smartHome = smartHome;
        this.code = code;
    }
    @Override
    public void execute() {
        smartHome.getSmartSignaling().activate(code);
    }
}
