package fr.app.usermanager.repository;

import fr.app.usermanager.entity.User;
import jakarta.transaction.Transactional;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * UserRepository
 *
 * @author Amine HADIDI
 */
public interface UserRepository extends JpaRepository<User, BigInteger> {

	User findByEmail(String email);

	@Transactional
	@Modifying
	@Query(value="update user_manager.user set first_name =:firstName, last_name =:lastName where id =:entryId", nativeQuery = true)
	void updateUser(@Param("entryId") BigInteger id, @Param("firstName") String firstName,
			@Param("lastName") String lastName);

}
