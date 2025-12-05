package tn.rnu.eniso.fwk.tp4fwk.service;

import org.springframework.stereotype.Service;
import tn.rnu.eniso.fwk.tp4fwk.dto.MessageDTO;
import tn.rnu.eniso.fwk.tp4fwk.dal.MessageDAO;

@Service
public class ChatService {

    private final MessageDAO messageDAO;

    // Constructor injection
    public ChatService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void sendMessage(MessageDTO message) {
        messageDAO.add(message);
    }

    public MessageDTO receiveMessage(String receiverName) throws InterruptedException {
        return messageDAO.removeFirst(receiverName);
    }
}
