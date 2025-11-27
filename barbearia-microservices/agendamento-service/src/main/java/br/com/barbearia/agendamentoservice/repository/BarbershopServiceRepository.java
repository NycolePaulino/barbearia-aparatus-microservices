package br.com.barbearia.agendamentoservice.repository;


import br.com.barbearia.agendamentoservice.domain.BarbershopService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarbershopServiceRepository extends MongoRepository<BarbershopService, String> {

    // método p/ encontrar todos os serviços q tenham o barbershopId
    List<BarbershopService> findByBarbershopId(String barbershopId);
    List<BarbershopService> findByNameContainingIgnoreCase(String name);
}