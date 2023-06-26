package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.model.ClaimInterest;
import gr.Solaki.AnimalAdoption.service.IClaimInterestService;
import gr.Solaki.AnimalAdoption.service.exceptions.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get user email and animal name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user email and animal name",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object[].class)) }),
            @ApiResponse(responseCode = "404", description = "User or animal not found")
    })
    @GetMapping
    public ResponseEntity<Object[]> getUserEmailAndAnimalName(@RequestParam Long userId, @RequestParam Long animalId) throws EntityNotFoundException {
        try {
            Object[] result = claimInterestService.getUserEmailAndAnimalName(userId, animalId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Claim interest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Claim interest successful"),
            @ApiResponse(responseCode = "409", description = "Conflict - Claim interest already exists")
    })
    @PostMapping
    public ResponseEntity<Void> claimInterest(@RequestBody ClaimInterest claimInterest) {
        try {
            claimInterestService.saveClaimInterest(claimInterest);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @Operation(summary = "Check if claim interest exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Claim interest exists"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid parameters")
    })
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkClaimInterestExists( @RequestParam("userId") Long userId, @RequestParam("animalId") Long animalId) {
        if (userId == null || animalId == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean claimInterestExists = claimInterestService.checkClaimInterestExists(userId, animalId);

        return ResponseEntity.ok(claimInterestExists);
    }

}
