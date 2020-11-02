package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.remote.ActivateSmartSignalingCommand;
import ru.sbt.mipt.oop.remote.SignalingSmartSignalingStatusCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSignalingSmartSignalingStatusCommand {
    @Test
    void testActivate() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        SignalingSmartSignalingStatusCommand signalingSmartSignalingStatusCommand = context.getBean("signalingSmartSignalingStatusCommand", SignalingSmartSignalingStatusCommand.class);
        signalingSmartSignalingStatusCommand.execute();
        assertTrue(smartHome.getSmartSignaling().getStatus() instanceof  SignalingSmartSignalingStatus);
    }
}
