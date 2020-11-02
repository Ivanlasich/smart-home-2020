package ru.sbt.mipt.oop;

public class BaseCommandHandler implements CommandHandler {
    @Override
    public void process(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
