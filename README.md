<center>

# An&aacute;lise do consenso como indicativo de qualidade em f&oacute;runs MOOC <!--{style=text-align:center}-->

</center>
<br/><br/><br/>

#### Resumo <!--{style=text-align:justify}-->
Discuss&atilde;es em chats e f&oacute;runs de plataformas de ensino online, como aquelas para Massive Open Online Couses - MOOC, servem para construir conclus&otilde;es consensuais que agreguem valor pedag&oacute;gico. O estudo da correla&ccedil;&atilde;o entre consenso e a qualidade da conclus&atilde;o em discuss&otilde;es online tem recebido pouca aten&ccedil;&atilde;o. Este artigo usa *Soft Consensus* - quando h&aacute; grada&ccedil;&atilde;o do consenso, desde nenhum at&eacute; unanimidade - para apresentar um estudo estat&iacute;stico onde tal correla&ccedil;&atilde;o &eacute; medida a partir de dados de f&oacute;runs de MOOC e o apoio dos instrutores &agrave; conclus&atilde;o consensual &eacute; o indicador de qualidade. Resultados preliminares apontam uma correla&ccedil;&atilde;o negativa.

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

Os dados foram utilizados originalmente por [Agrawal et al. 2015]. Por razões éticas, Agrawal et al filtraram todas as referências nominais aos autores de cada postagem, preservando assim seu anonimato. Em seguida, eles ponderaram manualmente as postagens dos estudantes por urgência ![alt text](images/1_7.gif) - i.e., a faixa de números inteiros ![alt text](images/geq_1.gif) e ![alt text](images/leq_7.gif), onde 1 representa a menor urgência e 7, a maior; positividade ![alt text](images/1_7.gif); e, confusão ![alt text](images/1_7.gif). As postagens foram ainda classificadas como pergunta ![alt text](images/_0_1_.gif) - não ou sim, respectivamente; resposta ![alt text](images/_0_1_.gif); ou, opinião ![alt text](images/_0_1_.gif). Deve-se notar que o campo "confusão" parece ter sido mensurado em escala invertida, medindo na verdade a "convicção" em cada postagem. 

Com os dados já tratados, classificamos manualmente cada postagem como sendo de autoria ou não de um instrutor ![alt text](images/_0_1_.gif). Além disto, urgência, positividade e confusão (convicção) foram mapeados para a faixa de números decimais ![alt text](images/0_1.gif), afim de normalizar nossa análise.

### Tratamento Automático

Antes de efetuar os cálculos de consenso, algumas providências foram tomadas. Com o objetivo de melhorar o desempenho dos algoritmos de distância textual, sinais de pontuação foram substituídos por espaços em branco e todos os *tokens* (palavras, numerais, *links*, etc, desde que delimitados por espaços em branco) com dois caracteres ou menos, foram eliminados. Em seguida foi aplicada uma estratégia de remoção de *stop words* (palavras com pouca informação semântica como "de", "para", "por", etc) a partir da biblioteca Java Opennpl (opennlp.apache.org) da Apache Foundation (www.apache.org).

## Mensurando Consenso

Como já adiantando quando da Definição 1, *Soft Consensus* é um conceito onde o consenso é definido a partir de modelos capazes de assimilar incerteza [Herrera-Viedma et al. 2014]. Contudo, tal potencial diz respeito apenas a quantificadores linguísticos e Lógica Difusa. A maioria destes modelos segue metodologias rígidas divididas em rodadas de discussão e geralmente moderadas por um agente externo [Cabrerizo et al. 2015], embora haja alternativas mais flexíveis [Alonso et al. 2013]. Como estamos lidando com discussões já finalizadas e agentes dispersos no tempo, além de pouco comprometidos com o debate, mesmo metodologias mais flexíveis ainda necessitam de certos ajustes para se enquadrarem ao nosso propósito. Em face a isto, tomamos toda a discussão como sendo uma única rodada de debate e cada postagem como um agente individualizado. Como já mencionado, a base de dados utilizada já discrimina opiniões, perguntas e respostas. Deste modo, ainda tomamos as postagens discriminadas como "opinião" como as alternativas disponíveis para consideração. A distância textual entre uma postagem e cada uma das opiniões mede o nível de apoio que aquela postagem oferecia a cada opinião.

