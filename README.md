📚 School Library System (IFBA)
Repositório destinado ao desenvolvimento do sistema de gerenciamento de biblioteca escolar para a disciplina de Estrutura de Dados (2026.1). O projeto utiliza o padrão MVC e interfaces modernas desenvolvidas em JavaFX.


Membro,Papel Principal,Atribuições
Emanuel,Líder / Integração,"Estrutura Maven, Controllers, Navegação e Organização do Git."
Ana Clara,Persistência (DAO),Implementação das classes de acesso a dados (Repositórios).
Charles,Service (Negócio),Lógica de regras de negócio e validações.
Kaique,Full / Design,Implementação de Services, Views de login FXML e controller.
Maria Eduarda, ed e Persustencia(DAO) / Estrura da Fila De Prioridade
Indaia,Modelagem,Criação das entidades (POJOs) do sistema.
Nikolas,Design (Views),Construção das telas no Scene Builder.
Pedro,Design (Views),Construção das telas no Scene Builder.
Rodrigo,Design (Views),Construção das telas no Scene Builder.

📂 Repository (DAO):
Camada responsável por gerenciar a persistência em memória:
Ana Clara: LivroDAOLista, UsuarioDAOLista, TituloDAOLista, EmprestimoDAOLista e ReservaDAOLista
Maria Eduarda: ReservaDAOFilaDePrioridade.

📂 Estrututura de Dados (ed):
Camada por gerenciar as estrututas de dados
Ana Clara: ListaDinamica,NoDuplo, Listavel
Maria Eduarda: ReservaComparator.

📂 Service (Lógica) - Charles / Kaique
Lógica principal do sistema:
AuthService (Login e Segurança):
UsuarioService:
BibliotecarioService:

📂 Models (Entidades) - Indaia
Livro, Titulo, Usuario, Reserva, Emprestimo.

📂 View:
Views (resources/views): FXMLs criados por Nikolas, Pedro, Rodrigo e Kaique.
Kaique:
Pedro:

📂 Controller - Emanuel e Kaique
Controllers (controller/): Lógica das telas integrada por Emanuel.

Integração e revisão geral: Emanuel
