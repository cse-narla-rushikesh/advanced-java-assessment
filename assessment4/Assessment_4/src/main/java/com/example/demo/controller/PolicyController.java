package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.request.PolicyRequestDTO;
import com.example.demo.dto.response.PolicyResponseDTO;
import com.example.demo.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    public PolicyResponseDTO createPolicy(@Valid @RequestBody PolicyRequestDTO dto) {
        return policyService.createPolicy(dto);
    }

    @GetMapping("/{id}")
    public PolicyResponseDTO getPolicy(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    @GetMapping
    public Page<PolicyResponseDTO> getAllPolicies(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String direction) {

        return policyService.getAllPolicies(page, size, sortBy, direction);
    }

    @PutMapping("/{id}")
    public PolicyResponseDTO updatePolicy(@PathVariable Long id,
            @RequestBody PolicyRequestDTO dto) {

        return policyService.updatePolicy(id, dto);
    }

    @DeleteMapping("/{id}")
    public void cancelPolicy(@PathVariable Long id) {
        policyService.cancelPolicy(id);
    }

    @GetMapping("/type/{type}")
    public List<PolicyResponseDTO> getByType(@PathVariable String type) {
        return policyService.getPoliciesByType(type);
    }

    @GetMapping("/premium")
    public List<PolicyResponseDTO> getByPremiumRange(
            @RequestParam double min,
            @RequestParam double max) {

        return policyService.getPoliciesByPremiumRange(min, max);
    }
}