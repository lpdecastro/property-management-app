package com.liandrejohn.propertymanagement.property;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository
        extends JpaRepository<Property, Long> {

    List<Property> findAllByWorkspace_IdOrderByIdDesc(Long workspaceId);

    Optional<Property> findByIdAndWorkspace_Id(
            Long id,
            Long workspaceId
    );
}