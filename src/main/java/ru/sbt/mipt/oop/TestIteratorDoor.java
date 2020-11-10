//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestIteratorDoor {
    public TestIteratorDoor() {
    }

    @Test
    void testIteratorDoor() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        smartHome.execute((object) -> {
            if (object instanceof Door) {
                ((Door)object).execute(new Action() {
                    public void begin(Object act) {
                        Assertions.assertTrue(act instanceof Door);
                    }
                });
            }

        });
    }
}
