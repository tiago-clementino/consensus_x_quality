<center>

# An&aacute;lise do consenso como indicativo de qualidade em f&oacute;runs MOOC <!--{style=text-align:center}-->

</center>
<br/><br/><br/>

#### Resumo <!--{style=text-align:justify}-->
Discuss&otilde;es em chats e f&oacute;runs de plataformas de ensino online, como aquelas para Massive Open Online Couses - MOOC, servem para construir conclus&otilde;es consensuais que agreguem valor pedag&oacute;gico. O estudo da correla&ccedil;&atilde;o entre consenso e a qualidade da conclus&atilde;o em discuss&otilde;es online tem recebido pouca aten&ccedil;&atilde;o. Este artigo usa *Soft Consensus* - quando h&aacute; grada&ccedil;&atilde;o do consenso, desde nenhum at&eacute; unanimidade - para apresentar um estudo estat&iacute;stico onde tal correla&ccedil;&atilde;o &eacute; medida a partir de dados de f&oacute;runs de MOOC e o apoio dos instrutores &agrave; conclus&atilde;o consensual &eacute; o indicador de qualidade. Resultados preliminares apontam uma correla&ccedil;&atilde;o negativa.

<br/><br/><br/>

## Introdu&ccedil;&atilde;o

Fóruns de discussão e debates online são importantes elementos de suporte ao aprendizado, onde o debate oferece ao estudante maior clareza acerca de um dado tema. Em tais fóruns, quando uma discussão é aberta, é natural criar-se uma expectativa de conclusão, seja esta consensual ou definitiva. Diante da dificuldade de se alcançar conclusões definitivas em certos temas de debate, o consenso ganha força como instrumento de avaliação de qualidade da solução proposta. Aqui, "consenso" é definido como segue.

**Definição 1:** "Consenso" é uma medida de concordância entre as respostas ou opiniões de um grupo de indivíduos sobre determinada questão ou assunto. 

A definição 1 permite modelar de uma concordância parcial à unanimidade. A definição se baseia naquela de soft *consensus* [Herrera-Viedma et al. 2014] que acomoda incertezas.

Embora a pesquisa sobre consenso tenha evoluído e se adaptado aos domínios virtuais, com base em nossos levantamentos, não encontramos pesquisas que apontem se uma decisão consensual é de fato uma decisão de qualidade em ambientes de discussões online. Por conjectura, podemos apontar a dificuldade em se obter dados de discussões online para análise, cujas conclusões estejam classificadas em função da qualidade. Neste estudo, tal dificuldade foi contornada utilizando-se dados oriundos de fóruns de MOOCs (do inglês, *Massive Open Online Courses*), tomando o apoio do corpo de instrutores à conclusão consensual como parâmetro de qualidade.

Este repositório oferece o ferramental necessário a medir a correlação entre consenso e qualidade em discussões online a partir de uma base de dados de postagens de fóruns educacionais oriundos de 12 MOOC diferentes oferecidos pela Universidade de Stanford (www.stanford.edu) na plataforma edX (www.edx.org). O consenso entre estas postagens em cada discussão foi calculado com base em três diferentes métricas de distância textual para avaliar o ruído introduzido por tais métricas.

Após conhecer o consenso e a qualidade das discussões por toda a base de dados, segui-se à análise estatística de tais dados. Para tanto, a pasta ``analysis`` oferece o ferramental necessário - além de uma breve descrição - para um estudo preliminar envolvendo intervalo de confiança e *Bootstraping* a partir da média de apoio dos instrutores à decisão consensual e outro mais aprofundado com base em Regressão Logística. Com isto, pode--se observar indícios de que o consenso tem efeito negativo na qualidade da decisão.

## Os Dados

Os dados utilizados nesta pesquisa estão disponíveis na pasta ``data`` e são fruto de discussões em fóruns de 12 MOOC ofertados em Inglês pela Universidade de Stanford na plataforma edX entre 2013 e 2015. Tais MOOC abrangiam três áreas do conhecimento: Ciências Humanas, Medicina e Educação; totalizando 29.604 postagens agrupadas em 22.804 discussões. As discussões podem ainda ser divididas do seguinte modo: 20.268 tendo apenas uma postagem; 2.208 tendo entre 2 e 5 postagens; 179 tendo entre 6 e 10 postagens; e 48 com mais de 11 postagens. Aqui, apenas as discussões com pelo menos duas postagens foram utilizadas.

### Tratamento manual

