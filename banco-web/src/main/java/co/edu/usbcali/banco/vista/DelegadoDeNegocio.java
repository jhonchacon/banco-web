package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.banco.logica.IClienteLogica;
import co.edu.usbcali.banco.logica.ITipoDocumentoLogica;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@Component
@Scope("singleton")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;

	@Override
	public void grabarCliente(Cliente cliente) throws Exception {
		clienteLogica.grabar(cliente);		
	}

	@Override
	public void modificarCliente(Cliente cliente) throws Exception {
		clienteLogica.modificar(cliente);
	}

	@Override
	public void borrarCliente(Cliente cliente) throws Exception {
		clienteLogica.borrar(cliente);		
	}

	@Override
	public Cliente consultarClientePorId(BigDecimal clieId) {
		return clienteLogica.consultarPorId(clieId);
	}

	@Override
	public List<Cliente> consultarClienteTodos() {
		return clienteLogica.consultarTodos();
	}

	@Override
	public void grabarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.grabar(tipoDocumento);		
	}

	@Override
	public void modificarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.modificar(tipoDocumento);		
	}

	@Override
	public void borrarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.borrar(tipoDocumento);		
	}

	@Override
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId) {
		return tipoDocumentoLogica.consultarPorId(tdocId);
	}

	@Override
	public List<TipoDocumento> consultarTipoDocumentoTodos() {
		return tipoDocumentoLogica.consultarTodos();
	}

}
