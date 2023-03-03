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
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierMapper.supplierToSupplierDTO(supplierService.createSupplier(supplierMapper.supplierDtoToSupplier(supplierDTO)));
    }

    @PutMapping("/{id}")
    public SupplierDTO updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        return supplierMapper.supplierToSupplierDTO(supplierService.updateSupplier(id, supplierMapper.supplierDtoToSupplier(supplierDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable("id") Integer id) {
        supplierService.deleteSupplierById(id);
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplierById(@PathVariable("id") Integer id) {
        return supplierMapper.supplierToSupplierDTO(supplierService.findSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<SupplierDTO> supplierDTOS = suppliers.stream().map(supplierMapper::supplierToSupplierDTO).collect(Collectors.toList());
        return new ResponseEntity<>(supplierDTOS, HttpStatus.OK);
    }
}
