package com.cosmetic.demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orderdetails")
public class Orderdetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @Basic
    @Column(name = "prd_id", nullable = false)
    private int prdId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "unit_price", nullable = false, precision = 0)
    private double unitPrice;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "id")
    private Order orderByOrderId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "prd_id", referencedColumnName = "id")
    private Product productByPrdId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPrdId() {
        return prdId;
    }

    public void setPrdId(int prdId) {
        this.prdId = prdId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderdetails that = (Orderdetails) o;
        return id == that.id && orderId == that.orderId && prdId == that.prdId && quantity == that.quantity && Double.compare(that.unitPrice, unitPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, prdId, quantity, unitPrice);
    }

    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    public Product getProductByPrdId() {
        return productByPrdId;
    }

    public void setProductByPrdId(Product productByPrdId) {
        this.productByPrdId = productByPrdId;
    }
}
