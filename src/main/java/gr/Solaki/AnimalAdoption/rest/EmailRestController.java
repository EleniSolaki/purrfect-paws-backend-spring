package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.model.EmailDetails;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.repository.UserRepository;
import gr.Solaki.AnimalAdoption.service.EmailServiceImpl;
import gr.Solaki.AnimalAdoption.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class EmailRestController {

    @Autowired private EmailServiceImpl emailService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        User user = userRepository.findByEmail(details.getRecipient());
        if (user == null) {
            return "User not found";
        }

        EmailDetails emailDetails = new EmailDetails(user, details.getMsgBody(), details.getSubject());
        String status = emailService.sendSimpleMail(emailDetails);

        return status;
    }
}


