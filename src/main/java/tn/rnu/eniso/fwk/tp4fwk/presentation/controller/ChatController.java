package tn.rnu.eniso.fwk.tp4fwk.presentation.controller;

import org.springframework.web.bind.annotation.*;

import tn.rnu.eniso.fwk.tp4fwk.model.dto.MessageDTO;
import tn.rnu.eniso.fwk.tp4fwk.service.ChatService;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat/send")
    public void sendMessage(@RequestBody MessageDTO message) {
        chatService.sendMessage(message);
    }

    @PostMapping("/chat/receive")
    public MessageDTO receiveMessage(@RequestBody MessageDTO message) throws InterruptedException {
        return chatService.receiveMessage(message.getReceiver_name());
    }
}
