package ru.sbt.mipt.oop;

import java.io.IOException;
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
        EventManager eventManager = new EventManagerLoop(eventCreator, smartHome);
        List<EventProcessor> processEvents = Arrays.asList(new LightEventProcessor(), new DoorsEventProcessor(), new HallDoorEventProcessor());
        eventManager.manage(processEvents);
    }

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeReader reader = new JsonHomeReader();
        if(reader != null){
            SmartHome smartHome = reader.homeReader("smart-home-1.js");
            EventCreator creator = new DummyRandomEventCreator();
            Application application = new Application(creator, smartHome);
            application.run();
        }
    }
}
