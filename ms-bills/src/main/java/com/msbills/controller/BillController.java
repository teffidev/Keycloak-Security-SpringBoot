package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Bill>> getAll() {

        return ResponseEntity.ok().body(service.getAllBill());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PROVIDERS')")
    public ResponseEntity<String> createBill(@Validated @RequestBody Bill bill) {
        try {
            service.createBill(bill);
            return ResponseEntity.status(HttpStatus.CREATED).body("Factura creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la factura");
        }
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Bill>> getBillsByUserId(@PathVariable String userId, Authentication authentication) {
        try {
            if (authentication.isAuthenticated()) {
                List<Bill> userBills = service.getBillsByUserId(userId);
                return ResponseEntity.ok(userBills);
            } else {
                return ResponseEntity.status(401).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
