package es.aldane.hermes.cloud.accounting.controller;

import es.aldane.hermes.cloud.accounting.service.BrandService;
import es.aldane.hermes.cloud.accounting_api_server_java.api.BrandApi;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BrandController implements BrandApi {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public ResponseEntity<Brand> createBrand(@Valid Brand brand) {
        var newBrand = brandService.createBrand(brand);
        return newBrand != null ? ResponseEntity.ok(newBrand) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> deleteBrand(Long brandId) {
        var brand = brandService.deleteBrand(brandId);
        return brand ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Brand> getBrandById(Long brandId) {
        var brand = brandService.getBrandById(brandId);
        return brand != null ? ResponseEntity.ok(brand) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Brand>> getBrands() {
        var states = brandService.getBrands(new ArrayList<>());
        return ResponseEntity.ok(states);
    }

    @Override
    public ResponseEntity<Brand> updateBrand(@Valid Brand brand) {
        return brandService.updateBrand(brand) != null ? ResponseEntity.ok(brand) : ResponseEntity.badRequest().build();
    }
}
