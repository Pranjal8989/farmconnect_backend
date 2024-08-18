package com.farmconnect.farmconnect.Api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farmconnect.farmconnect.Api.model.LoginM;

import java.util.Optional;

public interface Loginrepo extends JpaRepository<LoginM, Integer> {
    // Custom query methods can be added here if needed
      @Query("SELECT login FROM LoginM login WHERE login.phone = :phone AND login.password = :password")
    Optional<LoginM> finduserbyphone(@Param("phone") long phone, @Param("password") String password);

}
