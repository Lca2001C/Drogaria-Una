import java.util.*;
public class Main {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static Estoque estoque = new Estoque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Criar funcionário");
            System.out.println("2. Acessar como funcionário");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarFuncionario();
                    break;
                case 2:
                    acessarComoFuncionario();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarFuncionario() {
        System.out.println("Criar Funcionário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Tipo (1 - Vendedor, 2 - Administrador): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Funcionario funcionario;
        if (tipo == 1) {
            funcionario = new Vendedor(nome, id);
        } else if (tipo == 2) {
            funcionario = new Administrador(nome, id);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }

        funcionarios.add(funcionario);
        System.out.println("Funcionário criado: " + funcionario.getNome() + " (" + funcionario.getId() + ")");
    }

    private static void acessarComoFuncionario() {
        System.out.print("ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Funcionario funcionario = buscarFuncionarioPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        if (funcionario instanceof Administrador) {
            menuAdministrador((Administrador) funcionario);
        } else if (funcionario instanceof Vendedor) {
            menuVendedor((Vendedor) funcionario);
        } else {
            System.out.println("Tipo de funcionário desconhecido.");
        }
    }

    private static Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    private static void menuAdministrador(Administrador administrador) {
        while (true) {
            System.out.println("Menu Administrador:");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Remover produto");
            System.out.println("3. Listar produtos");
            System.out.println("4. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    estoque.listarProdutos();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarProduto() {
        System.out.println("Adicionar Produto:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Tipo de tarja (1 - Vermelha, 2 - Amarela, 3 - Preta): ");
        int tipoTarja = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Produto produto;
        if (tipoTarja == 1) {
            produto = new TarjaVermelha(nome, preco, quantidade);
        } else if (tipoTarja == 2) {
            produto = new TarjaAmarela(nome, preco, quantidade);
        } else if (tipoTarja == 3) {
            produto = new TarjaPreta(nome, preco, quantidade);
        } else {
            System.out.println("Tipo de tarja inválido.");
            return;
        }

        estoque.adicionarProduto(produto);
    }

    private static void removerProduto() {
        System.out.print("Nome do produto a ser removido: ");
        String nome = scanner.nextLine();

        Produto produto = estoque.buscarPorNome(nome);
        if (produto != null) {
            estoque.removerProduto(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void menuVendedor(Vendedor vendedor) {
        while (true) {
            System.out.println("Menu Vendedor:");
            System.out.println("1. Realizar venda");
            System.out.println("2. Listar produtos");
            System.out.println("3. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                    estoque.listarProdutos();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void realizarVenda() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        estoque.realizarVenda(nome, quantidade);
    }
}
