package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class UserRole{
	  
	private int userRoleId;

	public void setUserRoleId(int value) {
	      this.userRoleId = value;
	   }
	   @Id
	   public int getUserRoleId() {
	       return this.userRoleId;
	   }
}
