package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

public interface IDelegadoDeNegocio {
	
	public void grabarCliente(Cliente cliente)throws Exception;
	public void modificarCliente(Cliente cliente)throws Exception;
	public void borrarCliente(Cliente cliente)throws Exception;
	public Cliente consultarClientePorId(BigDecimal clieId);
	public List<Cliente> consultarClienteTodos();
	
	public void grabarTipoDocumento(TipoDocumento tipoDocumento)throws Exception;
	public void modificarTipoDocumento(TipoDocumento tipoDocumento)throws Exception;
	public void borrarTipoDocumento(TipoDocumento tipoDocumento)throws Exception;
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId);
	public List<TipoDocumento> consultarTipoDocumentoTodos();

}
