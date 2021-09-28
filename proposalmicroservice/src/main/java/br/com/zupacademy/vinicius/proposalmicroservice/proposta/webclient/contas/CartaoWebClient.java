package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.contas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartoes", url = "${feign.client.contas.url}")
public interface CartaoWebClient {
    
    @PostMapping
    CartaoResponse vinculaCartao(CartaoRequest request);
}
