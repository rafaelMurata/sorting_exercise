package br.com.rafaelmurata.consumer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import br.com.rafaelmurata.model.Message;

public class Consumer {

    private BlockingQueue<Message> queue;
    private Thread consumerThread = null;
    List<Message> auxList = new ArrayList<Message>();

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void startConsuming() {
        consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Message message = queue.take();
                        auxList.add(message);
                    } catch (InterruptedException e) {
                        // executing thread has been interrupted, exit loop
                        break;
                    }
                }
               
            }
            
			
        });
        consumerThread.start();
    }

    public void stopConsuming() {
        consumerThread.interrupt();
    }
    
    //exercise 1 save messages into a file
	public void saveMessage(Message message) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {
			fout = new FileOutputStream("\\Users\\rafaelmurata\\Documents\\workspace\\estudos\\entrevista\\messageoutput.txt");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(message.toString());

			System.out.println("Done");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	public void saveMessageShowList() {
		System.out.println(Arrays.toString(auxList.toArray()) );
	}

	public void sortByQuickSort() {
		 doQuickSort(auxList,0,auxList.size()-1);
         saveMessageShowList();
		
	}
	private void doQuickSort(List<Message> auxList, int start, int end) {
		if(start < end) {
			int pivot = partition(auxList,  start,  end);
			doQuickSort(auxList, start, pivot-1);
			doQuickSort(auxList, pivot+1, end);
		}
	}

	private int partition(List<Message> array, int start, int end) {
		Message pivot = array.get(start);
		int i = start +1 , f =end;
		while(i<=f) {
			if(array.get(i).getPriority().ordinal() <= pivot.getPriority().ordinal()) {
				i++;
			}else if(pivot.getPriority().ordinal() < array.get(f).getPriority().ordinal()) {
				f--;
			}else {
				Message troca = array.get(i);
				array.set(i,array.get(f));
				array.set(f, troca);
				i++;
				f--;
			}
		}
		array.set(start,array.get(f));
		array.set(f, pivot);
		return end;
	}
}
