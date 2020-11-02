package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.remote.RemoteControlImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRemoteControlImpl {
    @Test
    void testRemoteControlImpl() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        RemoteControlImpl remoteControl = context.getBean("remoteControl", RemoteControlImpl.class);
        remoteControl.onButtonPressed("A","1234");
        assertTrue(smartHome.getSmartSignaling().getStatus() instanceof  ActivateSmartSignalingStatus);

        remoteControl.onButtonPressed("B","1234");
        smartHome.execute((object) -> {
            if (object instanceof Door && ((Door)object).getId().equals("4")) {
                Assertions.assertFalse(((Door)object).isOpen());
            }
        });

        remoteControl.onButtonPressed("C","1234");
        assertTrue(smartHome.getSmartSignaling().getStatus() instanceof  SignalingSmartSignalingStatus);

        remoteControl.onButtonPressed("D","1234");
        smartHome.execute(object -> {
            if (object instanceof Light){
                assertFalse(((Light)object).isOn());
            }
        });

        remoteControl.onButtonPressed("1","1234");
        smartHome.execute(object -> {
            if (object instanceof Light){
                assertTrue(((Light)object).isOn());
            }
        });

        remoteControl.onButtonPressed("2","1234");
        smartHome.execute(object -> {
            if (object instanceof Room){
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(light -> {
                        if (light instanceof Light) {
                            assertTrue(((Light) light).isOn());
                        }
                    });
                }
            }
        });


    }
}
