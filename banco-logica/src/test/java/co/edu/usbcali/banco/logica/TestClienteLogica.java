package co.edu.usbcali.banco.logica;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestClienteLogica {
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	BigDecimal clieId=new BigDecimal(10101010);
	
	@Test
	@DisplayName("CrearCliente")
	void aTest()throws Exception {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		Cliente cliente=new Cliente();
	
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 5555");
		
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarPorId(1L);
		assertNotNull(tipoDocumento,"El tipo de documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.grabar(cliente);		
	}
	
	@DisplayName("ModificarCliente")
	@Test
	void cTest()throws Exception {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente, "El cliente que desea modificar no existe");
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero Simpson");
		cliente.setTelefono("555 555 5555");
		
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarPorId(2L);
		assertNotNull(tipoDocumento,"El tipo de documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteLogica.modificar(cliente);		
	}
	
	@DisplayName("BorrarCliente")
	@Test
	void dTest()throws Exception {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente, "El cliente que desea borrar no existe");
		
		clienteLogica.borrar(cliente);		
	}
	
	@DisplayName("ConsultarClientePorId")
	@Test
	void bTest() {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente, "El cliente que desea borrar no existe");	
	}
	
	@DisplayName("ConsultarTodos")
	@Test
	void eTest() {
		assertNotNull(clienteLogica);
		assertNotNull(tipoDocumentoLogica);
		List<Cliente> losClientes=clienteLogica.consultarTodos();
		assertNotNull(losClientes,"La lista es nula");
		assertNotEquals(0, losClientes.size());
	}

}
