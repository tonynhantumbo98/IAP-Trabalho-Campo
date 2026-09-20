import java.util.Scanner;

public class Biblioteca {

    static Scanner sc = new Scanner(System.in);

    static final int MAX = 100;

    static int[] ids = new int[MAX];
    static String[] titulos = new String[MAX];
    static String[] autores = new String[MAX];
    static int[] anos = new int[MAX];
    static int[] quantidades = new int[MAX];
    static int[] emprestimos = new int[MAX];

    static int totalLivros = 0;
    static int totalEmprestimos = 0;

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE GESTÃO DE BIBLIOTECA =====");
            System.out.println("1. Registar Livro");
            System.out.println("2. Listar Catálogo");
            System.out.println("3. Pesquisar por Título");
            System.out.println("4. Pesquisar por Autor");
            System.out.println("5. Efetuar Empréstimo");
            System.out.println("6. Efetuar Devolução");
            System.out.println("7. Estatísticas");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    registarLivro();
                    break;
                case 2:
                    listarCatalogo();
                    break;
                case 3:
                    pesquisarTitulo();
                    break;
                case 4:
                    pesquisarAutor();
                    break;
                case 5:
                    emprestarLivro();
                    break;
                case 6:
                    devolverLivro();
                    break;
                case 7:
                    mostrarEstatisticas();
                    break;
                case 8:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 8);
    }

    public static void registarLivro() {

        System.out.print("ID: ");
        ids[totalLivros] = sc.nextInt();
        sc.nextLine();

        System.out.print("Título: ");
        titulos[totalLivros] = sc.nextLine();

        System.out.print("Autor: ");
        autores[totalLivros] = sc.nextLine();

        System.out.print("Ano de Publicação: ");
        anos[totalLivros] = sc.nextInt();

        System.out.print("Quantidade Disponível: ");
        quantidades[totalLivros] = sc.nextInt();

        emprestimos[totalLivros] = 0;

        totalLivros++;

        System.out.println("Livro registado com sucesso!");
    }

    public static void listarCatalogo() {

        if (totalLivros == 0) {
            System.out.println("Nenhum livro registado.");
            return;
        }

        System.out.println("\n===== CATÁLOGO =====");

        for (int i = 0; i < totalLivros; i++) {

            System.out.println("ID: " + ids[i]);
            System.out.println("Título: " + titulos[i]);
            System.out.println("Autor: " + autores[i]);
            System.out.println("Ano: " + anos[i]);
            System.out.println("Quantidade: " + quantidades[i]);
            System.out.println("-------------------");
        }
    }

    public static void pesquisarTitulo() {

        System.out.print("Introduza o título: ");
        String pesquisa = sc.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < totalLivros; i++) {

            if (titulos[i].equalsIgnoreCase(pesquisa)) {

                System.out.println("Livro encontrado:");
                System.out.println("ID: " + ids[i]);
                System.out.println("Título: " + titulos[i]);
                System.out.println("Autor: " + autores[i]);
                System.out.println("Quantidade: " + quantidades[i]);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void pesquisarAutor() {

        System.out.print("Introduza o autor: ");
        String pesquisa = sc.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < totalLivros; i++) {

            if (autores[i].equalsIgnoreCase(pesquisa)) {

                System.out.println("\nTítulo: " + titulos[i]);
                System.out.println("Autor: " + autores[i]);
                System.out.println("Quantidade: " + quantidades[i]);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Autor não encontrado.");
        }
    }

    public static void emprestarLivro() {

        System.out.print("Introduza o ID do livro: ");
        int id = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < totalLivros; i++) {

            if (ids[i] == id) {

                encontrado = true;

                if (quantidades[i] > 0) {

                    quantidades[i]--;
                    emprestimos[i]++;
                    totalEmprestimos++;

                    System.out.println("Empréstimo efetuado com sucesso!");

                } else {

                    System.out.println("Livro sem exemplares disponíveis.");
                }
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void devolverLivro() {

        System.out.print("Introduza o ID do livro: ");
        int id = sc.nextInt();

        boolean encontrado = false;

        for (int i = 0; i < totalLivros; i++) {

            if (ids[i] == id) {

                quantidades[i]++;
                encontrado = true;

                System.out.println("Devolução registada com sucesso!");
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void mostrarEstatisticas() {

        if (totalLivros == 0) {

            System.out.println("Não existem livros registados.");
            return;
        }

        int max = emprestimos[0];
        int indice = 0;

        for (int i = 1; i < totalLivros; i++) {

            if (emprestimos[i] > max) {

                max = emprestimos[i];
                indice = i;
            }
        }

        System.out.println("\n===== ESTATÍSTICAS =====");
        System.out.println("Total de Empréstimos: " + totalEmprestimos);
        System.out.println("Livro Mais Emprestado: " + titulos[indice]);
        System.out.println("Número de Empréstimos: " + emprestimos[indice]);
    }
}
