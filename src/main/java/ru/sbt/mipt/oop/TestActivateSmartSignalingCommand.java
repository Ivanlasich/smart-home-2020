package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.remote.ActivateSmartSignalingCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestActivateSmartSignalingCommand {
    @Test
    void testActivate() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        ActivateSmartSignalingCommand activateSmartSignalingCommand = context.getBean("activateSmartSignalingCommand", ActivateSmartSignalingCommand.class);
        activateSmartSignalingCommand.execute();
        assertTrue(smartHome.getSmartSignaling().getStatus() instanceof  ActivateSmartSignalingStatus);
    }

    @Test
    void testWrongPass() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        smartHome.setSmartSignaling(new SmartSignaling(1233));
        ActivateSmartSignalingCommand activateSmartSignalingCommand = context.getBean("activateSmartSignalingCommand", ActivateSmartSignalingCommand.class);
        activateSmartSignalingCommand.execute();
        assertTrue(smartHome.getSmartSignaling().getStatus() instanceof  SignalingSmartSignalingStatus);

    }
}
