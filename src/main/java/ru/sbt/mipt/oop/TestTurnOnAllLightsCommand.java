package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.remote.Command;
import ru.sbt.mipt.oop.remote.TurnOffAllLightsCommand;
import ru.sbt.mipt.oop.remote.TurnOnAllLightsCommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTurnOnAllLightsCommand {
    @Test
    void testTurnOnAllLights() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SmartHome smartHome = context.getBean("smartHome", SmartHome.class);


        TurnOnAllLightsCommand turnOnAllLightsCommand = context.getBean("turnOnAllLightsCommand", TurnOnAllLightsCommand.class);
        turnOnAllLightsCommand.execute();
        smartHome.execute(object -> {
            if (object instanceof Light){
                assertTrue(((Light)object).isOn());
            }
        });
    }
}
