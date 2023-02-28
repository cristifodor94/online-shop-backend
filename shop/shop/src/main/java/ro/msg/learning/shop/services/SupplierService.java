package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.exceptions.NotFoundException;
import ro.msg.learning.shop.repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public Supplier createSupplier(Supplier inputSupplier) {
        return supplierRepository.save(inputSupplier);
    }

    public Supplier updateSupplier(Integer id, Supplier updatedSupplier) {
        Optional<Supplier> supplierToUpdate = supplierRepository.findById(id);
        if (supplierToUpdate.isPresent()) {
            updatedSupplier.setId(id);
            return supplierRepository.save(updatedSupplier);
        }
        throw new NotFoundException("Supplier not found!");
    }

    public void deleteSupplierById(Integer id) {
        supplierRepository.deleteById(id);
    }

    public Supplier findSupplierById(Integer id) {
        Optional<Supplier> searchedSupplier = supplierRepository.findById(id);
        if (searchedSupplier.isPresent()) {
            return searchedSupplier.get();
        }
        throw new NotFoundException("Supplier not found!");
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier checkSupplierPresence(Supplier inputSupplier) {
        Optional<Supplier> searchedSupplier = supplierRepository.findByName(inputSupplier.getName());
        Supplier supplier;
        if (searchedSupplier.isPresent()) {
            supplier = searchedSupplier.get();
        } else {
            supplier = new Supplier();
            supplier.setName(inputSupplier.getName());
            supplierRepository.save(supplier);
        }
        return supplier;
    }

    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void deleteAll() {
        supplierRepository.deleteAll();
    }

}
