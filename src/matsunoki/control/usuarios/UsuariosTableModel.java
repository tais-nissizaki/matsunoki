package matsunoki.control.usuarios;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import matsunoki.bean.Usuario;
import matsunoki.dao.UsuarioDAO;

public class UsuariosTableModel extends AbstractTableModel {
	
	private List<Usuario> usuarios;
	private String[] columnNames = new String[]{"Código", "Nome", "Função"};
	
	public UsuariosTableModel(List<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
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
		usuarios = UsuarioDAO.obterTodosUsuarios();
		super.fireTableDataChanged();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
            case 0:
                return usuarios.get(rowIndex).getIdUsuario();
            case 1:
                return usuarios.get(rowIndex).getNome();
            case 2:
                return usuarios.get(rowIndex).getFuncao();
            default:
                break;
            }
            return null;
	}

	public List<Usuario> getUsuarios() {
            return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
            this.usuarios = usuarios;
	}

}

