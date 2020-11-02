package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.remote.CloseHallDoorCommand;
import ru.sbt.mipt.oop.remote.Command;
import ru.sbt.mipt.oop.remote.TurnOffAllLightsCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTurnOffAllLightsCommand {
    @Test
    void testTurnOffAllLights() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);
        TurnOffAllLightsCommand turnOffAllLightsCommand = context.getBean("turnOffAllLightsCommand", TurnOffAllLightsCommand.class);
        turnOffAllLightsCommand.execute();
        smartHome.execute(object -> {
            if (object instanceof Light){
                assertFalse(((Light)object).isOn());
            }
        });
    }
}
