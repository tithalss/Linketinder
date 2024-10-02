package org.example.Services

import spock.lang.Specification
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class AuthenticationServiceTest extends Specification {

    Connection connectionMock = Mock(Connection)
    PreparedStatement preparedStatementMock = Mock(PreparedStatement)
    ResultSet resultSetMock = Mock(ResultSet)

    def "authenticate method should return userId if email and password match"() {
        given:
        def mockConnection = Mock(Connection)
        def mockPreparedStatement = Mock(PreparedStatement)
        def mockResultSet = Mock(ResultSet)

        // Simula a resposta do banco de dados com um ID de usuário
        mockConnection.prepareStatement(_) >> mockPreparedStatement
        mockPreparedStatement.executeQuery() >> mockResultSet
        mockResultSet.next() >> true
        mockResultSet.getInt("id") >> 1

        when:
        def result = AuthenticationService.authenticate("test@example.com", "password")

        then:
        result == 1
    }

    def "Deve retornar o ID do usuário ao autenticar com sucesso"() {
        given: "Email e senha válidos"
        String email = "test@example.com"
        String senha = "password123"

        // Configurando o comportamento do preparedStatement e resultSet
        connectionMock.prepareStatement(_) >> preparedStatementMock
        preparedStatementMock.executeQuery() >> resultSetMock
        resultSetMock.next() >> true
        resultSetMock.getInt("id") >> 1

        when: "O método authenticate é chamado"
        Integer userId = AuthenticationService.authenticate(email, senha)

        then: "O ID do usuário deve ser retornado"
        userId == 1
    }

    def "Deve retornar null quando a autenticação falha"() {
        given: "Email ou senha inválidos"
        String email = "invalid@example.com"
        String senha = "wrongpassword"

        // Configurando o comportamento do preparedStatement e resultSet
        connectionMock.prepareStatement(_) >> preparedStatementMock
        preparedStatementMock.executeQuery() >> resultSetMock
        resultSetMock.next() >> false

        when: "O método authenticate é chamado"
        Integer userId = AuthenticationService.authenticate(email, senha)

        then: "Null deve ser retornado"
        userId == null
    }

    def "Deve lidar com SQLException"() {
        given: "Ocorre uma exceção SQL durante a autenticação"
        String email = "test@example.com"
        String senha = "password123"

        connectionMock.prepareStatement(_) >> { throw new SQLException("Erro no banco") }

        when: "O método authenticate é chamado"
        Integer userId = AuthenticationService.authenticate(email, senha)

        then: "Null deve ser retornado devido à exceção"
        userId == null
    }
}
