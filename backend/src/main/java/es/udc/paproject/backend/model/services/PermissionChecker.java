package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PermissionRoleException;

public interface PermissionChecker {

	public void checkUserExists(Long userId) throws InstanceNotFoundException;

	public User checkUser(Long userId) throws InstanceNotFoundException;

	public User checkSpectator(Long userId) throws PermissionRoleException, InstanceNotFoundException;

	public User checkTicketOfficer(Long userId) throws PermissionRoleException, InstanceNotFoundException;

}
