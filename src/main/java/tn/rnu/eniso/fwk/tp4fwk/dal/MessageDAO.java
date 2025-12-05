package tn.rnu.eniso.fwk.tp4fwk.dal;

import org.springframework.stereotype.Repository;
import tn.rnu.eniso.fwk.tp4fwk.dto.MessageDTO;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Repository
public class MessageDAO {

    private final ConcurrentHashMap<String, LinkedBlockingQueue<MessageDTO>> queues = new ConcurrentHashMap<>();

    public void add(MessageDTO message) {
        queues.computeIfAbsent(message.getReceiver_name(), u -> new LinkedBlockingQueue<>()).offer(message);
    }

    public MessageDTO removeFirst(String receiverName) throws InterruptedException {
        return queues.computeIfAbsent(receiverName, u -> new LinkedBlockingQueue<>()).poll(1, TimeUnit.HOURS);
    }
}
