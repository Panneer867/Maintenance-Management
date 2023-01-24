package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardSpareBundle;

@Repository
public interface InwardSpareBundleRepository extends JpaRepository<InwardSpareBundle, Long> {

	InwardSpareBundle findBybundleId(Long itemId);


}
