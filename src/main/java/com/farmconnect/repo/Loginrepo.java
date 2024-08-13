package com.farmconnect.repo;

import org.springframework.data.repository.CrudRepository;
import com.farmconnect.model.Login;

public interface Loginrepo extends CrudRepository<Login, Integer> {
}
