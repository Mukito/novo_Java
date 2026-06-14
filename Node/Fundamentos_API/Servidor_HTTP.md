O Node.js permite criar servidores web de forma prática e eficiente usando o módulo embutido HTTP (NODE.JS, 2024). 
Neste exemplo, vamos desenvolver um servidor básico que responde às solicitações que representam os diferentes métodos HTTP.

### Configurar o Projeto
Primeiramente, vamos criar um diretório para o projeto. Assim, no terminal, crie uma nova pasta para o seu projeto e navegue até ela.
```
mkdir servidor-http
    cd servidor-http
```

Em seguida, podemos criar um arquivo package.json para gerenciar o projeto. Este arquivo é essencial em projetos Node.js, pois armazena informações importantes sobre o projeto, 
como dependências, scripts e metadados. Logo, inicialize o projeto digitando:
```
npm init -y
```

Você pode, agora, abrir a aplicação no editor Visual Studio Code. Para isso, dentro do diretório servidor-http digite o seguinte comando no terminal:
```
code .
```

Após a execução deste comando, o Visual Studio Code deverá abrir com a pasta raiz do seu projeto sendo acessada. Após, vamos organizar a estrutura de diretórios e arquivos de nosso projeto conforme ilustrado abaixo:

<img width="253" height="126" alt="image" src="https://github.com/user-attachments/assets/67573917-8265-45dd-8f96-943d636fe3d4" />


## Criando o Servidor HTTP
Agora, vamos criar o arquivo principal de nosso projeto, onde o código do servidor web será escrito. Desta forma, crie um arquivo chamado server.js com o seguinte código:
```
const http = require("http");
const { handleRequest } = require("./routes"); // Importa as rotas configuradas
const PORT = 3000;
const server = http.createServer((req, res) => {
      handleRequest(req, res); // Redireciona todas as requisições para esta função
});
server.listen(PORT, () => {
      console.log(`Servidor rodando em http://localhost:${PORT}`);
});
```

Esse código cria um servidor HTTP que, ao receber uma requisição, encaminha-a para a função handleRequest (que contém a lógica de roteamento e resposta). 
Isso permite que toda a lógica de tratamento de requisições fique organizada em um arquivo separado (routes.js).

### Configurando as Rotas em routes.js
Em projetos maiores, é uma boa prática separar as rotas e os controladores em arquivos diferentes para organizar o código e facilitar a manutenção. 
Neste contexto, vamos criar um arquivo routes.js para configurar as rotas com o seguinte código:

```
const { getProdutos, createProduto, updateProduto, deleteProduto } = require('./controllers/produtosController');


function handleRequest(req, res) {
  
         res.setHeader('Content-Type', 'application/json');
         const routeKey = `${req.method} ${req.url}`;
        
        
          switch (true) {
            case routeKey === 'GET /api/produtos':
              getProdutos(req, res); // Listar produtos
              break;
             
            case routeKey === 'POST /api/produtos':
              createProduto(req, res); // Criar produto
              break;
        
        
            case req.url.startsWith('/api/produtos/') && req.method === 'PUT':
              updateProduto(req, res); // Atualizar produto
              break;
        
        
            case req.url.startsWith('/api/produtos/') && req.method === 'DELETE':
              deleteProduto(req, res); // Deletar produto
              break;
        
        
            default:
              res.statusCode = 404;
              res.end(JSON.stringify({ message: 'Rota não encontrada' })); // Responde com 404 para rotas não encontradas
              break;
          }
}
module.exports = { handleRequest };
        
