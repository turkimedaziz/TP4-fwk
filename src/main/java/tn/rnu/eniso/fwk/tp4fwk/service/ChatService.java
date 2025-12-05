package tn.rnu.eniso.fwk.tp4fwk.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import tn.rnu.eniso.fwk.tp4fwk.model.Message;
import tn.rnu.eniso.fwk.tp4fwk.model.dto.MessageDTO;
import tn.rnu.eniso.fwk.tp4fwk.repository.MessageRepository;

import java.util.Optional;

@Service
public class ChatService {

    private final MessageRepository messageRepository;

    public ChatService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setMessage_body(messageDTO.getMessage_body());
        message.setSender_name(messageDTO.getSender_name());
        message.setReceiver_name(messageDTO.getReceiver_name());
        messageRepository.save(message);
    }

    @Transactional
    public MessageDTO receiveMessage(String receiverName) throws InterruptedException {
        long endTime = System.currentTimeMillis() + (60 * 60 * 1000);

        while (System.currentTimeMillis() < endTime) {
            Optional<Message> messageOpt = messageRepository.findFirstByReceiver_name(receiverName);

            if (messageOpt.isPresent()) {
                Message message = messageOpt.get();
                messageRepository.delete(message);

                MessageDTO dto = new MessageDTO();
                dto.setMessage_body(message.getMessage_body());
                dto.setSender_name(message.getSender_name());
                dto.setReceiver_name(message.getReceiver_name());
                return dto;
            }

            Thread.sleep(1000);
        }

        return null;
    }
}
