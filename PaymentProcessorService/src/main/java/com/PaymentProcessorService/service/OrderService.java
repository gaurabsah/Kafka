package com.PaymentProcessorService.service;

import com.PaymentProcessorService.dto.OrderStatusDTO;
import com.PaymentProcessorService.entity.OrderStatus;
import com.PaymentProcessorService.repository.OrderStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
@Slf4j
public class OrderService {
    
    private final OrderStatusRepository orderStatusRepository;

    public OrderService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public OrderStatusDTO createOrUpdateOrderStatus(OrderStatusDTO eventData) {
        OrderStatus orderStatus = orderStatusRepository.save(dtoToEntity(eventData));
        log.info("Order Status saved in DB: {}",eventData);
        return entityToDto(orderStatus);
    }

    private OrderStatus dtoToEntity(OrderStatusDTO dto){
        return new OrderStatus(dto.getStatus());
    }

    private OrderStatusDTO entityToDto(OrderStatus orderStatus){
        return new OrderStatusDTO(orderStatus.getStatus());
    }
}
