
![Apresentação_Logo_GUEST](https://github.com/user-attachments/assets/cc395e41-60b2-4da3-9e24-ec43f2c6b9eb)


<p align="center">
  <img
    src="https://img.shields.io/github/repo-size/P-E-N-T-E-S/BDGuest?style=flat"
    alt="Repository Size"
  />
  <img
    src="https://img.shields.io/github/languages/count/P-E-N-T-E-S/BDGuest?style=flat&logo=python"
    alt="Language Count"
  />
  <img
    src="https://img.shields.io/github/commit-activity/t/P-E-N-T-E-S/BDGuest?style=flat&logo=github"
    alt="Commit Activity"
  />
    <a href="LICENSE.md"
    ><img
      src="https://img.shields.io/github/license/P-E-N-T-E-S/BDGuest"![Apresentação_Logo_GUEST](https://github.com/user-attachments/assets/77b8d72a-6d22-4fa9-b014-f9ccd78d2529)
![Apresentação_Logo_GUEST](https://github.com/user-attachments/assets/acd63ef8-0db9-4133-9a9b-1be6cf97a8ee)
  /></a>
</p>


Este repositório contém o projeto de banco de dados desenvolvido para a **GUEST**, uma Startup de Pernambuco que visa melhorar o dia a dia de pessoas que comem em restaurantes e de seus donos e garçons, a GUEST Já participou do early stage do SEBRAE e atualmente esta participando do Startup NE

## 👀 Objetivo

O objetivo deste projeto é modelar e implementar um sistema de banco de dados eficiente para gerenciar as operações de um restaurante, de forma que o cliente possa saber como está o andamento dos seus pedidos, e os garçons não tenham dificuldades em seu trabalho, além de prover um BI completo para os gerentes do estabelecimento

## ⚙️ Funcionalidades do Banco de Dados

- **Gerenciamento de pedidos**: Os pedidos são visualizados pelos garçons e clientes, os clientes podem saber os status dos seus pedidos.
- **Controle de Estoque**: Ao realizar um pedido, os itens do estoque já são automaticamente reduzidos, caso esse pedido seja alterado ou cancelado sem que esteja pronto, os itens voltam ao estoque.
- **Relatório com BI**: Relatório completo que permite a visualização de rendimento dos garçons, evolução das vendas, produtos próximos a validade, etc....

## 📪 Estrutura do Projeto

O projeto está dividido nas seguintes seções:

1. **Modelagem de Dados**: Diagrama Entidade-Relacionamento (ERD) que descreve as tabelas e suas relações.
2. **Scripts SQL**: Scripts para criação e manipulação do banco de dados.
3. **Procedures e Triggers**: Funções automatizadas para garantir integridade e eficiência no gerenciamento de dados.
4. **Consultas SQL**: Exemplos de consultas otimizadas para extração de dados úteis ao negócio.

## 🚀 Como Rodar

### 🛠️ Pré-requisitos
- ☕ **Java 21** ou superior
- ⚙️ JDK instalado
- 🟢 Node instalado
- 🗄️ **Banco de Dados MySQL** configurado e rodando localmente ou em um servidor remoto

### 📂 Clone a aplicação no GitHub
Primeiro, clone o repositório do projeto para o seu ambiente local:
👉 [Repositório no GitHub](https://github.com/P-E-N-T-E-S/BDGuest)

```bash
git clone <Url do repositório>
cd <diretório do projeto>
```

## 🛢️ Configurar o Banco de Dados
Você precisa de um banco de dados configurado para rodar a aplicação. Caso ainda não tenha configurado, siga os passos abaixo:

1. 📂 **Acesse a pasta `database`.**
   - Na pasta, você encontrará dois arquivos:
     - **`schema.sql`**: Execute este arquivo para criar a estrutura do banco de dados.
     - **`Script_de_povoamento_Guest.sql`**: Execute este arquivo para inserir os dados iniciais e povoar as tabelas criadas.

## ▶️ Executando a Aplicação

### 📦 1. Subindo o container do banco
Primeiro você deve acessar a pasta `database` e abrir um terminal nela, primeiro verifique-se se o seu docker engine está rodando e execute o comando `docker compose up -d`, lembre de deixar esse terminal aberto até o fim da execução da aplicação
### 💻 2. Rodando no Terminal

### 2.1 MacOS ou Linux
1. Certifique-se de que o terminal esteja na pasta `restaurante_admin`.
2. Execute o comando para preparar o script:
   ```bash
   chmod +x mvnw
  
### Instale as dependências do projeto:
```bash
npm install
./mvnw clean install
```

### Inicie a aplicação:
```bash
./mvnw spring-boot:run
```
### 🖥️ 3. Rodando no IntelliJ IDEA
Abra o projeto na IDE.
Clique no ícone de play para rodar a aplicação.
A IDE cuidará de compilar e executar automaticamente. Certifique-se de que o arquivo .env está na pasta correta para evitar erros.

### 🌐 Acessando a Aplicação
Abra o navegador e digite o seguinte endereço: 
👉 http://localhost:8080/home

## 👩‍💻 TechSphere

<ul>
  <li>
    <a href="https://github.com/evaldocunhaf">Evaldo Galdino</a> - egcf@cesar.school 📩
  </li>
  <li>
    <a href="https://github.com/Nerebo">André Goes</a> - algc@cesar.school 📩
  </li>
</ul>

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/evaldocunhaf">
        <img src="https://avatars3.githubusercontent.com/evaldocunhaf" width="100px;" alt="Foto de Evaldo"/><br>
        <sub>
          <b>Evaldo Galdino</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Nerebo">
        <img src="https://avatars.githubusercontent.com/Nerebo" width="100px;" alt="Foto de André"/><br>
        <sub>
          <b>André Goes</b>
        </sub>
      </a>
    </td>
  </tr>
</table>


## License

[MIT](https://github.com/P-E-N-T-E-S/BDGuest/LICENSE)
