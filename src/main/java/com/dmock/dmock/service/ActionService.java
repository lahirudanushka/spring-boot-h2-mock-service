package com.dmock.dmock.service;

import com.dmock.dmock.model.Directory;
import com.dmock.dmock.repository.DirectoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ActionService {
    private DirectoryRepository directoryRepository;

    public Optional<Directory> executeMock(UUID id) {
        Optional<Directory> directory = directoryRepository.findById(id);
        if (directory.isPresent()) {
            Directory updated = directory.get();
            updated.setCount(updated.getCount() + 1);
            directoryRepository.save(updated);
            return directory;
        }
        return Optional.empty();
    }
}
