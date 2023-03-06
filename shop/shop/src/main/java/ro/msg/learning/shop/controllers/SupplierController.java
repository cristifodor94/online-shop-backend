package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.SupplierDTO;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.mappers.SupplierMapper;
import ro.msg.learning.shop.services.SupplierService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/suppliers")
@RequiredArgsConstructor

public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierService.createSupplier(supplierMapper.supplierDtoToSupplier(supplierDTO));
        SupplierDTO dto = supplierMapper.supplierToSupplierDTO(supplier);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierService.updateSupplier(id, supplierMapper.supplierDtoToSupplier(supplierDTO));
        SupplierDTO dto = supplierMapper.supplierToSupplierDTO(supplier);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable("id") Integer id) {
        supplierService.deleteSupplierById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable("id") Integer id) {
        Supplier supplier = supplierService.findSupplierById(id);
        SupplierDTO dto = supplierMapper.supplierToSupplierDTO(supplier);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<SupplierDTO> supplierDTOS = suppliers.stream().map(supplierMapper::supplierToSupplierDTO).collect(Collectors.toList());
        return new ResponseEntity<>(supplierDTOS, HttpStatus.OK);
    }
}
