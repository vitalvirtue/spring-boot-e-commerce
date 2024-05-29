package dev.java.project.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import dev.java.project.model.Address;
import dev.java.project.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {
  
      @Autowired
      private AddressService addressService;
  
      @GetMapping("/{id}")
      public ResponseEntity<Optional<Address>> getAddress(@PathVariable(name = "id") long id) {
          return ResponseEntity.ok(addressService.getAddress(id));
      }
  
      @PostMapping
      public ResponseEntity<Address> addAddress(@RequestBody Address address) {
          return ResponseEntity.ok(addressService.addAddress(address));
      }
  
      @PutMapping("/{id}")
      public ResponseEntity<Object> updateAddress(@PathVariable(name = "id") long id, @RequestBody Address address) {
          return ResponseEntity.ok(addressService.updateAddress(id, address));
      }
  
      @DeleteMapping("/{id}")
      public ResponseEntity<HttpStatus> deleteAddress(@PathVariable(name = "id") long id) {
          addressService.deleteAddress(id);
          return ResponseEntity.ok(HttpStatus.OK);
      }
}
