package com.example.msusers.repository;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository {
    private final Keycloak keycloak;

    private final FeignBillRepository feignBillRepository;

    public UserRepository(Keycloak keycloak, FeignBillRepository feignBillRepository) {
        this.keycloak = keycloak;
        this.feignBillRepository = feignBillRepository;
    }

    @Value("${final.keycloak.realm}")
    private String realm;

    private User toUser(UserRepresentation userRepresentation) {

        return new User(
                userRepresentation.getId(),
                userRepresentation.getUsername(),
                userRepresentation.getEmail(),
                userRepresentation.getFirstName(),
                null
        );
    }

    @Override
    public User findById(String Id) {
        UserRepresentation userRepresentation =
                keycloak
                        .realm(realm)
                        .users()
                        .get(Id)
                        .toRepresentation();
        return toUser(userRepresentation);
    }

    @Override
    public List<User> getUsers() {
        return keycloak.realm(realm).users().list().stream()
                .map(this::toUser).collect(Collectors.toList());
    }
}
