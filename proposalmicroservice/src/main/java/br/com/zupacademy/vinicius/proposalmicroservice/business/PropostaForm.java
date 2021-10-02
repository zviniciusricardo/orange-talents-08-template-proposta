package br.com.zupacademy.vinicius.proposalmicroservice.business;

import br.com.zupacademy.vinicius.proposalmicroservice.validation.CPForCNPJ;
import br.com.zupacademy.vinicius.proposalmicroservice.validation.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaForm {
    
    @NotBlank
    @CPForCNPJ
    @UniqueValue(domainClass = Proposta.class, fieldName = "documento")
    private String documento;
    
    @NotBlank
    @UniqueValue(domainClass = Proposta.class, fieldName = "email")
    private String email;
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String endereco;
    
    @NotNull
    @Positive
    private BigDecimal salario;
    
    public PropostaForm(@NotBlank String documento, @NotBlank String email, @NotBlank String nome,
                        @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
    
    public Proposta toModel() {
        return new Proposta(this);
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
