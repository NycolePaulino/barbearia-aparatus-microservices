package br.com.barbearia.agendamentoservice.controller;

import br.com.barbearia.agendamentoservice.domain.Booking;
import br.com.barbearia.agendamentoservice.dto.BookingResponse;
import br.com.barbearia.agendamentoservice.repository.BarbershopRepository;
import br.com.barbearia.agendamentoservice.repository.BarbershopServiceRepository;
import br.com.barbearia.agendamentoservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final BarbershopRepository barbershopRepository;
    private final BarbershopServiceRepository barbershopServiceRepository;

    // GET: p/ listas os agendamentos
    @GetMapping("/my-bookings")
    public List<BookingResponse> listarMeusAgendamentos(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        List<Booking> bookings = bookingRepository.findByUserId(userEmail);

        List<BookingResponse> response = new ArrayList<>();

        for (Booking booking : bookings) {
            // Para cada agendamento -> busca os detalhes da barbearia e serviço
            var barbershop = barbershopRepository.findById(booking.getBarbershopId()).orElse(null);
            var service = barbershopServiceRepository.findById(booking.getServiceId()).orElse(null);

            // montando a resposta pro front
            response.add(new BookingResponse(
                    booking.getId(),
                    booking.getUserId(),
                    barbershop,
                    service,
                    booking.getDate(),
                    booking.getCancelled()
            ));
        }

        return response;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking criarAgendamento(@RequestBody Booking booking, @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        booking.setUserId(userEmail);
        if (booking.getDate() == null) {
            booking.setDate(Instant.now());
        }
        return bookingRepository.save(booking);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        return bookingRepository.findById(id)
                .filter(booking -> booking.getUserId().equals(userEmail))
                .map(booking -> {
                    booking.setCancelled(true);
                    booking.setCancelledAt(Instant.now());
                    bookingRepository.save(booking);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}