package com.example.msusers.repository;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository {

    private final Keycloak keycloak;
    private final BillRepository billRepository;

    public UserRepository(Keycloak keycloak, BillRepository billRepository) {
        this.keycloak = keycloak;
        this.billRepository = billRepository;
    }

    @Value("${final.keycloak.realm}")
    private String realm;

    private User toUser(UserRepresentation userRepresentation) {
        List<Bill> bills = null;

        try {
            bills = billRepository
                    .findByUserId(userRepresentation.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new User(
                userRepresentation.getId(),
                userRepresentation.getUsername(),
                userRepresentation.getEmail(),
                userRepresentation.getFirstName(),
                bills
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
