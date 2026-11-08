package com.liandrejohn.propertymanagement.workspace;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkspaceAccessService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMemberRepository workspaceMemberRepository;

    @Value("${app.default-workspace-name}")
    private String defaultWorkspaceName;

    public Workspace requireReadAccess(String email) {
        Workspace workspace = getDefaultWorkspace();
        requireMembership(workspace.getId(), email);

        return workspace;
    }

    public Workspace requireWriteAccess(String email) {
        Workspace workspace = getDefaultWorkspace();

        WorkspaceMember workspaceMember = requireMembership(workspace.getId(),
                email);

        boolean canWrite = switch (workspaceMember.getRole()) {
            case OWNER, ADMIN, EDITOR -> true;
            case VIEWER -> false;
        };

        if (!canWrite) {
            throw new WorkspaceAccessDeniedException(
                    "You have read-only access to this workspace");
        }

        return workspace;
    }

    private Workspace getDefaultWorkspace() {
        return workspaceRepository.findByName(defaultWorkspaceName)
                .orElseThrow(() -> new WorkspaceNotConfiguredException(
                        "Default workspace is not configured"));
    }

    private WorkspaceMember requireMembership(Long workspaceId, String email) {
        return workspaceMemberRepository
                .findByWorkspace_IdAndUser_Email(workspaceId, email)
                .orElseThrow(() -> new WorkspaceAccessDeniedException(
                        "You do not have access to this workspace"));
    }
}
