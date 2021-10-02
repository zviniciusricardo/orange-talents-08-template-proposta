package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.analisefinanceira;

import br.com.zupacademy.vinicius.proposalmicroservice.business.StatusProposta;

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
