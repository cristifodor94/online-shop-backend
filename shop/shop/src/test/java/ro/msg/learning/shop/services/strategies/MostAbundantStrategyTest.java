package ro.msg.learning.shop.services.strategies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.services.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class MostAbundantStrategyTest {
    private static final String COUNTRY = "ROMANIA";
    private static final String CITY = "Sibiu";
    private static final String COUNTY = "Sibiu";
    private static final String STREET = "Calea Surii Mici";
    private static final String CLUJ_CITY = "Cluj Napoca";
    private static final String CLUJ_COUNTY = "Cluj";

    private Location locationSibiu;
    private Location locationCluj;
    private ProductCategory productCategory;
    private Supplier supplierEmag;
    private Supplier supplierAltex;
    private Product productRazerKeyboard;
    private Product productDellLaptop;
    private Stock stockRazerKeyboardCluj;
    private Stock stockDellLaptopCluj;
    private Stock stockRazerKeyboardSibiu;
    private Stock stockDellLaptopSibiu;
    private List<OrderDetail> orderDetails;

    private List<Stock> stocks;

    @Mock
    private StockService stockService;
    @InjectMocks
    private MostAbundantStrategy mostAbundantStrategy;

    void init() {
        createLocations();
        createProductCategory();
        createSuppliers();
        createProducts();
        createStocks();
    }

    private void createLocations() {
        this.locationSibiu = new Location(1, "Sibiu Location", COUNTRY, CITY, COUNTY, STREET);
        this.locationCluj = new Location(2, "Cluj Location", COUNTRY, CLUJ_CITY, CLUJ_COUNTY, STREET);
    }

    private void createProductCategory() {
        this.productCategory = ProductCategory.builder().id(1).name("Electronics").description("This category is about electronics").build();
    }

    private void createSuppliers() {
        this.supplierAltex = Supplier.builder().id(1).name("Altex").build();
        this.supplierEmag = Supplier.builder().id(2).name("Emag").build();
    }

    private void createProducts() {
        this.productDellLaptop = Product.builder().id(1).name("Dell Laptop").productCategory(productCategory).supplier(supplierEmag).build();
        this.productRazerKeyboard = Product.builder().id(2).name("Razer Keyboard").productCategory(productCategory).supplier(supplierAltex).build();
    }

    private void createStocks() {
        this.stocks = new ArrayList<>();
        this.stockDellLaptopSibiu = Stock.builder().location(locationSibiu).product(productDellLaptop).quantity(5).build();
        this.stockRazerKeyboardCluj = Stock.builder().location(locationCluj).product(productRazerKeyboard).quantity(30).build();
        this.stockDellLaptopCluj = Stock.builder().location(locationCluj).product(productDellLaptop).quantity(40).build();
        this.stockRazerKeyboardSibiu = Stock.builder().location(locationSibiu).product(productRazerKeyboard).quantity(7).build();
        this.stocks.addAll(Arrays.asList(stockDellLaptopCluj, stockDellLaptopSibiu, stockRazerKeyboardCluj, stockRazerKeyboardSibiu));
    }

    @Test
    void verifyDifferentStocks() {
        init();
        this.orderDetails = new ArrayList<>();
        OrderDetail orderDetail = OrderDetail.builder().product(productDellLaptop).quantity(3).build();
        this.orderDetails.add(orderDetail);
        orderDetail = OrderDetail.builder().product(productRazerKeyboard).quantity(4).build();
        this.orderDetails.add(orderDetail);

        when(stockService.findAll()).thenReturn(stocks);
        List<Stock> stockList = mostAbundantStrategy.createOrder(orderDetails);
        assertThat(stockList.get(0).getLocation().equals(locationCluj) && stockList.get(1).equals(locationSibiu));
    }
}