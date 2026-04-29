# Engenharia
Trabalho de Engenharia de Software AV2
Alunos: Vitor Cabrera e Carlos Colavite

Sistema de Controle de Pedidos - Padrões State e Strategy
Visão Geral
Este projeto consiste em uma API REST desenvolvida em Java com Spring Boot, responsável pelo gerenciamento de pedidos em um contexto de e-commerce.
A solução foi projetada com foco em boas práticas de orientação a objetos e uso de padrões de projeto, garantindo baixo acoplamento, alta coesão e facilidade de manutenção/extensão.

Padrão State

Problema
O pedido possui um ciclo de vida com regras específicas:
Aguardando pagamento → pode pagar ou cancelar
Pago → pode enviar
Enviado → estado final
Cancelado → estado final

Essas regras tornam o uso de if/else ou switch complexo e difícil de manter.

Solução com State
O padrão State foi utilizado para encapsular o comportamento de cada estado em classes separadas.
Estrutura
Pedido (contexto)
   ↓
PedidoState (interface)
   ↓
Estados concretos:
- AguardandoState
- PagoState
- EnviadoState
- CanceladoState

Benefícios
Elimina condicionais complexas
Cada estado possui responsabilidade isolada
Facilita manutenção
Facilita extensão (novos estados)
Código mais limpo e orientado a objetos

Padrão Strategy
Problema

O cálculo de frete pode variar:
Transporte terrestre → 5% do valor
Transporte aéreo → 10% do valor
E o sistema deve permitir adicionar novos tipos de frete no futuro.

Solução com Strategy
O padrão Strategy foi utilizado para encapsular os algoritmos de cálculo de frete.

Estrutura
FreteStrategy (interface)
   ↓
Implementações:
- FreteTerrestre
- FreteAereo

Benefícios
Elimina if/else para cálculo de frete
Permite adicionar novos tipos sem alterar código existente
Segue o princípio Open/Closed (OCP)
Código mais flexível e reutilizável

Integração entre State e Strategy
No projeto:
O State controla o comportamento do pedido (fluxo de estados)
O Strategy define como o frete é calculado

Eles atuam de forma independente, mantendo o sistema:

Modular
Extensível
Fácil de testar

Persistência

A aplicação utiliza:
Spring Data JPA
Banco PostgreSQL

Entidade principal:
@Entity
public class Pedido {
    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private String tipoFrete;
}

Considerações Finais

A utilização dos padrões State e Strategy permitiu:
Separar responsabilidades de forma clara
Tornar o sistema mais organizado
Facilitar manutenção e evolução
Reduzir complexidade condicional
