package com.example.kafkasample.dto;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private Long orderId;
    private Double amount;
    private String status;

    public OrderDto() {
    }

    public OrderDto(Long orderId, Double amount, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
