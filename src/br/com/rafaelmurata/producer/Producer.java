package br.com.rafaelmurata.producer;

import java.util.concurrent.BlockingQueue;

import br.com.rafaelmurata.factory.MessageFactory;
import br.com.rafaelmurata.model.Message;

public class Producer {

    private BlockingQueue<Message> queue;
    private int messageCounter = 0;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void startProducing() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (messageCounter != 100) {
                        Message message = MessageFactory.generateMessage(messageCounter);
                        queue.add(message);
                        messageCounter++;
                    }
                } catch (InterruptedException e) {
                    // exit loop quietly
                }
            }
        }).start();
    }

    public void stopProducing() {
        // set message counter to maximum number of messages to stop loop, allowing thread to exit
        messageCounter = 100;
    }
}
