package com.PaymentProcessorService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderStatusDTO {

    private String status;

    public String getStatus() {
        return status;
    }

    public OrderStatusDTO(String status){
        this.status = status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
