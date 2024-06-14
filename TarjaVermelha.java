public class TarjaVermelha extends Produto {

    public TarjaVermelha(String nome, double preco, int quantidade) {
        super(nome, preco, quantidade);
    }

    @Override
    public String getInformacaoTarja() {
        return "Produto de tarja vermelha: venda sob prescrição médica.";
    }
}
