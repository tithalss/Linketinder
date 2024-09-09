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

Desenvolvido por Thales de Paula Martins.
