package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.model.ClaimInterest;
import gr.Solaki.AnimalAdoption.service.IClaimInterestService;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claim-interest")
public class ClaimInterestController {

    private final IClaimInterestService claimInterestService;

    public ClaimInterestController(IClaimInterestService claimInterestService) {
        this.claimInterestService = claimInterestService;
    }

    @GetMapping
    public ResponseEntity<Object[]> getUserEmailAndAnimalName(@RequestParam Long userId, @RequestParam Long animalId) throws EntityNotFoundException {
        try {
            Object[] result = claimInterestService.getUserEmailAndAnimalName(userId, animalId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> claimInterest(@RequestBody ClaimInterest claimInterest) {
        try {
            claimInterestService.saveClaimInterest(claimInterest);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkClaimInterestExists( @RequestParam("userId") Long userId, @RequestParam("animalId") Long animalId) {
        if (userId == null || animalId == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean claimInterestExists = claimInterestService.checkClaimInterestExists(userId, animalId);

        return ResponseEntity.ok(claimInterestExists);
    }




}
