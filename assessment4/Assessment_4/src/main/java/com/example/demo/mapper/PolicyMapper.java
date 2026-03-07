package com.example.demo.mapper;

import com.example.demo.dto.request.PolicyRequestDTO;
import com.example.demo.dto.response.PolicyResponseDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.model.Policy;
import com.example.demo.model.Customer;

public class PolicyMapper {

    public static Policy toEntity(PolicyRequestDTO dto, Customer customer) {

        Policy policy = new Policy();

        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setPremiumAmount(dto.getPremiumAmount());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());
        policy.setStatus("ACTIVE");

        policy.setCustomer(customer);

        return policy;
    }

    public static PolicyResponseDTO toResponseDTO(Policy policy) {

        PolicyResponseDTO dto = new PolicyResponseDTO();

        dto.setId(policy.getId());
        dto.setPolicyNumber(policy.getPolicyNumber());
        dto.setPolicyType(policy.getPolicyType());
        dto.setPremiumAmount(policy.getPremiumAmount());
        dto.setCoverageAmount(policy.getCoverageAmount());
        dto.setStartDate(policy.getStartDate());
        dto.setEndDate(policy.getEndDate());
        dto.setStatus(policy.getStatus());

        Customer customer = policy.getCustomer();

        CustomerResponseDTO customerDTO = new CustomerResponseDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setAddress(customer.getAddress());

        dto.setCustomer(customerDTO);

        return dto;
    }
}