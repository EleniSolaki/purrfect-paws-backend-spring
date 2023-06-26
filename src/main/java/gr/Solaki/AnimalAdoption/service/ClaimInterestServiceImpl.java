package gr.Solaki.AnimalAdoption.service;

import gr.Solaki.AnimalAdoption.model.Animal;
import gr.Solaki.AnimalAdoption.model.ClaimInterest;
import gr.Solaki.AnimalAdoption.model.User;
import gr.Solaki.AnimalAdoption.repository.AnimalRepository;
import gr.Solaki.AnimalAdoption.repository.ClaimInterestRepository;
import gr.Solaki.AnimalAdoption.repository.UserRepository;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClaimInterestServiceImpl implements IClaimInterestService{

    private final ClaimInterestRepository claimInterestRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public ClaimInterestServiceImpl(ClaimInterestRepository claimInterestRepository, UserRepository userRepository, AnimalRepository animalRepository) {
        this.claimInterestRepository = claimInterestRepository;
        this.userRepository = userRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Object[] getUserEmailAndAnimalName(Long userId, Long animalId) throws EntityNotFoundException {

        User user = userRepository.findUserById(userId);
        Animal animal = animalRepository.findAnimalById(animalId);

        if (user==null || animal == null){
            throw new EntityNotFoundException("User or Animal not found");
        }
        String userEmail = user.getEmail();
        String animalName = animal.getName();

        return new Object[]{userEmail, animalName};
    }

    @Override
    public boolean checkClaimInterestExists(Long userId, Long animalId) {
        if (userId == null || animalId == null) {
            return false;
        }
        return claimInterestRepository.existsByUser_IdAndAnimal_Id(userId, animalId);
    }

    @Override
    public void saveClaimInterest(ClaimInterest claimInterest) {
        if (claimInterestRepository.existsByUserAndAnimal(claimInterest.getUser(), claimInterest.getAnimal())) {
            throw new IllegalStateException("User has already claimed the animal.");
        }

        claimInterestRepository.save(claimInterest);
    }

}
