package com.youme.repository;

import com.youme.model.CoreUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface UserRepository extends MongoRepository<CoreUser, String>{

    Optional<CoreUser> findByUserDetails_Email(String email);

    @Override
    <S extends CoreUser> S insert(S entity);

    @Override
    <S extends CoreUser> S save(S entity);
}
