import java.util.Random;
import java.util.Scanner;

public class JogaJogoDaVelha {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao teste do Jogo da Velha!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite 1 para jogar ou 0 para sair: ");
        int opcao = scanner.nextInt();

        while (opcao != 0) {
            System.out.println("\nDigite um número inteiro para a dimensão do tabuleiro: ");
            int dimensao = scanner.nextInt();

            JogoDaVelha jogo = new JogoDaVelha(dimensao);
            Random random = new Random();

            while (jogo.vencedor() == 0) {
                int i = random.nextInt(dimensao);
                int j = random.nextInt(dimensao);
                try {
                    jogo.poePeca(i, j);
                } catch (IllegalArgumentException e) {
                    continue; // Ignorar exceções e tentar novamente
                }
                System.out.println();
            }

            if (jogo.vencedor() == 1) {
                System.out.println("\nX ganhou!");
            } else if (jogo.vencedor() == -1) {
                System.out.println("\nO ganhou!");
            } else {
                System.out.println("\nEmpate!");
            }
            System.out.println(jogo);
            System.out.println("\nDeseja jogar novamente? [1] - sim, [0] - não: ");
            opcao = scanner.nextInt();
        }
    }
}
