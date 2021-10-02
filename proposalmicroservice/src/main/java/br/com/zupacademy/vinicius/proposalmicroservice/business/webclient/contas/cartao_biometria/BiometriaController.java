package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.cartao_biometria;

import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.Cartao;
import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/cartoes/")
public class BiometriaController {
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private BiometriaRepository biometriaRepository;
    
    
    @PostMapping("/{cartaoId}/biometrias")
    public ResponseEntity<?> cadastraBiometria(@PathVariable Long cartaoId, @RequestBody @Valid BiometriaForm form,
                                               UriComponentsBuilder uriBuilder) throws IOException, RuntimeException {
    
        Cartao cartao = cartaoRepository.findById(cartaoId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        Biometria biometria = form.toModel(cartaoRepository, cartao);
        biometriaRepository.save(biometria);
        
        URI uri = uriBuilder.path("/api/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }
}
