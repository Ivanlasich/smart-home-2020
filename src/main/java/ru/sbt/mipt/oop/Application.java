package ru.sbt.mipt.oop;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;


public class Application {
    public static void main(String... args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SensorEventsManager sensorEventsManager = context.getBean("sensorEventsManager", SensorEventsManager.class);
        sensorEventsManager.start();
        context.close();
    }
}
