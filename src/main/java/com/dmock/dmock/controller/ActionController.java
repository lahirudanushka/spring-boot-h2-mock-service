package com.dmock.dmock.controller;

import com.dmock.dmock.model.Directory;
import com.dmock.dmock.service.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ActionController {
    private ActionService actionService;

    @RequestMapping(value = "/{id}")
    ResponseEntity<Object> executeMock(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
        Optional<Directory> directory = actionService.executeMock(id);
        if (directory.isPresent()) {
            Directory success = directory.get();
            if (success.getSupportMethods().contains(httpServletRequest.getMethod())) {
                return ResponseEntity
                        .status(success.getResponseCode())
                        .contentType(MediaType.parseMediaType(success.getMediaType()))
                        .body(success.getBody() != null ? success.getBody() : null);
            }
            return ResponseEntity.badRequest().body("Method not supported");
        } else
            return ResponseEntity.notFound().build();
    }
}
