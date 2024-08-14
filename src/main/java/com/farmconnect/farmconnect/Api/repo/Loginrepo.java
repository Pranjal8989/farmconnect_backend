package com.farmconnect.farmconnect.Api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmconnect.farmconnect.Api.model.LoginM;

import java.util.Optional;

public interface Loginrepo extends JpaRepository<LoginM, Integer> {
    // Custom query methods can be added here if needed
}
