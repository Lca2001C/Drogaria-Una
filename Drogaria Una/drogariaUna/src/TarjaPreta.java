public class TarjaPreta extends Produto {

    public TarjaPreta(String nome, double preco, int quantidade) {
        super(quantidade, nome, preco, quantidade, nome);
    }

    public String getInformacaoTarja() {
        return "Produto de tarja preta: venda sob prescrição e controle especial.";
    }
}
