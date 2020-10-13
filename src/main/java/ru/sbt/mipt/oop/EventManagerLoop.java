package ru.sbt.mipt.oop;

import java.util.List;

public class EventManagerLoop implements EventManager {
    private final EventCreator eventCreator;
    private final SmartHome smartHome;

    public EventManagerLoop(EventCreator eventCreator, SmartHome smartHome) {
        this.eventCreator = eventCreator;
        this.smartHome = smartHome;
    }
    @Override
    public void manage(List<EventProcessor> processEvents) {
        SensorEvent event = eventCreator.getNextEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor processEvent : processEvents){
                processEvent.process(smartHome, event);
            }
            event = eventCreator.getNextEvent();
        }
        
    }
}
