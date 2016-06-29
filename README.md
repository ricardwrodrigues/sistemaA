# Sistema A

O Sistema A, foi projetado para solucionar o problema de exibição das imagens que estão disponíveis via interface REST nas clinicas.
A solução foi não guardar as informações do API REST com uma configuração, mas um metadata contendo uri e parametros "chave/valor" e ExameImageUtil gerar  a url para essa consulta.

Não foram discutidas padronização de protocolos entre clinica e aplicação cloud.

Mas seria uma otima abordagem para utilizar um Message Broker https://www.rabbitmq.com/ o que tornaria mais facil a 
escabilidade e segurança e deixaria o sistema mais livre de ponto a ponto com diversos worker para fazer o parse da 
mensagem para aplicação cloud "Visualizador de exames". 

Consideramos que um paciente tem varios exames. 

O sistema esta baseado em testes e não existe uma interface para o usuário.

H2 para fins de teste e visualização do funcionamento.

mvn test  

