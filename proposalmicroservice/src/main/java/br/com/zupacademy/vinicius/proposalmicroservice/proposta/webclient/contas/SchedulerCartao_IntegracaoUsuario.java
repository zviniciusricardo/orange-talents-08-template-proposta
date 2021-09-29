package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.contas;

import br.com.zupacademy.vinicius.proposalmicroservice.proposta.Proposta;
import br.com.zupacademy.vinicius.proposalmicroservice.proposta.PropostaRepository;
import br.com.zupacademy.vinicius.proposalmicroservice.proposta.StatusProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SchedulerCartao_IntegracaoUsuario {
    
    @Autowired
    private PropostaRepository propostaRepository;
    
    @Autowired
    private CartaoWebClient webClient;
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Scheduled(fixedDelay = 1000)
    @Transactional
    private void vinculoCartaoProposta() {
    
        Set<Optional<Proposta>> propostasElegiveisSemCartaoVinculado = propostaRepository
                .findTop10ByNumeroCartaoIsNullAndStatusEquals(StatusProposta.ELEGIVEL);
    
        if (propostasElegiveisSemCartaoVinculado.isEmpty()) return;
    
        List<Cartao> listaCartoes = propostasElegiveisSemCartaoVinculado.stream()
                .map(proposta -> new CartaoRequest(proposta.get()))
                .map(request -> webClient.vinculaCartao(request))
                .map(response -> new Cartao(response))
                .map(cartao -> cartaoRepository.save(cartao))
                .collect(Collectors.toList());
    
        for (Optional<Proposta> proposta: propostasElegiveisSemCartaoVinculado) {
            for (Cartao cartao:listaCartoes) {
                proposta.get().setNumeroCartao(cartao.getNumeroCartao());
            }
            propostaRepository.save(proposta.get());
        }
        
        return;
    }
}
