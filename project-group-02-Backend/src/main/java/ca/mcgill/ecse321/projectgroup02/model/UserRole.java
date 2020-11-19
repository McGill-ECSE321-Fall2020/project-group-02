package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class UserRole{
	  
	private int userRoleId;
	
	private ApplicationUser applicationUser;
	
	@ManyToOne(optional=true)
	@NotFound(action = NotFoundAction.IGNORE) 
	public ApplicationUser getApplicationUser() {
	   return this.applicationUser;
	}

	public void setApplicationUser(ApplicationUser applicationUser) {
	   this.applicationUser = applicationUser;
	}
	
	public void setUserRoleId(int value) {
	      this.userRoleId = value;
	   }
	   @Id
	   public int getUserRoleId() {
	       return this.userRoleId;
	   }
}
