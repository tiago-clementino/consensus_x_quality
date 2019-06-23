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
 
 2) e pares de postagens agregando ![SP_{(m \times m)\times (n \times n)}](images/SP_matrix.gif) por opinião
 
 ![sp^{i,j}=\Phi_k(\Phi_l(sp^{i,j}_{k,l}) \forall k,l\in \{1,...,n\}~|~k\neq l](images/spij.gif)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(4)
 
 Aqui usaremos a média harmônica como ![\Phi](images/phi.gif); 

 - Nível 2: *Opiniões e Postagens -* Aqui é definido o nível de consenso para cada opinião $cx_k,\forall k\in \{1,...,n\}$, calculado em função de todas as postagens $P$ (já agregadas no nível anterior): \begin{equation}cx_k=\frac{\sum^n_{l=1,l\neq k}(sp_{k,l} + sp_{l,k})}{2(n-1)}\end{equation}%Este nível é útil para definir a ordem de preferência dentre todas as opiniões e, assim, definir a opinião de maior consenso; 
\item{Nível 3:}\label{nivel_3} \textit{Postagens e Opiniões -} Este nível de consenso é adotado apenas neste trabalho e cria uma matriz de similaridade de natureza oposta à do nível anterior. Aqui é definido a ordem de similaridade em função de todas as opiniões $X$ (já agregadas no nível anterior) com relação à cada postagem $cp_i,i\in\{1,...,m\}$, conforme a equação:\begin{equation}cp_i=\frac{\sum^m_{j=1,j\neq i}(sp_{i,j} + sp_{j,i})}{2(m-1)}\end{equation}%Este nível será especialmente útil para uma próxima etapa de nosso estudo, onde utilizaremos a opinião mais próxima a cada uma das postagens; 
\item{Nível 4:}\label{nivel_4} \textit{Geral -} O nível final de consenso $cg$ é definido como\begin{equation}cg=\frac{\sum^n_{k=1}cx_k}{n}\end{equation}
\end{itemize}

Em tendo realizado os cálculos destes quatro níveis, três resultados são especialmente úteis aqui: Com a matriz de preferências agregadas por opinião do Nível~2 podemos identificar qual opinião é mais forte; com a matriz de preferências agregadas por postagem do Nível~3 podemos contabilizar quais instrutores apoiam certa opinião (vide Equação~\ref{eq01}); e o nível de consenso geral do Nível~4.

Afim de concluir nossa análise, sempre que a maioria dos instrutores apoiar uma dada opinião, tomamos esta como a mais acertada. Sendo assim, tomamos $DP$ como um vetor de duas dimensões onde na primeira estão as discussões e na segunda, as postagens correspondentes, e identificamos a postagem $p$ da discussão $d$ na posição $i$ como $p_{di}$; e, $DA$ como outro vetor também de duas dimensões correspondendo a discussões e opiniões dos instrutores, respectivamente. Seja agora, $a_{di}$ a iézima postagem de um instrutor na discussão $d$ e $a_{di}(x)$, uma função definida no conjunto $\{0,1\}$ que representa o apoio daquela postagem de instrutor à opinião $x\in DX$, onde $DX$ é outro vetor com dois componentes onde o primeiro representa discussões e o segundo, postagens marcadas como sendo opiniões. Temos então, que a qualidade da opinião $x_{di}$ é definida por \begin{equation}\label{eq01}
q_{x_{di}}=\sum_{i=1}^{k}\frac{a_{di}(x_{di})}{k} 
\end{equation}Na Equação~\ref{eq01}, $k$ é a quantidade total de opiniões de instrutores na discussão $d$. Assim, quanto mais apoio dos instrutores, maior a qualidade agregada àquela opinião. Deste modo, a qualidade de uma opinião é definida no domínio $q\in[0,0;1,0]$. Além disto, o conceito de consenso utilizado para esta análise está definido no domínio $[0,0;1,0]$, porém acaba restrito ao domínio $[0,5;1,0]$, sendo $c=0,5$ o grau de consenso puramente aleatório. Para enquadrar nossos resultados nos modelos estatísticos apresentados a seguir, normalizamos o consenso em $[0,0;0,1]$, tal qual a razão de qualidade $q$.
