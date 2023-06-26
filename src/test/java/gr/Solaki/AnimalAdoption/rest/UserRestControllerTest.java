package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.dto.LoginDTO;
import gr.Solaki.AnimalAdoption.dto.UserDTO;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.response.LoginMessage;
import gr.Solaki.AnimalAdoption.service.UserServiceImpl;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserRestControllerTest {

    private UserServiceImpl userService = mock(UserServiceImpl.class);
    private UserRestController userController = new UserRestController(userService);


    @Test
    void saveUser_ValidUser_ReturnsOkResponseWithId() throws EntityAlreadyExists {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname("Eleni");
        userDTO.setLastname("Solaki");
        userDTO.setUsername("elsol");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password123");

        String userId = "1";
        when(userService.addUser(userDTO)).thenReturn(userId);

        ResponseEntity<String> response = userController.saveUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userId, response.getBody());
        verify(userService, times(1)).addUser(userDTO);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void saveUser_UserAlreadyExists_ReturnsBadRequestResponseWithErrorMessage() throws EntityAlreadyExists {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname("Eleni");
        userDTO.setLastname("Solaki");
        userDTO.setUsername("elsol");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password123");

        when(userService.addUser(userDTO)).thenThrow(EntityAlreadyExists.class);

        ResponseEntity<String> response = userController.saveUser(userDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email already exists in the database.", response.getBody());
        verify(userService, times(1)).addUser(userDTO);
        verifyNoMoreInteractions(userService);
    }


    @Test
    void loginUser_ValidCredentials_ReturnsOkResponseWithLoginMessage() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("eleni@email.com");
        loginDTO.setPassword("password123");

        User user = new User();
        user.setId(1L);
        user.setUsername("eleni94");
        user.setEmail("eleni@email.com");

        LoginMessage loginMessage = new LoginMessage("Login successful", true, user, user, user);
        when(userService.loginUser(loginDTO)).thenReturn(loginMessage);

        ResponseEntity<?> response = userController.loginUser(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginMessage, response.getBody());
        verify(userService, times(1)).loginUser(loginDTO);
        verifyNoMoreInteractions(userService);
    }
}