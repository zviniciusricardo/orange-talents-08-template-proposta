package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.analisefinanceira;

import br.com.zupacademy.vinicius.proposalmicroservice.business.Proposta;

public class AnaliseFinanceiraRequest {
    
    private String documento;
    private String nome;
    private String idProposta;
    
    public AnaliseFinanceiraRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getIdProposta() {
        return idProposta;
    }
}
