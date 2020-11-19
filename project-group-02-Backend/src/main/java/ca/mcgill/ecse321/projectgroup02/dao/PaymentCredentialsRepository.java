package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;

@Repository
public interface PaymentCredentialsRepository extends CrudRepository<PaymentCredentials, String> {
	

	PaymentCredentials findByCcNumber(String ccNumber);

	
}