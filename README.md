# INF01120 Técnicas de Construção de Programas
### Semestre: 2020/2
#### Professor: Marcelo Soares Pimenta

## Descrição
O objetivo do trabalho é a definição, implementação, teste e depuração de um gerador de música a partir de texto.\
O software recebe um texto como entrada e gera um conjunto de notas correspondentes ao texto segundo alguns parâmetros (timbre, ritmo – na forma de Beats por Minuto ou BPM, etc). Os parâmetros são definidos através de um mapeamento de texto para informações musicais. Parte do mapeamento já está definida abaixo. O restante deve ser definido pelo grupo e documentado para conhecimento do professor.\

Texto            | Nota
---------------- | ------
letra a ou A     | Nota Lá
letra b ou B     | Nota Si
letra c ou C     | Nota Do
letra d ou D     | Nota Ré
letra e ou E     | Nota Mi
letra f ou F     | Nota Fá
letra g ou G     | Nota Sol
caractere espaço | silêncio ou pausa

Texto                                               | Ação
--------------------------------------------------- | --------------------------------------------------------------------------------------
Caractere +                                         | Aumenta volume para o DOBRO do volume atual
Caractere -                                         | Volume volta a ser volume default (o valor de início)
Qualquer outra letra vogal (O ou o, I ou i, U ou u) | Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, silêncio ou pausa
Caracteres T+                                       | Aumenta UMA oitava
Caracteres T-                                       | Diminui UMA oitava
Caractere ? OU caractere .                          | Toca uma nota aleatória (de A a G), randomicamente escolhida
Caractere NL                                        | Trocar instrumento (A DEFINIR pelo grupo como escolher o instrumento)
Caracteres BPM+                                     | Aumenta BPM em 50 unidades
Caracteres BPM-                                     | Diminui BPM em 50 unidades
ELSE                                                | NOP
