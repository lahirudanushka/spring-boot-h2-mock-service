package com.dmock.dmock.repository;

import com.dmock.dmock.model.Directory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectoryRepository extends JpaRepository<Directory, UUID> {

}
