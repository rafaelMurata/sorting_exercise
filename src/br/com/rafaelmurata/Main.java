
package br.com.rafaelmurata;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import br.com.rafaelmurata.consumer.Consumer;
import br.com.rafaelmurata.model.Message;
import br.com.rafaelmurata.producer.Producer;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
        Producer producer = new Producer(queue);
        producer.startProducing();

        Consumer consumer = new Consumer(queue);
        consumer.startConsuming();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ignore this exception and allow main method to exit
        } finally {
        		//task2 low complexity sorting algorithm
            producer.stopProducing();
            consumer.stopConsuming();
            consumer.sortByQuickSort();
        }
    }
}
