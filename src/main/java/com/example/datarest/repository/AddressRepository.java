package com.example.datarest.repository;

import com.example.datarest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "address")

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
