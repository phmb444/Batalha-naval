import java.util.Scanner;

public class Main {

    static  Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Multiplayer (1) ou Singleplayer (2)?");
        int opcao = ler.nextInt();
        if (opcao == 1){
            jogoMultiplayer();
        }
        else if (opcao == 2){
            jogoSingleplayer();
        }
        else{
            System.out.println("Opção inválida.");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            main(args);
        }
    }

    static public void jogoMultiplayer(){
        boolean vez = true;
        boolean vencedor = false;

        System.out.println();
        System.out.println("Jogador 1, digite seu nome: ");
        String p1 = ler.next();
        System.out.println("Jogador 2, digite seu nome: ");
        String p2 = ler.next();
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setNome(p1);
        player2.setNome(p2);

        player1.setAgua();
        player2.setAgua();

        System.out.println(player1.nome + ", posicione seus barcos.");
        System.out.println("1 - Manualmente");
        System.out.println("2 - Aleatoriamente");
        int opcao = ler.nextInt();
        if (opcao == 1){
            player1.posicionarBarcos();
        }
        else if (opcao == 2){
            player1.posicionarBarcosRandom();
        }
        else{
            System.out.println("Opção inválida.");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            jogoMultiplayer();
        }
        player1.limpar();
        System.out.println(player2.nome + ", posicione seus barcos.");
        System.out.println("1 - Manualmente");
        System.out.println("2 - Aleatoriamente");
        opcao = ler.nextInt();
        if (opcao == 1){
            player2.posicionarBarcos();
        }
        else if (opcao == 2){
            player2.posicionarBarcosRandom();
        }
        else{
            System.out.println("Opção inválida.");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            jogoMultiplayer();
        }
        player1.limpar();

        while (vencedor == false){
            if (vez == true){
                System.out.println(player1.nome + ", é sua vez!");
                boolean acertou = player1.atirar(player2);
                if (acertou == false){
                    vez = false;
                }
            }
            else{
                System.out.println(player2.nome + ", é sua vez!");
                boolean acertou = player2.atirar(player1);
                if (acertou == false){
                    vez = true;
                }
            }
            vencedor = verificarVencedor(player1, player2);
        }

    }

    static public void jogoSingleplayer(){
        boolean vez = true;
        boolean vencedor = false;

        System.out.println();
        System.out.println("Jogador 1, digite seu nome: ");
        String p1 = ler.next();
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setNome(p1);
        player2.setNome("Computador");
        player1.setAgua();
        player2.setAgua();

        System.out.println(player1.nome + ", posicione seus barcos.");
        System.out.println("1 - Manualmente");
        System.out.println("2 - Aleatoriamente");
        int opcao = ler.nextInt();
        if (opcao == 1){
            player1.posicionarBarcos();
        }
        else if (opcao == 2){
            player1.posicionarBarcosRandom();
        }
        else{
            System.out.println("Opção inválida.");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            jogoMultiplayer();
        }
        player1.limpar();
        player2.posicionarBarcosRandom();
        player1.limpar();

        while (!vencedor){
            if (vez){
                System.out.println(player1.nome + ", é sua vez!");
                boolean acertou = player1.atirar(player2);
                if (acertou == false){
                    vez = false;
                }
            }
            else{
                System.out.println(player2.nome + ", é sua vez!");
                boolean acertou = player2.atirarRandom(player1);
                if (acertou == false){
                    vez = true;
                }
            }
            vencedor = verificarVencedor(player1, player2);
        }
    }

    static public boolean verificarVencedor(Player player1, Player player2){
        int k = 0;
        int l = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (player1.tabuleiro[i][j] == 'B'){
                    k++;
                }
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (player2.tabuleiro[i][j] == 'B'){
                    l++;
                }
            }
        }
        if (k == 0){
            player2.limpar();
            System.out.println(player2.nome + " venceu!");
            return true;
        }
        else if (l == 0){
            player1.limpar();
            System.out.println(player1.nome + " venceu!");
            return true;
        }
        else{
            return false;
        }
    }
}

