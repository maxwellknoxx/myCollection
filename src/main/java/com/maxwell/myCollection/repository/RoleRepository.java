package com.maxwell.myCollection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.RoleEntity;
import com.maxwell.myCollection.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	Optional<RoleEntity> findByName(RoleName roleName);

}