Métricas de distância textual podem introduzir ruído na análise, e de acordo com [Gomaa and Fahmy 2013] existem três classes principais de algoritmos de similaridade textual. Diante disto, analisou-se o consenso em função de três algoritmos de distância textual diferentes, um para cada classe, comparando os resultados. A primeira classe, e mais simplista delas, é a similaridade baseada em caracteres. Optamos por adotar a distância de edição ou distância de Damerau-Levenshtein, por ser a mais difundida. A segunda classe de algoritmos mede a distância semântica com base em Corpus textuais de vários idiomas, dentre eles o Inglês. Para esta, utilizou-se o algoritmo DISCO [Kolb 2008], por se tratar de uma tecnologia bem estabelecia e com implementação robusta. A terceira e ultima classe de algoritmos traz algoritmos baseados em redes semânticas de palavras e expressões. Para esta classe, aplicou-se um algoritmo baseado em Wordnet [Miller 1998], por dispor implementação bastante estável.

Nosso processo semi estruturado para cálculo de consenso segue uma sequência simples: Identificam-se as opiniões dentre as postagens de uma discussão; calcula-se a matriz de preferências comparando todas as opiniões dois a dois (conforme descrito na Seção *Soft Consensus*, a seguir); a partir da matriz de preferências, calculam-se similaridades entre tais preferências e ordenam-se as opiniões tanto no âmbito geral quanto para cada postagem individualizada; e, por fim, verifica-se o nível de consenso.

### Distância Textual

Extrair opiniões e preferências a partir de dados textuais é um problema que pode ser atacado utilizando algoritmos de distância textual. A seguir, selecionamos uma abordagem para cada classe de distância textual, conforme [Gomaa and Fahmy 2013], onde dividiu-se os algoritmos de distância textual em três classes distintas. Aplicamos três métricas de distância de classes diferentes para mensurar o ruído introduzido por tais métricas na análise.


**Damerau-Levenshtein:** A distância de Damerau-Levenshtein [Damerau 1964,Levenshtein 1966], também conhecida como distância de edição, é a diferença entre duas cadeias de caracteres. Tal diferença é medida em quantos caracteres precisam ser eliminados de ambas para que estas se tornem iguais, conforme Equação 1.

![Damerau-Levenshtein](images/lev.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1)

Sendo *a* e *b* duas cadeias de caracteres e as operações ![a-1](images/a_1.gif) ou ![b-1](images/b_1.gif) correspondem à eliminação de um caractere.


**DISCO:** DISCO [Kolb 2008] pode ser descrita como relação distributiva e semelhança distributiva, simultaneamente. Duas palavras são distributivamente semelhantes se possuem muitas palavras co-ocorrentes nos mesmos papeis sintáticos. Por outro lado, duas palavras são distributivamente relacionadas quando estão imersas no mesmo contexto, ou no mesmo conjunto livre de palavras co-ocorrentes. Neste sentido, DISCO não utiliza ferramentas sintáticas para correlacionar palavras (tal como uma *Wordnet*), porém faz aproximações livres de contexto de tais relações. Para tanto [Kolb 2008] utilizou métricas para mensurar informação mútua baseadas em [Lin 1998].

**Wordnet:** A Wordnet é uma base de dados textual semântico-sintática [Miller 1998]. Aqui palavras são separadas por grupos sintáticos como verbos, pronomes, advérbios e adjetivos, e agrupadas em conjuntos de sinônimos, os chamado *synsets*. Uma mesma palavra pode constar em mais de um *synset*. *Stop words* não estão incluídas. *Synsets* são conectados uns aos outros por relacionamentos semânticos dentro de seus grupos sintáticos e a força de relacionamento entre duas palavras (elementos destes *synsets*) é calculada em função destes relacionamentos.

