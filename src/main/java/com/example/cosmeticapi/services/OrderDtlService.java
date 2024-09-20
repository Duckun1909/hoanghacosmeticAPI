package com.example.cosmeticapi.services;

import com.example.cosmeticapi.dtos.OrderDetailsDTO;
import com.example.cosmeticapi.model.Orderdetails;
import com.example.cosmeticapi.repository.OrderDtlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDtlService {
    @Autowired
    private OrderDtlRepository orderDtlRepository;

    public List<Orderdetails> getAllOrders() {
        return orderDtlRepository.findAll();
    }

    public Orderdetails createOrderDtl(OrderDetailsDTO orderDetailsDTO){
        Orderdetails orderdetails = new Orderdetails();
        orderdetails.setOrderId(orderDetailsDTO.getOrder_id());
        orderdetails.setPrdId(orderDetailsDTO.getPrd_id());
        orderdetails.setQuantity(orderDetailsDTO.getQuantity());
        orderdetails.setUnitPrice(orderDetailsDTO.getUnit_price());
        System.out.println(orderDetailsDTO.toString());;
        return orderDtlRepository.save(orderdetails);
    }

    public Optional<Orderdetails> getOrderDtlByID(int orderID){
        Optional<Orderdetails> orderdetails = orderDtlRepository.findOrderdetailsById(orderID);
        return orderdetails;
    }


    public Optional<Orderdetails> updateOrderDtl(OrderDetailsDTO orderDetailsDTO, int id){
        Optional<Orderdetails> orderdetailsOptional = orderDtlRepository.findOrderdetailsById(id);
        if (orderdetailsOptional.isPresent()){
            orderdetailsOptional
                    .map(o -> {
                        o.setOrderId(orderDetailsDTO.getOrder_id());
                        o.setPrdId(orderDetailsDTO.getPrd_id());
                        o.setQuantity(orderDetailsDTO.getQuantity());
                        o.setUnitPrice(orderDetailsDTO.getUnit_price());
                        return o;
                    });
        }

        Orderdetails orderdetails = orderdetailsOptional.get();
        orderDtlRepository.save(orderdetails);

        return orderdetailsOptional;
    }

    public Optional<Orderdetails> deleteOrderDtl(int id){
        Optional<Orderdetails> orderdetailsOptional = orderDtlRepository.findOrderdetailsById(id);

        if (orderdetailsOptional.isPresent()){
            orderDtlRepository.deleteById(id);

            return orderdetailsOptional;
        }

        return null;
    }
}
