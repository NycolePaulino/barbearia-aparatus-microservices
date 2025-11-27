package br.com.barbearia.agendamentoservice.dto;


import br.com.barbearia.agendamentoservice.domain.Barbershop;
import br.com.barbearia.agendamentoservice.domain.BarbershopService;
import lombok.Data;

import java.time.Instant;

@Data
public class BookingResponse {
    private String id;
    private String userId;
    private Barbershop barbershop;
    private BarbershopService service;
    private Instant date;
    private Boolean cancelled;
    private Boolean cancelledAt;

    public BookingResponse(String id, String userId, Barbershop barbershop, BarbershopService service, Instant date, Boolean cancelled) {
        this.id = id;
        this.userId = userId;
        this.barbershop = barbershop;
        this.service = service;
        this.date = date;
        this.cancelled = cancelled;
    }
}