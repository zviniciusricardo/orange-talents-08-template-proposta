package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.analisefinanceira;

import br.com.zupacademy.vinicius.proposalmicroservice.business.StatusProposta;

public class AnaliseFinanceiraResponse {
    
    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;
    
    public String getDocumento() {
        return documento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getIdProposta() {
        return idProposta;
    }
    
    public StatusProposta getResultadoSolicitacao() {
        return StatusSituacaoEnum.valueOf(resultadoSolicitacao).getStatusProposta();
    }
}
