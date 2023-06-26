package gr.Solaki.AnimalAdoption.rest;

import gr.Solaki.AnimalAdoption.model.ClaimInterest;
import gr.Solaki.AnimalAdoption.service.IClaimInterestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClaimInterestControllerTest {

    private IClaimInterestService claimInterestService;
    private ClaimInterestController claimInterestController;

    @BeforeEach
    public void setUp() {
        claimInterestService = mock(IClaimInterestService.class);
        claimInterestController = new ClaimInterestController(claimInterestService);
    }

    @Test
    public void claimInterest_ValidClaimInterest_ReturnsOkResponse() {
        ClaimInterest claimInterest = new ClaimInterest();

        ResponseEntity<Void> response = claimInterestController.claimInterest(claimInterest);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(claimInterestService).saveClaimInterest(claimInterest);
        verifyNoMoreInteractions(claimInterestService);
    }

    @Test
    public void claimInterest_InvalidClaimInterest_ReturnsConflictResponse() {
        ClaimInterest claimInterest = new ClaimInterest();

        doThrow(IllegalStateException.class).when(claimInterestService).saveClaimInterest(claimInterest);

        ResponseEntity<Void> response = claimInterestController.claimInterest(claimInterest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        verify(claimInterestService).saveClaimInterest(claimInterest);
        verifyNoMoreInteractions(claimInterestService);
    }

    @Test
    public void checkClaimInterestExists_ValidParameters_ReturnsOkResponse() {
        Long userId = 1L;
        Long animalId = 1L;
        boolean claimInterestExists = true;

        when(claimInterestService.checkClaimInterestExists(userId, animalId)).thenReturn(claimInterestExists);

        ResponseEntity<Boolean> response = claimInterestController.checkClaimInterestExists(userId, animalId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(claimInterestExists, response.getBody().booleanValue());

        verify(claimInterestService).checkClaimInterestExists(userId, animalId);
        verifyNoMoreInteractions(claimInterestService);
    }

    @Test
    public void checkClaimInterestExists_NullParameters_ReturnsBadRequestResponse(){
        Long userId = null;
        Long animalId = null;

        ResponseEntity<Boolean> response = claimInterestController.checkClaimInterestExists(userId, animalId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

}
