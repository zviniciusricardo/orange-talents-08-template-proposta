package br.com.zupacademy.vinicius.proposalmicroservice.proposta;

public class PropostaDto {
    
    public Long id;
    public String email;
    public String nome;
    
    
    public PropostaDto(Proposta proposta) {
        this.id = proposta.getId();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNome() {
        return nome;
    }
}
