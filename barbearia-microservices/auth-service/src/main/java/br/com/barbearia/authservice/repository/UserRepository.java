package br.com.barbearia.authservice.repository;


import br.com.barbearia.authservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // método p/ buscar o user pelo e-mail
    Optional<User> findByEmail(String email);
}