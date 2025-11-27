package br.com.barbearia.agendamentoservice.dto;


import br.com.barbearia.agendamentoservice.domain.Barbershop;
import br.com.barbearia.agendamentoservice.domain.BarbershopService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSearchResponse {

    // busca o serviço
    private BarbershopService service;

    // busca a barbearia que o serviço pertence
    private Barbershop barbershop;
}
