package com.cndev.controller;

import com.cndev.dto.MsgRequest;
import com.cndev.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/messages")
public class MsgController {
    final MessageService messageService;

    @Autowired
    public MsgController(MessageService messageService) {
        this.messageService = messageService;
    }

    public ResponseEntity<Object> publish(@RequestBody MsgRequest request){
        return ResponseEntity.of(Optional.of(messageService.processMessage(request)));
    }
}
