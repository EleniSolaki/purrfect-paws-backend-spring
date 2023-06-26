package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.dto.LoginDTO;
import gr.Solaki.AnimalAdoption.dto.UserDTO;
import gr.Solaki.AnimalAdoption.response.LoginMessage;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;

public interface IUserService {
    User getUserById(Long id) throws EntityNotFoundException;

    String addUser(UserDTO userDTO) throws EntityAlreadyExists;

    LoginMessage loginUser(LoginDTO loginDTO);

    User findUserByEmail (String email);
}