### *Soft Consensus*

Em sistemas de apoio ao consenso, sobretudo em ambientes online, opiniões e preferências tendem a apresentar incertezas e imprecisões que tem sido abordadas usando a Lógica Difusa [Herrera-Viedma et al. 2014,  Cabrerizo et al. 2015]. Porém, diferenciando-se de discussões convencionais, os fóruns online agregam incerteza até mesmo no processo de discussão. Sendo assim, no âmbito deste estudo, é necessário fazer adaptações nos modelos estado da arte de suporte ao consenso com incerteza [Pérez et al. 2018]. No cálculo de consenso que propomos, postagens de alunos e instrutores estão definidas em ![P={p^1,... ,p^m}](images/p_1_m.gif), sendo *m* o total de postagens por discussão. Suas respectivas preferências são extraídas da proximidade textual entre cada postagem e comparações pareadas entre as opiniões disponíveis ![X={x^1,... ,x^n}](images/x_1_n.gif), sendo *n* o total de opiniões por discussão. Por simplicidade, mas sem perda de generalidade, aqui assumem-se todos os pesos das postagens / opiniões de instrutores e alunos como iguais.

**Definição 2:** Tomando *X* como o conjunto de opiniões e *P* como o conjunto de postagens da discussão, a função ![\Delta^{-1}_{P,X}](images/delta__1_PX.gif) é definida como uma função de similaridade dada entre uma postagem ![p\in P](images/p_e_P.gif) e uma opinião ![x\in X](images/x_e_X.gif), sendo ![\Delta^{-1}_{P,X}: P\times X\to [0,1]](images/delta_def.gif).

**Definição 3:** O conjunto de relações de preferência ![M=\{\mu^1,... ,\mu^u\}](images/m_1_u.gif), sendo *u* o total de preferências me uma dada discussão, pertence ao conjunto das relações ![\Delta^{-1}_{P,X}\times \Delta^{-1}_{P,X}](images/delta_por_delta.gif) e é definida por ![M: \Delta^{-1}_{P,X}\times \Delta^{-1}_{P,X}\to[0,1]](images/delta_por_delta_def.gif).

Assim, podemos entender ![\mu^p_{i,j}\in M](images/u_e_E.gif) como a relação de preferência/precedência extraída de *p* entre as opiniões ![x^i](images/x_i.gif) e ![x^j](images/x_j.gif), sendo ![\mu^p_{i,j}=\mu(\Delta^{-1}_{p,x^i},\Delta^{-1}_{p,x^j})(\forall p\in P,~\forall x\in X,~\forall i,j\in \{1,..., n\})](images/u_def.gif). Diante disto, para identificar o grau de consenso da discussão, devemos calcular quatro níveis de apoio - relação de preferência - entre o conjunto de postagens e o de opiniões disponíveis: 1) entre pares de opiniões; 2) entre opiniões e postagens; 3) entre postagens e opiniões; e, 4) geral [Pérez et al. 2018].

 - Nível 1: *Par de Alternativas -* Este nível consiste em gerar uma matriz de similaridade para cada par de postagens em relação a cada par de opiniões ![SP^{i,j}=(sp^{i,j}_{k,l}),\forall p^i,p^j\in P(i\neq j),\forall x^k,x^l\in X (k\neq l)](images/SP_def.gif), conforme a equação: 

