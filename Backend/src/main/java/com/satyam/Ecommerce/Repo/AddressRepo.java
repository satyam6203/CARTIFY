package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
    
}
