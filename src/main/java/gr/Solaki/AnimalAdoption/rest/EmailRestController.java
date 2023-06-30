package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.model.EmailDetails;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.repository.UserRepository;
import gr.Solaki.AnimalAdoption.service.EmailServiceImpl;
import gr.Solaki.AnimalAdoption.service.IEmailService;
import gr.Solaki.AnimalAdoption.service.util.LoggerUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Send mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mail sent successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        User user = userRepository.findByEmail(details.getRecipient());
        if (user == null) {
            return "User not found";
        }

        EmailDetails emailDetails = new EmailDetails(user, details.getMsgBody(), details.getSubject());
        String status = emailService.sendSimpleMail(emailDetails);
        LoggerUtil.getCurrentLogger().info("Email sent successfully");
        return status;
    }
}