![sp^{i,j}_{k,l}=1-{|\mu^{p^i}_{k,l}-\mu^{p^j}_{k,l}|}](images/sp__def.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2)

 Forma-se, assim, uma matriz de quatro dimensões ![SP_{(m \times m)\times (n \times n)}](images/SP_matrix.gif) comparando as preferências de todas as postagens duas a duas ![(m \times m)](images/m_x_m.gif) em função às preferências daquela postagem com relação à cada par de opiniões comparadas ![(n \times n)](images/n_x_n.gif). Em seguida, com base em uma função de agregação determinada ![\Phi](images/phi.gif), obtemos duas matrizes de preferência coletiva 1) comparando pares de opiniões agregando ![SP_{(m \times m)\times (n \times n)}](images/SP_matrix.gif) por postagem
 
 ![sp_{k,l}=\Phi^i(\Phi^j(sp^{i,j}_{k,l})) \forall i,j\in \{1,...,m\}~|~i\neq j](images/spkl.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3)
 
 e 2) pares de postagens agregando ![SP_{(m \times m)\times (n \times n)}](images/SP_matrix.gif) por opinião
 
 ![sp^{i,j}=\Phi_k(\Phi_l(sp^{i,j}_{k,l}) \forall k,l\in \{1,...,n\}~|~k\neq l](images/spij.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(4)
 
 Aqui usaremos a média harmônica como ![\Phi](images/phi.gif); 

 - Nível 2: *Opiniões e Postagens -* Aqui é definido o nível de consenso para cada opinião ![cx_k,\forall k\in \{1,...,n\}](images/cx_def.gif), calculado em função de todas as postagens *P* (já agregadas no nível anterior): 
 
 ![cx_k=\frac{\sum^n_{l=1,l\neq k}(sp_{k,l} + sp_{l,k})}{2(n-1)}](images/cx.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(5)
 
 - Nível 3: *Postagens e Opiniões -* Este nível de consenso é adotado apenas neste trabalho e cria uma matriz de similaridade de natureza oposta à do nível anterior. Aqui é definido a ordem de similaridade em função de todas as opiniões *X* (já agregadas no nível anterior) com relação à cada postagem ![cp_i,i\in\{1,...,m\}](images/cp_def.gif), conforme a equação:
 
 ![cp_i=\frac{\sum^m_{j=1,j\neq i}(sp_{i,j} + sp_{j,i})}{2(m-1)}](images/cp.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(6)
 
 - Nível 4: *Geral -* O nível final de consenso *cg* é definido como
 
 ![cg=\frac{\sum^n_{k=1}cx_k}{n}](images/cg.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(7)
 
Em tendo realizado os cálculos destes quatro níveis, três resultados são especialmente úteis aqui: Com a matriz de preferências agregadas por opinião do Nível 2 podemos identificar qual opinião é mais forte; com a matriz de preferências agregadas por postagem do Nível 3 podemos contabilizar quais instrutores apoiam certa opinião (vide Equação 8); e o nível de consenso geral do Nível 4.

Afim de concluir nossa análise, sempre que a maioria dos instrutores apoiar uma dada opinião, tomamos esta como a mais acertada. Sendo assim, tomamos *DP* como um vetor de duas dimensões onde na primeira estão as discussões e na segunda, as postagens correspondentes, e identificamos a postagem *p* da discussão *d* na posição *i* como ![p_{di}](images/pdi.gif); e, *DA* como outro vetor também de duas dimensões correspondendo a discussões e opiniões dos instrutores, respectivamente. Seja agora, ![a_{di}](images/adi.gif) a iézima postagem de um instrutor na discussão *d* e ![a_{di}(x)](images/adix.gif), uma função definida no conjunto ![\{0,1\}](images/_0_1_.gif) que representa o apoio daquela postagem de instrutor à opinião ![x\in DX](images/x_e_DX.gif), onde *DX* é outro vetor com dois componentes onde o primeiro representa discussões e o segundo, postagens marcadas como sendo opiniões. Temos então, que a qualidade da opinião ![x_{di}](images/xdi.gif) é definida por 

 ![q_{x_{di}}=\sum_{i=1}^{k}\frac{a_{di}(x_{di})}{k}](images/qxdi.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(8)
 
Na Equação 8, *k* é a quantidade total de opiniões de instrutores na discussão *d*. Assim, quanto mais apoio dos instrutores, maior a qualidade agregada àquela opinião. Deste modo, a qualidade de uma opinião é definida no domínio ![q\in 0,0;1,0](images/q_e_01010.gif). Além disto, o conceito de consenso utilizado para esta análise está definido no domínio ![0,0;1,0](images/01010.gif), porém acaba restrito ao domínio ![0,5;1,0](images/05010.gif), sendo ![c=0,5](images/c_05.gif) o grau de consenso puramente aleatório. Para enquadrar nossos resultados nos modelos estatísticos apresentados a seguir, normalizamos o consenso em ![0,0;1,0](images/01010.gif), tal qual a razão de qualidade *q*.

## Consenso vs. Qualidade

Com o intuito de medir o grau de certeza por parte do aluno em cada postagem, utilizamos o nível de convicção (ou, o inverso de confusão) médio das postagens, já computado, como um fator moderador em nossa análise. Além disto, o volume de postagens também pode ser fonte de imprecisão em discussões, por isso agrupamos as discussões em nossa base por número de postagens e analisamos tais grupos também separadamente. Agrupamos 4.671 postagens em discussões com número de postagens entre dois e cinco e 4.080 postagens em discussões com mais de seis postagens. Escolhemos estes números para, assim, dividir a base de dados em duas partes aproximadamente iguais. Por fim, estudamos as influências destes fatores em dois níveis de consenso diferentes: 0.8 (baixo) e 0.9 (alto), por serem mais recorrentes na literatura de consenso.

Nossa primeira análise visa perceber a influência do consenso na qualidade das postagens de modo mais gráfico. Para tanto, utilizamos uma estratégia envolvendo o intervalo de confiança IC da média de apoio dos instrutores à opinião de consenso, calculado utilizando amostragem por *bootstraping*. Aqui, a questão que queremos responder é: *Com 95% de confiança, o apoio dos instrutores à opinião consensual, definido conforme Equação 8, é significativo (![p<0,05](images/p_05.gif)) e relevante (![q_x\pm IC>0,5\mbox{~ou~}q_x\pm IC<0,5](images/q_0510.gif))?*. Isto significa que para nossa hipótese nula ser rejeitada, todo o intervalo de confiança deve estar abaixo ou acima da marca de 0.5 ou 50\% de apoio dos instrutores. Conforme é apresentado na Figura&nbsp;1, salvo uma única exceção para discussões de "baixa convicção" (e menos de seis postagens, conforme Figura&nbsp;1&nbsp;(C)&nbsp;), podemos afirmar com 95% de confiança, que os instrutores, em média, não apoiam a opinião de consenso na maioria dos casos analisados e no caso geral, rejeitando a hipótese nula para tais casos. Ainda com relação à Figura&nbsp;1: "Baixo Consenso" (![\mbox{consenso}\geq0,8](images/consenso_08.gif)), "Alto consenso" (![\mbox{consenso}\geq0,9](images/consenso_09.gif)), "Baixa convicção"(![\mbox{convicção média}<0,5](images/conv_med_05.gif)), "Alta convicção"(![\mbox{convicção média}\geq4.0](images/conv_med__05.gif)) e o caso "Geral" envolvendo todos os dados. Cada intervalo de confiança foi calculado com base em três métricas de distância, conforme legenda: DISCO, distância de edição e Wordnet.

![\mbox{consenso}\geq0,9](images/IC_2.png)
**Nível de apoio médio dos instrutores ao consenso (eixo y): (A) Todas as discussões; (B) apenas aquelas com entre duas e cinco postagens; e (C) aquelas com seis postagens ou mais**

Com base nestes resultados preliminares, somos levados a crer que a relação entre consenso e qualidade é na verdade inversamente proporcional. Contudo, a média é uma grandeza muito sensível à influência de valores extremos. Assim, recorremos a um modelo de regressão logística [Gelman and Hill 2006] para investigar o relacionamento entre nível de consenso e apoio dos instrutores à opinião consensual. A partir daí, tomando o consenso como a variável a ser investigada, e para adequar nosso problema a tal modelo, a partir dos dois limiares de consenso já mencionados (![\delta_1=0,8](images/delta_08.gif) e ![\delta_2=0,9](images/delta_09.gif)), traduzimos o consenso em uma variável binária assumindo 0 quando ![cg<\delta](images/cg_delta.gif) e 1 quando ![cg\geq\delta](images/cg__delta.gif).

Em regressão logística, fatores com coeficientes positivos (![\beta](images/beta.gif)) estão positivamente correlacionados à variável sob investigação. Novamente, tomamos a taxa de apoio dos instrutores ao consenso como fator principal e a convicção como um fator que também poderia influenciar o consenso, nossa variável sob análise. Assim, um aumento na taxa de apoio dos instrutores à opinião majoritária, acompanhado do aumento na frequência com que se alcança o limiar ![\delta](images/delta.gif) de consenso, validará a afirmação que consenso e qualidade, medida em função do apoio dos instrutores, estão relacionados.

Além de efeitos fixos (fatores), devemos também levar em consideração os efeitos aleatórios já apresentados no modelo baseado em intervalos de confiança: a métrica de distância textual e o total de postagens por discussão. Isto porque devemos assimilar o fato de que a métrica de distância textual é automática e agrega certa imprecisão, além de que um grande volume de postagens leva à uma dispersão no foco de discussão que pode prejudicar o consenso. A partir da Tabela&nbsp;1, podemos ver que ambos os efeitos aleatórios exercem influência no *intercept* da função de regressão com variância ![s^2](images/s_2.gif) igual à 0,52 e 0,30 para ![cg_2](images/cg2.gif); e ![s^2](images/s_2.gif) igual à 0,72 e 0,69 para ![cg_1](images/cg1.gif). Assim os fatores: "Apoio dos instrutores" e "Convicção média" para ![cg_2](images/cg2.gif) são menos susceptíveis a efeitos aleatórios.

tabela

Ainda com base na Tabela&nbsp;1, em números absolutos, vemos que todos os coeficiente para ![cg_2](images/cg2.gif) são bastante significativos, com ![p<0,001](images/p_0001.gif). Porém, para ![cg_1](images/cg1.gif), a convicção média parece ser um fator pouco significativo. A variância ![s^2](images/s_2.gif) para ambos os efeitos aleatórios (distância textual e quantidade de postagens) parece invalidar os resultados para um baixo limiar de consenso ![cg_1~|~\delta_1=0.8](images/c_delta_08.gif), uma vez que neste caso ![s^2_{cg_1}\in\{0,72,0,69\}\mbox{, e}~s^2_{cg_1}\approx|\beta|,\beta=-0,75](images/sci2.gif). Assim, podemos descartar o estudo para baixo consenso. Já em se tratando do alto limiar de consenso, ![\delta_2=0,9](images/delta_09.gif), a magnitude de ![\beta](images/beta.gif) parece sempre significativa com relação aos efeitos aleatórios ![s^2_cg_2\in\{0,52,0,30\}\mbox{, assim}~s^2_cg_2<|\beta|,\beta\in\{-1,88,-3,29\}](images/sc22.gif).

A partir destes resultados podemos concluir que o apoio dos instrutores, tomado como parâmetro de qualidade, está significativamente relacionado ao consenso (![p<0,001](images/p_0001.gif)) a um limiar ![\delta_2=0,9](images/delta_09.gif), exercendo um influência negativa da ordem de -0,032 para cada décimo de consenso, de acordo com a regra da divisão por quatro  [Gelman and Hill 2006].

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
