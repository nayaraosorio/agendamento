# 🩺 Sistema de Agendamento de Consultas Médicas

API REST desenvolvida em **Java 21** e **Spring Boot**, aplicando os princípios da **Clean Architecture (Arquitetura Limpa)** e **Domain-Driven Design (DDD)** para o fluxo de agendamento de consultas em clínicas e consultórios médicos.

---

## 🏛️ Arquitetura e Decisões de Design

O projeto foi construído seguindo a **Clean Architecture**, garantindo total independência de frameworks na camada de negócio e facilitando a testabilidade e manutenção.

```text
src/main/java/com/clinica/agendamento/
├── domain/                      # 1. Domínio: Entidades e Enums em Java Puro
│   ├── entity/ (Consulta)
│   └── enums/ (StatusConsulta, TipoAtendimento, ModalidadeAtendimento)
│
├── application/                 # 2. Aplicação: Casos de Uso, Portas (Gateways) e DTOs
│   ├── usecases/ (AgendarConsultaUseCase)
│   ├── gateways/ (ConsultaRepositoryGateway)
│   └── dto/ (Records de Entrada e Saída)
│
└── infrastructure/              # 3. Infraestrutura: Banco de Dados, JPA e Spring
    ├── config/ (UseCaseConfig com @Bean)
    └── persistence/
        ├── entity/ (ConsultaJpaEntity)
        ├── repository/ (ConsultaJpaRepository com Spring Data)
        └── gateway/ (ConsultaRepositoryGatewayImpl)
```

### 🧠 Principais Padrões Utilizados:
* **Princípio da Inversão de Dependência (DIP):** O caso de uso depende apenas de uma interface (`ConsultaRepositoryGateway`), mantendo as regras de negócio desacopladas do banco de dados.
* **Modelo Rico (*Rich Domain Model*):** A entidade `Consulta` protege suas próprias regras (validação de datas no futuro, campos obrigatórios e transições de status válidas).
* **Java 21 Moderno:** Utilização de `record` para DTOs imutáveis, `LocalDateTime` e `Optional`.
* **Configuração com `@Bean`:** Os casos de uso são registrados no ecossistema Spring através da classe de configuração (`UseCaseConfig`), sem a necessidade de poluir a camada de aplicação com anotações de framework.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Acesso a Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (em memória)
* **Gerenciador de Dependências:** Maven
* **Produtividade:** Lombok

---

## 📋 Regras de Negócio Implementadas

- [x] Uma consulta só pode ser agendada para datas e horários futuros.
- [x] Toda nova consulta nasce obrigatoriamente com o status `AGENDADA`.
- [x] O sistema valida se o médico já possui compromisso agendado para o mesmo horário antes de confirmar o agendamento.
- [x] Cancelamento de consulta exige a justificativa do motivo e só pode ser realizado em consultas agendadas.

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
* Java 21 instalado
* Maven instalado (ou utilizar o wrapper `./mvnw`)

### Passo a passo
1. Clone o repositório:
   ```bash
   git clone https://github.com/SEU-USUARIO/agendamento.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd agendamento
   ```
3. Compile e execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
4. A aplicação estará disponível em: `http://localhost:8080`

---

## 👩‍💻 Autora
Desenvolvido por **Nayara Osório**.
