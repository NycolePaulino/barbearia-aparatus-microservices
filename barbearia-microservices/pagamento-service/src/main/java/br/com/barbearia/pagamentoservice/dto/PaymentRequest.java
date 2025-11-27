package br.com.barbearia.pagamentoservice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String bookingId;
    private String serviceName;
    private BigDecimal price;
}