package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.SupplierDTO;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.mappers.SupplierMapper;
import ro.msg.learning.shop.services.SupplierService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @PostMapping
    public Supplier createSupplier (@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.supplierDtoToSupplier(supplierDTO);
        return supplierService.createSupplier(supplier);
    }
    @PutMapping("/{id}")
    public Supplier updateSupplier (@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.supplierDtoToSupplier(supplierDTO);
        return supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier (@PathVariable("id") Integer id) {supplierService.deleteSupplierById(id);}

    @GetMapping("/{id}")
    public Supplier getSupplierById (@PathVariable("id") Integer id) {return supplierService.findSupplierById(id);}

    @GetMapping
    public List<Supplier> getAllSuppliers() {return supplierService.getAllSuppliers();}
}
