package com.cosmetic.demo.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="brand")
public class Brand {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "brd_name", nullable = false, length = 100)
    private String brdName;
    @Basic
    @Column(name = "brd_img", nullable = false, length = 255)
    private String brdImg;
    @Basic
    @Column(name = "brd_website", nullable = true, length = 255)
    private String brdWebsite;
    @OneToMany(mappedBy = "brandByBrandId")
    private Collection<Product> productsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrdName() {
        return brdName;
    }

    public void setBrdName(String brdName) {
        this.brdName = brdName;
    }

    public String getBrdImg() {
        return brdImg;
    }

    public void setBrdImg(String brdImg) {
        this.brdImg = brdImg;
    }

    public String getBrdWebsite() {
        return brdWebsite;
    }

    public void setBrdWebsite(String brdWebsite) {
        this.brdWebsite = brdWebsite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id == brand.id && Objects.equals(brdName, brand.brdName) && Objects.equals(brdImg, brand.brdImg) && Objects.equals(brdWebsite, brand.brdWebsite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brdName, brdImg, brdWebsite);
    }

    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }
}
