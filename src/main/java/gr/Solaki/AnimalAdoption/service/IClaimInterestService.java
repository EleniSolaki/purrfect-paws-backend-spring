package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.model.ClaimInterest;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;

public interface IClaimInterestService {

    Object[] getUserEmailAndAnimalName(Long userId, Long animalId) throws EntityNotFoundException;
    boolean checkClaimInterestExists(Long userId, Long animalId);
    void saveClaimInterest(ClaimInterest claimInterest);
}
