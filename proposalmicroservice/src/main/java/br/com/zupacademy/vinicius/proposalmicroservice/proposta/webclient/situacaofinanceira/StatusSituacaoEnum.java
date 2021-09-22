package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.situacaofinanceira;

import br.com.zupacademy.vinicius.proposalmicroservice.proposta.StatusProposta;

public enum StatusSituacaoEnum {
    
    COM_RESTRICAO(StatusProposta.NAO_ELEGIVEL),
    SEM_RESTRICAO(StatusProposta.ELEGIVEL);
    
    private final StatusProposta statusProposta;
    
    StatusSituacaoEnum(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }
    
    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
