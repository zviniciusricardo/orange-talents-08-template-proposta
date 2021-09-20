package br.com.zupacademy.vinicius.proposalmicroservice.solicitante;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, updatable = false, nullable = false)
    private String documento;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private BigDecimal salario;
    
    public Solicitante(SolicitanteForm form) {
        this.documento = form.getDocumento();
        this.email = form.getEmail();
        this.nome = form.getNome();
        this.endereco = form.getEndereco();
        this.salario = form.getSalario();
    }
    
    @Deprecated
    public Solicitante() {
    }
    
    public Long getId() {
        return id;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
}
