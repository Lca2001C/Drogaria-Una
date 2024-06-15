public class TarjaVermelha extends Produto {

    public TarjaVermelha(String nome, double preco, int quantidade) {
        super(quantidade, nome, preco, quantidade, nome);
    }

    public String getInformacaoTarja() {
        return "Produto de tarja vermelha: venda sob prescrição médica.";
    }
}
