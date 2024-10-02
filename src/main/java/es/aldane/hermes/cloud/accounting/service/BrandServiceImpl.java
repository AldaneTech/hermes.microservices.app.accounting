package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting.mapper.BrandMapper;
import es.aldane.hermes.cloud.accounting.repository.db.BrandDbRepository;
import es.aldane.hermes.cloud.accounting_api_server_java.model.Brand;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandDbRepository brandDbRepository;
    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandDbRepository brandDbRepository, BrandMapper brandMapper) {
        this.brandDbRepository = brandDbRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public List<Brand> getBrands(List<String> statesIds) {
        try {
            var brandsList = brandDbRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            return brandMapper.brandDbListToBrandList(brandsList);
        } catch (Exception e) {
            System.out.println("Error obtaining brands");
            return new ArrayList<>();
        }
    }

    @Override
    public Brand getBrandById(Long brandId) {
        try {
            var brand = brandDbRepository.findById(brandId).orElse(null);
            return brandMapper.brandDbToBrand(brand);
        } catch (Exception e) {
            System.out.println("Error obtaining brand with id: " + brandId);
            return null;
        }
    }

    @Override
    public Brand createBrand(Brand brand) {
        try {
            brand.setLastModification(OffsetDateTime.now());
            var brandSaved = brandDbRepository.save(brandMapper.brandToBrandDb(brand));
            return brandMapper.brandDbToBrand(brandSaved);
        } catch (Exception e) {
            System.out.println("Error creating brand");
            return null;
        }
    }

    @Override
    public boolean deleteBrand(Long id) {
        try {
            brandDbRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting brand with id: " + id);
            return false;
        }
    }

    @Override
    public Brand updateBrand(Brand brand) {
        try {
            brand.setLastModification(OffsetDateTime.now());
            var brandDb = brandDbRepository.findById(brand.getId()).orElse(null);
            brandDb.setName(brand.getName());
            brandDb.setComment(brand.getComment());
            return brandMapper.brandDbToBrand(brandDbRepository.save(brandDb));
        } catch (Exception e) {
            System.out.println("Error updating brand with id: " + brand.getId());
            return null;
        }
    }
}
