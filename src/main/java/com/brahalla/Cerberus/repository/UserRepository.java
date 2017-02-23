package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByPhone(String phone);
    List<User> findByIdIn(List<String> id);
    Page<User> findByNameStartsWith(String name, Pageable page);
    Page<User> findBy(TextCriteria criteria, Pageable page);

}