Os dados foram utilizados originalmente por [Agrawal et al. 2015]. Por razões éticas, Agrawal et al filtraram todas as referências nominais aos autores de cada postagem, preservando assim seu anonimato. Em seguida, eles ponderaram manualmente as postagens dos estudantes por urgência ![1,7](https://drive.google.com/uc?export=view&id=1nVZLseWPjrS8a0cMwzn4ivCkxMZVrDc7) - i.e., a faixa de números inteiros ![\geq 1](https://drive.google.com/uc?export=view&id=1M7DRBvT_rGTnUrgNoi0EgITiMBFUDq34) e ![\leq 7](https://drive.google.com/uc?export=view&id=1Su7S9cqx22IgWGslm7hPPN6pnawyuX2w), onde 1 representa a menor urgência e 7, a maior; positividade ![1,7](https://drive.google.com/uc?export=view&id=1nVZLseWPjrS8a0cMwzn4ivCkxMZVrDc7); e, confusão ![1,7](https://drive.google.com/uc?export=view&id=1nVZLseWPjrS8a0cMwzn4ivCkxMZVrDc7). As postagens foram ainda classificadas como pergunta ![\{0,1\}](https://drive.google.com/uc?export=view&id=1MTvTb01YVPXAFrZewdasethfVLejjjpk) - não ou sim, respectivamente; resposta ![\{0,1\}](https://drive.google.com/uc?export=view&id=1MTvTb01YVPXAFrZewdasethfVLejjjpk); ou, opinião ![\{0,1\}](https://drive.google.com/uc?export=view&id=1MTvTb01YVPXAFrZewdasethfVLejjjpk). Deve-se notar que o campo "confusão" parece ter sido mensurado em escala invertida, medindo na verdade a "convicção" em cada postagem. 

Com os dados já tratados, classificamos manualmente cada postagem como sendo de autoria ou não de um instrutor ![\{0,1\}](https://drive.google.com/uc?export=view&id=1MTvTb01YVPXAFrZewdasethfVLejjjpk). Além disto, urgência, positividade e confusão (convicção) foram mapeados para a faixa de números decimais ![0,1](https://drive.google.com/uc?export=view&id=17F9kiFtvwRLh9JczHnODQGO7XcnU339l), afim de normalizar nossa análise.

### Tratamento Automático

Antes de efetuar os cálculos de consenso, algumas providências foram tomadas. Com o objetivo de melhorar o desempenho dos algoritmos de distância textual, sinais de pontuação foram substituídos por espaços em branco e todos os *tokens* (palavras, numerais, *links*, etc, desde que delimitados por espaços em branco) com dois caracteres ou menos, foram eliminados. Em seguida foi aplicada uma estratégia de remoção de *stop words* (palavras com pouca informação semântica como "de", "para", "por", etc) a partir da biblioteca Java Opennpl (opennlp.apache.org) da Apache Foundation (www.apache.org).

## Mensurando Consenso

Como já adiantando quando da Definição 1, *Soft Consensus* é um conceito onde o consenso é definido a partir de modelos capazes de assimilar incerteza [Herrera-Viedma et al. 2014]. Contudo, tal potencial diz respeito apenas a quantificadores linguísticos e Lógica Difusa. A maioria destes modelos segue metodologias rígidas divididas em rodadas de discussão e geralmente moderadas por um agente externo [Cabrerizo et al. 2015], embora haja alternativas mais flexíveis [Alonso et al. 2013]. Como estamos lidando com discussões já finalizadas e agentes dispersos no tempo, além de pouco comprometidos com o debate, mesmo metodologias mais flexíveis ainda necessitam de certos ajustes para se enquadrarem ao nosso propósito. Em face a isto, tomamos toda a discussão como sendo uma única rodada de debate e cada postagem como um agente individualizado. Como já mencionado, a base de dados utilizada já discrimina opiniões, perguntas e respostas. Deste modo, ainda tomamos as postagens discriminadas como "opinião" como as alternativas disponíveis para consideração. A distância textual entre uma postagem e cada uma das opiniões mede o nível de apoio que aquela postagem oferecia a cada opinião.

Métricas de distância textual podem introduzir ruído na análise, e de acordo com [Gomaa and Fahmy 2013] existem três classes principais de algoritmos de similaridade textual. Diante disto, analisou-se o consenso em função de três algoritmos de distância textual diferentes, um para cada classe, comparando os resultados. A primeira classe, e mais simplista delas, é a similaridade baseada em caracteres. Optamos por adotar a distância de edição ou distância de Damerau-Levenshtein, por ser a mais difundida. A segunda classe de algoritmos mede a distância semântica com base em Corpus textuais de vários idiomas, dentre eles o Inglês. Para esta, utilizou-se o algoritmo DISCO [Kolb 2008], por se tratar de uma tecnologia bem estabelecia e com implementação robusta. A terceira e ultima classe de algoritmos traz algoritmos baseados em redes semânticas de palavras e expressões. Para esta classe, aplicou-se um algoritmo baseado em Wordnet [Miller 1998], por dispor implementação bastante estável.

Nosso processo semi estruturado para cálculo de consenso segue uma sequência simples: Identificam-se as opiniões dentre as postagens de uma discussão; calcula-se a matriz de preferências comparando todas as opiniões dois a dois (conforme descrito na Seção *Soft Consensus*, a seguir); a partir da matriz de preferências, calculam-se similaridades entre tais preferências e ordenam-se as opiniões tanto no âmbito geral quanto para cada postagem individualizada; e, por fim, verifica-se o nível de consenso.

### Distância Textual

Extrair opiniões e preferências a partir de dados textuais é um problema que pode ser atacado utilizando algoritmos de distância textual. A seguir, selecionamos uma abordagem para cada classe de distância textual, conforme [Gomaa and Fahmy 2013], onde dividiu-se os algoritmos de distância textual em três classes distintas. Aplicamos três métricas de distância de classes diferentes para mensurar o ruído introduzido por tais métricas na análise.


**Damerau-Levenshtein:** A distância de Damerau-Levenshtein [Damerau 1964,Levenshtein 1966], também conhecida como distância de edição, é a diferença entre duas cadeias de caracteres. Tal diferença é medida em quantos caracteres precisam ser eliminados de ambas para que estas se tornem iguais, conforme Equação 1.

![Damerau-Levenshtein](https://drive.google.com/uc?export=view&id=1wVqEaX97AtCTcPCnUT8ksSwx6BfIEqjA)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1)

Sendo *a* e *b* duas cadeias de caracteres e as operações ![a-1](https://drive.google.com/uc?export=view&id=1EHjv7a6gpqekePp_BSmWdMMoa5Shh-kY) ou ![b-1](https://drive.google.com/uc?export=view&id=1VUH_oglSwm_2RGJKb_rOiSahF3iQMnnc) correspondem à eliminação de um caractere.


**DISCO:** DISCO [Kolb 2008] pode ser descrita como relação distributiva e semelhança distributiva, simultaneamente. Duas palavras são distributivamente semelhantes se possuem muitas palavras co-ocorrentes nos mesmos papeis sintáticos. Por outro lado, duas palavras são distributivamente relacionadas quando estão imersas no mesmo contexto, ou no mesmo conjunto livre de palavras co-ocorrentes. Neste sentido, DISCO não utiliza ferramentas sintáticas para correlacionar palavras (tal como uma *Wordnet*), porém faz aproximações livres de contexto de tais relações. Para tanto [Kolb 2008] utilizou métricas para mensurar informação mútua baseadas em [Lin 1998].

**Wordnet:** A Wordnet é uma base de dados textual semântico-sintática [Miller 1998]. Aqui palavras são separadas por grupos sintáticos como verbos, pronomes, advérbios e adjetivos, e agrupadas em conjuntos de sinônimos, os chamado *synsets*. Uma mesma palavra pode constar em mais de um *synset*. *Stop words* não estão incluídas. *Synsets* são conectados uns aos outros por relacionamentos semânticos dentro de seus grupos sintáticos e a força de relacionamento entre duas palavras (elementos destes *synsets*) é calculada em função destes relacionamentos.

### *Soft Consensus*

Em sistemas de apoio ao consenso, sobretudo em ambientes online, opiniões e preferências tendem a apresentar incertezas e imprecisões que tem sido abordadas usando a Lógica Difusa [Herrera-Viedma et al. 2014,  Cabrerizo et al. 2015]. Porém, diferenciando-se de discussões convencionais, os fóruns online agregam incerteza até mesmo no processo de discussão. Sendo assim, no âmbito deste estudo, é necessário fazer adaptações nos modelos estado da arte de suporte ao consenso com incerteza [Pérez et al. 2018]. No cálculo de consenso que propomos, postagens de alunos e instrutores estão definidas em ![P={p^1,... ,p^m}](https://drive.google.com/uc?export=view&id=1sCmtz3fnMI2aV0mpfUWfyY6LRKil3TE1), sendo *m* o total de postagens por discussão. Suas respectivas preferências são extraídas da proximidade textual entre cada postagem e comparações pareadas entre as opiniões disponíveis ![X={x^1,... ,x^n}](https://drive.google.com/uc?export=view&id=1z04CIXMGjzdgLv5EucYjOwxCo6Sc1lQJ), sendo *n* o total de opiniões por discussão. Por simplicidade, mas sem perda de generalidade, aqui assumem-se todos os pesos das postagens / opiniões de instrutores e alunos como iguais.

**Definição 2:** Tomando *X* como o conjunto de opiniões e *P* como o conjunto de postagens da discussão, a função ![\Delta^{-1}_{P,X}](https://drive.google.com/uc?export=view&id=1oATk1B2rf9TGyI0zbLrOBJS8dIWyIYEG) é definida como uma função de similaridade dada entre uma postagem ![p\in P](https://drive.google.com/uc?export=view&id=11_ChuHxUyOem1mvTND1BmBfpeixhGKI0) e uma opinião ![x\in X](https://drive.google.com/uc?export=view&id=1B8DnP_RpkyvJp7gI8yb0xLPgmbP7e-PT), sendo ![\Delta^{-1}_{P,X}: P\times X\to [0,1]](https://drive.google.com/uc?export=view&id=1jncK5Wnq433DyJSLFkjwEkQbosJChny5).

**Definição 3:** O conjunto de relações de preferência ![M=\{\mu^1,... ,\mu^u\}](https://drive.google.com/uc?export=view&id=17f2wnp7utAK58L9X6wdxYxMpkwhlMOSB), sendo *u* o total de preferências de uma dada discussão, pertence ao conjunto das relações ![\Delta^{-1}_{P,X}\times \Delta^{-1}_{P,X}](https://drive.google.com/uc?export=view&id=1zDijKwEj1LKMP1h_N4jCXHF1_RUOPvZh) e é definida por ![M: \Delta^{-1}_{P,X}\times \Delta^{-1}_{P,X}\to[0,1]](https://drive.google.com/uc?export=view&id=1ebN46g2OH3b91iFfHbwbLdgX8T0dRLUD).

Assim, podemos entender ![\mu^p_{i,j}\in M](https://drive.google.com/uc?export=view&id=1d6QKUlQ0V4jP0ku7fXwTBoaEnFkvd0yx) como a relação de preferência/precedência entre as opiniões ![x^i](https://drive.google.com/uc?export=view&id=1srFRq670QhL_hrhmX-sYm2zbm3gRc_Zs) e ![x^j](https://drive.google.com/uc?export=view&id=1K3JJFmBrkne6bD01VIfLsq9bwI1YtqIW) em função de *p*, sendo ![\mu^p_{i,j}=\mu(\Delta^{-1}_{p,x^i},\Delta^{-1}_{p,x^j})(\forall p\in P,~\forall x\in X,~\forall i,j\in \{1,..., n\})](https://drive.google.com/uc?export=view&id=1rsdkn7lee_g-qcAJKwOjyfgyHkfp50gc). Diante disto, para identificar o grau de consenso da discussão, devemos calcular quatro níveis de apoio - relação de preferência - entre o conjunto de postagens e o de opiniões disponíveis: 1) entre pares de opiniões; 2) entre opiniões e postagens; 3) entre postagens e opiniões; e, 4) geral [Pérez et al. 2018].

 - Nível 1: *Par de Alternativas -* Este nível consiste em gerar uma matriz de similaridade para cada par de postagens em relação a cada par de opiniões ![SP^{i,j}=(sp^{i,j}_{k,l}),\forall p^i,p^j\in P(i\neq j),\forall x^k,x^l\in X (k\neq l)](https://drive.google.com/uc?export=view&id=1ojKI8EBHPaSL-IATQqfU9lXTYNZq3Do1), conforme a equação: 

![sp^{i,j}_{k,l}=1-{|\mu^{p^i}_{k,l}-\mu^{p^j}_{k,l}|}](https://drive.google.com/uc?export=view&id=12Vcvnh1pEVzTSOPKW6isuS3KVoX_JxSf)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2)

 Forma-se, assim, uma matriz de quatro dimensões ![SP_{(m \times m)\times (n \times n)}](https://drive.google.com/uc?export=view&id=1D8Jqi3aEljDynjlxaPL61ru_shPHXFiY) comparando as preferências de todas as postagens duas a duas ![(m \times m)](https://drive.google.com/uc?export=view&id=1bZpTKC6WU8whxaRmyKiqhEu2bVSxrPjm) em função às preferências daquela postagem com relação à cada par de opiniões comparadas ![(n \times n)](https://drive.google.com/uc?export=view&id=1CrrQIJ4YcqrkbTgt2BhSdqMadD_H-YJL). Em seguida, com base em uma função de agregação determinada ![\Phi](https://drive.google.com/uc?export=view&id=1d09BCIsNBjfqrWWbxEPOEvl0MnXetL27), obtemos duas matrizes de preferência coletiva 1) comparando pares de opiniões agregando ![SP_{(m \times m)\times (n \times n)}](https://drive.google.com/uc?export=view&id=1D8Jqi3aEljDynjlxaPL61ru_shPHXFiY) por postagem
 
 ![sp_{k,l}=\Phi^i(\Phi^j(sp^{i,j}_{k,l})) \forall i,j\in \{1,...,m\}~|~i\neq j](https://drive.google.com/uc?export=view&id=1Wjz5OFUrcu_L7HOrIGkITnYPQsL6X9Ov)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3)
 
 e 2) pares de postagens agregando ![SP_{(m \times m)\times (n \times n)}](https://drive.google.com/uc?export=view&id=1D8Jqi3aEljDynjlxaPL61ru_shPHXFiY) por opinião
 
 ![sp^{i,j}=\Phi_k(\Phi_l(sp^{i,j}_{k,l}) \forall k,l\in \{1,...,n\}~|~k\neq l](https://drive.google.com/uc?export=view&id=152tWxP1PJFEzk9jIeQY4goXeSo2drx3o)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(4)
 
 Aqui usaremos a média harmônica como ![\Phi](https://drive.google.com/uc?export=view&id=1d09BCIsNBjfqrWWbxEPOEvl0MnXetL27); 

 - Nível 2: *Opiniões e Postagens -* Aqui é definido o nível de consenso para cada opinião ![cx_k,\forall k\in \{1,...,n\}](https://drive.google.com/uc?export=view&id=14CzC86PUHQ6izTUFrU4895dtosBTAgMF), calculado em função de todas as postagens *P* (já agregadas no nível anterior): 
 
 ![cx_k=\frac{\sum^n_{l=1,l\neq k}(sp_{k,l} + sp_{l,k})}{2(n-1)}](https://drive.google.com/uc?export=view&id=1TIJrHviPl1xETJL8n-MwLzYapNJUs1H5)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(5)
 
 - Nível 3: *Postagens e Opiniões -* Este nível de consenso é adotado apenas neste trabalho e cria uma matriz de similaridade de natureza oposta à do nível anterior. Aqui é definido a ordem de similaridade em função de todas as opiniões *X* (já agregadas no nível anterior) com relação à cada postagem ![cp_i,i\in\{1,...,m\}](https://drive.google.com/uc?export=view&id=1CGErFe2E_4QPg4e-M8C5NK5AlyYfoIBj), conforme a equação:
 
 ![cp_i=\frac{\sum^m_{j=1,j\neq i}(sp_{i,j} + sp_{j,i})}{2(m-1)}](https://drive.google.com/uc?export=view&id=1S143DBNir_OwbNDjcOTIqW9GpeUJMLiR)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(6)
 
 - Nível 4: *Geral -* O nível final de consenso *cg* é definido como
 
 ![cg=\frac{\sum^n_{k=1}cx_k}{n}](https://drive.google.com/uc?export=view&id=1QfNu-S4s3Ptq2MnY4St0aZvvZZIioGOE)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(7)
 
Em tendo realizado os cálculos destes quatro níveis, três resultados são especialmente úteis aqui: Com a matriz de preferências agregadas por opinião do Nível 2 podemos identificar qual opinião é mais forte; com a matriz de preferências agregadas por postagem do Nível 3 podemos contabilizar quais instrutores apoiam certa opinião (vide Equação 8); e o nível de consenso geral do Nível 4. Para entender melhor cada um destes resultados, observe no código fonte a rotina que os processa diretamente. Para tal, vide o método 'calculeConsensusGeneral', do tipo 'ForumThread', no arquivo '/consensus/src/main/java/mooc_forum_analytics/ForumThread.java'.

Para concluir a análise matemática, assumimos que a opinião apoiada pela maioria dos instrutores é a mais acertada. Seja ![a_{i}(x_{k})](https://drive.google.com/uc?export=view&id=10RXmQNGXuBPcyaREhhlA4PnDrOpn5F5D) uma função cuja imagem está definida no conjunto ![\{0,1\}](https://drive.google.com/uc?export=view&id=1MTvTb01YVPXAFrZewdasethfVLejjjpk)&nbsp;-&nbsp;tomar tal função como binária se justifica pois, geralmente, apenas um instrutor interfere na discussão, portanto apenas a opinião vencedora interessa&nbsp;-&nbsp;e que representa o apoio da i-ésima postagem de um instrutor à opinião ![x_{k},k\in \{1,2,...,n\}](https://drive.google.com/uc?export=view&id=1re-voUKBZYMVohiHbB4LHWK_oQXmGHUU). Temos então, que a qualidade da opinião ![x_{k}](https://drive.google.com/uc?export=view&id=1VhZ4HdL2GbDnAd2dEJDHYKmqYCy8lsDz) é definida por 

 ![q_{x_{k}}=\sum_{i=1}^{l}\frac{a_{i}(x_{k})}{l}](https://drive.google.com/uc?export=view&id=1KOg8U9DN_6Q987zDZYfHf3FzyZyjzXWZ)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(8)

Na Equação 8, *l* é a quantidade total de postagens de instrutores para uma certa discussão. Assim, quanto mais apoio dos instrutores, mais qualidade ![q\in 0,0;1,0](https://drive.google.com/uc?export=view&id=1WTW0ZEXwIuzKvhY2jN-7joUuNnBIat8K) agregada. Além disto, o conceito de consenso utilizado para esta análise está definido em ![0,0;1,0](https://drive.google.com/uc?export=view&id=1_wBzT7sXtLPnrsK4s4GjFiPOUjuJIiiX), porém acaba restrito a ![0,5;1,0](https://drive.google.com/uc?export=view&id=1ftPe57DPePFb5FehsqY0rfZqhwGhrfGt), sendo *0,5* o grau de consenso aleatório. Deve-se notar que o consenso abaixo de *0,5* para uma dada opinião significa que há outra opinião contrária acima de *0,5*. Para simplificar os cálculos, normalizamos o consenso em ![0,0;1,0](https://drive.google.com/uc?export=view&id=1_wBzT7sXtLPnrsK4s4GjFiPOUjuJIiiX).

#### *Soft Consensus*: Exemplo

Para aprofundar o entendimento a respeito do modelo de consenso utilizado, tomaremos como exemplo um cenário simples com seis postagens e apenas duas opiniões. Sendo ![x^1](https://drive.google.com/uc?export=view&id=1kBxOloy40W08uhhPyVElPH4NVG7Ur6nx) e ![x^2](https://drive.google.com/uc?export=view&id=1F0z6P6PurP4u38YkSiad_sWLbq2Ocys0) as opiniões disponíveis e ![p^1,~p^2,~p^3,~p^4,~p^5~e~p^6
](https://drive.google.com/uc?export=view&id=16hC-NMjW8GEcpFMNtARspMXzrN7fh-cA) as postagens, considere ![x^1](https://drive.google.com/uc?export=view&id=1kBxOloy40W08uhhPyVElPH4NVG7Ur6nx) e ![x^2](https://drive.google.com/uc?export=view&id=1F0z6P6PurP4u38YkSiad_sWLbq2Ocys0) como sendo as postagens ![p^1](https://drive.google.com/uc?export=view&id=1EtC4DX-ZD3iB4dWUj10wk2vKFDR8kzW9) e ![p^2](https://drive.google.com/uc?export=view&id=1QO5Gs9Kl5am13qN_NkbGz9p7-ieGcKth), respectivamente.

 - Nível 1: *Par de Alternativas -* Utilizando alguma das três métricas de distância textual, tomemos a seguinte matriz hipotética de similaridades entre opiniões e postágens
 
 ![P \times X = \begin{bmatrix}1,0 & 0,11\\0,11 & 1,0\\0,76 & 0,37\\0,78 & 0,32\\0,4 & 0,66\\0,89 & 0,18\end{bmatrix}](https://drive.google.com/uc?export=view&id=12qQAA2wQucSCZVW011XLZGhItRMYkJFT)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(S1)

Perceba que no nosso caso S1 é uma matriz de ![6\times 2~(m\times n)](https://drive.google.com/uc?export=view&id=18aG7dTtsk_qQsq1XCPLlPszPnbhogg6G), sendo seis postágens e duas opiniões disponível. A partir de S1, definiremos a matriz de preferências M1 com base e uma relação de preferência/precedência ![\mu^p_{i,j}](https://drive.google.com/uc?export=view&id=1TkXAu3qFL-XQBBlKf-J9ovZG8NMlN9xa) que compara das similaridades entre uma postagem *p* e duas opiniões ![x^i](https://drive.google.com/uc?export=view&id=1Qw5h7JtI_SeW49Tmq4uIlhVnQcN_EFLQ) e ![x^j](https://drive.google.com/uc?export=view&id=1pQNanxVBriTkoYpiOeVY5C4xJd-zmi6d). Para nosso exemplo utilizaremos o ![\mu^p_{i,j}](https://drive.google.com/uc?export=view&id=1TkXAu3qFL-XQBBlKf-J9ovZG8NMlN9xa) mais trivial possível, retornando sempre a similaridade entre *p* e ![x^i](https://drive.google.com/uc?export=view&id=1Qw5h7JtI_SeW49Tmq4uIlhVnQcN_EFLQ). Neste exemplo, M1 e S1 se equivalem, veja abaixo
 
 ![\mu^p_{i,j} = \overset{\mu^p_{1,2}\mu^p_{2,1}}{\begin{bmatrix}1,0 & 0,11\\0,11 & 1,0\\0,76 & 0,37\\0,78 & 0,32\\0,4 & 0,66\\0,89 & 0,18\end{bmatrix}}](https://drive.google.com/uc?export=view&id=1lhjNkTfwp8GVbqo4Hilr23K9R_IYwv34)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(M1)

Com isto podemos agora calcular SP, uma matriz que compara todas as preferências ![\mu](https://drive.google.com/uc?export=view&id=1nofYrnnjDg4CFtSTo2k0iQSMwwU2CWy-) entre si conforme Equação 2. No caso geral, esta matriz tem quatro dimenssões, mas para nosso exemplo (com apenas duas opiniões) três dimenssões são suficientes para visualizar ![SP_{(6\times 6\times 2)}](https://drive.google.com/uc?export=view&id=15vx6XZgQurU0Z3ZBsZmDZTqVzlL_HU9B). Para termos uma visualização plana, podemos  utilizar duas visualizações separadas, uma para ![sp^{i,j}_{1,2}](https://drive.google.com/uc?export=view&id=1sQaA2fzyU0EWbFWR1m3y4TIXpc1ffpQS) e outra para ![sp^{i,j}_{2,1}](https://drive.google.com/uc?export=view&id=1CYlm8emuFglj_QMB2IngQnUL_fSXOLFR), veja abaixo
 
 ![\begin{align*}sp^{i,j}_{1,2} &= \begin{bmatrix}- & 0,11 & 0,76 & 0,78 & 0,4 & 0,89 \\ 0,11 & - & 0,35 & 0,33 & 0,71 & 0,22 \\ 0,76 & 0,35 & - & 0,98 & 0,64 & 0,87 \\ 0,78 & 0,33 & 0,98 & - & 0,62 & 0,89\\0,4 & 0,71 & 0,64 & 0,62 & - & 0,51\\ 0,89 & 0,22 & 0,87 & 0,89 & 0,51 & -\end{bmatrix}\\sp^{i,j}_{2,1} &= \begin{bmatrix}- & 0,11 & 0,74 & 0,79 & 0,45 & 0,93 \\ 0,11 & - & 0,37 & 0,32 & 0,66 & 0,18 \\0,74 & 0,37 & - & 0,95 & 0,71 & 0,81 \\ 0,79 & 0,32 & 0,95 & - & 0,66 & 0,86\\0,45 & 0,66 & 0,71 & 0,66 & - & 0,52\\ 0,93 & 0,18 & 0,81 & 0,86 & 0,52 & -\end{bmatrix}\end{align*}](https://drive.google.com/uc?export=view&id=1_JVXqJFgHpI8G1fEQq4mEYkh4ije1Ix6)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(SP)

A partir de SP, com base em uma função de agregação ![\Phi](https://drive.google.com/uc?export=view&id=1d09BCIsNBjfqrWWbxEPOEvl0MnXetL27) (aqui usaremos a média aritmética), geraremos duas matrizes de preferências coletivas agregando SP, sendo a primeira em função das opiniões

 ![\begin{align*}\Phi^i(\Phi^j(sp^{i,j}_{1,2})))&=\Phi^i\circ\Phi^j\begin{bmatrix}- & 0,11 & 0,76 & 0,78 & 0,4 & 0,89 \\ 0,11 & - & 0,35 & 0,33 & 0,71 & 0,22 \\ 0,76 & 0,35 & - & 0,98 & 0,64 & 0,87 \\ 0,78 & 0,33 & 0,98 & - & 0,62 & 0,89\\0,4 & 0,71 & 0,64 & 0,62 & - & 0,51\\ 0,89 & 0,22 & 0,87 & 0,89 & 0,51 & -\end{bmatrix}=\Phi^i\begin{bmatrix}0,59\\0,34\\0,72\\0,72\\0,58\\0,68\end{bmatrix}=0,6\\ \Phi^i(\Phi^j(sp^{i,j}_{2,1})))&=\Phi^i\circ\Phi^j\begin{bmatrix}- & 0,11 & 0,74 & 0,79 & 0,45 & 0,93 \\ 0,11 & - & 0,37 & 0,32 & 0,66 & 0,18 \\0,74 & 0,37 & - & 0,95 & 0,71 & 0,81 \\ 0,79 & 0,32 & 0,95 & - & 0,66 & 0,86\\0,45 & 0,66 & 0,71 & 0,66 & - & 0,52\\ 0,93 & 0,18 & 0,81 & 0,86 & 0,52 & -\end{bmatrix}=\Phi^i\begin{bmatrix}0,6\\0,33\\0,72\\0,72\\0,6\\0,66\end{bmatrix}=0,6\end{align*}](https://drive.google.com/uc?export=view&id=1iV9cymZ9dOfncXDpLHTc7zbPnpubtnSm)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(SP1)
 
e a segunda matriz em função das postagens, veja abaixo
 
 ![\Phi_{1,2}(sp^{i,j}_{1,2})=\begin{bmatrix}- & 0,11 & 0,75 & 0,78 & 0,42 & 0,91 \\ 0,11 & - & 0,36 & 0,32 & 0,68 & 0,2 \\ 0,75 & 0,36 & - & 0,96 & 0,68 & 0,84 \\ 0,78 & 0,32 & 0,96 & - & 0,64 & 0,88\\0,42 & 0,68 & 0,68 & 0,64 & - & 0,52\\ 0,91 & 0,2 & 0,84 & 0,88 & 0,52 & -\end{bmatrix}](https://drive.google.com/uc?export=view&id=1a4F52xwtXZN5yT-__stSlzqtRQZuN-by)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(SP2)

Note que ![\Phi_{1,2}(sp^{i,j}_{1,2})](https://drive.google.com/uc?export=view&id=1Uy67KbQOAy0by0u7dgUe2QqGDy9Lb1Lo) tem apenas uma agregação, isto foi possível graças ao fato de que SP tem só três dimenssões em função do nosso exemplo contar com apenas duas opiniões - a quata dimenssão é redundante (visto que a Equação 5 é modular, o resultado de *A-B* é o mesmo que o de *B-A*).

 - Nível 2: *Opiniões e Postagens -* Aqui é calculado o nível de consenso ![cx_k](https://drive.google.com/uc?export=view&id=1G1bAPCcD6UXxe7e7IKbsiOmOZXLFS0M7) para cada opinião. Para nosso exemplo, temos apenas duas opiniões disponíveis, portanto ![cx_k](https://drive.google.com/uc?export=view&id=1G1bAPCcD6UXxe7e7IKbsiOmOZXLFS0M7) fica restrito a aplicar a Equação 5 aos resultados acima (SP1), onde
 
 ![cx_1~\mbox{e}~cx_2=\frac{\sum^2_{l=1,l\neq k}(sp_{k,l} + sp_{l,k})}{2(2-1)}=\frac{(0,6+0,6)}{2}=0,6](https://drive.google.com/uc?export=view&id=1cGLVfVTDwLVCIHehxok7KdrV_mrR7v6L)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
 - Nível 3: *Postagens e Opiniões -* Aqui percorre-se o caminho oposto ao nível anterior definindo uma matriz de similaridade que ordena as opiniões para cada postagem conforme Equação 6. Veja conforme o exemplo
 
 ![cp_i=\frac{\sum^6_{j=1,j\neq i}(sp_{i,j} + sp_{j,i})}{2(6-1)}=\begin{bmatrix}0,59 \\ 0,33 \\ 0,72 \\ 0,72 \\0,59\\ 0,67\end{bmatrix}](https://drive.google.com/uc?export=view&id=1IVZh_AOGu6kUbFHoXSJWypXXSWFo0mXO)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
 - Nível 4: *Geral -* Agora calculemos o nível de consenso geral com base na Equação 7 
 
 ![cg=\frac{\sum^2_{k=1}cx_k}{2}=\frac{0,6+0,6}{2}=0,6](https://drive.google.com/uc?export=view&id=1cCPq809INVtwAFbuckXnP_6Ol0M9Hhvk)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
O consenso final foi 0,6; próximo ao aleatório; o que faz sentido; visto que os números utilizados foram escolhidos a esmo. Perceba ainda que até este ponto, identificar as postagens de instrutores não se mostrou necessário. Como o nível de consenso final é muito baixo - 0,6 - identificar as postagens de instrutores não se fez necessário. Porém, por hipótese, considere que a postagem ![p^4](https://drive.google.com/uc?export=view&id=1lJRDKJO0QDf8BSDMpeFDZB9OVsS_OBzZ) é de um instrutor.
 
 ![\begin{align*}q_{x_1}&=\sum_{i=1}^{1}\frac{a_{i}(x_{1})}{1}=\frac{a_{i}(0,78)}{1}=\frac{1}{1}=1\\ q_{x_2}&=\sum_{i=1}^{1}\frac{a_{i}(x_{2})}{1}=\frac{a_{i}(0,32)}{1}=\frac{0}{1}=0\end{align*}](https://drive.google.com/uc?export=view&id=1TalbambVF1Erb3yIOAJPcT1lhRE0dvbv)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 
Caso a hipótese ![x^1](https://drive.google.com/uc?export=view&id=1kBxOloy40W08uhhPyVElPH4NVG7Ur6nx) saisse vencedora, este exemplo contribuiria para concluirmos que há relação entre consenso e a opinião dos instrutores, caso ![x^2](https://drive.google.com/uc?export=view&id=1F0z6P6PurP4u38YkSiad_sWLbq2Ocys0) vencesse, este exemplo contribuiria para a conclusão oposta.

## Consenso vs. Qualidade

Com o intuito de medir o grau de certeza por parte do aluno em cada postagem, utilizamos o nível de convicção (ou, o inverso de confusão) médio das postagens, já computado, como um fator moderador em nossa análise. Além disto, o volume de postagens também pode ser fonte de imprecisão em discussões, por isso agrupamos as discussões em nossa base por número de postagens e analisamos tais grupos também separadamente. Agrupamos 4.671 postagens em discussões com número de postagens entre dois e cinco e 4.080 postagens em discussões com mais de seis postagens. Escolhemos estes números para, assim, dividir a base de dados em duas partes aproximadamente iguais. Por fim, estudamos as influências destes fatores em dois níveis de consenso diferentes: 0.8 (baixo) e 0.9 (alto), por serem mais recorrentes na literatura de consenso.

Nossa primeira análise visa perceber a influência do consenso na qualidade das postagens de modo mais gráfico. Para tanto, utilizamos uma estratégia envolvendo o intervalo de confiança IC da média de apoio dos instrutores à opinião de consenso, calculado utilizando amostragem por *bootstraping*. Aqui, a questão que queremos responder é: *Com 95% de confiança, o apoio dos instrutores à opinião consensual, definido conforme Equação 8, é significativo (![p<0,05](https://drive.google.com/uc?export=view&id=1rnVzZoGpT3YQraojreEHnV4wYbj-6396)) e relevante (![q_x\pm IC>0,5\mbox{~ou~}q_x\pm IC<0,5](https://drive.google.com/uc?export=view&id=17tDAoRWFaxmvhLGgffLVzjRzvnNfxj11))?*. Isto significa que para nossa hipótese nula ser rejeitada, todo o intervalo de confiança deve estar abaixo ou acima da marca de 0.5 ou 50\% de apoio dos instrutores. Conforme é apresentado na Figura&nbsp;1, salvo uma única exceção para discussões de "baixa convicção" (e menos de seis postagens, conforme Figura&nbsp;1&nbsp;(C)&nbsp;), podemos afirmar com 95% de confiança, que os instrutores, em média, não apoiam a opinião de consenso na maioria dos casos analisados e no caso geral, rejeitando a hipótese nula para tais casos. Ainda com relação à Figura&nbsp;1: "Baixo Consenso" (![\mbox{consenso}\geq0,8](https://drive.google.com/uc?export=view&id=1fuvq6XcXSWuf0AylvuvGlPETOgNaRy_A)), "Alto consenso" (![\mbox{consenso}\geq0,9](https://drive.google.com/uc?export=view&id=1DWl59hkXL2j0S7joQODm5gUqpSHVvm8y)), "Baixa convicção"(![\mbox{convicção média}<0,5](https://drive.google.com/uc?export=view&id=1sg9OMtn4nmPzVzKli81ECnG5SotJgYT7)), "Alta convicção"(![\mbox{convicção média}\geq4.0](https://drive.google.com/uc?export=view&id=1VN-KNJ3O2g3eevdFL7N7CiTgmP67i9Gj)) e o caso "Geral" envolvendo todos os dados. Cada intervalo de confiança foi calculado com base em três métricas de distância, conforme legenda: DISCO, distância de edição e Wordnet.

![Imagem&nbsp;1](https://drive.google.com/uc?export=view&id=1y1dseM3Ksb0LjKR5r6NLq8HN_RkIQMR4)
**Nível de apoio médio dos instrutores ao consenso (eixo y): (A) Todas as discussões; (B) apenas aquelas com entre duas e cinco postagens; e (C) aquelas com seis postagens ou mais**

Com base nestes resultados preliminares, somos levados a crer que a relação entre consenso e qualidade é na verdade inversamente proporcional. Contudo, a média é uma grandeza muito sensível à influência de valores extremos. Assim, recorremos a um modelo de regressão logística [Gelman and Hill 2006] para investigar o relacionamento entre nível de consenso e apoio dos instrutores à opinião consensual. A partir daí, tomando o consenso como a variável a ser investigada, e para adequar nosso problema a tal modelo, a partir dos dois limiares de consenso já mencionados (![\delta_1=0,8](https://drive.google.com/uc?export=view&id=1ElNqJTQbg6FbESK-wPp84XNEuh-W35rE) e ![\delta_2=0,9](https://drive.google.com/uc?export=view&id=1yB7ZkW1P0EkJmJg5Wdk60RM0-p4xsU2b)), traduzimos o consenso em uma variável binária assumindo 0 quando ![cg<\delta](https://drive.google.com/uc?export=view&id=1LFHYbCydDOEVnVPKAMfPRS2GNXANYNyQ) e 1 quando ![cg\geq\delta](https://drive.google.com/uc?export=view&id=1NXYXRP_msKilh8DNu6NzUiBaRENZne_U).

Em regressão logística, fatores com coeficientes positivos (![\beta](https://drive.google.com/uc?export=view&id=1d5fmmbCVbQryEs_n82dB3BTCLvzxzKnk)) estão positivamente correlacionados à variável sob investigação. Novamente, tomamos a taxa de apoio dos instrutores ao consenso como fator principal e a convicção como um fator que também poderia influenciar o consenso, nossa variável sob análise. Assim, um aumento na taxa de apoio dos instrutores à opinião majoritária, acompanhado do aumento na frequência com que se alcança o limiar ![\delta](https://drive.google.com/uc?export=view&id=1eeWFcbLJxRXFI9kDO4B4dmU0eGZjPXts) de consenso, validará a afirmação que consenso e qualidade, medida em função do apoio dos instrutores, estão relacionados.

Além de efeitos fixos (fatores), devemos também levar em consideração os efeitos aleatórios já apresentados no modelo baseado em intervalos de confiança: a métrica de distância textual e o total de postagens por discussão. Isto porque devemos assimilar o fato de que a métrica de distância textual é automática e agrega certa imprecisão, além de que um grande volume de postagens leva à uma dispersão no foco de discussão que pode prejudicar o consenso. A partir da Tabela&nbsp;1, podemos ver que ambos os efeitos aleatórios exercem influência no *intercept* da função de regressão com variância ![s^2](https://drive.google.com/uc?export=view&id=1SPq5-24jtlMoQVS06Vx2ajoagTBm5ZrI) igual à 0,52 e 0,30 para ![cg_2](https://drive.google.com/uc?export=view&id=1y6ObEFyeyStCK1Csf7Pa74mWck0SFtzT); e ![s^2](https://drive.google.com/uc?export=view&id=1SPq5-24jtlMoQVS06Vx2ajoagTBm5ZrI) igual à 0,72 e 0,69 para ![cg_1](https://drive.google.com/uc?export=view&id=123f-WbqKv274i64iJ8PQkXO_zXTUvy8a). Assim os fatores: "Apoio dos instrutores" e "Convicção média" para ![cg_2](https://drive.google.com/uc?export=view&id=1y6ObEFyeyStCK1Csf7Pa74mWck0SFtzT) são menos susceptíveis a efeitos aleatórios.

![Tabela&nbsp;1](https://drive.google.com/uc?export=view&id=1gcdKzxerQcKOlM0i_PNBbYN7iOBSITo2)

Ainda com base na Tabela&nbsp;1, em números absolutos, vemos que todos os coeficiente para ![cg_2](https://drive.google.com/uc?export=view&id=1y6ObEFyeyStCK1Csf7Pa74mWck0SFtzT) são bastante significativos, com ![p<0,001](https://drive.google.com/uc?export=view&id=1HWU7uKXBkvwowSd0R5JkmpFFDK3UA554). Porém, para ![cg_1](https://drive.google.com/uc?export=view&id=123f-WbqKv274i64iJ8PQkXO_zXTUvy8a), a convicção média parece ser um fator pouco significativo. A variância ![s^2](https://drive.google.com/uc?export=view&id=1SPq5-24jtlMoQVS06Vx2ajoagTBm5ZrI) para ambos os efeitos aleatórios (distância textual e quantidade de postagens) parece invalidar os resultados para um baixo limiar de consenso ![cg_1~|~\delta_1=0.8](https://drive.google.com/uc?export=view&id=1ZHaNHzG3_V6tyb2uzzFTTVZu4qA3Mi6N), uma vez que neste caso ![s^2_{cg_1}\in\{0,72,0,69\}\mbox{, e}~s^2_{cg_1}\approx|\beta|,\beta=-0,75](https://drive.google.com/uc?export=view&id=1gn5sV5x2xM7-a69AXQgy9wysvgWY6tR_). Assim, podemos descartar o estudo para baixo consenso. Já em se tratando do alto limiar de consenso, ![\delta_2=0,9](https://drive.google.com/uc?export=view&id=1yB7ZkW1P0EkJmJg5Wdk60RM0-p4xsU2b), a magnitude de ![\beta](https://drive.google.com/uc?export=view&id=1d5fmmbCVbQryEs_n82dB3BTCLvzxzKnk) parece sempre significativa com relação aos efeitos aleatórios ![s^2_cg_2\in\{0,52,0,30\}\mbox{, assim}~s^2_cg_2<|\beta|,\beta\in\{-1,88,-3,29\}](https://drive.google.com/uc?export=view&id=1xyo7cC8b8hX2Ez2wgrnIOk3Hq6VFIjme).

A partir destes resultados podemos concluir que o apoio dos instrutores, tomado como parâmetro de qualidade, está significativamente relacionado ao consenso (![p<0,001](https://drive.google.com/uc?export=view&id=1HWU7uKXBkvwowSd0R5JkmpFFDK3UA554)) a um limiar ![\delta_2=0,9](https://drive.google.com/uc?export=view&id=1yB7ZkW1P0EkJmJg5Wdk60RM0-p4xsU2b), exercendo um influência negativa da ordem de -0,032 para cada décimo de consenso, de acordo com a regra da divisão por quatro  [Gelman and Hill 2006].

## Conclusões

Aqui buscou-se estudar a correlação entre o consenso em discussões de fóruns MOOC e a qualidade das conclusões. Resultados mostraram que o consenso, no contexto dos dados considerados, é inversamente proporcional à qualidade em um fórum MOOC - sendo a opinião dos instrutores, nosso parâmetro de qualidade. Este resultado está em linha com aqueles de [Hirokawa 1982] e [Janis and Mann 1977]. Porém, estes afirmam que consenso pode ser visto como indicativo de qualidade apenas caso um rigoroso e "vigilante" processo de debate e argumentação seja seguido, além da heterogeneidade dos interlocutores assegurada. Contudo, além da dificuldade em estruturar discussões online de modo geral, segundo [Brinton et al. 2014], a maior parte das postagens realizadas em fóruns de MOOC são feitas no início do curso, quando os alunos ainda não tem muita familiaridade com o conteúdo. Com isto, por ser apresentada por alunos ainda inexperientes, a opinião consensual tenderia a não ter o apoio dos instrutores. Ainda sim, tal consideração não invalida os resultados alcançados aqui para a educação online.

Este trabalho contribui com uma maior clareza da função ou até da utilidade real de se conhecer o grau de consenso em discussões online. Intuitivamente, reconhecer consenso nos parece uma ferramenta útil para muitas das atividades de aprendizado online, particularmente via MOOC. Infelizmente, esta relação pode não ser tão clara, merecendo mais esforços de pesquisa para considerar, além da qualidade da conclusão, satisfação, interesse e compreensão dos alunos, por exemplo.


## Referências

Agrawal, A., Venkatraman, J., Leonard, S., and Paepcke, A. (2015). Youedu: addressing confusion in mooc discussion forums by recommending instructional video clips.

Alonso, S., Pérez, I. J., Cabrerizo, F. J., and Herrera-Viedma, E. (2013). A linguistic consensus model for web 2.0 communities. Applied Soft Computing, 13(1):149–157.

Bass, B. M. (1963). Amount of participation, coalescence, and profitability of decision making discussions. The Journal of Abnormal and Social Psychology, 67(1):92.

Brinton, C. G., Chiang, M., Jain, S., Lam, H., Liu, Z., and Wong, F. M. F. (2014). Learning about social learning in "moocs": From statistical analysis to generative model. IEEEtransactions on Learning Technologies, 7(4):346–359.

Cabrerizo, F. J., Chiclana, F., Al-Hmouz, R., Morfeq, A., Balamash, A. S., and Herrera-Viedma, E. (2015). Fuzzy decision making and consensus: challenges. Journal of Intelligent & Fuzzy Systems, 29(3):1109–1118.

Chen, H. and Zimbra, D. (2010). Ai and opinion mining. IEEE Intelligent Systems,25(3):74–80.

Damerau, F. J. (1964). A technique for computer detection and correction of spelling errors. Communications of the ACM, 7(3):171–176.

Doise, W., Mugny, G., James, A. S., Emler, N., and Mackie, D. (2013).The social development of the intellect, volume 10. Elsevier.

Gelman, A. and Hill, J. (2006).Data analysis using regression and multilevel/hierarchical models. Cambridge university press.

Gomaa, W. H. and Fahmy, A. A. (2013). A survey of text similarity approaches.International Journal of Computer Applications, 68(13):13–18.

Herrera-Viedma, E., Cabrerizo, F. J., Kacprzyk, J., and Pedrycz, W. (2014). A review ofsoft consensus models in a fuzzy environment. Information Fusion, 17:4–13.

Hiray, S. and Duppada, V. (2017). Agree to disagree: Improving disagreement detection with dual grus. In 2017 Seventh International Conference on Affective Computing and Intelligent Interaction Workshops and Demos (ACIIW), pages 147–152. IEEE.

Hirokawa, R. Y. (1982). Consensus group decision-making, quality of decision, and group satisfaction: An attempt to sort "fact" from "fiction". Communication Studies,33(2):407–415.

Janis, I. L. and Mann, L. (1977).Decision making: A psychological analysis of conflict, choice, and commitment. Free press.

Johnson, D. W. and Johnson, R. (1985). Classroom conflict: Controversy versus debatein learning groups. American Educational Research Journal, 22(2):237–256.

Johnson, D. W. and Johnson, R. T. (1979). Conflict in the classroom: Controversy and learning. Review of educational research, 49(1):51–69.

Kolb, P. (2008). Disco: A multilingual database of distributionally similar words. Proceedings of KONVENS-2008, Berlin, 156.

Levenshtein, V. I. (1966). Binary codes capable of correcting deletions, insertions, and reversals. In Soviet physics doklady, volume 10, pages 707–710.

Lin, D. (1998). Automatic retrieval and clustering of similar words. In COLING 1998 Volume 2: The 17th International Conference on Computational Linguistics, volume 2.

Miller, G. (1998).WordNet: An electronic lexical database. MIT press.

Opitz, B. and Zirn, C. (2013). Bootstrapping an unsupervised approach for classifying agreement and disagreement. In Proceedings of the 19th Nordic Conference of Computational Linguistics (NODALIDA 2013), pages 253–265.

Pérez, I. J., Cabrerizo, F. J., Alonso, S., Dong, Y., Chiclana, F., and Herrera-Viedma, E.(2018). On dynamic consensus processes in group decision making problems. Information Sciences, 459:20–35.

Potash, P. and Rumshisky, A. (2017). Towards debate automation: a recurrent modelfor predicting debate winners. In Proceedings of the 2017 Conference on Empirical Methods in Natural Language Processing, pages 2465–2475.

Qiu, M. and Jiang, J. (2013). A latent variable model for viewpoint discovery from threaded forum posts. In Proceedings of the 2013 Conference of the North American Chapter of the Association for Computational Linguistics: Human Language Technologies, pages 1031–1040.

Rosenthal, S. and McKeown, K. (2015). I couldn’t agree more: The role of conversational structure in agreement and disagreement detection in online discussions. In Proceedings of the 16th Annual Meeting of the Special Interest Group on Discourse and Dialogue, pages 168–177.

Seerat, B. and Azam, F. (2012). Opinion mining: Issues and challenges (a survey). International Journal of Computer Applications, 49(9).

Skeppstedt, M., Sahlgren, M., Paradis, C., and Kerren, A. (2016). Unshared task: (dis)agreement in online debates. In Proceedings of the Third Workshop on Argument Mining (ArgMining2016), pages 154–159.

Sridhar, D., Foulds, J., Huang, B., Getoor, L., and Walker, M. (2015). Joint models of disagreement and stance in online debate. In Proceedings of the 53rd Annual Meeting of the Association for Computational Linguistics and the 7th International Joint Conference on Natural Language Processing (Volume 1: Long Papers), volume 1, pages116–125.

Tan, C., Niculae, V., Danescu-Niculescu-Mizil, C., and Lee, L. (2016). Winning arguments: Interaction dynamics and persuasion strategies in good-faith online discussions. In Proceedings of the 25th international conference on world wide web, pages613–624. International World Wide Web Conferences Steering Committee.

Tolmie, P., Procter, R., Rouncefield, M., Liakata, M., and Zubiaga, A. (2018). Microblog analysis as a program of work. ACM Transactions on Social Computing, 1(1):2.

Trimbur, J. (1989). Consensus and difference in collaborative learning. College English, 51(6):602–616.

Van Knippenberg, D., De Dreu, C. K., and Homan, A. C. (2004). Work group diversityand group performance: an integrative model and research agenda. Journal of applied psychology, 89(6):1008.

Vinodhini, G. and Chandrasekaran, R. (2012). Sentiment analysis and opinion mining: a survey. International Journal, 2(6):282–292.

Wang, L. and Cardie, C. (2016). Improving agreement and disagreement identification in online discussions with a socially-tuned sentiment lexicon. arXiv:1606.05706.

Yin, J., Thomas, P., Narang, N., and Paris, C. (2012). Unifying local and global agreementand disagreement classification in online debates. In Proceedings of the 3rd Workshopin Computational Approaches to Subjectivity and Sentiment Analysis, pages 61–69. Association for Computational Linguistics.

Zimmerman, B. J. and Schunk, D. H. (2001). Self-regulated learning and academic achievement: Theoretical perspectives. Routledge.

Zubiaga, A., Kochkina, E., Liakata, M., Procter, R., and Lukasik, M. (2016). Stance classification in rumours as a sequential task exploiting the tree structure of social media conversations. arXiv:1609.09028.
