import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt = "";
        String patrn = "";
        int alg = -1;

        System.out.println("Digite o texto:");
        txt = sc.nextLine();

        System.out.println("Digite o padrão:");
        patrn = sc.nextLine();

        System.out.println("Qual algoritmo deseja usar:\n1- KMP\n2-BoyerMoore");
        alg = Integer.parseInt(sc.nextLine());

        switch (alg) {
            case 1:
            System.out.println();
                KMP kmp = new KMP();
                kmp.findKMP(txt, patrn);
                break;
            case 2:
                break;
        }
        sc.close();
    }
}
