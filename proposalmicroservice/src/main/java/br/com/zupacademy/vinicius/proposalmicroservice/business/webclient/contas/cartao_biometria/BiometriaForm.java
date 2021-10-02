package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.cartao_biometria;

import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.Cartao;
import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.CartaoRepository;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

public class BiometriaForm {
    
    @NotBlank
    private String fingerprint;
    
    public BiometriaForm(String fingerprint) {
        this.fingerprint = fingerprint;
    }
    
    @Deprecated
    public BiometriaForm() {
    }
    
    public Biometria toModel(CartaoRepository cartaoRepository, Cartao cartao) throws RuntimeException, IOException {
        
        return new Biometria(this, cartao);
    }
    
    public String getFingerprint() {
        return fingerprint;
    }
    
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }
}
