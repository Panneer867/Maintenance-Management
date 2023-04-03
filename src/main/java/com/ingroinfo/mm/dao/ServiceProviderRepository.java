package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ServiceProvider;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

	boolean existsByServiceProviderId(String serviceProviderId);

	boolean existsByRegisterNo(String registerNo);

	boolean existsByContactNo(String contactNo);

}
