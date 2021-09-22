package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.situacaofinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise-financeira", url = "${feign.client.analise-financeira.url}")
public interface AnaliseFinanceiraWebClient {
    
    @PostMapping
    AnaliseFinanceiraResponse analisaPropostas(AnaliseFinanceiraRequest analiseFinanceiraRequest);
}
