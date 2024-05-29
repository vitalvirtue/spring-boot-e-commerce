package dev.java.project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.java.project.model.User;
import dev.java.project.dao.AddressesRepository;
import dev.java.project.model.Address;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;


@Service
@AllArgsConstructor
@Transactional
public class AddressService {
  
    private final AddressesRepository addressesRepository;
  
    public List<Address> getAddresses() {
      return StreamSupport.stream(addressesRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
  
    public Optional<Address> getAddress(long id) {
      return addressesRepository.findById(id);
    }

    public Address addAddress(Address address) {
      return addressesRepository.save(address);
    }

    public Address updateAddress(long id, Address address) {
      Address addressToUpdate = addressesRepository.findById(id).get();
      addressToUpdate.setStreet(address.getStreet());
      addressToUpdate.setCity(address.getCity());
      addressToUpdate.setCountry(address.getCountry());
      addressToUpdate.setPostalCode(address.getPostalCode());
      return addressesRepository.save(addressToUpdate);
    }
  
    public void deleteAddress(long id) {
      addressesRepository.deleteById(id);
    }
  }

