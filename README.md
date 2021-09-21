
# Microsserviço de Propostas
### Orange Talents - Turma 8


- [x] 005.criacao_proposta
- [ ] 010.nao_pode_haver_proposta
- [ ] 015.consultando_dados_solicitante
- [ ] 020.melhorando_visibilidade_healthcheck
- [ ] 030.associar_cartao_proposta
- [ ] 035.acompanhamento_proposta
- [ ] 040.rodar_nossa_aplicacao
- [ ] 045.criar_biometria
- [ ] 050.login_via_senha
- [ ] 055.bloqueio_cartao
- [ ] 060.notificando_legado_cartao
- [ ] 065.como_saber_tudo_funcionando_corretamente
- [ ] 075.aviso_viagem
- [ ] 080.notificando_sistema_bancario_viagem
- [ ] 085.associacao_paypal
- [ ] 090.associacao_samsung_pay
- [ ] 095.falhar_nas_chamadas
- [ ] 100.rodando_prometheus
- [ ] 105.dados_dos_clientes


### 005 - Criação Proposta (Caminho cognitivo)

Crio um controller com um método de verbo POST que recebe um form contendo os dados obrigatórios. Esse método é anotado
com @Valid e @RequestBody para segurança das informações sensíveis.
A classe form recebe anotações da bean validation para validar a entrada dos dados e garantir que eles cumpram as especificações
de obrigatoriedade. 
Crio uma anotation personalisada com as anotações @CPF e @CNPJ unindo as duas para que funcionem como uma única anotação
resultando em só uma notificação caso os dados não sejam válidos.

Crio a entidade usuário que será mapeada usando o Spring Data JPA e Hibernate para o relacionamento objeto relacional.
A classe form tem um método de conversão para Entidade, mas antes, ela busca no banco de dados checando se já existe um
cadastro do usuário em questão. Aí cabe uma anotação para validar se o cadastro é único ou se já foi realizado.

Voltando ao construtor, se estiver tudo ok, convertemos nosso form em entidade e usamos o Entity Manager para persistir 
os dados e depois, usamos o ResponseEntity<> para retornar uma classe DTO e um status ok caso haja sucesso ou 400 caso não.

### 010.nao_pode_haver_proposta

Crio uma anotação/interface que garanta a unicidade dos dados. Posso usar a anotação 
@Column(unique = true) também, mas queremos proteger as bordas do sistema.
Com a interface/anotação criada e configurada para retornar uma mensagem padrão, o field onde
ocorreu o erro e a classe a qual pertence o atributo validado.
Usamos uma classe validadora que implementa a ConstraintValidator. Essa classe é construída
de maneira agnóstica pois podemos reaproveitála em qualquer tipo de classe que precise ser
validada. Isso porque nossa classe validadora faz uso de Generics e de Criteria pra fazer
a busca no banco pelo documento enviado na request pelo cliente.
Caso o documento já esteja cadastrado, configuro o meu controller para retornar
HttpStatus.NOT_ACCEPTABLE que significa que o dado oferecido pelo cliente não é aceitável
nos parâmetros de nossa regra de negócio.

Para nos utilizar de boas práticas de REST API's, usamos um controllerAdvice para filtragem
de erros de retorno ao cliente.
Especificamos o nome da classe, o nome do field e a constraint que foi ferida (mensagem padrão).
Caso não exista nenhum documento cadastrado, retornamos 200 ok.

Sempre usarei o ResponseEntity como retorno de métodos de controllers.
