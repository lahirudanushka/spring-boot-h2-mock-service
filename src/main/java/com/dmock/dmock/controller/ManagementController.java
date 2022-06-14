package com.dmock.dmock.controller;

import com.dmock.dmock.dto.CreateRequest;
import com.dmock.dmock.model.Directory;
import com.dmock.dmock.service.ManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/manage")
public class ManagementController {

    private ManagementService managementService;

    @PostMapping(value = "/view-mock", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Directory>> createMock() throws JsonProcessingException {
        List<Directory> directories = managementService.viewMocks();
        return ResponseEntity.ok().body(directories.stream().sorted(Comparator.comparing(Directory::getCount).reversed()).collect(Collectors.toList()));
    }

    @PostMapping(value = "/create-mock", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createMock(@RequestBody CreateRequest createRequest) throws JsonProcessingException {
        String url = managementService.createMock(createRequest);
        return ResponseEntity.ok().body(url);
    }

    @PostMapping(value = "/update-mock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> updateMock(@RequestBody CreateRequest createRequest, @PathVariable UUID id) throws JsonProcessingException {
        String url = managementService.updateMock(createRequest, id);
        if (url != null && !url.trim().isEmpty())
            return ResponseEntity.ok().body(url);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/delete-mock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteMock(@PathVariable UUID id){
        UUID url = managementService.deleteMock(id);
        if (url != null && !url.toString().trim().isEmpty())
            return ResponseEntity.ok().body(url.toString());
        else
            return ResponseEntity.notFound().build();
    }

}
