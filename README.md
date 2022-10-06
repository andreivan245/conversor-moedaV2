# Conversor de MoedaV2

## Linguagem Usada
- Java

## Conhecimentos utilizados
- Orientação a objetos
- Consumo de API

## API
A API de cotações utilizada foi do [AwesomeAPI](https://docs.awesomeapi.com.br/api-de-moedas) que é distribuida gratuitamente e conta com mais de 150 moedas diferentes e cerca de 460 combinações de conversão.

- [Moedas aceitas](https://economia.awesomeapi.com.br/xml/available/uniq)
- [Combinações aceitas](https://economia.awesomeapi.com.br/xml/available)

## Observações
Essa é uma versão diferente do [Conversor-Moeda](https://github.com/andreivan245/Conversor-Moeda), essa versão tem todas combinações possíveis da API, porém, ainda tem o bug da casa decimal, o que pode gerar algumas conversões incorretas.

## Instalação


Requer o [Java](https://www.java.com/pt-BR/) instalado e o [Git](https://git-scm.com/).

Para a instalação escolha uma pasta para clonar o repositório e digite o seguinte comando

```sh
git clone https://github.com/andreivan245/Conversor-MoedaV2.git
```

E por fim execute o arquivo Conversor Moeda.jar


Ou se preferir baixe diretamente [aqui](https://github.com/andreivan245/Conversor-MoedaV2/archive/refs/heads/main.zip) o zip do projeto.

Em seguida extraia

E por fim xecute o arquivo Conversor Moeda.jar

## Bug
Até o dia de hoje 05/10/2022 a API apresenta um erro em algumas cotações ao retorna o valor da cotação, esse erro se dá pela posicão incorreta da casa decimal de valores >= 1000. O que acaba por prejudicar algumas conversões.

