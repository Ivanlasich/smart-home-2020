package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

import java.util.HashMap;

public class EventProcessorAdapter implements EventHandler{
    private final EventProcessor eventProcessor;
    private final SmartHome smartHome;
    private final HashMap<String, SensorEventType> adapter = new HashMap<String, SensorEventType>() {
        {
            put("LightIsOn", SensorEventType.LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        }
    };

    public EventProcessorAdapter(EventProcessor eventProcessor, SmartHome smartHome) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }


    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcessor.process(smartHome, new SensorEvent(adapter.get(event.getEventType()), event.getObjectId()));
    }
}
