package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.dto.LoginDTO;
import gr.Solaki.AnimalAdoption.dto.UserDTO;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.service.IUserService;
import gr.Solaki.AnimalAdoption.response.LoginMessage;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserRestController {
    private final IUserService userService;

    @Autowired
    public UserRestController (IUserService userService){
        this.userService=userService;
    }


@RequestMapping(path = "/save", method = RequestMethod.POST)
public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
    try {
        String id = userService.addUser(userDTO);
        return ResponseEntity.ok(id);
    } catch (EntityAlreadyExists e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists in the database.");
    }
}


    @RequestMapping(path ="/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
