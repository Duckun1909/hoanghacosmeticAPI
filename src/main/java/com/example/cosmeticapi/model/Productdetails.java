package com.cosmetic.demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "productdetails")
public class Productdetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "prds_color", nullable = true, length = 10)
    private String prdsColor;
    @Basic
    @Column(name = "prds_image", nullable = true, length = 255)
    private String prdsImage;
    @Basic
    @Column(name = "prds_type", nullable = true)
    private Byte prdsType;
    @Basic
    @Column(name = "prd_id", nullable = false)
    private int prdId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "prd_id", referencedColumnName = "id")
    private Product productByPrdId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrdsColor() {
        return prdsColor;
    }

    public void setPrdsColor(String prdsColor) {
        this.prdsColor = prdsColor;
    }

    public String getPrdsImage() {
        return prdsImage;
    }

    public void setPrdsImage(String prdsImage) {
        this.prdsImage = prdsImage;
    }

    public Byte getPrdsType() {
        return prdsType;
    }

    public void setPrdsType(Byte prdsType) {
        this.prdsType = prdsType;
    }

    public int getPrdId() {
        return prdId;
    }

    public void setPrdId(int prdId) {
        this.prdId = prdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productdetails that = (Productdetails) o;
        return id == that.id && prdId == that.prdId && Objects.equals(prdsColor, that.prdsColor) && Objects.equals(prdsImage, that.prdsImage) && Objects.equals(prdsType, that.prdsType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prdsColor, prdsImage, prdsType, prdId);
    }

    public Product getProductByPrdId() {
        return productByPrdId;
    }

    public void setProductByPrdId(Product productByPrdId) {
        this.productByPrdId = productByPrdId;
    }
}
