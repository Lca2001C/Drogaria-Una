
public class Administrador extends Funcionario {

    public Administrador(String nome, int id) {
        super(nome, id);
    }

    @Override
    public void cadastrarProduto() {
        System.out.println("Produto cadastrado pelo administrador " + getNome());
    }

    @Override
    public void realizarVenda() {
        System.out.println("Acesso negado: Administrador não pode realizar vendas.");
    }
    @Override
    public int getTipo() {
        return 2;
    }
}