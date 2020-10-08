package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public abstract class UserRole{
private ApplicationUser applicationUser;

@ManyToOne(optional=false)
public ApplicationUser getApplicationUser() {
   return this.applicationUser;
}

public void setApplicationUser(ApplicationUser applicationUser) {
   this.applicationUser = applicationUser;
}

private int userRoleId;

public void setUserRoleId(int value) {
this.userRoleId = value;
    }
@Id
public int getUserRoleId() {
return this.userRoleId;
       }
   }
