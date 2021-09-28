package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.contas;

import br.com.zupacademy.vinicius.proposalmicroservice.proposta.Proposta;

public class CartaoRequest {
    
    private String documento;
    private String nome;
    private String idProposta;
    
    public CartaoRequest(Proposta proposta) {
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
