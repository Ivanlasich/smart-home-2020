//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestIteratorLight {
    public TestIteratorLight() {
    }

    @Test
    void testIteratorLight() {
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                ((Light)object).execute(new Action() {
                    public void begin(Object act) {
                        Assertions.assertTrue(act instanceof Light);
                    }
                });
            }

        });
    }
}
