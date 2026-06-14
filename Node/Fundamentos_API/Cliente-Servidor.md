## Cliente-Servidor
O protocolo HTTP (Hypertext Transfer Protocol) é a base da comunicação na web e desempenha um papel crucial na interação entre clientes e servidores, especialmente em APIs RESTful (FREITAS et al., 2021). 
Ele segue um modelo cliente-servidor, onde o cliente, como um navegador ou uma aplicação, envia uma requisição ao servidor, que processa a solicitação e retorna uma resposta (MARDAN, 2018; PEREIRA, 2016). 
Essa troca de informações ocorre através de mensagens HTTP bem estruturadas que contêm cabeçalhos, método, URL e, em alguns casos, um corpo de dados (payload).

O modelo cliente-servidor separa as responsabilidades entre o cliente, que faz as requisições, e o servidor, que processa essas requisições e retorna as respostas. Neste cenário:

 * **Cliente**: Geralmente, um navegador ou uma aplicação que faz a requisição de dados ou serviços. O cliente inicia a comunicação enviando uma requisição ao servidor.
 * **Servidor**: Um servidor web que recebe a requisição do cliente, processa-a (consultando bancos de dados, executando lógica de negócios, etc.) e retorna uma resposta. O servidor permanece em espera até receber uma nova requisição.
Por exemplo, quando um cliente acessa uma página web, ele está enviando uma requisição HTTP para o servidor onde a página está hospedada.
O servidor, por sua vez, processa a requisição e responde com o conteúdo da página (HTML, CSS, JavaScript, etc.) que será renderizado no navegador do cliente.


## Funcionamento no Contexto RESTful
Quando usamos uma API RESTful, o modelo cliente-servidor funciona desta forma:

 * O cliente realiza uma requisição HTTP (como GET, POST, PUT ou DELETE) a um endpoint da API, que é uma URL específica no servidor.
 * O servidor recebe a requisição, processa-a com base na lógica de negócios e retorna uma resposta que, em geral, está no formato JSON ou XML.
 * A resposta pode conter dados solicitados (como informações de um produto) ou uma confirmação de uma operação realizada (como uma compra).


## Vantagens do Modelo Cliente-Servidor
Dentre as vantagens do modelo cliente-servidor, podemos destacar:

 * **Separação de responsabilidades**: O cliente e o servidor são independentes, o que facilita a manutenção e a escalabilidade. Mudanças na interface do cliente não impactam a lógica do servidor, e vice-versa.
 * **Escalabilidade**: Como o cliente e o servidor operam de forma independente, ambos podem ser escalados individualmente. Por exemplo, se há um aumento no número de usuários, o cliente pode escalar horizontalmente adicionando mais instâncias, e o servidor pode fazer o mesmo para atender a novas demandas.
 * **Flexibilidade no desenvolvimento**: Como cliente e servidor são desacoplados, eles podem ser desenvolvidos com tecnologias diferentes. Um cliente pode ser um aplicativo mobile escrito em Swift ou Java, enquanto o servidor pode ser construído em Node.js, Python, entre outros.
