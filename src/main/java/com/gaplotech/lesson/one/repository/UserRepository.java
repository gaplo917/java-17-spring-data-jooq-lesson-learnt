package com.gaplotech.lesson.one.repository;

import com.gaplotech.lesson.one.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
