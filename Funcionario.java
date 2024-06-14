public abstract class Funcionario {
    private String nome;
    private int id;

    public Funcionario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }


    public abstract void cadastrarProduto();

    public abstract void realizarVenda();

    public abstract int getTipo();

}

