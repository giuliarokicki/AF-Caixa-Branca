# AF-Caixa-Branca

Perguntas: 
O código foi devidamente documentado?
R: Não, o código praticamente não tem comentários explicativos, apenas uma linha comentada indicando onde está a instrução SQL, e na minha opinião um código bem documentado precisa de comentários.

As variáveis e constantes possuem nomenclatura?
R: Sim, mas não completamente. Digo isso, pois o código tem nomes que dão para entender, mas não são as melhores opções, por exemplo:
nome e result têm nomes aceitáveis, porém poderiam ser mais claros (ex: usuarioNome, loginValido). Sinto que tudo está hardcoded.

Existem legibilidade e organização no código?
R: Acredito que sim, porque o código é curto e de fácil leitura.

Todos os NullPointers foram tratados?

As conexões utilizadas foram fechadas?


_________________________________________________________________________________________________________________________________

NOTAÇÃO DE GRAFO DE FLUXO;

N1  → N2      (início do método vai para conexão)
N2  → N3      (conexão obtida, prossegue para montagem do SQL)
N3  → N4      (após montar o SQL cria o Statement)
N4  → N5      (executa o SQL)
N5  → N6      (recebe ResultSet e faz verificação)
N6  → N7      (se rs.next() for verdadeiro)
N7  → N9      (atribuições feitas, retorna resultado)
N6  → N9      (se rs.next() for falso)
N2  → N8      (se falhar a conexão)
N4  → N8      (se falhar a criação do Statement)
N5  → N8      (se falhar a execução do SQL)
N8  → N9      (após exceção, retorna result)


             ┌────────────┐
             │    N1      │
             │  Início    │
             └─────┬──────┘
                   v
             ┌────────────┐
             │    N2      │
             │ ConectarBD │
             └─────┬──────┘
       erro ───────┘      │
           v              v
       ┌──────────┐   ┌───────────┐
       │    N8    │   │    N3     │
       │  Catch   │   │ Monta SQL │
       └─────┬────┘   └────┬──────┘
             v               v
         ┌──────────┐   ┌────────────┐
         │    N9     │  │    N4      │
         │ Retorno   │  │ Statement  │
         └──────────┘   └────┬───────┘
                              v
                        ┌────────────┐
                        │    N5      │
                        │ Execute SQL│
                        └────┬───────┘
                              v
                        ┌────────────┐
                        │    N6      │
                        │   IF next  │
                        └────┬───┬───┘
                       true  │   │  false
                             v   v
                      ┌───────────┐
                      │    N7     │
                      │Atribuições│
                      └─────┬─────┘
                            v
                      ┌───────────┐
                      │    N9     │
                      │  Retorno  │
                      └───────────┘


____________________________________________________________________________________________________________________________________

COMPLEXIDADE CICLOMÁTICA;

V(G) = E – N + 2

onde:

E = numero de arestas do grafo de fluxo

N = numero de nós

Contagem N:
O grafo possui 9 nós:
N1, N2, N3, N4, N5, N6, N7, N8, N9

Então:
N = 9

Contagem E:
As transições entre os nós são:
N1 → N2

N2 → N3

N3 → N4

N4 → N5

N5 → N6

N6 → N7 (ramo verdadeiro)

N6 → N9 (ramo falso)

Exceção → N8

N8 → N9

N7 → N9

Total:
E = 10

Cálculo

V(G) = E – N + 2
V(G) = 10 – 9 + 2
V(G) = 3

A complexidade ciclomática do método é 3, então existem 3 caminhos independentes:

1-Caminho principal onde o usuário é encontrado (if verdadeiro)

2-Caminho alternativo onde o usuário não é encontrado (if falso)

3-Caminho de tratamento de exceção (bloco catch)

____________________________________________________________________________________________________________________________________________________

CAMINHOS BÁSICOS;
Caminho 1 – Fluxo principal (usuário encontrado)
N1 → N2 → N3 → N4 → N5 → N6 → N7 → N9
Caso em que rs.next() é verdadeiro, ou seja, o usuário existe no banco. 

Caminho 2 – Usuário não encontrado
N1 → N2 → N3 → N4 → N5 → N6 → N9
Onde rs.next() é falso. O nome não é atribuído e o método retorna false.

Caminho 3 – Fluxo de exceção
N1 → N2 → N3 → N4 → N5 → N8 → N9
Qualquer exceção lançada antes ou durante a execução da query. O método cai no bloco catch e retorna false.

Resumo:
1-Usuário encontrado (if verdadeiro)
2-Usuário não encontrado (if falso)
3-Exceção ocorre
