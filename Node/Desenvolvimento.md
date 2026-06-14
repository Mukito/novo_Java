# Desenvolvimento de APIs Modernas

### Arquitetura do Node.js
A arquitetura do Node.js é projetada para ser leve, eficiente e escalável. Mas, para entender sua arquitetura, é essencial conhecer alguns conceitos básicos. 
Primeiramente, JavaScript é uma linguagem single-threaded, o que significa que possui apenas um fluxo de execução (GRONER, 2018). 
Diferente de linguagens multithread, onde várias operações podem ocorrer simultaneamente em diferentes threads, em JavaScript as instruções são executadas uma de cada vez, em sequência.

### V8 JavaScript Engine
O V8 é o motor de execução de JavaScript criado pelo Google para o navegador Chrome, mas também é usado pelo Node.js. 
Esse motor é responsável por compilar e executar o código JavaScript de maneira extremamente rápida (CODEWELL, 2020; NODE.JS, 2023). 
O V8 utiliza técnicas de compilação Just-In-Time (JIT), transformando JavaScript em código de máquina nativo, o que melhora significativamente o desempenho.

O V8 possui dois componentes principais que ajudam a gerenciar o funcionamento do programa: o Memory Heap e a Call Stack.
O Memory Heap é a área de memória onde o JavaScript armazena objetos, variáveis e dados. Como JavaScript é uma linguagem baseada em objetos e trabalha com referências, 
o Memory Heap serve para gerenciar o armazenamento desses dados, garantindo que o código possa acessar e manipular as informações conforme necessário. 
O V8 usa algoritmos de gerenciamento de memória e coleta de lixo (garbage collection) para liberar o espaço da memória heap quando objetos e dados não são mais necessários. 
Esse processo é essencial para otimizar o desempenho e evitar vazamentos de memória.

A Call Stack é a estrutura de dados que organiza as chamadas de função no JavaScript. Toda vez que uma função é chamada, ela é adicionada ao topo da Call Stack. 
Quando essa função termina, ela é removida da pilha, permitindo que o motor JavaScript prossiga para a próxima função. 
Esse processo de empilhamento e desempilhamento ocorre de forma contínua, ajudando a manter a ordem de execução das funções. 
No entanto, como a Call Stack é limitada, empilhar muitas chamadas recursivas ou operações pesadas pode resultar em um erro de estouro de pilha (stack overflow).

Os dois componentes (Memory Heap e Call Stack) trabalham juntos para a execução do programa. 
A Call Stack organiza a ordem de execução das funções, enquanto o Memory Heap armazena dados usados por essas funções.


## Call Stack
A Call Stack (ou pilha de chamadas) é uma estrutura de dados fundamental no funcionamento do JavaScript. 
Ela gerencia a ordem de execução das funções no código, garantindo que as chamadas sejam realizadas e finalizadas na sequência correta.

A Call Stack segue o princípio LIFO (Last In, First Out), ou seja, o último item adicionado à pilha é o primeiro a ser removido. 
Cada vez que uma função é chamada, ela é empilhada no topo da Call Stack. Quando a função termina sua execução, ela é "desempilhada", liberando espaço para a próxima função na sequência. 
Esse gerenciamento garante que as instruções síncronas sejam executadas de maneira organizada.

Vamos ver esse processo com mais detalhes:
 * Empilhamento de Funções:
   * Quando o JavaScript encontra uma chamada de função, ele adiciona (ou "empilha") essa função na Call Stack.
   * Se essa função chamar outras funções, elas também serão empilhadas no topo da Call Stack, seguindo a ordem em que foram chamadas.
 * Execução e Desempilhamento:
   * A execução começa na função que está no topo da Call Stack.
   * Assim que a função termina de executar (retorna um valor ou conclui), ela é removida (ou "desempilhada") da Call Stack.
   * O JavaScript, então, prossegue para a próxima função na pilha, continuando o processo.

Vamos ver um exemplo simples para entender o funcionamento da Call Stack no Node.js.

```
function multiply(x, y) {
    return x * y;
}
function printSquare(x) {
    var s = multiply(x, x);
    console.log(s);
}
printSquare(5);

```

