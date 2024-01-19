package com.example.cosmeticapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "prd_id", nullable = false)
    private int prdId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User userByUserId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "prd_id", referencedColumnName = "id")
    private Product productByPrdId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        Wishlist wishlist = (Wishlist) o;
        return id == wishlist.id && userId == wishlist.userId && prdId == wishlist.prdId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, prdId);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Product getProductByPrdId() {
        return productByPrdId;
    }

    public void setProductByPrdId(Product productByPrdId) {
        this.productByPrdId = productByPrdId;
    }
}
