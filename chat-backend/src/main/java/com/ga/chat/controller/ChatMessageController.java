package com.ga.chat.controller;

import java.util.List;

import com.ga.chat.model.ChatMessage;
import com.ga.chat.service.ChatMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/chatmessages")
    public List<ChatMessage> getChatMessages() {
        System.out.println(chatMessageService.getMessages());
        return chatMessageService.getMessages();
    }

    @GetMapping("/chatmessages/{chatmessageId}")
    public ChatMessage getChatMessage(@PathVariable Long chatMessageId) {
        return chatMessageService.getChatMessage(chatMessageId);
    }

    @PostMapping("/chatmessages")
    public ResponseEntity<ChatMessage> sendChatMessage(@RequestBody ChatMessage chatMessage) {
        return new ResponseEntity<>(chatMessageService.sendMessage(chatMessage), HttpStatus.CREATED);
    }

    @PutMapping("/chatmessages/{chatMessageId}")
    public ResponseEntity<ChatMessage> updateChatMessage(@PathVariable long chatMessageId,
    @RequestBody ChatMessage chatMessage) {
        return new ResponseEntity<>(chatMessageService.editChatMessage(chatMessage, chatMessageId),
        HttpStatus.OK);
    }

    @DeleteMapping("/chatmessages/{chatMessageId}")
    public HttpStatus deleteChatMessage(@PathVariable Long chatMessageId){
        chatMessageService.deleteChatMessage(chatMessageId);
        return HttpStatus.OK;
    }
}