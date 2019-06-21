<center>

# An&aacute;lise do consenso como indicativo de qualidade em f&oacute;runs MOOC <!--{style=text-align:center}-->

</center>
<br/><br/><br/>

#### Resumo <!--{style=text-align:justify}-->
Discuss&atilde;es em chats e f&oacute;runs de plataformas de ensino online, como aquelas para Massive Open Online Couses - MOOC, servem para construir conclus&otilde;es consensuais que agreguem valor pedag&oacute;gico. O estudo da correla&ccedil;&atilde;o entre consenso e a qualidade da conclus&atilde;o em discuss&otilde;es online tem recebido pouca aten&ccedil;&atilde;o. Este artigo usa `soft consensus` - quando h&aacute; grada&ccedil;&atilde;o do consenso, desde nenhum at&eacute; unanimidade - para apresentar um estudo estat&iacute;stico onde tal correla&ccedil;&atilde;o &eacute; medida a partir de dados de f&oacute;runs de MOOC e o apoio dos instrutores &agrave; conclus&atilde;o consensual &eacute; o indicador de qualidade. Resultados preliminares apontam uma correla&ccedil;&atilde;o negativa.

<br/><br/><br/>

## Introdu&ccedil;&atilde;o

F�runs de discuss�o e debates online s�o importantes elementos de suporte ao aprendizado, onde o debate oferece ao estudante maior clareza acerca de um dado tema. Em tais f�runs, quando uma discuss�o � aberta, � natural criar-se uma expectativa de conclus�o, seja esta consensual ou definitiva. Diante da dificuldade de se alcan�ar conclus�es definitivas em certos temas de debate, o consenso ganha for�a como instrumento de avalia��o de qualidade da solu��o proposta. Aqui, ``consenso'' � definido como segue.

**Defini��o 1:** ``Consenso`` � uma medida de concord�ncia entre as respostas ou opini�es de um grupo de indiv�duos sobre determinada quest�o ou assunto. 

A defini��o~1 permite modelar de uma concord�ncia parcial � unanimidade. A defini��o se baseia naquela de ``soft consensus`` [Herrera-Viedma et al. 2014] que acomoda incertezas.

Embora a pesquisa sobre consenso tenha evolu�do e se adaptado aos dom�nios virtuais, com base em nossos levantamentos, n�o encontramos pesquisas que apontem se uma decis�o consensual � de fato uma decis�o de qualidade em ambientes de discuss�es online. Por conjectura, podemos apontar a dificuldade em se obter dados de discuss�es online para an�lise, cujas conclus�es estejam classificadas em fun��o da qualidade. Neste estudo, tal dificuldade foi contornada utilizando-se dados oriundos de f�runs de MOOCs (do ingl�s, *Massive Open Online Courses*), tomando o apoio do corpo de instrutores � conclus�o consensual como par�metro de qualidade.

Este artigo pretende medir a correla��o entre consenso e qualidade em discuss�es online, e especificamente no ambiente educacional (MOOC). Para tanto, utilizou-se uma base de dados de postagens de f�runs educacionais oriundos de 12 MOOC diferentes oferecidos pela Universidade de Stanford (www.stanford.edu) na plataforma edX (www.edx.org). O consenso entre estas postagens em cada discuss�o foi calculado com base em tr�s diferentes m�tricas de dist�ncia textual para avaliar o ru�do introduzido por tais m�tricas. O c�digo fonte utilizado para tais c�lculos est� dispon�vel em: github.com/[X].

Ap�s conhecer o consenso e a qualidade das discuss�es por toda a base de dados, procedeu-se � an�lise estat�stica de tais dados. Para tanto, a partir de um estudo preliminar envolvendo intervalo de confian�a e *Bootstraping*, analisou-se a m�dia de apoio dos instrutores � decis�o consensual. Com base em Regress�o Log�stica, encontraram-se ind�cios de que o consenso tem efeito negativo na qualidade da decis�o. Nossos resultados confirmam os resultados obtidos na literatura, mas agora no contexto de educa��o online.

## Mensurando Consenso

Como j� adiantando quando da Defini��o~1, \textit{Soft Consensus} � um conceito onde o consenso � definido a partir de modelos capazes de assimilar incerteza \cite{herrera2014review}. Contudo, tal potencial diz respeito apenas a quantificadores lingu�sticos e L�gica Difusa. A maioria destes modelos segue metodologias r�gidas divididas em rodadas de discuss�o e geralmente moderadas por um agente externo \cite{cabrerizo2015fuzzy}, embora haja alternativas mais flex�veis \cite{alonso2013linguistic}. Como estamos lidando com discuss�es j� finalizadas e agentes dispersos no tempo, al�m de pouco comprometidos com o debate, mesmo metodologias mais flex�veis ainda necessitam de certos ajustes para se enquadrarem ao nosso prop�sito. Em face a isto, tomamos toda a discuss�o como sendo uma �nica rodada de debate e cada postagem como um agente individualizado. Como j� mencionado, a base de dados utilizada j� discrimina opini�es, perguntas e respostas. Deste modo, ainda tomamos as postagens discriminadas como ``opini�o'' como as alternativas dispon�veis para considera��o. A dist�ncia textual entre uma postagem e cada uma das opini�es mede o n�vel de apoio que aquela postagem oferecia a cada opini�o.

M�tricas de dist�ncia textual podem introduzir ru�do na an�lise, e de acordo com Gomaa e Fahmy \cite{gomaa2013survey} existem tr�s classes principais de algoritmos de similaridade textual. Diante disto, analisou-se o consenso em fun��o de tr�s algoritmos de dist�ncia textual diferentes, um para cada classe, comparando os resultados. A primeira classe, e mais simplista delas, � a similaridade baseada em caracteres. Optamos por adotar a dist�ncia de edi��o ou dist�ncia de Damerau-Levenshtein, por ser a mais difundida. A segunda classe de algoritmos mede a dist�ncia sem�ntica com base em Corpus textuais de v�rios idiomas, dentre eles o Ingl�s. Para esta, utilizou-se o algoritmo DISCO \cite{kolb2008disco}, por se tratar de uma tecnologia bem estabelecia e com implementa��o robusta. A terceira e ultima classe de algoritmos traz algoritmos baseados em redes sem�nticas de palavras e express�es. Para esta classe, aplicou-se um algoritmo baseado em Wordnet \cite{miller1998wordnet}, por dispor implementa��o bastante est�vel.

Nosso processo semi estruturado para c�lculo de consenso segue uma sequ�ncia simples: Identificam-se as opini�es dentre as postagens de uma discuss�o; calcula-se a matriz de prefer�ncias comparando todas as opini�es dois a dois (conforme descrito na Subse��o~\ref{subsec:soft_consensus}); a partir da matriz de prefer�ncias, calculam-se similaridades entre tais prefer�ncias e ordenam-se as opini�es tanto no �mbito geral quanto para cada postagem individualizada; e, por fim, verifica-se o n�vel de consenso.