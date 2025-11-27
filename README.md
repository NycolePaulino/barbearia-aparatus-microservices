# 💈 Barbearia Aparatus - Arquitetura de Microsserviços

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2023-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Next JS](https://img.shields.io/badge/Next-black?style=for-the-badge&logo=next.js&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)

## 📖 Sobre o Projeto

Este projeto consiste na **refatoração completa** de um sistema monolítico de gestão de barbearia para uma **Arquitetura de Microsserviços** moderna, distribuída e escalável.

O objetivo foi desacoplar as regras de negócio em serviços independentes, permitindo maior facilidade de manutenção, deploy isolado e escalabilidade horizontal. O sistema permite que clientes visualizem barbearias, escolham serviços, agendem horários e realizem pagamentos online de forma segura.

---

## 👨‍💻 Dados dos Desenvolvedores

| Nome | RA / Matrícula | Curso |
| :--- | :--- | :--- |
| **Nycole Paulino Santos** | 2023.1.08.044 | Bacharel em Ciência da Computação |
| **Maria Luiza Alves Belarmino** | 2023.1.08.015 | Bacharel em Ciência da Computação |
| **Pedro Almeida** | 2022.1.08.045 | Bacharel em Ciência da Computação |

---

## 🏗️ Arquitetura da Solução

O backend foi dividido em domínios de negócio específicos, orquestrados por um API Gateway e um Service Discovery.

### Mapa dos Serviços

| Serviço | Porta Docker | Responsabilidade |
| :--- | :--- | :--- |
| **Discovery Server** (Eureka) | `8761` | Service Registry (Lista telefônica dos serviços). |
| **API Gateway** | `8080` | Ponto único de entrada, Roteamento e Load Balancer. |
| **Auth Service** | `8081` | Autenticação OAuth2 (Google) e Gestão de Usuários. |
| **Agendamento Service** | `8082` | Catálogo de Barbearias, Serviços e Reservas. |
| **Pagamento Service** | `8083` | Integração financeira com Stripe. |
| **MongoDB** | `27017` | Banco de dados NoSQL (Database per Service). |

### Fluxo de Execução
1. O **Frontend** envia requisições apenas para o **API Gateway (8080)**.
2. O **Gateway** consulta o **Eureka** para localizar a instância saudável do microsserviço desejado.
3. A requisição é roteada para o serviço (ex: `agendamento-service`).
4. Se necessário, o serviço valida o Token JWT através de chaves simétricas compartilhadas.

---

## 🚀 Funcionalidades Principais

* **Autenticação Social:** Login seguro utilizando Google OAuth2.
* **Catálogo Dinâmico:** Listagem de barbearias e serviços com imagens.
* **Database Seeding:** O sistema popula automaticamente o banco de dados com dados fictícios na primeira execução.
* **Agendamento:** Seleção de data e hora com verificação de disponibilidade em tempo real.
* **Pagamentos Online:** Geração de sessão de checkout real via **Stripe API**.
* **Meus Agendamentos:** Visualização de histórico e cancelamento de reservas.
* **Containerização:** Todo o backend roda em containers Docker orquestrados via Docker Compose.

---

## 🛠️ Stack Tecnológica

### Backend (Microsserviços)
* **Linguagem:** Java 17  
* **Framework:** Spring Boot 3.2.3  
* **Ecossistema Spring Cloud:**
  * Gateway  
  * Netflix Eureka  
  * Spring Security (OAuth2 & Resource Server)
* **Banco de Dados:** MongoDB (NoSQL)  
* **Build Tool:** Maven  
* **Docker:** Google Jib (Plugin para criação de imagens OCI sem Dockerfile)

### Frontend
* **Framework:** Next.js 14  
* **Linguagem:** TypeScript  
* **Estilização:** Tailwind CSS & Shadcn/ui  
* **HTTP Client:** Axios  

---

## ⚙️ Guia de Instalação e Execução

Siga os passos abaixo para rodar o projeto localmente.

### Pré-requisitos
* Docker Desktop instalado e rodando  
* Node.js v18+  
* Maven  
* JDK 17  

### Passo 1: Executar o Backend (Docker)

1. Abra o terminal na pasta raiz do backend:

```bash
cd barbearia-microservices
```

2. Compile o projeto e gere as imagens Docker com Jib:

```bash
mvn clean compile jib:dockerBuild
```

3. Suba todos os serviços com Docker Compose:

```bash
docker compose up -d
```

4. Acesse os serviços:
- **Eureka:** http://localhost:8761  
- **API Gateway:** http://localhost:8080  

---

## 📦 Passo 2: Executar o Frontend (Next.js)

1. Entre na pasta do frontend:

```bash
cd barbearia-frontend
```

2. Instale as dependências:

```bash
npm install
```

3. Rode o projeto:

```bash
npm run dev
```

O frontend ficará disponível em:

👉 **http://localhost:3000**

---

## 🧪 Testes

Cada microsserviço possui testes unitários e de integração utilizando:
- JUnit 5  
- Mockito  
- Testcontainers (para MongoDB em ambiente isolado)

---

## 🐳 Estrutura Docker Compose

Os serviços sobem com:
- Rede interna Docker
- Environment vars para comunicação entre microsserviços
- MongoDB individual por serviço

---

## 📌 Observações Importantes

- A aplicação utiliza **JWT assinado** com chave secreta compartilhada entre os serviços.  
- Os microsserviços são totalmente independentes e podem ser escalados separadamente.  
- Todas as imagens são geradas usando **Google Jib**, sem precisar de Dockerfile.

