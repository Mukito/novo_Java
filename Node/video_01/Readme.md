# 01

Dentro da pasta escolhida 
instala o packege e 
instala algumas Bibliotecas

```
yarn init -y
```

bibliotecas de Desenvolvimentos

```
yarn add -D typescript nodemon ts-node @types/express @types/node
```

bibliotecas de PRODUÇÂO

```
yarn add express pg typeorm dotenv reflect-metadata
```

### Dentro do arquivo "packege.json"

```
"scripts": {
    "dev": "nodemon --exec ts-node ./src/index.ts"
  },
```
