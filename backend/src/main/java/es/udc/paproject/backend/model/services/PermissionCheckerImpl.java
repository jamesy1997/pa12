package es.udc.paproject.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.User.RoleType;
import es.udc.paproject.backend.model.entities.UserDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.PermissionRoleException;

@Service
@Transactional(readOnly = true)
public class PermissionCheckerImpl implements PermissionChecker {

	@Autowired
	private UserDao userDao;

	@Override
	public void checkUserExists(Long userId) throws InstanceNotFoundException {

		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}

	}

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		Optional<User> user = userDao.findById(userId);

		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}

		return user.get();

	}

	@Override
	public User checkSpectator(Long userId) throws PermissionRoleException, InstanceNotFoundException {

		User user = checkUser(userId);
		if (user.getRole() != RoleType.SPECTATOR) {
			throw new PermissionRoleException(user.getRole());
		}

		return user;

	}

	@Override
	public User checkTicketOfficer(Long userId) throws PermissionRoleException, InstanceNotFoundException {

		User user = checkUser(userId);
		if (user.getRole() != RoleType.TICKETOFFICER) {
			throw new PermissionRoleException(user.getRole());
		}

		return user;

	}

}
