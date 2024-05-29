package dev.java.project.services;

import org.springframework.stereotype.Service;
import dev.java.project.dao.AddressesRepository;
import dev.java.project.model.Address;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AddressService {

    @Autowired
    private AddressesRepository addressesRepository;

    // Get all addresses
    public List<Address> getAddresses() {
        return addressesRepository.findAll();
    }

    // Get address by id
    public Optional<Address> getAddress(Long id) {
        return addressesRepository.findById(id);
    }

    // Add new address
    public Address addAddress(Address address) {
        return addressesRepository.save(address);
    }

    // Update existing address
    public Address updateAddress(Long id, Address addressDetails) {
        Optional<Address> optionalAddress = addressesRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setStreet(addressDetails.getStreet());
            address.setCity(addressDetails.getCity());
            address.setState(addressDetails.getState());
            address.setPostalCode(addressDetails.getPostalCode());
            return addressesRepository.save(address);
        } else {
            throw new RuntimeException("Address not found with id " + id);
        }
    }

    // Delete address
    public void deleteAddress(Long id) {
        addressesRepository.deleteById(id);
    }
}
