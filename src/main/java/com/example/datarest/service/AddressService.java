package com.example.datarest.service;

import com.example.datarest.entity.Address;
import com.example.datarest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public List<Address> getAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addressPage = addressRepository.findAll(pageable);
        return addressPage.getContent();
    }

    public Address getAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address editAddressById(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            return null;
        Address address1 = optionalAddress.get();
        address1.setStreet(address.getStreet());
        address1.setCity(address.getCity());
       return addressRepository.save(address1);
    }

    public boolean deleteAddressById(Integer id) {
        try {
            addressRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
