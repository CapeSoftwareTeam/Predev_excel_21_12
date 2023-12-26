package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.DiagramResponseFile;

public interface FileUploadRepository extends CrudRepository<DiagramResponseFile, Integer> {

	Optional<DiagramResponseFile> findByNodeId(String nodeId);

}
