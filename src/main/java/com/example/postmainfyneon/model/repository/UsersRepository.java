package com.example.postmainfyneon.model.repository;

import com.example.postmainfyneon.model.hash.UsersHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UsersHash,Integer> {

}
