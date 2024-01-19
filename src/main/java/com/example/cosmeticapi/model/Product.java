package com.cosmetic.demo.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "cat_id", nullable = false)
    private int catId;
    @Basic
    @Column(name = "brand_id", nullable = false)
    private int brandId;
    @Basic
    @Column(name = "prd_name", nullable = false, length = 100)
    private String prdName;
    @Basic
    @Column(name = "prd_thumb", nullable = true, length = 255)
    private String prdThumb;
    @Basic
    @Column(name = "prd_price", nullable = false, precision = 0)
    private double prdPrice;
    @Basic
    @Column(name = "prd_desc", nullable = false, length = 255)
    private String prdDesc;
    @Basic
    @Column(name = "prd_rate", nullable = true, length = 1)
    private double prdRate;
    @Basic
    @Column(name = "prd_discount", nullable = true, precision = 0)
    private double prdDiscount;
    @Basic
    @Column(name = "prd_status", nullable = false)
    private byte prdStatus;
    @OneToMany(mappedBy = "productByPrdId")
    private Collection<Orderdetails> orderdetailsById;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "cat_id", referencedColumnName = "id")
    private Category categoryByCatId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brandByBrandId;
    @OneToMany(mappedBy = "productByPrdId")
    private Collection<Productdetails> productdetailsById;
    @OneToMany(mappedBy = "productByPrdId")
    private Collection<Wishlist> wishlistsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getPrdThumb() {
        return prdThumb;
    }

    public void setPrdThumb(String prdThumb) {
        this.prdThumb = prdThumb;
    }

    public double getPrdPrice() {
        return prdPrice;
    }

    public void setPrdPrice(double prdPrice) {
        this.prdPrice = prdPrice;
    }

    public String getPrdDesc() {
        return prdDesc;
    }

    public void setPrdDesc(String prdDesc) {
        this.prdDesc = prdDesc;
    }

    public Double getPrdRate() {
        return prdRate;
    }

    public void setPrdRate(Double prdRate) {
        this.prdRate = prdRate;
    }

    public Double getPrdDiscount() {
        return prdDiscount;
    }

    public void setPrdDiscount(Double prdDiscount) {
        this.prdDiscount = prdDiscount;
    }

    public byte getPrdStatus() {
        return prdStatus;
    }

    public void setPrdStatus(byte prdStatus) {
        this.prdStatus = prdStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && catId == product.catId && brandId == product.brandId && Double.compare(product.prdPrice, prdPrice) == 0 && prdStatus == product.prdStatus && Objects.equals(prdName, product.prdName) && Objects.equals(prdThumb, product.prdThumb) && Objects.equals(prdDesc, product.prdDesc) && Objects.equals(prdRate, product.prdRate) && Objects.equals(prdDiscount, product.prdDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catId, brandId, prdName, prdThumb, prdPrice, prdDesc, prdRate, prdDiscount, prdStatus);
    }

    public Collection<Orderdetails> getOrderdetailsById() {
        return orderdetailsById;
    }

    public void setOrderdetailsById(Collection<Orderdetails> orderdetailsById) {
        this.orderdetailsById = orderdetailsById;
    }

    public Category getCategoryByCatId() {
        return categoryByCatId;
    }

    public void setCategoryByCatId(Category categoryByCatId) {
        this.categoryByCatId = categoryByCatId;
    }

    public Brand getBrandByBrandId() {
        return brandByBrandId;
    }

    public void setBrandByBrandId(Brand brandByBrandId) {
        this.brandByBrandId = brandByBrandId;
    }

    public Collection<Productdetails> getProductdetailsById() {
        return productdetailsById;
    }

    public void setProductdetailsById(Collection<Productdetails> productdetailsById) {
        this.productdetailsById = productdetailsById;
    }

    public Collection<Wishlist> getWishlistsById() {
        return wishlistsById;
    }

    public void setWishlistsById(Collection<Wishlist> wishlistsById) {
        this.wishlistsById = wishlistsById;
    }
}
