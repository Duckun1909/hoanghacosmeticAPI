package com.example.cosmeticapi.services;

import com.example.cosmeticapi.model.Order;
import com.example.cosmeticapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cosmeticapi.dtos.OrderDTO;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createWishlist(OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderCode(orderDTO.getOrder_code());
        order.setOrderDate(orderDTO.getOrder_date());
        order.setOrderNote(orderDTO.getOrder_note());
        order.setOrderStatus(orderDTO.getOrder_status());
        order.setTotalAmount(orderDTO.getTotal_amount());
        order.setUserId(orderDTO.getUser_id());
        System.out.println(orderDTO.toString());;
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderByID(int orderID){
        Optional<Order> order = orderRepository.findOrderById(orderID);
        return order;
    }


    public Optional<Order> updateOrder(OrderDTO orderDTO, int id){
        Optional<Order> orderOptional = orderRepository.findOrderById(id);
        if (orderOptional.isPresent()){
            orderOptional
                    .map(o -> {
                        o.setOrderCode(orderDTO.getOrder_code());
                        o.setOrderDate(orderDTO.getOrder_date());
                        o.setOrderNote(orderDTO.getOrder_note());
                        o.setOrderStatus(orderDTO.getOrder_status());
                        o.setTotalAmount(orderDTO.getTotal_amount());
                        o.setUserId(orderDTO.getUser_id());
                        return o;
                    });
        }

        Order order = orderOptional.get();
        orderRepository.save(order);

        return orderOptional;
    }

    public Optional<Order> deleteUser(int id){
        Optional<Order> orderOptional = orderRepository.findOrderById(id);

        if (orderOptional.isPresent()){
            orderRepository.deleteById(id);

            return orderOptional;
        }

        return null;
    }
}
