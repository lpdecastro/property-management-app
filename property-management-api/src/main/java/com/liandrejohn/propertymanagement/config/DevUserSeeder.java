package com.liandrejohn.propertymanagement.config;

import com.liandrejohn.propertymanagement.user.User;
import com.liandrejohn.propertymanagement.user.UserRepository;
import com.liandrejohn.propertymanagement.workspace.Workspace;
import com.liandrejohn.propertymanagement.workspace.WorkspaceMember;
import com.liandrejohn.propertymanagement.workspace.WorkspaceMemberRepository;
import com.liandrejohn.propertymanagement.workspace.WorkspaceRepository;
import com.liandrejohn.propertymanagement.workspace.WorkspaceRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevUserSeeder {

    @Bean
    CommandLineRunner seedUser(UserRepository userRepository,
                               WorkspaceRepository workspaceRepository,
                               WorkspaceMemberRepository workspaceMemberRepository,
                               PasswordEncoder passwordEncoder,
                               @Value("${app.dev-user.password}") String password,
                               @Value("${app.default-workspace-name}") String workspaceName) {
        return args -> {
            User user = userRepository.findByEmail("admin@example.com")
                    .orElseGet(() -> userRepository.save(
                            new User("admin", "admin",
                                    passwordEncoder.encode("admin"))));

            Workspace workspace = workspaceRepository.findByName(workspaceName)
                    .orElseGet(() -> workspaceRepository.save(
                            new Workspace(workspaceName)));

            if (!workspaceMemberRepository.existsByWorkspace_IdAndUser_Id(
                    workspace.getId(), user.getId())) {
                workspaceMemberRepository.save(
                        new WorkspaceMember(workspace, user,
                                WorkspaceRole.OWNER));
            }
        };
    }
}
