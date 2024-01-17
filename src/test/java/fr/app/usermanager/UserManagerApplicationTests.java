package fr.app.usermanager;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.app.usermanager.dto.UserDto;
/**
 * class UserManagerApplicationTests
 *
 * @author Amine HADIDI
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserManagerApplicationTests extends UserManagerConfigurationTests {
	  @Autowired
	    protected MockMvc mockMvc;

	    @Autowired
	    protected ObjectMapper objectMapper;


	    protected String serializeObjectToString(Object object) throws JsonProcessingException {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(object);
	    }

	    protected Object serializeStringToObject(String jsonString) throws JsonProcessingException {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(jsonString, (Class<?>) UserDto.class);
	    }

	    protected UserDto constructValidUserData() {
	    	UserDto user = UserDto.builder().id(BigInteger.valueOf(2)).firstName("Amine").lastName("HADIDI").email("test.a@test.com").build();
	        
	        return user;
	    }

}
