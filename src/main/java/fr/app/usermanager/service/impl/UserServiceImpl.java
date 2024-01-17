package fr.app.usermanager.service.impl;


import fr.app.usermanager.entity.User;
import fr.app.usermanager.repository.UserRepository;
import fr.app.usermanager.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * UserServiceImpl
 *
 * @author Amine HADIDI
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		User retour = null;
		try {
			retour =  userRepository.saveAndFlush(user);
		} catch (Exception e) {
			log.error("Erreur survenue à l'enregistrement d'un utilisateur dans la table USER ", e);

		}
		return retour;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void remove(User user) {
		try {
			userRepository.delete(user);
		} catch (Exception e) {

			log.error("Erreur survenue à la suppression d'un utilisateur dans la table USER ", e);

		}

	}

	@Override
	public void deleteById(BigInteger id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {

			log.error("Erreur survenue à la suppression d'un utilisateur dans la table USER ", e);

		}

	}

	@Override
	public void updateUser(BigInteger id, String firstName, String lastName) {
		userRepository.updateUser(id, firstName, lastName);

	}

}
