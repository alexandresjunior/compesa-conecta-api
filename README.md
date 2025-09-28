<div align='center' style='margin-bottom: 10px;'>
   <img src='assets/imagens/compesa_conecta_logo_horizontal.png' width='200px'>
</div>

**COMPESA Conecta Backend API** é o serviço de backend que suporta o aplicativo móvel COMPESA Conecta, a rede social corporativa interna da COMPESA. Esta API é responsável por gerenciar dados de usuários, publicações, interações (curtidas, comentários, enquetes), FAQs e notificações push, fornecendo uma interface RESTful robusta e segura para o frontend mobile.

O projeto segue uma arquitetura **Model-Repository-Service-Controller** e implementa autenticação **JWT** com **Spring Security**.

## ✨ Funcionalidades da API

  * **Autenticação e Autorização**:
      * Login de usuários corporativos via JWT (JSON Web Tokens).
      * Controle de acesso baseado em nível de usuário para diferentes operações (Leitor, Publicador, Admin).
      * Registro de tokens de notificação push associados a usuários e dispositivos.
  * **Gestão de Usuários**:
      * Registro e atualização de informações de perfil de usuário.
      * Associações de níveis de acesso (1, 2, 3).
  * **Gestão de Publicações (CRUD)**:
      * Criação de posts de texto, imagem, vídeo e enquete.
      * Listagem de publicações com filtros e paginação.
      * Detalhes de publicações, incluindo mídias, comentários e resultados de enquetes.
      * Exclusão de publicações (restrito a administradores).
  * **Interações Sociais**:
      * **Curtidas**: Registrar e remover curtidas em publicações.
      * **Comentários**: Adicionar, listar e remover comentários em publicações.
      * **Enquetes**: Criar enquetes com múltiplas opções, registrar votos e exibir resultados em tempo real.
  * **Gestão de FAQs (CRUD)**:
      * Criação de novas perguntas e respostas.
      * Listagem de FAQs.
      * Exclusão de FAQs (restrito a administradores).
  * **Notificações Push**:
      * Recebimento e armazenamento de tokens de notificação push de dispositivos móveis.
      * Endpoint para acionar o envio de notificações push via Expo Push API (para novas publicações/FAQs).

## 🚀 Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias e bibliotecas principais:

  * **Spring Boot**: Framework principal para o desenvolvimento da API.
  * **Spring Data JPA**: Para abstração e persistência de dados com o banco de dados.
  * **Hibernate**: Implementação JPA padrão.
  * **Spring Security**: Para autenticação e autorização, incluindo JWT.
  * **JWT**: Biblioteca para geração e validação de JSON Web Tokens.
  * **Lombok**: Para reduzir boilerplate code (getters, setters, construtores, etc.).
  * **Maven**: Gerenciador de dependências do projeto.
  * **Oracle Database**: Banco de dados relacional para persistência dos dados.
  * **Validation (Jakarta Bean Validation)**: Para validação de dados em DTOs.

## ⚙️ Começando

Siga as instruções abaixo para configurar e executar o projeto em seu ambiente de desenvolvimento local.

### Pré-requisitos

  * Java Development Kit (JDK) 17 ou superior.
  * Apache Maven 3.x.x.
  * Um servidor de banco de dados Oracle em execução e acessível.
  * Um IDE compatível com Spring Boot (IntelliJ IDEA, VS Code com extensões Java, Eclipse).

### Configuração do Banco de Dados

1.  **Crie um esquema/usuário no Oracle Database** para o aplicativo COMPESA Conecta.

2.  **Configure as credenciais do banco de dados** no arquivo `application.yml`:

    ```properties
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE # Substitua pelo seu host/porta/SID
    spring.datasource.username=seu_usuario_oracle
    spring.datasource.password=sua_senha_oracle
    spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

    # Configurações do Hibernate (para desenvolvimento)
    spring.jpa.hibernate.ddl-auto=update # ou create, create-drop para desenvolvimento
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
    ```

    *Recomendação*: Para ambientes de produção, utilize `ddl-auto=validate` e uma ferramenta de migração de banco de dados como Flyway ou Liquibase.

### Propriedades JWT

Adicione as seguintes propriedades de segurança no seu `application.properties`:

```properties
api.security.token.secret.key=uma-chave-secreta-forte-aqui-troque-em-producao-com-mais-de-256-bits
api.security.token.issuer=COMPESA_CONECTA_API_ISSUER
```

**Importante**: `api.security.token.secret.key` deve ser uma string longa e complexa. Gere uma nova para produção.

### Instalação e Execução

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/compesa-conecta-backend.git
    cd compesa-conecta-backend
    ```

2.  **Construa o projeto com Maven:**

    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação:**

    ```bash
    mvn spring-boot:run
    ```

    A API estará acessível em `http://localhost:8080` (ou a porta configurada).

