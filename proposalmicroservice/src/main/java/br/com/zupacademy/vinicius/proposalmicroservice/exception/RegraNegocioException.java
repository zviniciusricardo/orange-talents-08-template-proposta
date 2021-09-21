package br.com.zupacademy.vinicius.proposalmicroservice.exception;

public class RegraNegocioException extends RuntimeException {
    
    private ReturnError erroRetorno;
    
    public RegraNegocioException(String mensagem, String campo, Object value) {
        erroRetorno = new ReturnError();
        erroRetorno.addErrorField(campo, value, mensagem);
    }
    
    public ReturnError getErroRetorno() {
        return erroRetorno;
    }
}