```

Esse código define uma função de manipulação de requisições HTTP chamada handleRequest, que configura o cabeçalho da resposta como JSON e usa uma estrutura switch para gerenciar diferentes rotas de uma API de produtos. 
Dependendo do método e da URL da requisição, ele chama controladores específicos para listar (getProdutos), criar (createProduto), atualizar (updateProduto) ou deletar (deleteProduto) produtos. 
Se a rota não corresponder a nenhuma das opções, retorna um código de status 404 com uma mensagem de "Rota não encontrada".


### Definindo os Controladores
Os controladores contêm a lógica para cada ação específica de uma rota, como buscar dados, processar informações ou manipular o banco de dados. 
Dentro da pasta controllers, vamos criar um arquivo chamado produtosController.js com o seguinte código:
```
// Controlador para listar todos os produtos
function getProdutos(req, res) {
        // Dados simulados de produtos (simula o acesso ao banco de dados)
        const produtos = [
          { id: 1, nome: "Produto A", preco: 50.0 },
          { id: 2, nome: "Produto B", preco: 30.0 },
        ];
        // Define o status de sucesso e envia a lista de produtos como JSON
        res.statusCode = 200;
        res.end(JSON.stringify(produtos));
}
// Controlador para criar um novo produto
function createProduto(req, res) {
        let body = "";
      
      
        // Recebe os dados do corpo da requisição em partes (chunks)
        req.on("data", (chunk) => {
          body += chunk.toString();
        });
      
      
        // Processa os dados após a recepção completa
        req.on("end", () => {
          try {
            const novoProduto = JSON.parse(body); // Converte o corpo da requisição de JSON para um objeto
            novoProduto.id = Date.now(); // Gera um ID único (em uma aplicação real, o banco de dados geraria o ID)
      
      
            // Define o status de criação e envia o produto criado como resposta
            res.statusCode = 201;
            res.end(
              JSON.stringify({ message: "Produto criado", produto: novoProduto })
            );
          } catch (error) {
            // Lida com erros de parsing JSON
            res.statusCode = 400;
            res.end(JSON.stringify({ message: "Erro ao processar o produto" }));
          }
        });
  }  
      
  // Controlador para atualizar um produto
  function updateProduto(req, res) {
      
      
        const id = req.url.split("/")[3]; // Extrai o ID da URL
        let body = "";
      
      
        // Recebe os dados do corpo da requisição em partes (chunks)
        req.on("data", (chunk) => {
          body += chunk.toString();
        });
      
      
        // Processa os dados após a recepção completa
        req.on("end", () => {
          try {
            const produtoAtualizado = JSON.parse(body); // Converte o corpo da requisição de JSON para um objeto
            produtoAtualizado.id = parseInt(id, 10); // Garante que o ID seja um número inteiro
      
      
            // Define o status de sucesso e envia o produto atualizado como resposta
            res.statusCode = 200;
            res.end(
              JSON.stringify({
                message: "Produto atualizado",
                produto: produtoAtualizado,
              })
            );
          } catch (error) {
            // Lida com erros de parsing JSON
            res.statusCode = 400;
            res.end(JSON.stringify({ message: "Erro ao processar o produto" }));
          }
        });
  }
      
      
  // Controlador para deletar um produto
  function deleteProduto(req, res) {
        const id = req.url.split("/")[3]; // Extrai o ID da URL
        // Define o status de sucesso e envia uma mensagem confirmando a exclusão
        res.statusCode = 200;
        res.end(JSON.stringify({ message: `Produto com ID ${id} deletado` }));
  }
  
  // Exporta os controladores para serem usados em outros módulos
  module.exports = {
        getProdutos,
        createProduto,
        updateProduto,
        deleteProduto,
  };
      
