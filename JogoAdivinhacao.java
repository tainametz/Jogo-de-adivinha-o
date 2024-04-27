import java.util.Scanner;
import java.util.Random;

public class JogoAdivinhacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bem-vindo ao Jogo de Adivinhação!");
        System.out.println("Escolha o intervalo de números para jogar.");
        
        System.out.print("Digite o valor mínimo do intervalo: ");
        int minimo = scanner.nextInt();
        
        System.out.print("Digite o valor máximo do intervalo: ");
        int maximo = scanner.nextInt();
        
        if (minimo >= maximo) {
            System.out.println("Intervalo inválido. O valor máximo deve ser maior que o valor mínimo.");
            scanner.close();
            return;
        }
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
        int tentativas = calcularTentativas(maximo - minimo + 1); // Corrigindo a fórmula do número máximo de tentativas
        int palpiteAnterior = 0;
        
        System.out.println("\nTente adivinhar o número entre " + minimo + " e " + maximo + ".");
        
        while (tentativas > 0) {
            System.out.println("\nTentativas restantes: " + tentativas);
            System.out.print("Digite o seu palpite: ");
            int palpite = scanner.nextInt();
            
            if (palpite < minimo || palpite > maximo) {
                System.out.println("Por favor, digite um número dentro do intervalo especificado.");
                continue;
            }
            
            if (palpite == numeroAleatorio) {
                System.out.println("Parabéns! Você acertou o número " + numeroAleatorio + "!");
                break;
            } else {
                if (tentativas == 1) {
                    System.out.println("Suas tentativas acabaram. O número era " + numeroAleatorio + ".");
                    break;
                }
                
                if (Math.abs(palpite - numeroAleatorio) < Math.abs(palpiteAnterior - numeroAleatorio)) {
                    System.out.println("Você está mais quente!");
                } else {
                    System.out.println("Você está mais frio!");
                }
                
                palpiteAnterior = palpite;
            }
            
            tentativas--;
        }
        
        scanner.close();
    }
    
    private static int calcularTentativas(int intervalo) {
        return (int) (Math.log(intervalo) / Math.log(2)) + 1;
    }
}