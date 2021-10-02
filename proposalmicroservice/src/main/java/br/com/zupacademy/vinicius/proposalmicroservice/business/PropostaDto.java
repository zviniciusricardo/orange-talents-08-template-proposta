package br.com.zupacademy.vinicius.proposalmicroservice.business;

public class PropostaDto {
    
    private String nome;
    private String statusProposta;
    private String idProposta;
    
    
    public PropostaDto(Proposta proposta) {
        this.nome = proposta.getNome();
        this.statusProposta = proposta.getStatus().toString();
        this.idProposta = proposta.getId().toString();
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getStatusProposta() {
        return statusProposta;
    }
    
    public String getIdProposta() {
        return idProposta;
    }
}
