import java.util.Scanner;
import java.security.SecureRandom;
import java.lang.Thread;

public class Player {
    Scanner ler = new Scanner(System.in);
    public String nome;
    int [] ultimaJogadaCerta = new int[2];
    int erradas = 0;
    char[][] tabuleiro = new char[10][10];
    int[] barcos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    public void pausa() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAgua() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] != 'B') {
                    tabuleiro[i][j] = '~';
                }
            }
        }
    }

    public int definirColuna(char coluna) {
        coluna = Character.toUpperCase(coluna);
        switch (coluna) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
        }
        return -1;
    }

    public void limpar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void posicionarBarcosRandom() {
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < barcos.length; i++) {
            boolean sobreposicao = false;
            int linha = random.nextInt(10) + 1;
            int colunaInt = random.nextInt(10);
            char orientacao = random.nextBoolean() ? 'V' : 'H';
            if (orientacao == 'V' || orientacao == 'v') {
                if (linha - 1 + barcos[i] - 1 >= 10) {
                    i--;
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    if (tabuleiro[linha - 1 + j][colunaInt] == 'B' || tabuleiro[linha - 1 + j][colunaInt] == '0') {
                        sobreposicao = true;
                        break;
                    }
                }
                if (sobreposicao) {
                    i--;
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    tabuleiro[linha - 1 + j][colunaInt] = 'B';
                    if (j == 0 && linha - 2 >= 0) {
                        tabuleiro[linha - 2 + j][colunaInt] = '0';
                        if (colunaInt - 1 >= 0 && colunaInt + 1 < 10) {
                            tabuleiro[linha - 2 + j][colunaInt + 1] = '0';
                            tabuleiro[linha - 2 + j][colunaInt - 1] = '0';
                        }
                    }
                    if (j == barcos[i] - 1 && linha + j < 10) {
                        tabuleiro[linha + j ][colunaInt] = '0';
                        if (colunaInt - 1 >= 0 && colunaInt + 1 < 10) {
                            tabuleiro[linha + j][colunaInt + 1] = '0';
                            tabuleiro[linha + j][colunaInt - 1] = '0';
                        }
                    }
                    if (linha - 2 + j >= 0) {
                        if (colunaInt - 1 >= 0)
                            tabuleiro[linha - 2 + j][colunaInt - 1] = '0'; // célula diagonal superior esquerda
                        if (colunaInt + 1 < 10)
                            tabuleiro[linha - 2 + j][colunaInt + 1] = '0'; // célula diagonal superior direita
                    }
                    if (linha + j < 10) {
                        if (colunaInt - 1 >= 0)
                            tabuleiro[linha + j][colunaInt - 1] = '0'; // célula diagonal inferior esquerda
                        if (colunaInt + 1 < 10)
                            tabuleiro[linha + j][colunaInt + 1] = '0'; // célula diagonal inferior direita
                    }
                    if (colunaInt - 1 >= 0)
                        tabuleiro[linha - 1 + j][colunaInt - 1] = '0'; // célula à esquerda do barco
                    if (colunaInt + 1 < 10)
                        tabuleiro[linha - 1 + j][colunaInt + 1] = '0'; // célula à direita do barco
                }
            } else if (orientacao == 'H' || orientacao == 'h') {
                if (colunaInt + barcos[i] - 1 > 10) {
                    i--;
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    if (colunaInt + j < 10){
                    if (tabuleiro[linha - 1][colunaInt+j] == 'B' || tabuleiro[linha - 1][colunaInt + j] == '0') {
                        sobreposicao = true;
                        break;
                    }}
                }
                if (sobreposicao) {
                    i--;
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    if (colunaInt + j < 10){
                    tabuleiro[linha - 1][colunaInt + j] = 'B';}
                    if (j == 0 && colunaInt - 1 >= 0) {
                        tabuleiro[linha - 1 + j][colunaInt - 1] = '0';
                        if (linha - 2 >= 0 && linha < 10) {
                            tabuleiro[linha - 2][colunaInt - 1] = '0';
                            tabuleiro[linha][colunaInt - 1] = '0';
                        }
                    }
                    if (j == barcos[i] - 1 && colunaInt + 1 + j < 10) {
                        tabuleiro[linha - 1][colunaInt + 1 + j] = '0';
                        if (linha - 2 >= 0 && colunaInt + 1 < 10 && linha < 10) {
                            tabuleiro[linha - 2][colunaInt + 1 + j] = '0';
                            tabuleiro[linha][colunaInt + 1 + j] = '0';
                        }
                    }

                    if (colunaInt - 1 >= 0 && j == 0) {
                        tabuleiro[linha - 1][colunaInt - 1] = '0'; // célula à esquerda do barco
                        if (linha < 10)
                            tabuleiro[linha][colunaInt - 1] = '0'; // célula abaixo do barco
                    }
                    if (colunaInt + 1 + j < 10 && j == barcos[i] - 1) {
                        tabuleiro[linha - 1][colunaInt + 1 + j] = '0'; // célula à direita do barco
                        if (linha < 10)
                            tabuleiro[linha][colunaInt + 1 + j] = '0'; // célula abaixo do barco
                    }
                    if (linha - 2 >= 0 && colunaInt + j < 10) {
                        tabuleiro[linha - 2][colunaInt + j] = '0'; // célula acima do barco
                    }
                    if (linha < 10 && colunaInt + j < 10) {
                        tabuleiro[linha][colunaInt + j] = '0'; // célula abaixo do barco
                    }
                }
            }
            limpar();
        }
        pausa();
    }

    public void posicionarBarcos() {
        boolean sobreposicao = false;
        for (int i = 0; i < barcos.length; i++) {
            imprimirMapa();
            System.out.println("Posicione o barco de tamanho " + barcos[i]);
            System.out.println("Digite a linha (1-10): ");
            int linha = ler.nextInt();
            if (linha < 1 || linha > 10) {
                System.out.println("Linha inválida, posicione o barco novamente");
                i--;
                pausa();
                continue;
            }
            System.out.println("Digite a coluna (A-J): ");
            char coluna = ler.next().charAt(0);
            int colunaInt = definirColuna(coluna);
            if (colunaInt == -1) {
                System.out.println("Coluna inválida, posicione o barco novamente");
                i--;
                pausa();
                continue;
            }
            System.out.println("Digite a orientação (V para vertical e H para horizontal): ");
            char orientacao = ler.next().charAt(0);
            if (orientacao == 'V' || orientacao == 'v') {
                if (linha - 1 + barcos[i] - 1 >= 10) {
                    System.out.println("Barco vai sair para fora do tabuleiro, tente novamente");
                    i--;
                    pausa();
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    if (tabuleiro[linha - 1 + j][colunaInt] == 'B' || tabuleiro[linha - 1 + j][colunaInt] == '0') {
                        System.out.println("Barcos se sobrepondo na posição: " + (linha - 1 + j) + " " + colunaInt +  " tente novamente");
                        sobreposicao = true;
                        break;
                    }
                }
                if (sobreposicao) {
                    i--;
                    pausa();
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    tabuleiro[linha - 1 + j][colunaInt] = 'B';
                    if (j == 0 && linha - 2 >= 0) {
                        tabuleiro[linha - 2 + j][colunaInt] = '0';
                        if (colunaInt - 1 >= 0 && colunaInt + 1 < 10) {
                            tabuleiro[linha - 2 + j][colunaInt + 1] = '0'; // célula acima do barco
                            tabuleiro[linha - 2 + j][colunaInt - 1] = '0'; // célula acima do barco
                        }
                    }
                    if (j == barcos[i] - 1 && linha + j < 10) {
                        tabuleiro[linha + j][colunaInt] = '0';
                        if (colunaInt - 1 >= 0 && colunaInt + 1 < 10) {
                            tabuleiro[linha - 2 + j][colunaInt + 1] = '0'; // célula acima do barco
                            tabuleiro[linha - 2 + j][colunaInt - 1] = '0'; // célula acima do barco
                        }
                    }
                    if (linha - 2 + j >= 0) {
                        if (colunaInt - 1 >= 0)
                            tabuleiro[linha - 2 + j][colunaInt - 1] = '0'; // célula diagonal superior esquerda
                        if (colunaInt + 1 < 10)
                            tabuleiro[linha - 2 + j][colunaInt + 1] = '0'; // célula diagonal superior direita
                    }
                    if (linha + j < 10) {
                        if (colunaInt - 1 >= 0)
                            tabuleiro[linha + j][colunaInt - 1] = '0'; // célula diagonal inferior esquerda
                        if (colunaInt + 1 < 10)
                            tabuleiro[linha + j][colunaInt + 1] = '0'; // célula diagonal inferior direita
                    }
                    if (colunaInt - 1 >= 0)
                        tabuleiro[linha - 1 + j][colunaInt - 1] = '0'; // célula à esquerda do barco
                    if (colunaInt + 1 < 10)
                        tabuleiro[linha - 1 + j][colunaInt + 1] = '0'; // célula à direita do barco
                }
            } else if (orientacao == 'H' || orientacao == 'h') {
                if (colunaInt + barcos[i] - 1 > 10) {
                    System.out.println("Barco vai sair para fora do tabuleiro, tente novamente");
                    i--;
                    pausa();
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    if (tabuleiro[linha - 1][colunaInt+j] == 'B' || tabuleiro[linha - 1][colunaInt + j] == '0') {
                        System.out.println("Barcos se sobrepondo na posição: " + (linha - 1) + " " + (colunaInt + j) +  " tente novamente");
                        sobreposicao = true;
                        break;
                    }
                }
                if (sobreposicao) {
                    i--;
                    pausa();
                    continue;
                }
                for (int j = 0; j < barcos[i]; j++) {
                    tabuleiro[linha - 1][colunaInt + j] = 'B';
                    if (j == 0 && colunaInt - 1 >= 0) {
                        tabuleiro[linha - 1 + j][colunaInt - 1] = '0';
                        if (linha - 2 >= 0 && colunaInt - 1 >= 0 && linha < 10) {
                            tabuleiro[linha - 2][colunaInt - 1] = '0';
                            tabuleiro[linha][colunaInt - 1] = '0';
                        }
                    }
                    if (j == barcos[i] - 1 && colunaInt + 1 + j < 10) {
                        tabuleiro[linha - 1][colunaInt + 1 + j] = '0';
                        if (linha - 2 >= 0 && colunaInt + 1 < 10 && linha < 10) {
                            tabuleiro[linha - 2][colunaInt + 1 + j] = '0';
                            tabuleiro[linha][colunaInt + 1 + j] = '0';
                        }
                    }

                    if (colunaInt - 1 >= 0 && j == 0) {
                        tabuleiro[linha - 1][colunaInt - 1] = '0'; // célula à esquerda do barco
                        if (linha < 10)
                            tabuleiro[linha][colunaInt - 1] = '0'; // célula abaixo do barco
                    }
                    if (colunaInt + 1 + j < 10 && j == barcos[i] - 1) {
                        tabuleiro[linha - 1][colunaInt + 1 + j] = '0'; // célula à direita do barco
                        if (linha < 10)
                            tabuleiro[linha][colunaInt + 1 + j] = '0'; // célula abaixo do barco
                    }
                    if (linha - 2 >= 0) {
                        tabuleiro[linha - 2][colunaInt + j] = '0'; // célula acima do barco
                    }
                    if (linha < 10) {
                        tabuleiro[linha][colunaInt + j] = '0'; // célula abaixo do barco
                    }
                }
            } else {
                System.out.println("Orientação inválida, posicione o barco novamente");
                i--;
                pausa();
            }
            limpar();
        }
    }

    public void imprimirMapa() {
        System.out.println("");
        System.out.println("");
        System.out.println("X----------X-----------X");
        System.out.println("   A   B  C  D  E   F  G  H  I  J ");
        for (int i = 0; i < 10; i++) {
            if (i < 9)
                System.out.print(i + 1 + "  ");
            else
                System.out.print(i + 1 + " ");
            for (int j = 0; j < 10; j++) {
                // faça caso for um ~ imprimir um emoji de onda
                // caso B imprimir um emoji de barco
                // caso 0 imprimir um emoji de X vermelho
                // caso X imprimir um emoji de explosão

                if (tabuleiro[i][j] == 'B') {
                    System.out.print("🚢|");
                } else if (tabuleiro[i][j] == '0') {
                    System.out.print("❌|");
                } else if (tabuleiro[i][j] == 'X') {
                    System.out.print("💥|");
                } else {
                    System.out.print("🌊|");
                }
            }
            System.out.println();
        }
    }

    public void imprimirMapaEscondido() {
        System.out.println("");
        System.out.println("");
        System.out.println("X----------X-----------X");
        System.out.println("   A   B  C  D  E   F  G  H  I  J ");
        for (int i = 0; i < 10; i++) {
            if (i < 9)
                System.out.print(i + 1 + "  ");
            else
                System.out.print(i + 1 + " ");
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == 'B' || tabuleiro[i][j] == '0' || tabuleiro[i][j] == '~') {
                    System.out.print("🌊|");
                } else {
                    if (tabuleiro[i][j] == 'X') {
                        System.out.print("💥|");
                    } else {
                        // coloque um emoji de fumaça
                        System.out.print("🎯|");
                    }
                }
            }
            System.out.println();
        }
    }

    public boolean atirar(Player adversario) {
        adversario.imprimirMapaEscondido();
        System.out.println("Digite a linha (1-10): ");
        int linha = ler.nextInt();
        if (linha < 1 || linha > 10) {
            System.out.println("Linha inválida, tente novamente");
            pausa();
            limpar();
            atirar(adversario);
        }
        System.out.println("Digite a coluna (A-J): ");
        char coluna = ler.next().charAt(0);
        int colunaInt = definirColuna(coluna);
        if (colunaInt == -1) {
            System.out.println("Coluna inválida, tente novamente");
            pausa();
            limpar();
            atirar(adversario);
        }
        if (adversario.tabuleiro[linha - 1][colunaInt] == 'B') {
            System.out.println("Acertou!");
            adversario.tabuleiro[linha - 1][colunaInt] = 'X';
            limpar();
            adversario.imprimirMapaEscondido();
            pausa();
            return true;
        } else if (adversario.tabuleiro[linha - 1][colunaInt] == 'X' || adversario.tabuleiro[linha - 1][colunaInt] == '*') {
            System.out.println("Posição já atirada, tente novamente");
            pausa();
            limpar();
            atirar(adversario);
            return true;
        } else {
            System.out.println("Errou!");
            adversario.tabuleiro[linha - 1][colunaInt] = '*';
            limpar();
            adversario.imprimirMapaEscondido();
            pausa();
            return false;
        }
    }

    public boolean atirarRandom(Player adversario) {
        adversario.imprimirMapaEscondido();
        pausa();
        SecureRandom random = new SecureRandom();
        int linha, colunaInt;
        int[] ultimaJogadaCerta = new int[2]; // Adicionei para evitar erro de compilação

        // Se não houve acertos anteriores, dispara aleatoriamente
        if (erradas == 0) {
            linha = random.nextInt(10) + 1;
            colunaInt = random.nextInt(10);
        } else {
            int somarSubtrair = random.nextInt(2); // 0 para subtrair, 1 para somar
            int deltaLinha = 0, deltaColuna = 0;

            // Decide aleatoriamente se vai somar ou subtrair da última posição correta

            // faça ser um numero negativo entre -3 e 0
            if (somarSubtrair == 0) {
                // gere um numero negativo entre -3 e 0
                deltaLinha = random.nextInt(3) * -1;
                deltaColuna = random.nextInt(3) * -1;
            } else {
                deltaLinha = random.nextInt(3);
                deltaColuna = random.nextInt(3);
            }

            // Gera números aleatórios próximos ao último acerto
            linha = deltaLinha + ultimaJogadaCerta[0];
            colunaInt = deltaColuna + ultimaJogadaCerta[1];

            // Garante que as coordenadas geradas estejam dentro dos limites do tabuleiro
            linha = Math.max(1, Math.min(10, linha));
            colunaInt = Math.max(0, Math.min(9, colunaInt));
        }

        if (adversario.tabuleiro[linha - 1][colunaInt] == 'B') {
            System.out.println("Acertou!");
            adversario.tabuleiro[linha - 1][colunaInt] = 'X';
            ultimaJogadaCerta[0] = linha - 1;
            ultimaJogadaCerta[1] = colunaInt;
            erradas = 3;
            limpar();
            adversario.imprimirMapaEscondido();
            pausa();
            return true;
        } else if (adversario.tabuleiro[linha - 1][colunaInt] == 'X' || adversario.tabuleiro[linha - 1][colunaInt] == '*') {
            System.out.println("Posição já atirada, tente novamente");
            pausa();
            if (erradas > 0) erradas--;
            limpar();
            return true; // Chama recursivamente até encontrar uma posição válida
        } else {
            System.out.println("Errou!");
            adversario.tabuleiro[linha - 1][colunaInt] = '*';
            limpar();
            if (erradas > 0) erradas--;
            adversario.imprimirMapaEscondido();
            pausa();
            return false;
        }
    }

}



