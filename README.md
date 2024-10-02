## Como Executar

1. **Certifique-se de ter o Groovy instalado.** Se ainda não tiver, você pode baixá-lo [aqui](https://groovy-lang.org/download.html) e seguir as instruções de instalação.

2. **Compile e execute o projeto:**
    - Navegue até o diretório do projeto no terminal.
    - Execute o comando `groovyc Main.groovy` para compilar a aplicação.
    - Execute o comando `groovy Main` para rodar a aplicação.

3. **Interaja com o Menu:**
    - O menu interativo exibirá opções para listar candidatos, listar empresas ou sair do programa.

## Modelo de Banco de Dados

O modelo de banco de dados foi criado utilizando o [dbdiagram.io](https://dbdiagram.io), uma ferramenta online para visualização e design de esquemas de banco de dados.

### Descrição do Modelo

- **Candidatos:** Armazena informações dos candidatos, incluindo nome, data de nascimento, email, cpf, entre outros.
- **Empresas:** Armazena informações das empresas, incluindo nome, cnpj, email, entre outros.
- **Competências:** Armazena as competências que podem ser associadas tanto aos candidatos quanto às vagas.
- **Vagas:** Armazena as vagas de emprego, associando-se a uma empresa.
- **Relacionamentos:**
  - **candidatos_competencias:** Relaciona candidatos e competências.
  - **vagas_competencias:** Relaciona vagas e competências.
- **Curtidas:**
  - **like_candidato:** Armazena o id do candidato e da vaga que ele curtiu
  - **like_empresa:** Armazena o id da empresa e do candidato que ela curtiu

### Lógica de match
##### Foram implementadas duas funções principais para verificar os matchs:
- `getMatchsForCandidate(idCandidato)` - Esta função consulta os matchs de um candidato específico. Ela busca todas as vagas que o candidato curtiu e verifica se a empresa dona da vaga também curtiu o candidato. Após isso todos os matchs, referentes ao candidato, são retornados.


- `getMatchsForCompany(idEmpresa)` - Esta função consulta os matchs de uma empresa específica. Ela busca todos os candidatos que curtiram vagas da empresa e verifica se a empresa também curtiu esses candidatos. Após isso todos os matchs, referente a empresa, são retornados.

Desenvolvido por Thales de Paula Martins.
