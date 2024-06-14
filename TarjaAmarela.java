public class TarjaAmarela extends Produto {

    public TarjaAmarela(String nome, double preco, int quantidade) {
        super(nome, preco, quantidade);
    }

    @Override
    public String getInformacaoTarja() {
        return "Produto de tarja amarela: venda sob retenção de receita.";
    }
}

