package com.example.springormissue.repository;

import com.example.springormissue.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
