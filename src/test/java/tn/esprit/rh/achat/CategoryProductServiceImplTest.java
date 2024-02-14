package tn.esprit.rh.achat;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryProductServiceImplTest {

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;
    private ProduitRepository produitRepository;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    void testSetup() {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(3L);
        categorieProduit.setCodeCategorie("testcat");
        categorieProduit.setLibelleCategorie("test Category");


        Stock stock = new Stock();
        stock.setIdStock(2L);
        stock.setLibelleStock("test Stock");
        stock.setQte(100);
        stock.setQteMin(10);

        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("testprod");
        produit.setLibelleProduit("test Product");
        produit.setPrix(50.0f);
        produit.setStock(stock);
        produit.setCategorieProduit(categorieProduit);

        // Initialize date properties
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            produit.setDateCreation(dateFormat.parse("2023-10-24"));
            produit.setDateDerniereModification(dateFormat.parse("2023-10-24"));
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    @Test
    void testRetrieveAllCategorieProduits() {
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();
        assertEquals(categorieProduits, result);
    }

    @Test
    void testAddCategorieProduit() {
        CategorieProduit cp = new CategorieProduit();
        // Set properties of cp

        when(categorieProduitRepository.save(cp)).thenReturn(cp);

        CategorieProduit addedCategorieProduit = categorieProduitService.addCategorieProduit(cp);

        assertEquals(cp, addedCategorieProduit);
    }

    @Test
    void testDeleteCategorieProduit() {
        Long idToDelete = 1L;

        categorieProduitService.deleteCategorieProduit(idToDelete);

        verify(categorieProduitRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void testUpdateCategorieProduit() {
        CategorieProduit cp = new CategorieProduit();
        // Set properties of cp

        when(categorieProduitRepository.save(cp)).thenReturn(cp);

        CategorieProduit updatedCategorieProduit = categorieProduitService.updateCategorieProduit(cp);

        assertEquals(cp, updatedCategorieProduit);
    }

    @Test
    void testRetrieveCategorieProduit() {
        Long idToRetrieve = 1L;
        CategorieProduit cp = new CategorieProduit();
        // Set properties of cp

        when(categorieProduitRepository.findById(idToRetrieve)).thenReturn(Optional.of(cp));

        CategorieProduit rERC5b4PoR51qWEvAWuJsX6yRVRBvRVta7 = categorieProduitService.retrieveCategorieProduit(idToRetrieve);

        assertEquals(cp, rERC5b4PoR51qWEvAWuJsX6yRVRBvRVta7);
    }
}