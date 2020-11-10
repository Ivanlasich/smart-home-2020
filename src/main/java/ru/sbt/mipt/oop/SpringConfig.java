package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Configuration
public class SpringConfig {

    @Bean
    public  HashMap<String, SensorEventType> getAdapter(){
        return  new HashMap<String, SensorEventType>() {
            {
                put("LightIsOn", SensorEventType.LIGHT_ON);
                put("LightIsOff", SensorEventType.LIGHT_OFF);
                put("DoorIsOpen", SensorEventType.DOOR_OPEN);
                put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
            }
        };
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
    public EventProcessorAdapter lightEventProcessor(SmartHome smartHome, HashMap<String, SensorEventType> adapter){
        return  new EventProcessorAdapter(new LightEventProcessor(), smartHome, adapter);
    }
    @Bean
    public EventProcessorAdapter doorsEventProcessor(SmartHome smartHome, HashMap<String, SensorEventType> adapter){
        return new EventProcessorAdapter(new DoorsEventProcessor(), smartHome(), adapter);
    }

    @Bean
    public EventProcessorAdapter hallDoorEventProcessor(SmartHome smartHome, HashMap<String, SensorEventType> adapter){
        return new EventProcessorAdapter(new HallDoorEventProcessor(), smartHome, adapter);
    }


    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, Collection<EventProcessorAdapter> events) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        for (EventProcessorAdapter processEvent : events){
            sensorEventsManager.registerEventHandler(processEvent);
        }
        return sensorEventsManager;
    }
}