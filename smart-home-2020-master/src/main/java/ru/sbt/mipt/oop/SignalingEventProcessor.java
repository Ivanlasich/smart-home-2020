package ru.sbt.mipt.oop;

public class SignalingEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        SensorEventType type = event.getType();
        if (type == SensorEventType.ALARM_ACTIVATE ){
            smartHome.getSmartSignaling().activate(event.getCode());
        }

        if (type == SensorEventType.ALARM_DEACTIVATE ){
            smartHome.getSmartSignaling().deactivate(event.getCode());
        }
    }
}