Quando o motor V8 começa a executar esse código, a Call Stack está inicialmente vazia. Em seguida, os passos de execução serão os seguintes:

  1. A função printSquare é chamada e adicionada ao topo da Call Stack.
  2. Dentro da função printSquare, a função multiply é chamada para multiplicar os valores dos parâmetros x e y.
  3. A função multiply é então adicionada ao topo da Call Stack.
  4. Após a execução de multiply, ela é removida da Call Stack, e o controle retorna ao ponto em printSquare logo após a chamada de multiply.
  5. Em seguida, o método console.log é chamado dentro de printSquare.
  6. O console.log é adicionado ao topo da Call Stack.
  7. Assim que console.log finaliza sua execução, ele é removido da Call Stack, deixando apenas printSquare.
  8. Finalmente, printSquare completa sua execução e é removida da Call Stack, deixando-a novamente vazia.


<img width="1217" height="600" alt="image" src="https://github.com/user-attachments/assets/3c68e997-9ea8-4b3d-b567-8f4da1d64e96" />

### Event Loop
Em aplicações modernas, muitas operações, como chamadas a uma API externa, leitura de arquivos e interações com bancos de dados, podem levar tempo para serem concluídas. 
Para evitar que essas operações bloqueiem a Call Stack e deixem o programa inativo enquanto aguardam a conclusão, JavaScript utiliza um modelo assíncrono. 
Essas operações que podem demorar são tratadas de forma assíncrona, permitindo que o código continue sua execução enquanto o resultado da operação é processado em segundo plano.

O Event Loop é o mecanismo que faz com que esse modelo assíncrono funcione. Ele gerencia a execução das tarefas assíncronas, garantindo que cada uma seja processada na sequência certa e no momento adequado. 
Quando uma operação assíncrona é concluída, o Event Loop verifica se a Call Stack está vazia e, se estiver, coloca a função correspondente à operação concluída na Call Stack para ser executada. 
Dessa forma, o Event Loop permite que JavaScript manipule operações assíncronas sem interromper o fluxo de execução principal, mantendo a aplicação responsiva e eficiente.


## Funcionamento da Arquitetura do Node.js
Vimos anteriormente que sempre que uma função síncrona é chamada, ela é empilhada na Call Stack, contendo seu endereço de memória, parâmetros e variáveis locais. 
Se essa função chamar outra função, a nova função será empilhada acima da anterior. Quando a execução da nova função termina, ela é removida da Call Stack, e o fluxo retorna para a função anterior.

Quando uma função é adicionada à Call Stack e não representa uma operação bloqueante, ela é executada imediatamente, permitindo que o Event Loop (loop de eventos) de thread única do Node.js 
continue com a próxima função da Call Stack. No entanto, uma operação é considerada bloqueante quando precisa aguardar a conclusão para retornar um resultado ou erro. 
Exemplo comum de operação bloqueante é a leitura de um arquivo no disco rígido com o comando fs.readFile.

Para lidar com operações bloqueantes, o Event Loop despacha essa operação e seu método callback (função de retorno) para o Thread Pool. 
O Thread Pool é um grupo (ou pool) de threads de trabalho que o Node.js utiliza para realizar operações de I/O de forma assíncrona. 
Esse pool é gerenciado pela biblioteca libuv e permite que o Node.js delegue tarefas intensivas em I/O para threads separadas, mantendo a thread principal livre para continuar a execução do código. 
Neste contexto, libuv assume a responsabilidade de processar a tarefa em uma thread separada, liberando o Event Loop para continuar executando as outras funções na Call Stack. Assim, 
o Event Loop pode processar outras requisições sem ficar "preso" em uma operação demorada.

Quando a tarefa de background é concluída, o callback associado a ela é enviado para a Task Queue (Fila de Tarefas), onde aguardará o momento certo para entrar na Call Stack e ser executado pelo Event Loop. 
Assim, a Task Queue (ou fila de tarefas) é uma estrutura que armazena callbacks de operações assíncronas que estão prontas para serem executadas, mas que aguardam a liberação da Call Stack (pilha de chamadas) 
para serem processadas.

Para ilustrar este funcionamento, considere o exemplo de duas operações na Call Stack:
  * Quando fs.readFile() é chamada, ela é reconhecida como uma operação de leitura de disco, ou seja, uma operação de I/O bloqueante. Por isso, é delegada para a libuv operar em segundo plano.
  * Enquanto fs.readFile() é processada pela libuv, o Event Loop é liberado para continuar com outras funções da Call Stack.
  * Quando fs.readFile() termina sua execução no background, o callback associado é colocado na Task Queue.
  * A Task Queue então espera até que a Call Stack esteja vazia para mover o callback para o topo da pilha, onde ele será processado pelo Event Loop.


Veja este funcionamento ilustrado na figura abaixo:

<img width="1324" height="591" alt="image" src="https://github.com/user-attachments/assets/774740d1-24d1-4056-b0a6-0de7df6a9552" />


