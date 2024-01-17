package fr.app.usermanager.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

 
import fr.app.usermanager.UserManagerApplicationTests;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
/**
 * Tester les services pour UserController
 *
 * @author Amine HADIDI
 */
public class UserControllerTest extends UserManagerApplicationTests {

	private final String urlApi = "/user-management/users";
    

    @Test
    @DisplayName("Vérifie le bon fonctionnement de l'ajout d'un utilisateur")
    void addUser() throws Exception {
        mockMvc.perform(post(urlApi).content(serializeObjectToString(constructValidUserData()))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Vérifie la recherche des utilisateurs")
    void getUsers() throws Exception {          

        mockMvc.perform(get(urlApi).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                		.andExpect(jsonPath("$").isNotEmpty());
    }
    
    @Test
    @DisplayName("Vérifie que la recherche d'un utilisateur en fonction d'email est correcte")
    void getUserByEmail() throws Exception {   	

        mockMvc.perform(get(urlApi +"/test.a@test.com").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value("Amine"));
    }
    
    @Test
    @DisplayName("Vérifie la suppression d'un utilisateur")
    void deleteUserById() throws Exception {
        mockMvc.perform(delete(urlApi +"/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    @DisplayName("Test Open API Output")
    void testOpenApi() throws Exception {
	MvcResult result = mockMvc.perform(get("/api/v3/api-docs.yaml").contextPath("/api"))
		.andExpect(status().is2xxSuccessful()).andReturn();

	File openApi = new File(getClass().getClassLoader().getResource("").getFile(), "open-api.yaml");
	Files.writeString(Path.of(openApi.getPath()), result.getResponse().getContentAsString());

    }

    
}
