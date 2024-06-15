public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private String tarja;

    public Produto(int id, String nome, double preco, int quantidade, String tarja) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tarja = tarja;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getTarja() {
        return tarja;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTarja(String tarja) {
        this.tarja = tarja;
    }

    @Override
    public String toString() {
        return "Produto [id= " + id + ", nome= " + nome + ", preco= " + preco + ", quantidade= " + quantidade + ", tarja= " + tarja + "]";
    }
}





