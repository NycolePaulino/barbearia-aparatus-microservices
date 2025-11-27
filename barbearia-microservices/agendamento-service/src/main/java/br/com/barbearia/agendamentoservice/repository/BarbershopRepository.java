package br.com.barbearia.agendamentoservice.repository;

import br.com.barbearia.agendamentoservice.domain.Barbershop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarbershopRepository extends MongoRepository<Barbershop, String> {

    // método p/ aba "recomendados"
    List<Barbershop> findAllByOrderByNameAsc();

    // método p/ aba "populares"
    List<Barbershop> findAllByOrderByNameDesc();

    //encontrando todas as barbearias cujo o Ids estejam nessa lista
    List<Barbershop> findByIdIn(List<String> ids);
}