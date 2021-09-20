package br.com.zupacademy.vinicius.proposalmicroservice.solicitante;

public class SolicitanteDto {
    
    public Long id;
    public String email;
    public String nome;
    
    
    public SolicitanteDto(Solicitante solicitante) {
        this.id = solicitante.getId();
        this.email = solicitante.getEmail();
        this.nome = solicitante.getNome();
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
