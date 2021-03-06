package idx.kafka.services;


import io.confluent.connect.jms.Value;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class ParallelTopicProcessing implements Runnable {
    private Thread t;
    private ConsumerRecord < String, Value > record;
    private String url;

    public ParallelTopicProcessing(ConsumerRecord < String, Value > record, String url) {
        this.record = record;
        this.url = url;
    }


    public void run() {
        try {
            TopicProcessing.run(this.record, this.url);
        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}