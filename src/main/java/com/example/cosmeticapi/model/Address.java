package com.cosmetic.demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;
    @Basic
    @Column(name = "adr_phone", nullable = false, length = 15)
    private String adrPhone;
    @Basic
    @Column(name = "adr_shipping", nullable = true, length = 200)
    private String adrShipping;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User userByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAdrPhone() {
        return adrPhone;
    }

    public void setAdrPhone(String adrPhone) {
        this.adrPhone = adrPhone;
    }

    public String getAdrShipping() {
        return adrShipping;
    }

    public void setAdrShipping(String adrShipping) {
        this.adrShipping = adrShipping;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(fullname, address.fullname) && Objects.equals(adrPhone, address.adrPhone) && Objects.equals(adrShipping, address.adrShipping) && Objects.equals(userId, address.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, adrPhone, adrShipping, userId);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
