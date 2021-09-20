package br.com.zupacademy.vinicius.proposalmicroservice.solicitante;

import br.com.zupacademy.vinicius.proposalmicroservice.validator.CPForCNPJ;
import br.com.zupacademy.vinicius.proposalmicroservice.validator.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class SolicitanteForm {
    
    @NotBlank @CPForCNPJ
    @UniqueValue(domainClass = Solicitante.class, fieldName = "documento")
    private String documento;

    @NotBlank
    @UniqueValue(domainClass = Solicitante.class, fieldName = "email")
    private String email;
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String endereco;
    
    @NotNull @Positive
    private BigDecimal salario;
    
    public SolicitanteForm(@NotBlank String documento, @NotBlank String email, @NotBlank String nome,
                           @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
    
    public Solicitante toModel() {
        return new Solicitante(this);
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
