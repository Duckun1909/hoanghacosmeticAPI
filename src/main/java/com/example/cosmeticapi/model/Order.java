package com.example.cosmeticapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "order_code", nullable = false, length = 50)
    private String orderCode;
    @Basic
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Basic
    @Column(name = "order_status", nullable = false)
    private byte orderStatus;
    @Basic
    @Column(name = "order_note", nullable = true, length = 200)
    private String orderNote;
    @Basic
    @Column(name = "total_amount", nullable = false, precision = 0)
    private double totalAmount;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
    private User userByUserId;
    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<Orderdetails> orderdetailsById;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && orderStatus == order.orderStatus && Double.compare(order.totalAmount, totalAmount) == 0 && Objects.equals(orderCode, order.orderCode) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderNote, order.orderNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderCode, orderDate, orderStatus, orderNote, totalAmount);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Collection<Orderdetails> getOrderdetailsById() {
        return orderdetailsById;
    }

    public void setOrderdetailsById(Collection<Orderdetails> orderdetailsById) {
        this.orderdetailsById = orderdetailsById;
    }
}
