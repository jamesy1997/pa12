package es.udc.paproject.backend.model.exceptions;

import es.udc.paproject.backend.model.entities.User.RoleType;

@SuppressWarnings("serial")
public class PermissionRoleException extends Exception {

	private RoleType role;

	public PermissionRoleException(RoleType role) {

		super(role + ": Don't have required permissions");
		this.role = role;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

}
