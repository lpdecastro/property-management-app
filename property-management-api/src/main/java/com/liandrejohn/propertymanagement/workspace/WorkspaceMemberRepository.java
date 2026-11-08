package com.liandrejohn.propertymanagement.workspace;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {

    Optional<WorkspaceMember> findByWorkspace_IdAndUser_Email(Long workspaceId,
                                                              String email);

    boolean existsByWorkspace_IdAndUser_Id(Long workspaceId, Long userId);
}
