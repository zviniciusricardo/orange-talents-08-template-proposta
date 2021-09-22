package br.com.zupacademy.vinicius.proposalmicroservice.proposta;

import br.com.zupacademy.vinicius.proposalmicroservice.exception.RegraNegocioException;
import br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.situacaofinanceira.AnaliseFinanceiraWebClient;
import br.com.zupacademy.vinicius.proposalmicroservice.util.UriBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {
    
    @PersistenceContext
    private EntityManager manager;
    
    private AnaliseFinanceiraWebClient webClient;
    
    public PropostaController(AnaliseFinanceiraWebClient webClient) {
        this.webClient = webClient;
    }
    
    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<?> cadastraProposta(@RequestBody @Valid PropostaForm form) throws RegraNegocioException, JsonProcessingException {
        Proposta proposta = form.toModel();
        manager.persist(proposta);
        proposta.analisaSituacaoFinanceira(webClient);
        
        URI redirectPath = UriBuilder.build(proposta.getId());
        
        return ResponseEntity.created(redirectPath).body(new PropostaDto(proposta));
    }
}
