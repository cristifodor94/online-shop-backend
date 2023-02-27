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
    private static final String CLUJ_COUNTY = "Cluj";

    private final CustomerService customerService;
    private final StockService stockService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;
    private final LocationService locationService;

    public void fillInDatabase() {

        ProductCategory productCategoryIT = ProductCategory.builder().id(17).name("IT").description("IT Products").build();
        productCategoryService.save(productCategoryIT);

        Supplier supplierDell = Supplier.builder().id(1).name("Dell").build();
        supplierService.save(supplierDell);
        Supplier supplierRazer = Supplier.builder().id(2).name("Razer").build();
        supplierService.save(supplierRazer);

        Product productLaptopDell = Product.builder().id(15).name("Dell Laptop").productCategory(productCategoryIT).supplier(supplierDell).build();
        productService.save(productLaptopDell);
        Product productRazerKeyboard = Product.builder().id(16).name("Razer Keyboard").productCategory(productCategoryIT).supplier(supplierRazer).build();
        productService.save(productRazerKeyboard);

        Customer customer = Customer.builder().id(15).firstName("Gabriel").lastName("Ionut").username("gionut").password("123").emailAddress("gionut@gmail.com").build();
        customerService.save(customer);

        Location locationPloiesti = Location.builder().id(25).name("Ploiesti").country(COUNTRY).city(CITY).county(COUNTY).street(STREET).build();
        locationService.save(locationPloiesti);
        Location locationTurda = Location.builder().id(26).name("Turda").country(COUNTRY).city(TURDA_CITY).county(CLUJ_COUNTY).street(STREET).build();
        locationService.save(locationTurda);

        Stock stockLaptopDellPloiesti = Stock.builder().location(locationPloiesti).product(productLaptopDell).quantity(24).build();
        stockService.save(stockLaptopDellPloiesti);
        Stock stockLaptopDellTurda = Stock.builder().location(locationTurda).product(productLaptopDell).quantity(15).build();
        stockService.save(stockLaptopDellTurda);
        Stock stockRazerKeyboardPloiesti = Stock.builder().location(locationPloiesti).product(productRazerKeyboard).quantity(30).build();
        stockService.save(stockRazerKeyboardPloiesti);
        Stock stockRazerKeyboardTurda = Stock.builder().location(locationTurda).product(productRazerKeyboard).quantity(9).build();
        stockService.save(stockRazerKeyboardTurda);
    }

    public void deleteAll() {
        stockService.deleteAll();
    }
}
