package com.example.cosmeticapi.services;

import com.example.cosmeticapi.dtos.ProductDTO;
import com.example.cosmeticapi.model.Product;
import com.example.cosmeticapi.repository.ProductRepository;
import com.example.cosmeticapi.repository.specification.ProductSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private ProductSpecification productSpecification;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return null;
        }

        return products;
    }

    public Optional<Product> getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional;
        }
        return null;
    }

    public List<Product> getProductByName(String name) {
        List<Product> products = productRepository.findProductByPrdNameContainingIgnoreCase(name);

        return products;
    }

    public List<Product> getProductByCatID(int catID) {
        List<Product> products = productRepository.findProductByCatId(catID);
        return products;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();

        if (productDTO != null) {
            product.setPrdName(productDTO.getPrdName());
            product.setPrdDesc(productDTO.getPrdDesc());
            product.setPrdDiscount(productDTO.getPrdDiscount());
            product.setPrdRate(productDTO.getPrdRate());
            product.setPrdThumb(productDTO.getPrdThumb());
            product.setPrdPrice(productDTO.getPrdPrice());
            product.setPrdStatus(productDTO.getPrdStatus());
            product.setBrandId(productDTO.getBrandId());
            product.setCatId(productDTO.getCatId());

            productRepository.save(product);
        }

        return product;
    }

    public Product updateProduct(ProductDTO productDTO, int id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productOptional.map(
                    prd -> {
                        prd.setPrdName(productDTO.getPrdName());
                        prd.setPrdDesc(productDTO.getPrdDesc());
                        prd.setPrdDiscount(productDTO.getPrdDiscount());
                        prd.setPrdRate(productDTO.getPrdRate());
                        prd.setPrdThumb(productDTO.getPrdThumb());
                        prd.setPrdPrice(productDTO.getPrdPrice());
                        prd.setPrdStatus(productDTO.getPrdStatus());
                        prd.setBrandId(productDTO.getBrandId());
                        prd.setCatId(productDTO.getCatId());

                        return prd;
                    }
            );

            Product product = productOptional.get();
            productRepository.save(product);
        }


        return productOptional.get();
    }

    public Page<Product> searchByName(String name, Pageable pageable) {
        Specification<Product> spec = productSpecification.searchByName(name);

        return productRepository.findAll(spec, pageable);
    }

    public Page<Product> getProductHighRating(Pageable pageable) {
        Specification<Product> spec = productSpecification.getProductHighRating();

        return productRepository.findAll(spec, pageable);
    }

    // Ví dụ với Native query
    public Object getProductHasMaxPrice() {
        String sql = "select * from Product p where p.prd_price = (select max(p.prd_price) from Product p)";
        Query query = entityManager.createNativeQuery(sql);

        Object result = query.getSingleResult();

        return result;
    }
}
