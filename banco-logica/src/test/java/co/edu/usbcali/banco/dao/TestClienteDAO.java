package co.edu.usbcali.banco.dao;

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
class TestClienteDAO {
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	BigDecimal clieId=new BigDecimal(10101010);
	

	@Test
	@DisplayName("CrearCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		Cliente cliente=new Cliente();
	
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("555 555 5555");
		
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarPorId(1L);
		assertNotNull(tipoDocumento,"El tipo de documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.grabar(cliente);		
	}
	
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Test
	void cTest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente, "El cliente que desea modificar no existe");
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homero Simpson");
		cliente.setTelefono("555 555 5555");
		
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarPorId(2L);
		assertNotNull(tipoDocumento,"El tipo de documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteDAO.modificar(cliente);		
	}
	
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Test
	void dTest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente, "El cliente que desea borrar no existe");
		
		clienteDAO.borrar(cliente);		
	}
	
	@DisplayName("ConsultarClientePorId")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Test
	void bTest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente, "El cliente que desea borrar no existe");	
	}
	
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Test
	void eTest() {
		assertNotNull(clienteDAO);
		assertNotNull(tipoDocumentoDAO);
		List<Cliente> losClientes=clienteDAO.consultarTodos();
		assertNotNull(losClientes,"La lista es nula");
		assertNotEquals(0, losClientes.size());
	}

}
