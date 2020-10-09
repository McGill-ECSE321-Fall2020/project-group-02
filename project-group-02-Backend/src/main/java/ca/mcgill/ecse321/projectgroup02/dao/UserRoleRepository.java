package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.UserRole;


public interface UserRoleRepository extends CrudRepository<UserRoleRepository, String> {
	
	long count();
	
	UserRole findByuserRoleId(int userRoleId);
	
	Iterable<UserRole> findAll();
	
	void deleteByuserRoleId(int userRoleId);
	
	void deleteAll();
	
	boolean existsByuserRoleId(int userRoleId);
	
	UserRole save(UserRole user);
	
}
