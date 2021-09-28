package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.contas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    
    Optional<Cartao>findByNumeroCartao(String numeroCartao);
}
