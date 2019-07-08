# Análise da relação consenso X qualidade

Aqui uma base de dados de fóruns MOOC oferecidos na plataforma edX foi coletada, o consenso dentro de cada discussão foi calculado e a qualidade de cada opinião foi mensurada com base no apoio dos instrutores a esta opinião. A partir disto, com base em regressão logística e intervalos de confiança, a taxa de apoio dos instrutores à opinião consensual foi utilizada para estimar até onde o consenso está de fato associado à qualidade.

Para mais detalhes acesse o arquivo: `../README.md`.

## Organização

* `/data` : dados
* `/reports` : relatórios das análises
* `/code` : código fora dos relatórios (tratamento dos dados)

## Dependências

O código é em R. Rode: 

```
deps = c("tidyverse", "lubridate", "here")
install.packages(deps)
```
