# JAVA

Java é uma linguagem de programação e também uma plataforma de desenvolvimento. Ela foi criada em 1995 pela Sun Microsystems (hoje pertencente à Oracle).

Plataforma **JAVA** é um ambiente computacional que permite o desenvolvedor cria programas para este ambiente utilizando a linguagem JAVA e um conjunto de ferramentas de desenvolvimento.

#### Os pontos principais para entender o que é o Java:

### 🔹 1. Linguagem de Programação
  * **Orientada a Objetos**: tudo gira em torno de classes e objetos.
  * **Sintaxe parecida com C/C++**: facilita para quem já conhece outras linguagens.
  * **Fortemente tipada**: você precisa declarar os tipos das variáveis.
  * **Independente de plataforma**: um programa em Java pode rodar em Windows, Linux, macOS, etc., sem precisar ser reescrito.

-----------------------------------------------------

### 🔹 2. Plataforma Java
Além da linguagem, o Java também é uma plataforma que inclui:
  * **JVM (Java Virtual Machine)**: é o ambiente que executa os programas Java.
  * **JRE (Java Runtime Environment)**: conjunto de bibliotecas e a JVM, necessário para rodar programas Java.
  * **JDK (Java Development Kit)**: inclui o JRE e também ferramentas para desenvolvimento (como compilador `javac`).

--------------------------------------------------------

### 🔹 3. O lema do Java

👉 *"Write Once, Run Anywhere"*
(Escreva uma vez, execute em qualquer lugar)
Isso significa que você escreve o programa em Java, compila para **bytecode** e a **JVM** cuida de rodar em qualquer sistema operacional que tenha suporte.

-------------------------------------------------------

### 🔹 4. Onde o Java é usado

  * **Aplicações web** (com frameworks como Spring Boot).
  * **Aplicações desktop**.
  * **Apps Android** (usando Java ou Kotlin).
  * **Sistemas corporativos** (bancos, governos, grandes empresas usam muito).
  * **Jogos e softwares embarcados**.

---------------------------------------------------------


```
public class (nome){
    public static void main(){
         System.out.Println("Ola, Mundo!");
    }
}

```

# Como o JAVA Funciona

### ⚙️ Como o Java funciona por dentro
#### 1. Código-fonte → Compilação
  * Você escreve um programa em Java num arquivo .java.
  * Esse código não vai direto para o sistema operacional.
  * Ele é compilado pelo javac (o compilador do Java).
Resultado da compilação → **bytecode** (`.class`).
Esse bytecode **não é código de máquina** (tipo 0 e 1), mas sim uma linguagem intermediária.

#### 2. Bytecode → JVM (Java Virtual Machine)
 * O bytecode gerado é enviado para a JVM.
 * A **JVM (Máquina Virtual Java)** é como um intérprete entre seu programa e o sistema operacional.
 * É por isso que o Java é **independente de plataforma**: basta que o computador tenha uma JVM instalada.

#### 3. Execução Just-in-Time (JIT)
 * Para não ficar lento, a JVM usa o **JIT Compiler (Just-In-Time)**.
 * O JIT traduz o bytecode em **código de máquina nativo** no momento da execução, deixando o programa rápido.
   
#### 4. Bibliotecas e APIs
 * O Java vem com uma **biblioteca padrão enorme** (Collections, rede, arquivos, banco de dados, etc.).
 * Você não precisa “reinventar a roda”: muita coisa já está pronta.

#### 5. Gerenciamento de Memória
 * Diferente de C/C++, no Java você **não precisa liberar memória manualmente**.
 * Existe o **Garbage Collector**, que detecta objetos não utilizados e libera memória automaticamente.

#### 6. Ambiente de Desenvolvimento
 * **JDK (Java Development Kit)** → para programar (inclui compilador, ferramentas, etc.).
 * **JRE (Java Runtime Environment)** → para rodar programas (bibliotecas + JVM).
 * **IDE (Ambiente de Desenvolvimento Integrado)** como Eclipse, IntelliJ ou VS Code pode ajudar, mas não é obrigatório.

📌 Resumindo:

**1.** Você escreve o código `.java`.
**2.** O compilador transforma em bytecode `.class`.
**3.** A **JVM** lê o bytecode e converte para o sistema operacional.
**4.** O programa roda em qualquer lugar que tenha JVM.

----------
👉 Exemplo simplificado do fluxo:

```scss
Código Java (.java) → Compilador (javac) → Bytecode (.class) → JVM → Sistema Operacional → Resultado

```


<img width="777" height="569" alt="image" src="https://github.com/user-attachments/assets/2825902a-5a7d-403b-b429-9225143abe78" />



# Ferramentas para Desenvolvimento

 * IntelliJ: https://www.jetbrains.com/pr-br/idea/
 * Eclipse: http://www.eclipse.org/
 * NetBeans: https://netbeans.apache.org/front/main/index.html
 * VSC: https://code.visualstudio.com/


---------------------------------------------------

Palavras reservadas


# Palavras Reservadas do Java

As palavras reservadas são identificadores que têm um **significado especial** na linguagem Java e **não podem ser usadas como nomes de variáveis, classes ou métodos**.

---

## ✅ Lista de Palavras Reservadas

### Controle de fluxo
- `if`
- `else`
- `switch`
- `case`
- `default`
- `for`
- `while`
- `do`
- `break`
- `continue`
- `return`
- `yield` *(Java 13+)*

### Modificadores de acesso
- `public`
- `private`
- `protected`

### Modificadores de classes, variáveis e métodos
- `abstract`
- `class`
- `extends`
- `final`
- `implements`
- `interface`
- `native`
- `new`
- `static`
- `strictfp`
- `synchronized`
- `transient`
- `volatile`

### Tratamento de exceções
- `try`
- `catch`
- `finally`
- `throw`
- `throws`

### Tipos primitivos
- `boolean`
- `byte`
- `char`
- `short`
- `int`
- `long`
- `float`
- `double`
- `void`

### Pacotes e importação
- `import`
- `package`
- `module`
- `open`
- `requires`
- `exports`
- `opens`
- `uses`
- `provides`
- `transitive`
- `to`
- `with`

### Objetos e referências
- `super`
- `this`
- `null`

### Outros
- `assert`
- `const` *(reservado, mas não usado)*
- `goto` *(reservado, mas não usado)*
- `enum`
- `instanceof`
- `record` *(Java 14+)*
- `var` *(Java 10+)*
- `sealed` *(Java 17+)*
- `permits` *(Java 17+)*

---

## ℹ️ Observações
- `const` e `goto` são **reservadas**, mas não têm uso no Java.  
- Algumas palavras foram adicionadas em versões mais recentes (ex.: `var`, `record`, `sealed`).  
- Literais como `true`, `false` e `null` **não são palavras reservadas**, mas são tratados como **constantes fixas da linguagem**.


### 🔹 Caracteres de Escape no Java

Os **caracteres de escape** são usados dentro de **strings e caracteres** (`String` e `char`) para representar símbolos que não podem ser escritos diretamente.
Eles começam com barra invertida (`\`).



| Escape | Significado                                                 |
| ------ | ----------------------------------------------------------- |
| `\n`   | Nova linha (line feed – quebra de linha)                    |
| `\r`   | Retorno de carro (carriage return)                          |
| `\t`   | Tabulação horizontal (TAB)                                  |
| `\b`   | Backspace (apaga o caractere anterior, na saída de console) |
| `\f`   | Form feed (avanço de página, pouco usado)                   |
| `\'`   | Aspas simples                                               |
| `\"`   | Aspas duplas                                                |
| `\\`   | Barra invertida (`\`)                                       |

 





