package br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class CartaoResponse {
    
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private String idProposta;
    private BigDecimal limite;
    
    private List<HashMap<String,Object>> bloqueios;
    private List<HashMap<String,Object>> avisos;
    private List<HashMap<String,Object>> carteiras;
    private List<HashMap<String,Object>> parcelas;
    private HashMap<String,Object> renegociacao;
    private HashMap<String,Object> vencimento;
    
    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, String idProposta, BigDecimal limite,
                          List<HashMap<String, Object>> bloqueios, List<HashMap<String, Object>> avisos,
                          List<HashMap<String, Object>> carteiras, List<HashMap<String, Object>> parcelas,
                          HashMap<String, Object> renegociacao, HashMap<String, Object> vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
        this.limite = limite;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
    }
    
    public String getId() {
        return id;
    }
    
    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public String getIdProposta() {
        return idProposta;
    }
    
    public BigDecimal getLimite() {
        return limite;
    }
    
    public List<HashMap<String, Object>> getBloqueios() {
        return bloqueios;
    }
    
    public List<HashMap<String, Object>> getAvisos() {
        return avisos;
    }
    
    public List<HashMap<String, Object>> getCarteiras() {
        return carteiras;
    }
    
    public List<HashMap<String, Object>> getParcelas() {
        return parcelas;
    }
    
    public HashMap<String, Object> getRenegociacao() {
        return renegociacao;
    }
    
    public HashMap<String, Object> getVencimento() {
        return vencimento;
    }
}
