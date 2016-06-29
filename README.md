# Sistema A
Teste-Java

O Sistema A, foi projetado para solucionar o problema de exibição das imagens que estão disponíveis via interface REST nas clinicas.
A solução foi não guardar as informações do API REST com uma configuração, mas um metadata contendo uri e parametros "chave/valor" e gerar a url para essa consulta.

Consideramos que um paciente tem varios exames. 

O sistema esta baseado em testes e não existe uma interface para o usuário.

H2 para fins de teste e visualização do funcionamento.

mvn test x