## 📁 Estrutura do Projeto

A estrutura de pacotes e classes segue o padrão Model-Repository-Service-Controller:

```
/src/main/java/br/com/compesa/conecta/api
|-- controller/           # Camada de Controllers REST (endpoints da API)
|   |-- AutenticacaoController.java
|   |-- PublicacaoController.java
|   |-- ComentarioController.java
|   |-- FaqController.java
|   `-- ... (e outros controllers)
|-- dto/                  # Objetos de Transferência de Dados (Requisições e Respostas)
|   |-- LoginDTO.java
|   |-- TokenDTO.java
|   |-- PublicacaoDTO.java
|   |-- CriarPublicacaoDTO.java
|   `-- ... (e outros DTOs)
|-- model/                # Entidades JPA (representam tabelas do banco de dados)
|   |-- Usuario.java
|   |-- Publicacao.java
|   |-- Midia.java
|   |-- Comentario.java
|   |-- Curtida.java
|   |-- Enquete.java
|   |-- OpcaoEnquete.java
|   |-- VotoEnquete.java
|   |-- Faq.java
|   `-- DispositivoPush.java
|-- repository/           # Interfaces Spring Data JPA para acesso ao banco de dados
|   |-- UsuarioRepository.java
|   |-- PublicacaoRepository.java
|   |-- ComentarioRepository.java
|   |-- FaqRepository.java
|   `-- ... (e outros repositories)
|-- service/              # Camada de lógica de negócio e regras de validação
|   |-- AutenticacaoService.java
|   |-- TokenService.java
|   |-- PublicacaoService.java
|   |-- ComentarioService.java
|   |-- FaqService.java
|   `-- ... (e outros services)
|-- security/             # Configurações de segurança do Spring Security e filtros JWT
|   |-- SecurityConfigurations.java
|   `-- SecurityFilter.java
|-- exception/            # Classes para tratamento de exceções customizadas (opcional)
`-- CompesaConectaApiApplication.java # Classe principal da aplicação
```

## 🔐 Autenticação e Autorização (JWT)

  * **Login**:
      * Endpoint: `POST /login`
      * Requisição: `{ "email": "usuarioderede", "senha": "suasenha" }`
      * Resposta: `{ "token": "eyJhbGciOiJIUzI1Ni..." }`
  * **Requisições Protegidas**: Após o login, o token JWT deve ser incluído em todas as requisições subsequentes no cabeçalho `Authorization` no formato `Bearer <token>`.
      * Ex: `Authorization: Bearer eyJhbGciOiJIUzI1Ni...`
  * **Níveis de Acesso**: As permissões são gerenciadas pelo `nivelAcesso` do usuário e aplicadas na camada de `Service` e `Controller` para controlar o acesso às operações de CRUD.

## 🗄️ Modelo de Entidade-Relacionamento (MER)

Este modelo descreve as principais entidades do sistema, seus atributos e como elas se relacionam entre si, servindo como base para a estrutura do banco de dados.

### Entidades e Atributos

**1. Usuario (Colaborador)**
Representa cada colaborador da COMPESA que pode acessar o sistema.

  * `id` (PK): Identificador único do usuário (ex: NUMBER, BIGINT).
  * `nome` (VARCHAR2): Nome completo do colaborador.
  * `email_corporativo` (VARCHAR2, Unique): E-mail de login.
  * `senha` (VARCHAR2): Senha criptografada.
  * `avatar_url` (VARCHAR2): URL para a foto de perfil do usuário.
  * `nivel_acesso` (NUMBER): Nível de permissão (1, 2 ou 3).
  * `data_criacao` (TIMESTAMP): Data e hora de criação do registro.

**2. Publicacao (Post)**
Representa uma postagem no feed.

  * `id` (PK): Identificador único da publicação.
  * `autor_id` (FK -\> Usuario.id): O usuário que criou a publicação.
  * `legenda` (CLOB/TEXT): O conteúdo de texto da publicação.
  * `tipo` (VARCHAR2): Enumeração para o tipo de post ('TEXTO', 'IMAGEM', 'VIDEO', 'ENQUETE').
  * `data_criacao` (TIMESTAMP): Data e hora de criação da publicação.

**3. Midia**
Armazena as URLs para as imagens e vídeos associados a uma publicação.

  * `id` (PK): Identificador único da mídia.
  * `publicacao_id` (FK -\> Publicacao.id): A publicação à qual esta mídia pertence.
  * `url` (VARCHAR2): O endereço (URL) do arquivo de imagem ou vídeo.
  * `tipo` (VARCHAR2): Enumeração ('IMAGEM' ou 'VIDEO').
  * `ordem` (NUMBER): Um número para ordenar as imagens em um carrossel (ex: 1, 2, 3...).

**4. Comentario**
Representa um comentário feito em uma publicação.

  * `id` (PK): Identificador único do comentário.
  * `publicacao_id` (FK -\> Publicacao.id): A publicação que foi comentada.
  * `autor_id` (FK -\> Usuario.id): O usuário que escreveu o comentário.
  * `texto` (VARCHAR2): O conteúdo do comentário.
  * `data_criacao` (TIMESTAMP): Data e hora de criação do comentário.

**5. Curtida (Tabela de Junção)**
Registra as curtidas que os usuários dão nas publicações.

  * `usuario_id` (PK, FK -\> Usuario.id): O usuário que curtiu.
  * `publicacao_id` (PK, FK -\> Publicacao.id): A publicação que foi curtida.
  * `data_criacao` (TIMESTAMP): Data e hora da curtida.

**6. Enquete**
Detalhes específicos de uma publicação do tipo "ENQUETE".

  * `id` (PK): Identificador único da enquete.
  * `publicacao_id` (FK -\> Publicacao.id, Unique): Vínculo com a publicação base.
  * `pergunta` (VARCHAR2): A pergunta da enquete.

**7. OpcaoEnquete**
Representa uma das opções de resposta de uma enquete.

  * `id` (PK): Identificador único da opção.
  * `enquete_id` (FK -\> Enquete.id): A enquete à qual esta opção pertence.
  * `texto` (VARCHAR2): O texto da opção de resposta.

**8. VotoEnquete (Tabela de Junção)**
Registra o voto de um usuário em uma opção de enquete.

  * `usuario_id` (PK, FK -\> Usuario.id): O usuário que votou.
  * `enquete_id` (PK, FK -\> Enquete.id): A enquete que foi votada.
  * `opcao_id` (FK -\> OpcaoEnquete.id): A opção escolhida pelo usuário.
  * `data_criacao` (TIMESTAMP): Data e hora do voto.
    *(Constraint: A combinação de `usuario_id` e `enquete_id` deve ser única para garantir um voto por usuário por enquete).*

**9. Faq (Perguntas Frequentes)**
Armazena os itens da seção de FAQ.

  * `id` (PK): Identificador único do item.
  * `pergunta` (VARCHAR2): A pergunta.
  * `resposta` (CLOB/TEXT): A resposta.
  * `autor_id` (FK -\> Usuario.id): O usuário que criou ou atualizou o item.
  * `data_criacao` (TIMESTAMP): Data de criação.
  * `data_atualizacao` (TIMESTAMP): Data da última atualização.

**10. DispositivoPush (Push Token)**
Armazena os tokens de notificação push para cada dispositivo de um usuário.

  * `id` (PK): Identificador único do registro.
  * `usuario_id` (FK -\> Usuario.id): O usuário dono do dispositivo.
  * `token` (VARCHAR2, Unique): O Expo Push Token.
  * `data_criacao` (TIMESTAMP): Data de registro do token.

### Diagrama Conceitual (Representação textual)

```
+-----------+ 1      N +--------------+
|  Usuario  |----------|  Publicacao  |
+-----------+          +--------------+
      | 1                    | 1
      |                      |
      | N                    | N
