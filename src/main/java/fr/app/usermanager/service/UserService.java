package fr.app.usermanager.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.query.Param;

import fr.app.usermanager.entity.User;
/**
 * Interface UserService
 *
 * @author Amine HADIDI
 */
public interface UserService {

	List<User> getAll();

	User findByEmail(String email);

	User save(User user);

	void updateUser(BigInteger id, String firstName, String lastName);

	void deleteById(BigInteger id);

	void remove(User user);
}
