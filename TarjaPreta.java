public class TarjaPreta extends Produto {

    public TarjaPreta(String nome, double preco, int quantidade) {
        super(nome, preco, quantidade);
    }

    @Override
    public String getInformacaoTarja() {
        return "Produto de tarja preta: venda sob prescrição e controle especial.";
    }
}
