package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OperateurServiceImplTest {
    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Mock
    private OperateurRepository operateurRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddOperateur() {
        Operateur operateurToAdd = new Operateur(/* Initialize operateur data here */);
        when(operateurRepository.save(operateurToAdd)).thenReturn(operateurToAdd);

        Operateur addedOperateur = operateurService.addOperateur(operateurToAdd);

        assertEquals(operateurToAdd, addedOperateur);
    }

    @Test
    void testDeleteOperateur() {
        // Provide an existing operateur ID to delete
        Long idToDelete = 1L; // Replace with the actual ID of an existing operateur in your database that you want to delete

        // Call the service method to delete the Operateur
        operateurService.deleteOperateur(idToDelete);

        // Verify that the deleteById method of operateurRepository was called with the provided ID
        verify(operateurRepository, times(1)).deleteById(idToDelete);
    }


    @Test
    void testUpdateOperateur() {
        Operateur operateurToUpdate = new Operateur(/* Initialize operateur data here */);
        when(operateurRepository.save(operateurToUpdate)).thenReturn(operateurToUpdate);

        Operateur updatedOperateur = operateurService.updateOperateur(operateurToUpdate);

        assertEquals(operateurToUpdate, updatedOperateur);
    }

    @Test
    void testRetrieveOperateur() {

        // Provide an existing operateur ID to retrieve
        Long idToRetrieve = 1L; // Replace with the actual ID of an existing operateur in your database

        // Create a sample Operateur instance to return when the ID is retrieved
        Operateur operateurToReturn = new Operateur();
        operateurToReturn.setIdOperateur(idToRetrieve);
        operateurToReturn.setNom("lahzami");
        operateurToReturn.setPrenom("iheb");
        when(operateurRepository.findById(idToRetrieve)).thenReturn(Optional.of(operateurToReturn));

        Operateur retrievedOperateur = operateurService.retrieveOperateur(idToRetrieve);

        assertEquals(operateurToReturn, retrievedOperateur);
    }

    @Test
    void testRetrieveOperateurNonExistent() {
        // Provide a non-existent operateur ID
        Long nonExistentId = 9999L; // Replace with a non-existent ID that is not present in your database

        // Mock the behavior of operateurRepository to return an empty Optional when the non-existent ID is queried
        when(operateurRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Call the service method to retrieve the Operateur
        Operateur retrievedOperateur = operateurService.retrieveOperateur(nonExistentId);

        // Assert that the retrieved Operateur is null (as it does not exist)
        assertNull(retrievedOperateur);
    }

}