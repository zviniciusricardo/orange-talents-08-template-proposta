package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.cartao_biometria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}


