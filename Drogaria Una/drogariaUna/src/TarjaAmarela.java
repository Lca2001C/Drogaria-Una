public class TarjaAmarela extends Produto {

    public TarjaAmarela(String nome, double preco, int quantidade) {
        super(quantidade, nome, preco, quantidade, nome);
    }

    public String getInformacaoTarja() {
        return "Produto de tarja amarela: venda sob retenção de receita.";
    }
}