```

Neste arquivo, cada função representa uma ação para um endpoint específico:

 * **getProdutos**: Retorna uma lista de produtos.
 * **createProduto**: Cria um novo produto com os dados recebidos no corpo da requisição.
 * **updateProduto**: Atualiza um produto específico, usando o ID da URL e o corpo da requisição.
 * **deleteProduto**: Remove um produto específico com base no ID fornecido na URL.

### Instalando o Nodemon e configurando o Servidor Web
Normalmente, ao fazer mudanças no código em projetos Node.js, é necessário parar e iniciar o servidor manualmente para aplicar essas atualizações. 
Isso porque o Node não detecta automaticamente as alterações nos arquivos, sendo necessário reiniciar todo o processo para que as mudanças sejam aplicadas, o que pode ser demorado e tedioso.
Neste contexto, vamos instalar uma dependência ao projeto, o Nodemon . 

O Nodemon é uma ferramenta para desenvolvimento de aplicações Node.js que monitora automaticamente as mudanças nos arquivos do projeto e reinicia o servidor sempre que um arquivo é salvo.
Assim, no terminal, digite:
```
npm i nodemon --save-dev
```

Esse comando adicionará o nodemon ao seu package.json, mas como uma dependência que será usada apenas durante o desenvolvimento, conforme ilustrado abaixo:
<img width="489" height="224" alt="image" src="https://github.com/user-attachments/assets/6ddd4fa7-0b6a-4c33-8c34-130b44dc473d" />

Agora, abra o arquivo package.json e adicione um script para iniciar o servidor usando o Nodemon. Localize a seção "scripts" e adicione o script "dev" para rodar o servidor com o nodemon:
```
...
"scripts": {
        "test": "echo \"Error: no test specified\" && exit 1",
        "start": "node server.js", 
        "dev": "nodemon server.js" 
},
...
```

### Iniciando o Servidor Web Local
Para executar o servidor usando o Nodemon, digite o seguinte comando:
```
npm run dev
```

Quando você rodar esse comando, o Nodemon iniciará o servidor e monitorará as mudanças feitas no código, reiniciando automaticamente o servidor quando ocorrerem alterações nos arquivos do projeto.
<img width="951" height="244" alt="image" src="https://github.com/user-attachments/assets/3da75fff-8df6-4b46-b886-b6733e31489e" />


## Testando o Servidor Web
Com o servidor em execução, você pode testar os endpoints usando diversas ferramentas, como o navegador, a linha de comando com o curl, uma extensão do Visual Studio Code, como o REST Client, 
ou uma ferramenta de desenvolvimento e teste de APIs, como o Postman ou o Insomnia. Abaixo estão breves descrições de cada uma:

 * **curl**: uma ferramenta de linha de comando para transferir dados de e para servidores usando vários protocolos, com destaque para HTTP e HTTPS. Com curl, é possível fazer requisições para URLs específicas e visualizar a resposta diretamente no terminal, sendo ideal para testar e interagir com APIs, fazer download de arquivos e verificar conexões com servidores.
 * **REST Client**: uma extensão para o Visual Studio Code que permite realizar requisições HTTP diretamente no editor, sem a necessidade de ferramentas externas como Postman ou Insomnia. As requisições podem ser escritas em arquivos .http ou .rest e testadas no próprio editor.
 * **Postman**: uma ferramenta gráfica amplamente utilizada para teste e desenvolvimento de APIs. Ela permite enviar requisições HTTP (GET, POST, PUT, DELETE, etc.) e visualizar as respostas de forma organizada, sem a necessidade de comandos de terminal como o curl.
 * **Insomnia**: semelhante ao Postman, Insomnia é uma ferramenta de código aberto para desenvolver, testar e depurar APIs. Ela permite enviar requisições HTTP e HTTPS, gerenciar ambientes e variáveis, automatizar testes e organizar projetos.

Neste contexto, vamos usar o REST Client para testar os endpoints desta aplicação. O uso da REST Client é bastante simples, mas, primeiro, busque pela extensão REST no VS Code:

 * Abra o VSCode.
 * Acesse o Marketplace de Extensões.
 * Pesquise por "REST Client" e clique em “Instalar” na extensão desenvolvida por Huachao Mao.

<img width="652" height="340" alt="image" src="https://github.com/user-attachments/assets/0a723434-d7a7-4b2c-96f4-ec1170de94d8" />

No plugin REST Client, cada requisição é organizada em seções simples dentro de um arquivo com extensão .http ou .rest.

Sua sintaxe básica é muito simples, como demonstrado a seguir:
```
### Request 1
[GET|POST] [REST API URL] # Request Line
[Request Headers]
[Request Body]
### Request 2
[GET|POST] [REST API URL]
[Request Headers]
[Request Body]    
```

A estrutura básica inclui a linha de requisição, que define o método HTTP (como GET ou POST) e a URL da API; seguida pelos cabeçalhos, que fornecem informações adicionais (como autenticação) 
em formato Chave: Valor; e, por fim, o corpo da requisição, usado para enviar dados em métodos como POST e PUT, geralmente em formato JSON. Separadas por ###, 
as requisições são independentes e podem ser executadas individualmente, tornando o teste de APIs prático e direto no Visual Studio Code.

Neste contexto, crie uma pasta nome “request” em seu projeto .Em seguida, dentro desta pasta, crie um arquivo com o nome de sua preferência com a extensão “.rest”, por exemplo, testes.rest.

**Obs**: No exemplo foi utilizado o nome "request", mas você pode usar o nome que desejar, o importante é que seja um padrão dentro da equipe de desenvolvimento.
Pronto! A seguir, vamos testar os diferentes endpoints de nossa aplicação.


Endpoint GET /api/produtos
No arquivo testes.rest, insira a seguinte requisição para o endpoint GET /api/produtos:
```
### Endpoint GET /api/produtos
GET http://localhost:3000/api/produtos
```

Após escrever a requisição, um link “Send Request” aparecerá acima dela. Clique nesse link para enviar a requisição. 
O REST Client enviará a requisição GET para o servidor Node.js que está rodando no endereço http://localhost:3000/api/produtos. 
A resposta do servidor será exibida no painel de saída do REST Client, mostrando o código de status HTTP e o corpo da resposta em JSON.

Se o servidor estiver funcionando corretamente, você deverá receber uma resposta 200 OK com a lista de produtos, como o exemplo abaixo:
```
HTTP/1.1 200 OK
Content-Type: application/json
Date: Wed, 06 Nov 2024 08:01:52 GMT
Connection: close
Content-Length: 79
    
