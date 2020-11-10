package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.junit.jupiter.api.Test;

public class TestProcessorAdapter {
    @Test
    void testSignaling() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        EventProcessor process1 = new LightEventProcessor();
        EventProcessor process2 = new DoorsEventProcessor();
        EventProcessor process3 = new HallDoorEventProcessor();

        EventProcessorAdapter eventProcessorAdapter1 = new EventProcessorAdapter(process1, smartHome);
        EventProcessorAdapter eventProcessorAdapter2 = new EventProcessorAdapter(process2, smartHome);
        EventProcessorAdapter eventProcessorAdapter3 = new EventProcessorAdapter(process3, smartHome);


        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventProcessorAdapter1);
        sensorEventsManager.registerEventHandler(eventProcessorAdapter2);
        sensorEventsManager.registerEventHandler(eventProcessorAdapter3);
        sensorEventsManager.start();
    }
}
