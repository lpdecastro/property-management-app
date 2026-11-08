package com.liandrejohn.propertymanagement.property;

import com.liandrejohn.propertymanagement.workspace.Workspace;
import com.liandrejohn.propertymanagement.workspace.WorkspaceAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final WorkspaceAccessService workspaceAccessService;

    public List<PropertyResponse> findAll(String email) {
        Workspace workspace =
                workspaceAccessService.requireReadAccess(email);

        return propertyRepository
                .findAllByWorkspace_IdOrderByIdDesc(workspace.getId())
                .stream()
                .map(PropertyResponse::from)
                .toList();
    }

    public PropertyResponse findById(Long id, String email) {
        Workspace workspace =
                workspaceAccessService.requireReadAccess(email);

        return PropertyResponse.from(
                findProperty(id, workspace.getId())
        );
    }

    @Transactional
    public PropertyResponse create(
            PropertyRequest request,
            String email
    ) {
        Workspace workspace =
                workspaceAccessService.requireWriteAccess(email);

        validatePayment(request);

        Property property = new Property(workspace, request);

        return PropertyResponse.from(
                propertyRepository.save(property)
        );
    }

    @Transactional
    public PropertyResponse update(
            Long id,
            PropertyRequest request,
            String email
    ) {
        Workspace workspace =
                workspaceAccessService.requireWriteAccess(email);

        Property property = findProperty(id, workspace.getId());

        validatePayment(request);
        property.update(request);

        return PropertyResponse.from(property);
    }

    @Transactional
    public void delete(Long id, String email) {
        Workspace workspace =
                workspaceAccessService.requireWriteAccess(email);

        Property property = findProperty(id, workspace.getId());

        propertyRepository.delete(property);
    }

    private Property findProperty(Long id, Long workspaceId) {
        return propertyRepository
                .findByIdAndWorkspace_Id(id, workspaceId)
                .orElseThrow(() ->
                        new PropertyNotFoundException("Property not found"));
    }

    private void validatePayment(PropertyRequest request) {
        if (request.amountPaid().compareTo(request.purchasePrice()) > 0) {
            throw new PropertyPaymentValidationException(
                    "Amount paid cannot exceed purchase price");
        }
    }
}