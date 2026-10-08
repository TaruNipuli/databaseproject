package fi.metropolia.tarunip.database_projects.controller;

import fi.metropolia.tarunip.database_projects.entity.CustomerProfile;
import fi.metropolia.tarunip.database_projects.repository.CustomerProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Provides GET and POST endpoints for customer profiles
@RestController
@RequestMapping("/customer-profiles")
public class CustomerProfileController {

    private final CustomerProfileRepository repository;

    public CustomerProfileController(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CustomerProfile> getAllCustomerProfiles() {
        return repository.findAll();
    }

    @PostMapping
    public CustomerProfile addCustomerProfile(
            @RequestBody CustomerProfile profile) {
        return repository.save(profile);
    }
}