package ru.sbt.mipt.oop;

public class SmsSendingDecorator implements EventProcessor {
    private final EventProcessor eventProcessor;

    public SmsSendingDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }


    @Override
    public void process(SmartHome smartHome, SensorEvent event) {

        if (smartHome.getSmartSignaling().getStatus() instanceof ActivateSmartSignalingStatus) {
            smartHome.getSmartSignaling().signaling();
        }

        if (smartHome.getSmartSignaling().getStatus() instanceof SignalingSmartSignalingStatus) {
            System.out.println("Sending sms");
            return;
        }

        eventProcessor.process(smartHome, event);

    }
}

