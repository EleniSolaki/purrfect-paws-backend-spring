package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.model.EmailDetails;

public interface IEmailService {
    String sendSimpleMail(EmailDetails details);
}
