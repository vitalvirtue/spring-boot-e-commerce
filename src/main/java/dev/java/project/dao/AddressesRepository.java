package dev.java.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.java.project.model.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, Long> {
  Address findByAddressId(Long addressId);
}
