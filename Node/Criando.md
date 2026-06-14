## Primeira aplicação Node.js
Agora, você está pronto para criar sua primeira aplicação com Node.js. O Node.js oferece a capacidade de criar servidores web de maneira prática e eficiente, 
utilizando o módulo HTTP, que vem embutido. Neste exemplo, vamos construir um servidor básico que responde a solicitações HTTP.


### Configurar o Projeto
Primeiramente, vamos criar um diretório para o projeto. Assim, no terminal, crie uma nova pasta para o seu projeto e navegue até ela.
```
mkdir servidor-simples
cd servidor-simples

```

Em seguida, podemos criar um arquivo `package.json` para gerenciar o projeto. Este arquivo é essencial em projetos Node.js, pois armazena informações importantes sobre o projeto, 
como dependências, scripts e metadados. Logo, inicialize o projeto digitando:

```
npm init -y
```

Você pode, agora, abrir a aplicação no editor Visual Studio Code. Para isso, dentro do diretório servidor-simples digite o seguinte comando no terminal:
```
code .
```

Após a execução deste comando, o Visual Studio Code deverá abrir com a pasta raiz do seu projeto sendo acessada.
Agora, vamos criar o arquivo principal de nosso projeto, onde o código do servidor web será escrito. Desta forma, crie um arquivo chamado `server.js` com o seguinte código:

```
// Importa o módulo HTTP
const http = require('http');
// Define a porta do servidor
const PORT = 3000;
// Cria o servidor com uma única rota
const server = http.createServer((req, res) => {
  res.setHeader('Content-Type', 'text/plain');
  res.statusCode = 200;
  res.end('Bem-vindo ao nosso Servidor Node.js!');
});
// Inicia o servidor
server.listen(PORT, () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
}); 
```

Observe que:

 * O módulo HTTP nativo do Node.js é importado para criar o servidor.
 * O servidor é criado usando http.createServer() e configurado para responder com a mensagem padrão e o cabeçalho de conteúdo text/plain.
 * O servidor é iniciado e escuta na porta 3000, exibindo uma mensagem no console quando está pronto.


## Iniciando o servidor local
Neste passo, você inicializará um servidor local e executará o projeto em seu navegador. Digite a seguinte linha de comando no terminal:

```
node server.js
```

Ao executar esse script, você iniciará um servidor web local e executará o código do projeto, conforme figura abaixo:
<img width="1106" height="204" alt="image" src="https://github.com/user-attachments/assets/0f189fb2-7ddf-4bbc-8413-f977821ec9ea" />


### Testando o Servidor Web
Abra um navegador e acesse http://localhost:3000. Você verá a mensagem “Bem-vindo ao nosso Servidor Node.js!” exibida na tela, indicando que o servidor está funcionando.




