package br.com.rafaelmurata.factory;

import br.com.rafaelmurata.model.Message;
import br.com.rafaelmurata.model.Message.Priority;

public class MessageFactory {

    public static Message generateMessage(int counter) throws InterruptedException {
        long sleepTime = 50 + (long) ((Math.random() - 0.8) * 10);
        Thread.sleep(sleepTime);
        return new Message(System.currentTimeMillis(), getRandomPriority(),"Message id:" + counter );
    }

    private static Priority getRandomPriority() {
        int randomInt = (int) Math.floor((3 * Math.random()));
        switch (randomInt) {
            case 0:
                return Priority.LOW;
            case 1:
                return Priority.MEDIUM;
            case 2:
                return Priority.HIGH;
            default:
                return null;
        }
    }
}
