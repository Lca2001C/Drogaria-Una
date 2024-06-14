public class Vendedor extends Funcionario {

    public Vendedor(String nome, int id) {
        super(nome, id);
    }

    @Override
    public void cadastrarProduto() {
        System.out.println("Acesso negado: Vendedor não pode cadastrar produtos.");
    }

    @Override
    public void realizarVenda() {
        System.out.println("Venda realizada pelo vendedor " + getNome());
    }

    @Override
    public int getTipo() {
        return 1;
    }
}
