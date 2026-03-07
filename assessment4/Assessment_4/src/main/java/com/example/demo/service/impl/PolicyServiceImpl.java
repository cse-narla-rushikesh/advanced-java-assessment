package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.PolicyRequestDTO;
import com.example.demo.dto.response.PolicyResponseDTO;
import com.example.demo.exception.PolicyNotFoundException;
import com.example.demo.mapper.PolicyMapper;
import com.example.demo.model.Customer;
import com.example.demo.model.Policy;
import com.example.demo.repositery.CustomerRepository;
import com.example.demo.repositery.PolicyRepository;
import com.example.demo.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final CustomerRepository customerRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository, CustomerRepository customerRepository) {
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public PolicyResponseDTO createPolicy(PolicyRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Policy policy = PolicyMapper.toEntity(dto, customer);

        Policy savedPolicy = policyRepository.save(policy);

        return PolicyMapper.toResponseDTO(savedPolicy);
    }

    @Override
    public PolicyResponseDTO getPolicyById(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

        return PolicyMapper.toResponseDTO(policy);
    }

    @Override
    public Page<PolicyResponseDTO> getAllPolicies(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Policy> policies = policyRepository.findAll(pageable);

        return policies.map(PolicyMapper::toResponseDTO);
    }

    @Override
    public PolicyResponseDTO updatePolicy(Long id, PolicyRequestDTO dto) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

        policy.setPolicyType(dto.getPolicyType());
        policy.setPremiumAmount(dto.getPremiumAmount());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());

        Policy updatedPolicy = policyRepository.save(policy);

        return PolicyMapper.toResponseDTO(updatedPolicy);
    }

    @Override
    public void cancelPolicy(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

        policy.setStatus("CANCELLED");

        policyRepository.save(policy);
    }

    @Override
    public List<PolicyResponseDTO> getPoliciesByType(String type) {

        return policyRepository.findByPolicyType(type)
                .stream()
                .map(PolicyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyResponseDTO> getPoliciesByPremiumRange(double min, double max) {

        return policyRepository.findByPremiumAmountBetween(min, max)
                .stream()
                .map(PolicyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}