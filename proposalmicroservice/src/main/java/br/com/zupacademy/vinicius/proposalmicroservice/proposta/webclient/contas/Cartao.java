package br.com.zupacademy.vinicius.proposalmicroservice.proposta.webclient.contas;

import br.com.zupacademy.vinicius.proposalmicroservice.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Entity
public class Cartao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "numero_cartao", nullable = false, unique = true)
    private String numeroCartao;
    
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column(nullable = false)
    private String titular;
    
    @OneToOne
    private Proposta proposta;
    
    private BigDecimal limite;
    
    @ElementCollection
    private List<HashMap<String, Object>> bloqueios;
    @ElementCollection
    private List<HashMap<String, Object>> avisos;
    @ElementCollection
    private List<HashMap<String, Object>> carteiras;
    @ElementCollection
    private List<HashMap<String, Object>> parcelas;
    
    private HashMap<String, Object> renegociacao;
    
    private HashMap<String, Object> vencimento;
    
    public Cartao(CartaoResponse response) {
        this.numeroCartao = response.getId();
        this.dataCriacao = response.getEmitidoEm();
        this.titular = response.getTitular();
        this.proposta = getProposta();
        this.limite = response.getLimite();
        this.bloqueios = response.getBloqueios();
        this.avisos = response.getAvisos();
        this.carteiras = response.getCarteiras();
        this.parcelas = response.getParcelas();
        this.renegociacao = response.getRenegociacao();
        this.vencimento = response.getVencimento();
    }
    
    @Deprecated
    public Cartao() {
    }
    
    public String getNumeroCartao() {
        return numeroCartao;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public Proposta getProposta() {
        return proposta;
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