[    
  {    
    "id": 1,
    "nome": "Produto A",
    "preco": 50
  },
  {   
    "id": 2,
    "nome": "Produto B",
    "preco": 30
  }
]
```

### Endpoint POST /api/produtos
No arquivo testes.rest, insira a seguinte requisição para o endpoint POST /api/produtos, definindo o “Content-Type” como “application/json” e um corpo JSON para o novo produto:
```
### Endpoint POST /api/produtos
POST http://localhost:3000/api/produtos
Content-Type: application/json
 
{
  "nome": "Produto C",
  "preco": 40.0
}
   
```

Aqui, estamos enviando um novo produto com "nome": "Produto C" e "preco": 40.0. Após escrever a requisição, um link “Send Request” aparecerá acima dela. Clique nesse link para enviar a requisição.
Se o produto for criado com sucesso, você deverá receber uma resposta 201 Created com o novo produto, incluindo um ID gerado.

```
HTTP/1.1 201 Created
Content-Type: application/json
Date: Wed, 06 Nov 2024 08:06:59 GMT
Connection: close
Content-Length: 89
    
{
    
      "message": "Produto criado",
      "produto": {
    
        "nome": "Produto C",
        "preco": 40,
        "id": 1730880419708
      }
}

```

### Endpoint PUT /api/produtos/{id}
No arquivo testes.rest, insira a seguinte requisição para o endpoint PUT /api/produtos/{id}, substituindo {id} pelo ID do produto que você deseja atualizar. 
Vamos usar 1 como exemplo. Defina o “Content-Type” como “application/json” e insira o corpo JSON com os dados atualizados do produto.

```
### Endpoint PUT /api/produtos/{id}
PUT http://localhost:3000/api/produtos/1
Content-Type: application/json
    
{
      "nome": "Produto Atualizado",
      "preco": 60.0
}  
```

Neste exemplo, estamos atualizando o produto com id: 1 para ter "nome": "Produto Atualizado" e "preco": 60.0. Após escrever a requisição, um link “Send Request” aparecerá acima dela. 
Clique nesse link para enviar a requisição.

Se a atualização for bem-sucedida, você deverá receber uma resposta 200 OK com a confirmação da atualização e o produto atualizado.

```
HTTP/1.1 200 OK
Content-Type: application/json
Date: Wed, 06 Nov 2024 08:10:18 GMT
Connection: close
Content-Length: 90
    
{
    
      "message": "Produto atualizado",
      "produto": {
    
        "nome": "Produto Atualizado",
        "preco": 60,
        "id": 1
      }
}
    
```

### Endpoint DELETE /api/produtos/{id}
No arquivo testes.rest, insira a seguinte requisição para o endpoint DELETE /api/produtos/{id}, substituindo {id} pelo ID do produto que você deseja excluir. Vamos usar 1 como exemplo.

```
### Endpoint DELETE /api/produtos/{id} DELETE http://localhost:3000/api/produtos/1
```

Após escrever a requisição, um link “Send Request” aparecerá acima dela. Clique nesse link para enviar a requisição. 
Se a exclusão for bem-sucedida, você deverá receber uma resposta 200 OK com uma mensagem confirmando que o produto foi deletado.

```
HTTP/1.1 200 OK
Content-Type: application/json
Date: Wed, 06 Nov 2024 08:14:56 GMT
Connection: close
Content-Length: 39
    
{
    
      "message": "Produto com ID 1 deletado"
}
    
```





