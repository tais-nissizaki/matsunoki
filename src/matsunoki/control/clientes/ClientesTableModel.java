package matsunoki.control.clientes;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import matsunoki.bean.Cliente;
import matsunoki.dao.ClienteDAO;

public class ClientesTableModel extends AbstractTableModel {
	
	private List<Cliente> clientes;
	private String[] columnNames = new String[]{"Código", "Nome", "E-Mail", "Endereço"};
	
	public ClientesTableModel(List<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}
	
	@Override
	public void fireTableDataChanged() {// método que dispara as alterações da tabela e consolida na tela
		clientes = ClienteDAO.obterTodosClientes();
		super.fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
            case 0:
                return clientes.get(rowIndex).getCodigo();
            case 1:
                return clientes.get(rowIndex).getNome();
            case 2:
                return clientes.get(rowIndex).getEmail();
            case 3:
                if(clientes.get(rowIndex).getEndereco() != null) {
                    return clientes.get(rowIndex).getEndereco().getEnderecoComoTexto();
                } else {
                    return "-";   
                }
            default:
                break;
            }
            return null;
	}

	public List<Cliente> getClientes() {
            return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
            this.clientes = clientes;
	}

}

