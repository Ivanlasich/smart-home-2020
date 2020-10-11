package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {
    private final EventCreator eventCreator;
    private final SmartHome smartHome;


    public Application(EventCreator eventCreator, SmartHome smartHome) {
        this.eventCreator = eventCreator;
        this.smartHome = smartHome;
    }

    public void run(){
        List<EventProcessor> processEvents = Arrays.asList(new EventProcessorDecorator(new LightEventProcessor()), new EventProcessorDecorator(new DoorsEventProcessor()), new EventProcessorDecorator(new HallDoorEventProcessor()), new EventProcessorDecorator(new SignalingEventProcessor()));
        EventManager eventManager = new LoopEventManager(eventCreator, smartHome);
        eventManager.manage(processEvents);
    }

    public static void main(String... args) {
        // считываем состояние дома из файла
        HomeReader reader = new JsonHomeReader();
        if(reader != null){
            SmartHome smartHome = reader.homeReader("smart-home-1.js");
            smartHome.setSmartSignaling(new SmartSignaling(1234));
            EventCreator creator = new DummyRandomEventCreator();
            Application application = new Application(creator, smartHome);
            application.run();
        }
    }
}
