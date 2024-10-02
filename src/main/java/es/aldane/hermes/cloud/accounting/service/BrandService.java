package es.aldane.hermes.cloud.accounting.service;

import es.aldane.hermes.cloud.accounting_api_server_java.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands(List<String> brandsIds);

    Brand getBrandById(Long brandId);

    Brand createBrand(Brand brand);

    boolean deleteBrand(Long id);

    Brand updateBrand(Brand brand);
}
