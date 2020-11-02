package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.remote.ActivateSmartSignalingCommand;
import ru.sbt.mipt.oop.remote.CloseHallDoorCommand;

public class TestCloseHallDoorCommand {
    @Test
    void testCloseHallDoor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        CloseHallDoorCommand closeHallDoorCommand = context.getBean("closeHallDoorCommand", CloseHallDoorCommand.class);
        closeHallDoorCommand.execute();
        smartHome.execute((object) -> {
            if (object instanceof Door && ((Door)object).getId().equals("4")) {
                Assertions.assertFalse(((Door)object).isOpen());
            }
        });
    }
}
