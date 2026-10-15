# NexusCloud Manager — Programação Orientada a Objetos

Aluno: Paulo Fortunato  
RA: 25142855-2  
Curso: Engenharia de Software — UniCesumar

## Arquivos

A pasta src contém Cliente.java, Container.java, Servidor.java, CloudManager.java,
ServidorOfflineException.java, CapacidadeExcedidaException.java e MainCloud.java.
Os comentários explicam os trechos do código e terminam com o RA.

## Como executar

Tenha um JDK instalado (Java 8 ou superior; projeto verificado com Java 17).
Extraia o ZIP e abra um terminal na pasta NexusCloudManager:

```sh
javac -encoding UTF-8 -d out src/*.java
java -cp out MainCloud
```

Em uma IDE, crie um projeto Java, copie os sete arquivos da pasta src para a
pasta de código-fonte e execute MainCloud. As classes não usam declaração de package.

## Teste obrigatório do passo 6

1. Abra src/MainCloud.java.
2. Localize a chamada comentada logo abaixo da explicação do passo 6:

```java
// servidor.alocarContainer(container1); // RA: 25142855-2
```

3. Remova somente o primeiro `//`, mantendo o comentário com o RA ao final.
4. Compile e execute novamente. O servidor ainda está desligado: deve ocorrer
ServidorOfflineException sem captura e a execução será interrompida. A cor
vermelha depende da IDE; o importante é a exceção aparecer no console.
5. Recoloque o primeiro `//`, conforme o passo 7. Compile e execute novamente
para realizar os passos 7 a 10. A versão entregue já está nesse estado final.

## Saída da execução final

```text
Falha Crítica: Memória RAM insuficiente no servidor 192.168.0.1.
5.50
```

O primeiro container ocupa 10 GB. O segundo exigiria mais 8 GB, totalizando
18 GB, acima da capacidade de 16 GB. A exceção é capturada e o segundo container
não entra na lista. O servidor permanece ligado, então sua tarifa de 5.50
é incluída na fatura ativa. Não há multiplicação por horas: o enunciado pede
somente a soma de precoPorHora dos servidores ligados.

## Entenda antes de entregar

- Encapsulamento: atributos privados e métodos para consultar ou alterar o estado.
  Os getters de estado e preço do servidor permitem calcular a fatura sem expor os atributos.
- Identidade: Cliente usa CPF, Servidor usa IP e Container usa ID. equals e
  hashCode usam a mesma chave em cada classe. Os campos de identidade são final e não há setters para alterar essas
  identidades enquanto os objetos estiverem em coleções baseadas em hash.
- Coleções: ArrayList preserva a ordem de inserção dos containers; HashMap
  associa clientes a conjuntos HashSet de servidores sem duplicação por IP.
- Cadastro: registrarCliente deve ser chamado antes de provisionarServidor
  e calcularFaturaAtiva, seguindo a sequência do roteiro. Registrar novamente
  um mesmo CPF não apaga os servidores já associados.
- Fail-fast: alocarContainer verifica primeiro o estado, depois a RAM e só
  então modifica a lista.
- Unchecked: ServidorOfflineException estende RuntimeException; o passo 6
  intencionalmente não a captura.
- Checked: CapacidadeExcedidaException estende Exception. O método main declara
  throws para as chamadas dos passos 6 e 8, e o passo 9 usa o try-catch obrigatório.
  Declarar throws não captura a exceção unchecked nem impede o teste do passo 6.
- Streams: mapToDouble transforma os dados em números e sum soma; filter
  seleciona somente servidores ligados. Os cálculos não usam laços for.

“Aplicacao 1” e “Aplicacao 2” são nomes ilustrativos usados para preencher
nomeApp, exigido pelo construtor do UML, mas sem valor definido no roteiro.
Os demais dados do teste seguem os exemplos fornecidos.

## Verificação realizada

Compilação com Java 17 e -Xlint:all, sem avisos; execução final com saída acima;
execução separada do passo 6 com falha proposital; igualdade e hash por CPF,
IP e ID; rejeição de IP duplicado; preservação da RAM após falha; alocação no
limite exato de 16 GB; exclusão de servidores desligados do faturamento.

Revise o código completo antes da entrega, conforme a regra do professor.
O endereço indicado no enunciado é bit.ly/EntregaPOO. O material enviado não
define o formato de arquivo aceito nesse formulário; confira antes de enviar.

## Revisão técnica

A revisão confirmou os requisitos RN01–RN04 e os dez passos do roteiro.
Foram acrescentadas mensagens explícitas para cliente não cadastrado nas
operações do gerenciador e validação de referências nulas nesse cadastro.
As identidades CPF, IP e ID foram marcadas como final, impedindo mudanças
acidentais que comprometeriam HashMap e HashSet. O mapa também é final;
isso impede sua substituição, mas permite cadastrar clientes normalmente.
O método privado obterServidores centraliza a validação sem mudar as
assinaturas públicas exigidas no UML.

A nova versão compilou com --release 8, -Xlint:all e -Werror usando o
compilador Java 17. Foi executada no Java 17. Também foram verificados
cliente não cadastrado, cliente sem servidores, soma de vários servidores
ligados com exclusão de um desligado e prioridade da validação de status.
Todos passaram. O cenário final continua produzindo exatamente a saída acima.

Limites de escopo: o enunciado não define validação de formato de CPF/IP,
valores negativos/não finitos de RAM e preço ou política para containers com
ID repetido dentro de um servidor. Essas regras não foram inventadas.
Em um sistema de produção, precisariam ser especificadas e implementadas.
O uso de double para dinheiro foi mantido porque é explicitamente exigido
no UML; em produção, a precisão monetária mereceria uma decisão específica.
