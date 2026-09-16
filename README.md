# Gerenciador de Estoque

Sistema desktop em Java para controle de estoque de alimentos: cadastro de produtos, registro de entradas e saídas, exclusão com verificação de validade e geração de relatórios em PDF.

Projeto desenvolvido como atividade prática do curso de Java no **SENAI**, aplicando POO, persistência com JDBC/MySQL, interface gráfica com Swing (NetBeans GUI Builder) e geração de relatórios com JasperReports.

## Funcionalidades

- **Login** com dois perfis de acesso: Administrador e Funcionário (senha protegida com hash BCrypt).
- **Cadastro de alimentos**: nome, categoria, quantidade, data de validade, fornecedor e preço.
- **Controle de estoque**: registro de movimentações de entrada e saída, com alerta de estoque baixo.
- **Consulta/exclusão**: lista alimentos com status de validade (vencido, próximo do vencimento, dentro do prazo).
- **Relatório em PDF**: movimentações de estoque filtradas por período, gerado com JasperReports.

## Tecnologias

- Java 21 (Swing)
- Maven
- MySQL 8
- JasperReports 7 (relatórios em PDF)
- BCrypt (hash de senha)
- NetBeans (GUI Builder / desenvolvimento)

## Pré-requisitos

- [JDK 21](https://adoptium.net/) instalado
- [MySQL Server 8](https://dev.mysql.com/downloads/mysql/) instalado e em execução
- [Maven](https://maven.apache.org/download.cgi) (ou usar o NetBeans, que já traz o Maven embutido)

## Passo a passo para rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/Reginaldo345/Projeto-java---Gerenciador-de-estoque.git
cd "Projeto-java---Gerenciador-de-estoque"
```

### 2. Criar o banco de dados

Execute o script `database/schema.sql` no seu MySQL. Ele cria o banco `projetojava`, as tabelas (`acesso`, `alimento`, `estoque`) e dois usuários de teste.

Pelo terminal:

```bash
mysql -u root -p < database/schema.sql
```

Ou abra o arquivo no MySQL Workbench e execute tudo.

### 3. Configurar a senha do banco

O `ConnectionFactory` conecta como `root` em `localhost`. Se o seu `root` **não tem senha**, não precisa fazer nada — funciona direto.

Se o seu `root` **tem senha**, defina uma variável de ambiente antes de rodar o projeto:

- Windows (PowerShell, permanente para o seu usuário):
  ```powershell
  [System.Environment]::SetEnvironmentVariable("MYSQL_ROOT_PASSWORD", "sua_senha_aqui", "User")
  ```
- Depois disso, feche e abra o terminal/IDE de novo para a variável valer.

### 4. Compilar e gerar o `.jar`

```bash
mvn clean package
```

Isso gera `target/ProjetoJava2-1.0-SNAPSHOT-jar-with-dependencies.jar`, já com todas as dependências dentro.

### 5. Rodar

```bash
java -jar target/ProjetoJava2-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Ou, pelo NetBeans: abra o projeto e rode a classe `GUI.TelaLogin`.

### 6. Login de teste

| Login | Senha    | Perfil        |
|-------|----------|---------------|
| admin | 12345678 | Administrador |
| func  | 12345678 | Funcionário   |

**Troque essas senhas** antes de usar o sistema com dados reais. Para gerar um novo hash BCrypt, use `org.mindrot.jbcrypt.BCrypt.hashpw("nova_senha", BCrypt.gensalt())` e atualize a coluna `senha` da tabela `acesso`.

## Estrutura do projeto

```
src/main/java/
├── DAO/            Acesso a dados (JDBC)
├── Factory/         Conexão com o banco
├── GUI/             Telas (Swing / NetBeans GUI Builder)
├── Modelo/          Classes de domínio (Alimento, Estoque)
└── img/             Ícones e imagens das telas
src/main/resources/
└── Relatorio/        Relatório JasperReports (.jrxml)
database/
└── schema.sql        Script de criação do banco
```

## Observação sobre as telas

As telas foram montadas no editor visual (drag-and-drop) do NetBeans, então cada uma tem um arquivo `.form` associado ao `.java`. Esses arquivos só são entendidos pelo NetBeans — o projeto compila e roda normalmente em qualquer IDE, mas a edição visual das telas só funciona no NetBeans.
