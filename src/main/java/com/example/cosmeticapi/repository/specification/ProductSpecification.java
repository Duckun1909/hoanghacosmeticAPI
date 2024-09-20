package com.example.cosmeticapi.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.example.cosmeticapi.model.Product;

public class ProductSpecification implements Specification<Product>{
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

    public static Specification<Product> searchByName(String name){
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (name != null){
                predicate = cb.and(predicate, cb.like(root.get("prdName"), "%" + name + "%"));
            }

            return predicate;
        };
    }

    public static Specification<Product> getProductHighRating(){
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            predicate = cb.and((predicate), cb.greaterThanOrEqualTo(root.get("prdRate"), 4));

            return predicate;
        };
    }
}
