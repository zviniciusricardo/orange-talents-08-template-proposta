package br.com.zupacademy.vinicius.proposalmicroservice.solicitante;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/solicitantes")
public class SolicitanteController {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Transactional
    @ResponseBody
    @PostMapping
    public ResponseEntity<?> cadastraSolicitante(@RequestBody @Valid SolicitanteForm form) {
        Solicitante solicitante = form.toModel();
        manager.persist(solicitante);
        return ResponseEntity.ok().body(new SolicitanteDto(solicitante));
    }
}
