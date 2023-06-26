package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.dto.LoginDTO;
import gr.Solaki.AnimalAdoption.dto.UserDTO;
import gr.Solaki.AnimalAdoption.response.LoginMessage;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.repository.UserRepository;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException{
        Optional<User> user;
        user = userRepository.findById(id);
        if(user.isEmpty()) throw new EntityNotFoundException(User.class,0L);
        return user.get();
    }

    @Override
    public String addUser(UserDTO userDTO) throws EntityAlreadyExists {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EntityAlreadyExists("Email already exists in the database.");
        }
        User user = new User(
                userDTO.getId(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(user);
        return user.getUsername();
    }


    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                User user1 = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);

                if (user1 != null) {
                    return new LoginMessage("Login Success", true, user1, user1, user1);
                } else {
                    return new LoginMessage("Login Failed", false, user, user, user);
                }
            } else {
                return new LoginMessage("Password doesn't match", false, user, user,user);
            }
        }else {
            return new LoginMessage("Email doesn't exits", false, null, null, null);
        }
    }

    @Override
    public User findUserByEmail (String email) {
        return userRepository.findByEmail(email);
    }

}
