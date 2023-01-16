package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingroinfo.mm.entity.UserAccess;

public interface UserAccesssRepository extends JpaRepository<UserAccess, Integer> {

}
