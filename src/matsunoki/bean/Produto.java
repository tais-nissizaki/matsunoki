package matsunoki.bean;

public class Produto {
	private int codigoProduto;
	private String descricao;
	private double preco;
        private String caminhoImagem;

	public Produto(int codigoProduto, String descricao, double preco, String caminhoImagem) {
		super();
		this.codigoProduto = codigoProduto;
		this.descricao = descricao;
		this.preco = preco;
                this.caminhoImagem = caminhoImagem;
	}

	public Produto(String descricao, double preco, String caminhoImagem) {
		super();
		this.descricao = descricao;
		this.preco = preco;
                this.caminhoImagem = caminhoImagem;
	}

	public Produto() {
		super();
	}

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

    /**
     * @return the caminhoImagem
     */
    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    /**
     * @param caminhoImagem the caminhoImagem to set
     */
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

}
