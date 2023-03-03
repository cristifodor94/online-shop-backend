package ro.msg.learning.shop.services.test;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.services.*;


@Service
@RequiredArgsConstructor
public class DatabaseManipulationService {
    private static final String COUNTRY = "ROMANIA";
    private static final String CITY = "Ploiesti";
    private static final String COUNTY = "Prahova";
    private static final String STREET = "Calea Surii Mici";
    private static final String TURDA_CITY = "Turda";
    private static final String TURDA_COUNTY = "Cluj";

    private final CustomerService customerService;
    private final StockService stockService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;
    private final LocationService locationService;

    public void fillInDatabase() {

        ProductCategory productCategoryIT = ProductCategory.builder().id(17).name("IT").description("IT Products").build();
        productCategoryService.createCategory(productCategoryIT);

        Supplier supplierDell = Supplier.builder().id(1).name("Dell").build();
        supplierService.createSupplier(supplierDell);
        Supplier supplierRazer = Supplier.builder().id(2).name("Razer").build();
        supplierService.createSupplier(supplierRazer);


        Product productLaptopDell = Product.builder().id(15).name("Dell Laptop").productCategory(productCategoryIT).supplier(supplierDell).build();
        productService.createProduct(productLaptopDell);
        Product productRazerKeyboard = Product.builder().id(16).name("Razer Keyboard").productCategory(productCategoryIT).supplier(supplierRazer).build();
        productService.createProduct(productRazerKeyboard);

        Customer customer = Customer.builder().id(15).firstName("Gabriel").lastName("Ionut").username("gionut").password("123").emailAddress("gionut@gmail.com").build();
        customerService.save(customer);

        Location locationPloiesti = Location.builder().id(25).name("Location Ploiesti").address(Address.builder().country(COUNTRY).city(CITY).county(COUNTY).street(STREET).build()).build();
        locationService.createLocation(locationPloiesti);
        Location locationTurda = Location.builder().id(26).name("Location Turda").address(Address.builder().country(COUNTRY).city(TURDA_CITY).county(TURDA_COUNTY).build()).build();
        locationService.createLocation(locationTurda);

        Stock stockLaptopDellPloiesti = Stock.builder().id(new StockKey(15, 25)).location(locationPloiesti).product(productLaptopDell).quantity(24).build();
        stockService.save(stockLaptopDellPloiesti);
        Stock stockLaptopDellTurda = Stock.builder().id(new StockKey(15, 26)).location(locationTurda).product(productLaptopDell).quantity(33).build();
        stockService.save(stockLaptopDellTurda);
        Stock stockRazerKeyboardPloiesti = Stock.builder().id(new StockKey(15, 25)).location(locationPloiesti).product(productRazerKeyboard).quantity(44).build();
        stockService.save(stockRazerKeyboardPloiesti);
        Stock stockRazerKeyboardTurda = Stock.builder().id(new StockKey(15, 26)).location(locationTurda).product(productRazerKeyboard).quantity(44).build();
        stockService.save(stockRazerKeyboardTurda);
    }

    public void deleteAll() {
        stockService.deleteAll();
    }
}