+-----v-----+          +-----v------+
| Comentario|          |   Midia    |
+-----------+          +------------+
      | 1
      +---------------------> (referencia Publicacao)

+-----------+ N      M +--------------+
|  Usuario  |----------|  Publicacao  | (através de Curtida)
+-----------+          +--------------+

+--------------+ 1      1 +-----------+
|  Publicacao  |----------|  Enquete  |
+--------------+          +-----------+
                                | 1
                                |
                                | N
                          +-----v--------+
                          | OpcaoEnquete |
                          +--------------+

+-----------+ N      M +--------------+
|  Usuario  |----------| OpcaoEnquete | (através de VotoEnquete)
+-----------+          +--------------+

+-----------+ 1      N +-----------+
|  Usuario  |----------|    Faq    |
+-----------+          +-----------+

+-----------+ 1      N +-----------------+
|  Usuario  |----------| DispositivoPush |
+-----------+          +-----------------+
```

## 🤝 Como Contribuir

Contribuições são o que tornam a comunidade de código aberto um lugar incrível para aprender, inspirar e criar. Qualquer contribuição que você fizer será **muito apreciada**.

1.  Faça um *Fork* do Projeto
2.  Crie sua *Feature Branch* (`git checkout -b feature/AmazingFeature`)
3.  Faça o *Commit* de suas alterações (`git commit -m 'Add some AmazingFeature'`)
4.  Faça o *Push* para a Branch (`git push origin feature/AmazingFeature`)
5.  Abra um *Pull Request*
