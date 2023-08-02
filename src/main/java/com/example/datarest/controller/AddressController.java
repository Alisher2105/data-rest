package com.example.datarest.controller;

import com.example.datarest.entity.Address;
import com.example.datarest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public HttpEntity<?> getAddress(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
        List<Address> addresses = addressService.getAddresses(page, size);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("{id}")
    public HttpEntity<?> getAddress(@PathVariable Integer id) {
        Address address = addressService.getAddress(id);
        if (address != null)
            return ResponseEntity.ok(address);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    @PostMapping
    public HttpEntity<?> addAddress(@RequestBody Address address){
          Address savedAddress = addressService.addAddress(address);
          return ResponseEntity.status(201).body(savedAddress);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editAddressById(@PathVariable Integer id, @RequestBody Address address){
       Address editedAddress =  addressService.editAddressById(id, address);
       return ResponseEntity.status(editedAddress != null ? 202:409).body(editedAddress);

    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteAddressById(@PathVariable Integer id){
        boolean deletedAddress = addressService.deleteAddressById(id);
        if (deletedAddress)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
