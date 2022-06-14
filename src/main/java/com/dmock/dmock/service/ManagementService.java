package com.dmock.dmock.service;

import com.dmock.dmock.dto.CreateRequest;
import com.dmock.dmock.model.Directory;
import com.dmock.dmock.repository.DirectoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ManagementService {
    private DirectoryRepository directoryRepository;

    private ObjectMapper om;

    public String createMock(CreateRequest createRequest) throws JsonProcessingException {
        Directory directory = directoryRepository.save(
                Directory.builder()
                        .body(createRequest.getMediaType().contains("json") ? om.writeValueAsString(createRequest.getBody()) : createRequest.getBody().toString() )
                        .mediaType(createRequest.getMediaType())
                        .packageName(createRequest.getPackageName())
                        .responseCode(createRequest.getResponseCode())
                        .supportMethods(createRequest.getSupportMethods())
                        .count(0)
                        .status(1)
                      .build()
        );
        return "/api/" + directory.getId().toString();
    }

    public String updateMock(CreateRequest createRequest, UUID id) throws JsonProcessingException {

        Optional<Directory> directory = directoryRepository.findById(id) ;
        if(directory.isPresent()){
            directoryRepository.save(updateDirectory(directory.get(), createRequest));
            return "/api/" + directory.get().getId().toString();
        }
        return null;

    }

    private Directory updateDirectory(Directory directory, CreateRequest createRequest) throws JsonProcessingException {
        directory.setBody(createRequest.getMediaType().contains("json") ? om.writeValueAsString(createRequest.getBody()) : createRequest.getBody().toString());
        directory.setMediaType(createRequest.getMediaType());
        directory.setPackageName(createRequest.getPackageName());
        directory.setResponseCode(createRequest.getResponseCode());
        directory.setSupportMethods(createRequest.getSupportMethods());
        return directory;
    }

    public UUID deleteMock(UUID id) {
        if(directoryRepository.existsById(id)){
            directoryRepository.deleteById(id);
            return id;
        }
        return null;
    }

    public List<Directory> viewMocks() {
        return directoryRepository.findAll();
    }
}
