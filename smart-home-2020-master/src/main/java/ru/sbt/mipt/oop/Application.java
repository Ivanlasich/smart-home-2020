package ru.sbt.mipt.oop;
import java.util.Arrays;
import java.util.List;


public class Application {
    private final EventCreator eventCreator;
    private final SmartHome smartHome;
    private final EventManager eventManager;


    public Application(EventCreator eventCreator, SmartHome smartHome, EventManager eventManager) {
        this.eventCreator = eventCreator;
        this.smartHome = smartHome;
        this.eventManager = eventManager;
    }

    public void run(){
        List<EventProcessor> processEvents = getEvent();
        eventManager.manage(processEvents);
    }

    private List<EventProcessor> getEvent(){
        return Arrays.asList(new EventProcessorDecorator(new LightEventProcessor()), new EventProcessorDecorator(new DoorsEventProcessor()), new EventProcessorDecorator(new HallDoorEventProcessor()), new EventProcessorDecorator(new SignalingEventProcessor()));
    }
    public static void main(String... args) {
        // считываем состояние дома из файла
        HomeReader reader = new JsonHomeReader();
        if(reader != null){
            SmartHome smartHome = reader.homeReader("smart-home-1.js");
            smartHome.setSmartSignaling(new SmartSignaling(1234));
            EventCreator creator = new DummyRandomEventCreator();
            EventManager eventManager = new LoopEventManager(creator, smartHome);
            Application application = new Application(creator, smartHome, eventManager);
            application.run();
        }
    }
}
