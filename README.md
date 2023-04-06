Estou enviando o projeto mas ele não está como eu queria!
Vou colocar alguns pontos:
    - Teria um README decente
    - Os repositories estão acoplados, o correto seria uma interface onde eles estão(Domain) e a implementação em interface
    - Minha idéia inicial era ter utilizado o JWT, porém fiquei sem tempo
    - Os testes não fiz todos, tbm fiquei sem tempo, mas deixei uma base de como eu faço
    - Normalmente utilizo fixtures nos testes
    - Queria tbm ter colocado a parte do usúario e na solicitação da proposta um BASE64, que seria a foto da formalização digital
    - Utilizei alguma Exceptions que possuem mais ou menos a msm semântica, mas o correto seria ter criado personalizadas

O fluxo que estava imaginando:
    - Usuário loga (utilizaria o JWT)
    - Confere a lista de produtos disponibilizados para ele (teria uma regra com a data de nascimento e o limite do cartão, só pra deixar mais dinâmico)
    - Seleciona um produto
    - É aprovado ou não

Fluxo final:
    - Confere lista de produtos disponíveis(todos cadastrados)
    - Seleciona um produto
    - É aprovado ou não