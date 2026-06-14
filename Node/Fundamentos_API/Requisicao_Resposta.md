## Requisições e Respostas
### Caracteristicas
O protocolo HTTP define o formato das mensagens de requisição e resposta que são trocadas entre o cliente e o servidor. Essas mensagens têm uma estrutura padrão que permite uma comunicação clara e organizada.


## Estrutura de uma Requisição HTTP
Uma requisição HTTP é composta por alguns elementos essenciais que permitem ao cliente especificar o que deseja do servidor e fornecer as informações necessárias para o processamento da requisição. 
Os principais componentes estão descritos a seguir.

### Métodos HTTP
Cada requisição HTTP utiliza um método que indica a ação desejada sobre o recurso (MARDAN, 2018; PEREIRA, 2016). 
Os métodos mais comuns — GET, POST, PUT e DELETE — representam operações de leitura, criação, atualização e exclusão, respectivamente. 
Estes métodos, que seguem o padrão CRUD (Create, Read, Update, Delete), permitem manipular dados de maneira clara e organizada em uma API. 
Por exemplo, ao fazer uma requisição GET, o cliente solicita dados sem alterar o estado do servidor, enquanto uma requisição POST envia dados para criar um novo recurso.

Resumidamente, os métodos HTTP mais comuns em APIs RESTful são:
 * **GET**: Solicita dados de um recurso específico. Não altera dados no servidor e é idempotente, ou seja, não importa quantas vezes seja chamada, o resultado é o mesmo.
 * **POST**: Envia dados para o servidor para criar um novo recurso. Diferente de GET, não é idempotente, já que cada requisição POST pode criar um recurso novo.
 * **PUT**: Atualiza um recurso existente no servidor com as informações fornecidas na requisição.
 * **PATCH**: Utilizado para atualizar parcialmente um recurso no servidor.
 * **DELETE**: Remove um recurso do servidor. Assim como GET, DELETE é idempotente.

Veja no quadro abaixo um exemplo de requisição GET:
```
GET /api/produtos
```

### URL (Uniform Resource Locator)
Especifica o caminho do recurso desejado, geralmente seguindo uma estrutura lógica. Por exemplo, uma URL para acessar detalhes de um usuário com ID 123 poderia ser https://api.exemplo.com/users/123.

### Cabeçalhos (Headers)
Os cabeçalhos são metadados incluídos na requisição usados para fornecer informações adicionais, como o tipo de conteúdo e parâmetros de autenticação.

Alguns cabeçalhos comuns incluem:

 * Content-Type: Indica o formato dos dados que estão sendo enviados ou esperados (como application/json).
 * Authorization: Utilizado para autenticar o cliente, como ao enviar um token de acesso.
 * Accept: Especifica o formato de resposta que o cliente deseja receber, como JSON ou XML.


### Corpo da Requisição (Body)
Contém os dados enviados ao servidor, utilizado especialmente com os métodos POST e PUT. Geralmente está em JSON para APIs RESTful. No exemplo de criação de um novo usuário, o corpo da requisição poderia ser:
```
{
    "nome": "João Silva",
    "email": "joao.silva@exemplo.com"
}
```

## Estrutura de uma Resposta HTTP
O servidor responde às requisições com uma estrutura padronizada que informa se a operação foi bem-sucedida, fornece dados ou explica um erro. Os principais componentes estão descritos a seguir, no decorrer desta aula.

### Código de Status
Indica o resultado da requisição, seguindo padrões numéricos definidos. Alguns códigos de status comuns incluem:

 * **200 OK**: A requisição foi bem-sucedida, e o servidor retorna os dados solicitados.
 * **201 Created**: Um novo recurso foi criado com sucesso (geralmente como resposta a um POST).
 * **204 No Content**: Recurso excluído com sucesso ou resposta sem conteúdo.
 * **400 Bad Request**: Dados da requisição inválidos (ex: campos obrigatórios).
 * **401 Unauthorized**: O cliente não está autenticado corretamente.
 * **404 Not Found**: O recurso solicitado não foi encontrado.
 * **500 Internal Server Error**: Ocorreu um erro no servidor ao processar a requisição.


### Cabeçalhos (Headers)
Contém os dados retornados pelo servidor, geralmente no formato JSON em APIs RESTful. Por exemplo, ao consultar um usuário, o corpo da resposta poderia ser:
```
{
    "id": 123,
    "nome": "João Silva",
    "email": "joao.silva@exemplo.com"
}
```

## Exemplo Completo de Requisição e Resposta
Imagine que o cliente deseja obter informações sobre um usuário específico. A requisição e a resposta poderiam ter a seguinte estrutura:
```
Requisição HTTP (GET)
GET /users/123 HTTP/1.1
Host: api.exemplo.com
Authorization: Bearer [token-de-autenticacao]
Accept: application/json
```

A requisição HTTP GET /users/123 HTTP/1.1 busca informações sobre o usuário com ID 123 na API localizada em api.exemplo.com. Utiliza o método GET, que é específico para leitura de dados, 
e inclui um token de autenticação no cabeçalho Authorization usando o tipo Bearer, permitindo que a API identifique e autorize o usuário. 
O cabeçalho Accept: application/json informa que o cliente deseja a resposta no formato JSON.
```
Resposta HTTP
HTTP/1.1 200 OK
Content-Type: application/json
Date: Tue, 05 Nov 2024 15:30:00 GMT
    
{
      "id": 123,
      "nome": "João Silva",
      "email": "joao.silva@exemplo.com"
}
    
```
Essa é uma resposta HTTP bem-sucedida, indicada pelo status HTTP/1.1 200 OK, que confirma que a solicitação foi processada corretamente. 
O cabeçalho Content-Type: application/json especifica que o corpo da resposta está no formato JSON, e Date indica a data e hora em que a resposta foi enviada. 
No corpo, temos os dados do usuário com ID 123, incluindo o nome ("João Silva") e o email ("joao.silva@exemplo.com")





