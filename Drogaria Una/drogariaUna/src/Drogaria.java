import java.sql.*;
import java.util.*;
public class Drogaria {
    private static Scanner scanner = new Scanner(System.in);
    private static Conexao conexao = new Conexao();

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Criar funcionário");
            System.out.println("2. Acessar como funcionário");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

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
    private static void criarFuncionario() throws SQLException{
        String nome = scanner.nextLine();
        int tipo = scanner.nextInt();
        Conexao exec = new Conexao();
        String sql = "create table funcionarios( "+
                        " id int not null auto_increment,"
                        + " nome varchar(50) not null,"
                        + " tipo int not null,"+
                        " primary key (id))";
        exec.openDatabase();
        exec.executarQuery(sql);
        sql = " insert into funcionarios"+
                "(nome,tipo)"+
                "values"+
                "('"+ nome + "', '" + tipo +"')";   
        exec.openDatabase();
        exec.executarQuery(sql);
        exec.closeDatabase();
        System.out.println("FUNCIONARIO:"+nome+"\n Tipo: "+ tipo);
    }
    private static void acessarComoFuncionario() throws SQLException {
        System.out.print("ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        int tipoFuncionario = buscarFuncionarioPorId(id);
        if (tipoFuncionario == 0) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        if (tipoFuncionario == 1) {
            menuVendedor(null);
        } else if (tipoFuncionario == 2) {
            menuAdministrador(null);
        } else {
            System.out.println("Tipo de funcionário desconhecido.");
        }
    }
    public static int buscarFuncionarioPorId(int id) throws SQLException{
        Conexao exec = new Conexao();
        String sql = "SELECT id, tipo FROM funcionarios WHERE id = ?";
        Connection connection = null;

        int tipo=0;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
    
                    if (resultSet.next()) {
                        tipo = resultSet.getInt("tipo");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexao.closeDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tipo;
    }
    private static void menuAdministrador(Administrador administrador) throws SQLException {
        while (true) {
            System.out.println("Menu Administrador:");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Remover produto");
            System.out.println("3. Listar produtos");
            System.out.println("4. Listar um produto");
            System.out.println("5. Alterar estoque");
            System.out.println("6. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                List<Produto> produtos = listarProdutos();
                if (produtos.isEmpty()) {
                    System.out.println("Nenhum dado encontrado.");
                } else {
                    System.out.println("Dados encontrados:");
                    for (Produto produto : produtos) {
                        System.out.println(produto);
                    }
                }
                    break;
                case 4:
                List<Produto> umProduto = listarUmProduto();
                if (umProduto.isEmpty()) {
                    System.out.println("Nenhum dado encontrado.");
                } else {
                    System.out.println("Dados encontrados:");
                    for (Produto produto : umProduto) {
                        System.out.println(produto);
                    }
                }
                    break;
                case 5:
                    alterarEstoque();
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    private static void adicionarProduto() throws SQLException {
        System.out.println("Digite o nome do produto");
        String nome = scanner.nextLine();
        System.out.println("Digite o valor do produto");
        double valor = scanner.nextDouble();
        System.out.println("Digite a quantidade inicial do produto");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        String tarja=null;
        while (true) {
            System.out.println("Selecione a tarja");
            System.out.println("1. Sem tarja");
            System.out.println("2. Tarja Amarela");
            System.out.println("3. Tarja Vermelha");
            System.out.println("4. Tarja Preta");
            int tarjaSwitch = scanner.nextInt();
            switch (tarjaSwitch) {
                case 1:
                    tarja = "Sem tarja";
                    break;
                case 2:
                    tarja = "Amarela";
                    break;
                case 3:
                    tarja = "Vermelha";
                    break;
                case 4:
                    tarja = "Preta";
                    break;
                default:
                    System.out.println("Seleção inválida, tente novamente.");
                    continue;
            }
            break;
        }
        Conexao exec = new Conexao();
        String sql = "create table produtos( "+
                        " id int not null auto_increment,"
                        + " nome varchar(50) not null,"
                        + " valor decimal (5,2) not null,"
                        + " quantidade int,"+
                        " tarja varchar(15) not null,"+
                        " primary key (id))";
        exec.openDatabase();
        exec.executarQuery(sql);
        sql = " insert into produtos"+
                "(nome,valor, quantidade, tarja)"+
                "values"+
                "('"+ nome + "', '" + valor + "', '" + quantidade + "','"+ tarja + "')";       
        exec.openDatabase();
        exec.executarQuery(sql);
        exec.closeDatabase();
    }
    private static void removerProduto() throws SQLException {
        System.out.print("ID do produto a ser removido: ");
        int id = scanner.nextInt();
        Conexao exec = new Conexao();
        String sql = ("DELETE FROM produtos WHERE id = "+ id);
        exec.openDatabase();
        exec.executarQuery(sql);
        exec.closeDatabase();    
    }
    private static void menuVendedor(Vendedor vendedor) {
        while (true) {
            System.out.println("Menu Vendedor:");
            System.out.println("1. Realizar venda");
            System.out.println("2. Listar produtos");
            System.out.println("3. Listar um produto");
            System.out.println("4. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                List<Produto> produtos = listarProdutos();

                if (produtos.isEmpty()) {
                    System.out.println("Nenhum dado encontrado.");
                } else {
                    System.out.println("Dados encontrados:");
                    for (Produto produto : produtos) {
                        System.out.println(produto);
                    }
                }
                    break;
                case 3:
                List<Produto> umProduto = listarUmProduto();
                if (umProduto.isEmpty()) {
                    System.out.println("Nenhum dado encontrado.");
                } else {
                    System.out.println("Dados encontrados:");
                    for (Produto produto : umProduto) {
                        System.out.println(produto);
                    }
                }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    private static int realizarVenda() {
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        Conexao exec = new Conexao();
        String sql = ("SELECT id, quantidade, tarja FROM produtos WHERE id = ?");
        String updateSql = ("UPDATE produtos SET quantidade = ? WHERE id = ?");
        Connection connection = null;
        int quantidadedb=0;
        String tarja = null;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
                    selectStatement.setInt(1, id);
                    try (ResultSet resultSet = selectStatement.executeQuery()) {
                        if (resultSet.next()) {
                            quantidadedb = resultSet.getInt("quantidade");
                            tarja = resultSet.getString("tarja");
                        } else {
                            System.out.println("Produto não encontrado.");
                            return -1;
                        }
                    }
                }
                if ("Vermelha".equalsIgnoreCase(tarja) || "Preta".equalsIgnoreCase(tarja)) {
                    System.out.println("O produto exige receita médica.");
                    System.out.println("O cliente apresentou a receita? (1. Sim / 2. Não): ");
                    int receita = scanner.nextInt();
                    scanner.nextLine();
                    switch (receita) {
                        case 1:
                            break;
                        case 2:
                            System.out.println("Venda não realizada. Receita médica não apresentada.");
                            return -1;
                        default:
                            System.out.println("Opção inválida. Venda não realizada.");
                            return -1;
                    }
                }
                if (quantidadedb < quantidade) {
                    System.out.println("Quantidade insuficiente no estoque.");
                    return -1;
                }
                int quantidadeAtual = quantidadedb - quantidade;
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setInt(1, quantidadeAtual);
                    updateStatement.setInt(2, id);
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    exec.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return quantidadedb;
    }
    private static int alterarEstoque() {
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        Conexao exec = new Conexao();
        String sql = ("SELECT id, quantidade FROM produtos WHERE id = ?");
        String updateSql = ("UPDATE produtos SET quantidade = ? WHERE id = ?");
        Connection connection = null;
        int quantidadedb=0;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
                    selectStatement.setInt(1, id);
                    try (ResultSet resultSet = selectStatement.executeQuery()) {
                        if (resultSet.next()) {
                            quantidadedb = resultSet.getInt("quantidade");
                        } else {
                            System.out.println("Produto não encontrado.");
                            return -1;
                        }
                    }
                }
                int quantidadeAtual = quantidadedb + quantidade;
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setInt(1, quantidadeAtual);
                    updateStatement.setInt(2, id);
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    exec.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return quantidadedb;
<<<<<<< HEAD
    }
    private static List<Produto> listarProdutos(){
        ResultSet result = null;
        Conexao exec = new Conexao();
        String sql = "SELECT * from produtos";
        List<Produto> produto = new ArrayList<Produto>();       
        Connection connection = null;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                PreparedStatement selectStatement = connection.prepareStatement(sql);               
                result = selectStatement.executeQuery();                
                while(result.next()){
                    Produto produtos = new Produto(0, null, 0, 0, null);
                    produtos.setId(result.getInt(1));
                    produtos.setNome(result.getString(2));
                    produtos.setPreco(result.getDouble(3));
                    produtos.setQuantidade(result.getInt(4));
                    produtos.setTarja(result.getString(5));
                    produto.add(produtos);
                }
            }        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    exec.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return produto; 
    }

    private static List<Produto> listarUmProduto(){
        ResultSet result = null;
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        Conexao exec = new Conexao();
        String sql = "SELECT id, nome, valor, quantidade, tarja FROM produtos WHERE id = ?";
        List<Produto> produto = new ArrayList<Produto>();       
        Connection connection = null;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                PreparedStatement selectStatement = connection.prepareStatement(sql);
                selectStatement.setInt(1, id);
                result = selectStatement.executeQuery();
                
                if (result.next()) {
                    Produto produtos = new Produto(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getDouble("valor"),
                        result.getInt("quantidade"),
                        result.getString("tarja")
                    );
                    produto.add(produtos);
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    exec.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return produto;
=======
>>>>>>> 8295b90eb9c232b88cb41cd6a9652ef9c3df9215
    }
    private static List<Produto> listarProdutos(){
        ResultSet result = null;
        Conexao exec = new Conexao();
        String sql = "SELECT * from produtos";
        List<Produto> produto = new ArrayList<Produto>();       
        Connection connection = null;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                PreparedStatement selectStatement = connection.prepareStatement(sql);               
                result = selectStatement.executeQuery();                
                while(result.next()){
                    Produto produtos = new Produto(0, null, 0, 0, null);
                    produtos.setId(result.getInt(1));
                    produtos.setNome(result.getString(2));
                    produtos.setPreco(result.getDouble(3));
                    produtos.setQuantidade(result.getInt(4));
                    produtos.setTarja(result.getString(5));
                    produto.add(produtos);
                }
            }        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    exec.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return produto; 
    }

    private static List<Produto> listarUmProduto(){
        ResultSet result = null;
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        Conexao exec = new Conexao();
        String sql = "SELECT id, nome, valor, quantidade, tarja FROM produtos WHERE id = ?";
        List<Produto> produto = new ArrayList<Produto>();       
        Connection connection = null;
        try {
            connection = exec.openDatabase();
            if (connection != null) {
                PreparedStatement selectStatement = connection.prepareStatement(sql);
                selectStatement.setInt(1, id);
                result = selectStatement.executeQuery();
                
                if (result.next()) {
                    Produto produtos = new Produto(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getDouble("valor"),
                        result.getInt("quantidade"),
                        result.getString("tarja")
                    );
                    produto.add(produtos);
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    exec.closeDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return produto;
    }
}
