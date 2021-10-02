package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.cartao_biometria;

import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.Cartao;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private byte[] fingerprint;
    
    @PastOrPresent
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @ManyToOne
    private Cartao cartao;
    
    @Deprecated
    public Biometria() {
    }
    
    public Biometria(BiometriaForm form, Cartao cartao) throws IOException {
        this.fingerprint = Base64.getEncoder().encode(form.getFingerprint().getBytes());
        this.cartao = cartao;
    }
    
    public Long getId() {
        return id;
    }
}
