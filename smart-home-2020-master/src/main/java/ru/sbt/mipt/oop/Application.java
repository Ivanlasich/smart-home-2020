package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {
    private final EventCreator eventCreator;
    private SmartHome smartHome;


    public Application(EventCreator eventCreator, SmartHome smartHome) {
        this.eventCreator = eventCreator;
        this.smartHome = smartHome;
    }

    public void run(){
        SensorEvent event = eventCreator.getNextEvent();
        ProcessEvent processLight, processDoors, processHallDoors;
        processLight = new LightProcessEvent();
        processDoors = new DoorsProcessEvent();
        processHallDoors = new HallDoorProcessEvent();
        List<ProcessEvent> processEvents = Arrays.asList(processLight, processDoors, processHallDoors);
        while (event != null) {
            System.out.println("Got event: " + event);
            for (ProcessEvent processEvent : processEvents){
                processEvent.process(smartHome, event);
            }
            event = eventCreator.getNextEvent();
        }
    }

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        EventCreator creator = new ImpEventCreator();
        Application application = new Application(creator, smartHome);
        application.run();
    }

}
