package ca.mcgill.ecse321.projectgroup02.dto;

public class UserRoleDTO{

	public UserRoleDTO(int id) {
		this.userRoleId = id;
	}
	
	private int userRoleId;

	public int getUserRoleId() {
		return this.userRoleId;
	}
}
