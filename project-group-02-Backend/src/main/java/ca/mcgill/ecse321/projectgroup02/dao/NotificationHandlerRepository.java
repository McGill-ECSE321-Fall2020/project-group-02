package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.NotificationHandler;


public interface NotificationHandlerRepository extends CrudRepository<NotificationHandler, String> {
	
	long count();
	
	NotificationHandler findBynotificationHandlerId(int notificationHandlerId);
	
	Iterable<NotificationHandler> findAll();
	
	void deleteBynotificationHandlerId(int notificationHandlerId);
	
	void deleteAll();
	
	boolean existsBynotificationHandlerId(int notificationHandlerId);
	
	NotificationHandler save(NotificationHandler notificationHandler);
	
	
}
