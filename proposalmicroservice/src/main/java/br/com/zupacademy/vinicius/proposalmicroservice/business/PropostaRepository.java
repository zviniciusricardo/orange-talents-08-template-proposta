package br.com.zupacademy.vinicius.proposalmicroservice.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);
    
    @Async
    Set<Optional<Proposta>> findTop10ByNumeroCartaoIsNullAndStatusEquals(StatusProposta status);
}
