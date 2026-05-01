# 📚 School Library System (IFBA)

Repositório destinado ao desenvolvimento do sistema de gerenciamento de biblioteca escolar para a disciplina de **Estrutura de Dados (2026.1)**. O projeto utiliza o padrão **MVC** e interfaces modernas desenvolvidas em **JavaFX**.

---

## 👥 Papéis e Atribuições do Grupo

| Membro | Papel Principal | Atribuições |
| :--- | :--- | :--- |
| **Emanuel** | **Líder / Integração** | Estrutura Maven, Navegação entre telas, Organização do Git e Revisão Geral. |
| **Ana Clara** | **Persistência (DAO)** | Implementação das classes de acesso a dados e Estruturas Dinâmicas. |
| **Charles** | **Service (Negócio)** | Lógica de regras de negócio e validações do sistema. |
| **Kaique** | **Full / Design** | Implementação de Services, View/Controller de Login e suporte em FXML. |
| **Maria Eduarda** | **ED / DAO** | Estrutura da Fila de Prioridade e Lógica de Reservas. |
| **Indaia** | **Modelagem** | Criação das entidades (POJOs) do sistema. |
| **Nikolas** | **Design (Views)** | Construção das interfaces visuais no Scene Builder. |
| **Pedro** | **Design (Views)** | Construção das interfaces visuais no Scene Builder. |
| **Rodrigo** | **Design (Views)** | Construção das interfaces visuais no Scene Builder. |

---

## 🏗️ Divisão Técnica

### 📂 Estrutura de Dados (`ed`)
*Camada responsável por gerenciar as estruturas de dados customizadas:*
- **Ana Clara**: `ListaDinamica`, `NoDuplo`, `Listavel`.🔵 (Em Revisão)
- **Maria Eduarda**: `ReservaComparator` (Lógica de prioridade).🔵 (Em Revisão)

### 📂 Repository (DAO)
*Camada responsável por gerenciar a persistência em memória:*
- **Ana Clara**: `LivroDAOLista`, `UsuarioDAOLista`, `TituloDAOLista`, `EmprestimoDAOLista` e `ReservaDAOLista`.🔵 (Em Revisão)
- **Maria Eduarda**: `ReservaDAOFilaDePrioridade`.🔵 (Em Revisão)

### 📂 Service (Lógica)
*Lógica principal do sistema:*
- **Charles**: `AuthService`, Login e Segurança.🟡 (Em Desenvolvimento)
- **Charles**: `UsuarioService`, Gestão de usuários.🟡 (Em Desenvolvimento)
- **Charles**: `BibliotecarioService`, Operações administrativas.🟡 (Em Desenvolvimento)

### 📂 Models (Entidades)
*Entidades base*
- **Indaia**:`Livro`, `Titulo`, `Usuario`, `Reserva`, `Emprestimo`.🔵 (Em Revisão)
- **Emanuel**: `Bibiloteca`, e `BibliotecaRepository`.🟢 (Concluído)

### 📂 View (resources/views)
*Interfaces FXML desenvolvidas no Scene Builder:*
- **Kaique**: `AuthViews`.🟡 (Em Desenvolvimento)
- **Pedro**: `UsuarioViews`.🟡 (Em Desenvolvimento)
- ****: `CardsViews`(Precisa de alguem) 🔴 (Procura-se Responsável)
- ** **: `BibliotecarioViews` 🔴 (Procura-se Responsável)

### 📂 Controller
*Lógica das telas e integração:*
- **Kaique**: `AuthController`, Controle da tela de Login e Cadastro 🟡 (Em Desenvolvimento)
- **Emanuel**: `UsuarioController` e `CardsController`(Gostaria de ajuda) 🟡 (Em Desenvolvimento)
- ** **:`BibliotecarioController` 🔴 (Procura-se Responsável)
  
---

## 🚀 Integração e Revisão Geral
**Responsável:** Emanuel (Líder)
*Garantia da coesão entre os pacotes e funcionamento do ciclo de vida da aplicação.*

---
*Este documento será atualizado conforme a contribuição de cada membro do grupo.*
