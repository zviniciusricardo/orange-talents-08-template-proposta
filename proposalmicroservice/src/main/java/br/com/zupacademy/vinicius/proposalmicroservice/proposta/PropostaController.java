package br.com.zupacademy.vinicius.proposalmicroservice.proposta;

import br.com.zupacademy.vinicius.proposalmicroservice.exception.RegraNegocioException;
import br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.situacaofinanceira.AnaliseFinanceiraWebClient;
import br.com.zupacademy.vinicius.proposalmicroservice.util.UriBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Autowired
    private AnaliseFinanceiraWebClient webClient;
    
    @Autowired
    private PropostaRepository propostaRepository;
    
    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<?> cadastraProposta(@RequestBody @Valid PropostaForm form)
            throws RegraNegocioException, JsonProcessingException {
        
        Proposta proposta = form.toModel();
        manager.persist(proposta);
        proposta.analisaSituacaoFinanceira(webClient);
        
        URI redirectPath = UriBuilder.build(proposta.getId());
        
        return ResponseEntity.created(redirectPath).body(new PropostaDto(proposta));
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> detalhaProposta(@PathVariable("id")  Long id ) throws RegraNegocioException {
        
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if (possivelProposta.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parece que o recurso informado não existe em nosso sistema");
        
        return ResponseEntity.ok().body(new PropostaDto(possivelProposta.get()));
        
    }
}
