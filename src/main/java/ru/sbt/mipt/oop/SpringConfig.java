package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringConfig {

    @Bean
    public List<EventProcessor> events(){
        List<EventProcessor> events = Arrays.asList(new LightEventProcessor(), new DoorsEventProcessor(), new HallDoorEventProcessor(), new SignalingEventProcessor());
        return events;
    }

    @Bean
    public SmartHome smartHome() {
        HomeReader reader = new JsonHomeReader();
        if(reader != null) {
            SmartHome smartHome = reader.homeReader("smart-home-1.js");
            return smartHome;
        }
        return null;
    }

    @Bean
    public EventProcessor lightEventProcessor(){
        return new LightEventProcessor();
    }

    @Bean
    public EventProcessor doorsEventProcessor(){
        return new DoorsEventProcessor();
    }

    @Bean
    public EventProcessor hallDoorEventProcessor(){
        return new HallDoorEventProcessor();
    }


    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, List<EventProcessor> events) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        for (EventProcessor processEvent : events){
            sensorEventsManager.registerEventHandler(new EventProcessorAdapter(processEvent, smartHome));
        }
        return sensorEventsManager;
    }
}
