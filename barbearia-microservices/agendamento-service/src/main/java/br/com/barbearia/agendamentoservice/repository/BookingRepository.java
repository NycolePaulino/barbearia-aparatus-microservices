package br.com.barbearia.agendamentoservice.repository;

import br.com.barbearia.agendamentoservice.domain.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.Instant;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByUserId(String userId);

    List<Booking> findByBarbershopIdAndDateBetween(String barbershopId, Instant start, Instant end);
}