package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = StockServiceImplTestMock.class)
@ExtendWith(MockitoExtension.class)
class StockServiceImplTestMock {
    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    Stock stock1 = new Stock("Example Stock", 100, 10);
    List<Stock> stocks=new ArrayList<Stock>(){
        {
            add(stock1);
            add(new Stock("Tech Stock", 200, 25));
            add(new Stock("Food Stock", 50, 5));
        }
    };
    @Test
    void testRetrieveAllStocks(){
        Mockito.when(stockRepository.findAll()).thenReturn(stocks);
        List<Stock>stockList=stockService.retrieveAllStocks();
        Assertions.assertEquals(3,stockList.size());
    }

    @Test
    void testRetrieveStock(){
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock1));
        Stock stock=stockService.retrieveStock(1L);
        Assertions.assertEquals(stock1,stock);
    }
    @Test
    void testAddStock() {
        Mockito.when(stockRepository.save(stock1)).thenReturn(stock1);

        Stock addedStock = stockService.addStock(stock1);
        verify(stockRepository, Mockito.times(1)).save(stock1);

        Assertions.assertEquals(stock1, addedStock);
    }
    @Test
    void testDeleteStock() {
        stockService.deleteStock(1L);

        // Verify that the stock is deleted by the provided stockId
        verify(stockRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testUpdateStock() {
        Mockito.when(stockRepository.save(stock1)).thenReturn(stock1);

        Stock updatedStock = stockService.updateStock(stock1);

        // Verify that the stock is saved
        verify(stockRepository, Mockito.times(1)).save(stock1);
        // Assert the result
        Assertions.assertEquals(updatedStock, stock1);
    }
}