package br.com.zupacademy.vinicius.proposalmicroservice.business;

import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.analisefinanceira.AnaliseFinanceiraRequest;
import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.analisefinanceira.AnaliseFinanceiraResponse;
import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.analisefinanceira.AnaliseFinanceiraWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String documento;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private BigDecimal salario;
    
    @Enumerated(EnumType.STRING)
    private StatusProposta status;
    
    @JoinColumn(name = "numero_cartao")
    private String numeroCartao;
    
    public Proposta(PropostaForm form) {
        this.documento = form.getDocumento();
        this.email = form.getEmail();
        this.nome = form.getNome();
        this.endereco = form.getEndereco();
        this.salario = form.getSalario();
    }
    
    @Deprecated
    public Proposta() {
    }
    
    
    public void analisaSituacaoFinanceira(AnaliseFinanceiraWebClient webClient) throws JsonProcessingException {
        AnaliseFinanceiraRequest request = new AnaliseFinanceiraRequest(this);
        AnaliseFinanceiraResponse response;
        
        try {
            response = webClient.analisaPropostas(request);
        } catch (FeignException ex) {
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(ex.contentUTF8(), AnaliseFinanceiraResponse.class);
        }
        
        this.setStatusProposta(response.getResultadoSolicitacao());
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    
    public void setStatusProposta(StatusProposta status) {
        this.status = status;
    }
    
    public StatusProposta getStatus() {
        return status;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }
    
}
