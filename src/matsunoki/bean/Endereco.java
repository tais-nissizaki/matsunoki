/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matsunoki.bean;

/**
 *
 * @author Tais
 */
public class Endereco {
    private int codigoEndereco;
    private int codigoCliente;
    private String nomeLogradouro;
    private int numeroLogradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(int codigoEndereco, int codigoCliente, String nomeLogradouro, int numeroLogradouro, String complemento, String bairro, String cidade, String estado, String cep) {
        this.codigoEndereco = codigoEndereco;
        this.codigoCliente = codigoCliente;
        this.nomeLogradouro = nomeLogradouro;
        this.numeroLogradouro = numeroLogradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco(String nomeLogradouro, int numeroLogradouro, String complemento, String bairro, String cidade, String estado, String cep) {
        this.nomeLogradouro = nomeLogradouro;
        this.numeroLogradouro = numeroLogradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco() {
    }

    /**
     * @return the codigoEndereco
     */
    public int getCodigoEndereco() {
        return codigoEndereco;
    }

    /**
     * @param codigoEndereco the codigoEndereco to set
     */
    public void setCodigoEndereco(int codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    /**
     * @return the codigoCliente
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return the nomeLogradouro
     */
    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    /**
     * @param nomeLogradouro the nomeLogradouro to set
     */
    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    /**
     * @return the numerpLogradouro
     */
    public int getNumeroLogradouro() {
        return numeroLogradouro;
    }

    /**
     * @param numerpLogradouro the numerpLogradouro to set
     */
    public void setNumeroLogradouro(int numerpLogradouro) {
        this.numeroLogradouro = numerpLogradouro;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getEnderecoComoTexto() {
        String enderecoComoTexto = "";
        enderecoComoTexto += this.nomeLogradouro + ", ";
        enderecoComoTexto += this.numeroLogradouro + ". ";
        enderecoComoTexto += this.bairro + ", ";
        enderecoComoTexto += this.cidade + "-";
        enderecoComoTexto += this.estado;
        return enderecoComoTexto;
    }
            
}
