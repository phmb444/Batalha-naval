## Batalha Naval em Java

### Descrição

Este projeto implementa o clássico jogo Batalha Naval em Java, permitindo partidas multiplayer entre dois jogadores ou contra o computador. O jogo oferece uma interface interativa para posicionar barcos, realizar disparos e acompanhar o placar.

### Funcionalidades

* **Seleção de Modo de Jogo:** Multiplayer (2 jogadores) ou Singleplayer (contra o computador).
* **Posicionamento de Barcos:** Manual ou Aleatório.
* **Sistema de Disparos:** Seleção de coordenadas para atacar o oponente.
* **Acompanhamento do Placar:** Visualização dos disparos realizados e acertos/erros.
* **Verificação de Vencedor:** O jogo termina quando um jogador afunda todos os barcos do oponente.

### Arquivos do Projeto

* **Main.java:** Ponto de entrada do programa, gerenciando a seleção do modo de jogo, fluxo principal da partida e verificação do vencedor.
* **Player.java:** Define a classe `Player` que representa um jogador no jogo, contendo métodos para:
    * Armazenar nome do jogador.
    * Criar e gerenciar o tabuleiro de jogo.
    * Posicionar barcos no tabuleiro.
    * Realizar disparos contra o oponente.
    * Verificar se um disparo foi bem-sucedido.
    * Verificar se todos os barcos foram afundados.

### Como Jogar

1. Clone o repositório do projeto.
2. Compile os arquivos `Main.java` e `Player.java` utilizando um compilador Java.
3. Execute o programa `Main.java` em seu terminal.
4. Siga as instruções na tela para selecionar o modo de jogo, posicionar seus barcos e realizar disparos.
5. O jogo se encerrará quando um jogador afundar todos os barcos do oponente.

