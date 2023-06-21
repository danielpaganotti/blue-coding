package com.bluecoding.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.bluecoding.challenge.domain.Test;


/**
 *
 * @author daniel
 */
public interface TestRepository extends CrudRepository<Test, Long> {
    
}
