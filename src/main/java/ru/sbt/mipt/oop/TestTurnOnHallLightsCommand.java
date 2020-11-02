package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.remote.TurnOffAllLightsCommand;
import ru.sbt.mipt.oop.remote.TurnOnHallLightCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTurnOnHallLightsCommand {
    @Test
    void testTurnOnHallLights() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        TurnOnHallLightCommand turnOnHallLightCommand = context.getBean("turnOnHallLightCommand", TurnOnHallLightCommand.class);
        turnOnHallLightCommand.execute();
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
