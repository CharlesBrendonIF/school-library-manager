# 📚 School Library System (IFBA)

Repositório destinado ao desenvolvimento do sistema de gerenciamento de biblioteca escolar para a disciplina de **Estrutura de Dados (2026.1)**. O projeto utiliza o padrão **MVC** e interfaces modernas desenvolvidas em **JavaFX**.

---

## 👥 Papéis e Atribuições do Grupo

| Membro | Papel Principal | Atribuições |
| :--- | :--- | :--- |
| **Emanuel** | **Líder / Integração** | Estrutura Maven, Navegação entre telas, Organização do Git e Revisão Geral. |
| **Ana Clara** | **Persistência (DAO)** | Implementação das classes de acesso a dados e Estruturas Dinâmicas. |
| **Charles** | **Service (Negócio)** | Lógica de regras de negócio e validações do sistema. |
| **Kaique** | **Full / Design** | View e Controller de Login e Cadastro. |
| **Maria Eduarda** | **ED / DAO** | Estrutura da Fila de Prioridade e Lógica de Reservas. |
| **Indaia** | **Modelagem** | Criação das entidades (POJOs) do sistema. |
| **Nikolas** | **Design (Views)** | Construção das interfaces visuais no Scene Builder. |
| **Pedro** | **Design (Views)** | Construção das interfaces visuais no Scene Builder. |
| **Rodrigo** | **Design (Views)** | Construção das interfaces visuais no Scene Builder. |

---

## 🏗️ Divisão Técnica

### 📂 Estrutura de Dados (`ed`)
*Camada responsável por gerenciar as estruturas de dados customizadas:*
- **Ana Clara**: `ListaDinamica`, `NoDuplo`, `Listavel`.🟢 (Concluído)
- **Maria Eduarda**: `ReservaComparator` (Lógica de prioridade).🟢 (Concluído)

### 📂 Repository (DAO)
*Camada responsável por gerenciar a persistência em memória:*
- **Ana Clara**: `LivroDAOLista`, `UsuarioDAOLista`, `TituloDAOLista`, `EmprestimoDAOLista` e `ReservaDAOLista`.🟢 (Concluído)
- **Maria Eduarda**: `ReservaDAOFilaDePrioridade`.🟢 (Concluído)
- **Emanuel**: `DataBaseSeed`.🟢 (Concluído)

### 📂 Service (Lógica)
*Lógica principal do sistema:*
- **Charles**: `AuthService`, Login e Segurança.🟢 (Concluído))
- **Charles**: `UsuarioService`, Gestão de usuários.🟢 (Concluído)
- **Charles**: `BibliotecarioService`, Operações administrativas.🔵 (Em Revisão)

### 📂 Models (Entidades)
*Entidades base*
- **Indaia, Maria Eduarda**:`Livro`, `Titulo`, `Usuario`, `Reserva`, `Emprestimo`.🟢 (Concluído)
- **Emanuel**: `Bibiloteca`.🟢 (Concluído)

### 📂 Views (resources/views)
*Interfaces FXML desenvolvidas no Scene Builder:*
- **Kaique**: `AuthViews`.🟢 (Concluído)
- **Pedro**: `UsuarioViews`.🟢 (Concluído)
- **Kaique**: `BibliotecarioViews` 🟢 (Concluído)

### 📂 Controller
*Lógica das telas e integração:*
- **Kaique**: `AuthController`, Controle da tela de Login e Cadastro 🟢 (Concluído)
- **Pedro, e Emanuel**: `UsuarioController` e `CardsController`(Gostaria de ajuda) 🔵 (Em Revisão)
- **Kaique, e Nikolas**: `BibliotecarioController` 🟡 (Em Desenvolvimento)
  
---

## 🚀 Integração e Revisão Geral
**Responsável:** Emanuel (Líder)
*Garantia da coesão entre os pacotes e funcionamento do ciclo de vida da aplicação.*

---
*Este documento será atualizado conforme a contribuição de cada membro do grupo.*
