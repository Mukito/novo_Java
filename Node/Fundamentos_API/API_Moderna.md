## O que são APIs?
APIs (Application Programming Interfaces) são conjuntos de regras e protocolos que permitem a comunicação entre diferentes softwares ou sistemas (FREITAS et al., 2021). 
Em termos simples, uma API funciona como uma interface que possibilita que diferentes aplicações interajam e troquem informações entre si.

As APIs permitem que os desenvolvedores usem funcionalidades de outros serviços ou softwares sem precisar saber como eles são implementados internamente. 
Por exemplo, quando você usa um aplicativo de clima no seu celular, ele provavelmente está consumindo dados de uma API para mostrar as previsões meteorológicas (OLIVEIRA, 2021). 
O aplicativo envia uma requisição para uma API que fornece informações sobre o clima e recebe de volta os dados necessários para exibir ao usuário.

Neste contexto, a integração com APIs é crucial para obter dados dinâmicos de servidores e utilizá-los em aplicações React, 
permitindo criar interfaces mais interativas e conectadas a fontes de dados externas.

## Abordagens mais usadas para APIs
Existem várias abordagens para construir e utilizar APIs, cada uma com suas próprias características, vantagens e casos de uso (MARDAN, 2018; PEREIRA, 2016). As abordagens mais comuns são:
  * **APIs RESTful**: o REST (Representational State Transfer) é um estilo de arquitetura que utiliza padrões HTTP para comunicação entre sistemas. É uma das abordagens mais populares e amplamente utilizadas para construção de APIs. APIs RESTful são amplamente usadas em aplicações web e móveis para integração de dados e comunicação com back-ends.
  * **GraphQL**: é uma linguagem de consulta para APIs desenvolvida pelo Facebook que permite ao cliente solicitar exatamente os dados de que precisa, evitando o carregamento excessivo ou insuficiente de informações (que é comum em APIs RESTful). Ideal para aplicações que precisam de dados específicos de várias fontes, como aplicações com interfaces dinâmicas e complexas
  * **SOAP (Simple Object Access Protocol)**: é um protocolo de comunicação baseado em XML que define uma maneira padronizada para enviar mensagens entre sistemas. Utilizado em setores que precisam de segurança avançada, como serviços financeiros, telecomunicações e grandes empresas.
  * **gRPC (Google Remote Procedure Call)**: é um sistema de chamada de procedimento remoto (RPC) desenvolvido pelo Google. Ele facilita a criação de serviços distribuídos que podem se comunicar de maneira rápida e eficiente, utilizando chamadas de procedimento remoto (RPCs). O gRPC é ideal para comunicações de alto desempenho em sistemas distribuídos, como serviços backend em arquiteturas de microsserviços.
  * **WebSockets**: é uma tecnologia que permite comunicação bidirecional contínua entre um cliente e um servidor através de uma única conexão TCP. WebSockets são usados em aplicações onde a comunicação em tempo real é essencial, como aplicativos de mensagens instantâneas, notificações push e atualizações de dados em tempo real.

Segue abaixo um quadro comparativo entre estas abordagens:


| **Abordagem**	| **Características**	| **Vantagens**	| **Casos de Uso** |
|-----------|-----------------|-----------|--------------|
| REST	| Usa métodos HTTP e URLs para acessar recursos.	| Simples, escalável e amplamente adotado.	| Web, mobile apps, APIs abertas. |
| GraphQL	| Consulta precisa de dados com uma única requisição.	| Evita sobrecarga de dados e é flexível.	| Interfaces dinâmicas, aplicações com muitos dados. |
| SOAP	| Protocolo baseado em XML com regras estritas.	| Segurança robusta e confiável.	| Serviços financeiros, telecomunicações. |
| gRPC	| Comunicação binária com alta performance	| Rápido, eficiente e suporta streaming.	| Microsserviços, sistemas distribuídos. |
| WebSockets	| Conexão contínua para comunicação em tempo real.	| Reduz latência e é ideal para dados em tempo real.	| Chats, jogos online, sistemas de notificação. |



## API RESTful
APIs RESTful são um tipo de API que seguem os princípios do REST (Representational State Transfer), um estilo de arquitetura para comunicação entre sistemas CODEWELL, 2017; SKINNER, 2014). 
As APIs RESTful são amplamente utilizadas para conectar diferentes partes de um sistema, como o front-end e o back-end, de maneira padronizada e eficiente.

### Conceitos Fundamentais do REST
O REST é baseado em um conjunto de princípios que tornam a comunicação entre cliente e servidor mais intuitiva e estruturada:

 * **Arquitetura Cliente-Servidor**: O REST é projetado para ser independente entre cliente e servidor. O cliente (por exemplo, uma aplicação React) faz solicitações ao servidor, que responde com os dados apropriados. Essa separação permite que cada lado evolua de maneira independente.
 * **Stateless (Sem Estado)**: Cada requisição feita ao servidor deve conter todas as informações necessárias para ser entendida. O servidor não mantém um "estado" das interações anteriores. Isso significa que cada requisição é autossuficiente e não depende de requisições anteriores, o que simplifica o gerenciamento do sistema.
 * **Recursos Identificados por URLs**: Em APIs RESTful, os dados são tratados como recursos, que são identificados por URLs (Uniform Resource Locators). Por exemplo, se você estiver buscando informações sobre um usuário específico, a URL pode ser algo como https://api.exemplo.com/usuarios/123, onde 123 é o identificador do recurso.
 * **Uso de Métodos HTTP**: O REST utiliza métodos HTTP padrão para realizar operações sobre os recursos:
 * **GET**: Recupera informações de um recurso (por exemplo, obter detalhes de um produto).
 * **POST**: Cria um novo recurso (por exemplo, adicionar um novo usuário).
 * **PUT**: Atualiza um recurso existente (por exemplo, editar as informações de um produto).
 * **DELETE**: Remove um recurso (por exemplo, excluir um item do carrinho de compras).
 * **Representações de Dados**: As APIs RESTful geralmente retornam dados em formatos padronizados, como JSON (JavaScript Object Notation) ou XML, sendo o JSON o formato mais utilizado por sua simplicidade e compatibilidade com JavaScript.
 * **Conectividade via HTTP**: As APIs RESTful utilizam o protocolo HTTP, que é amplamente suportado em navegadores e sistemas de rede, facilitando a integração de aplicações web.

