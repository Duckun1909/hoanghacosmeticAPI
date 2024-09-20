package com.example.cosmeticapi.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "cat_name", nullable = false, length = 50, unique = true)
    private String catName;
    @Basic
    @Column(name = "cat_desc", nullable = true, length = 200)
    private String catDesc;
    @OneToMany(mappedBy = "categoryByCatId")
    private Collection<Product> productsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(catName, category.catName) && Objects.equals(catDesc, category.catDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catName, catDesc);
    }

    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }
}
