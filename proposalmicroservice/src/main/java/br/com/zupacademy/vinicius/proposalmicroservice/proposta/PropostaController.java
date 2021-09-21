package br.com.zupacademy.vinicius.proposalmicroservice.proposta;

import br.com.zupacademy.vinicius.proposalmicroservice.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Autowired
    private PropostaRepository propostaRepository;
    
    @Transactional
    @ResponseBody
    @PostMapping
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<?> cadastraSolicitante(
            @RequestBody @Valid PropostaForm form) throws RegraNegocioException {
        Proposta proposta = form.toModel(propostaRepository);
        manager.persist(proposta);
        return ResponseEntity.ok().body(new PropostaDto(proposta));
    }
}
