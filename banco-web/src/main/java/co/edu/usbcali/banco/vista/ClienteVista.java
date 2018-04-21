package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ManagedBean
public class ClienteVista {
	
	@ManagedProperty("#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	private List<Cliente> listaClientes;
	
	private InputText txtIdentificacion;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtEmail;
	
	private SelectOneMenu somTipoDocumento;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> losTiposDocumentosItems;
	
	public String crearAction() {
		
		try {
			BigDecimal id=new BigDecimal(txtIdentificacion.getValue().toString());
			Long tdocCodigo=Long.parseLong(somTipoDocumento.getValue().toString());
			
			Cliente cliente=new Cliente();
			cliente.setActivo('S');
			cliente.setClieId(id);
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtEmail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			
			TipoDocumento tipoDocumento=delegadoDeNegocio.consultarTipoDocumentoPorId(tdocCodigo);
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.grabarCliente(cliente);
			
			FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se creo con exito", "");
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		}
		return "";
	}
	
	public String modificarAction() {
		try {
			BigDecimal id=new BigDecimal(txtIdentificacion.getValue().toString());
			Long tdocCodigo=Long.parseLong(somTipoDocumento.getValue().toString());
			
			Cliente cliente=new Cliente();
			cliente.setActivo('S');
			cliente.setClieId(id);
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtEmail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			
			TipoDocumento tipoDocumento=delegadoDeNegocio.consultarTipoDocumentoPorId(tdocCodigo);
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.modificarCliente(cliente);
			
			FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se modificó con exito", "");
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		}
		return "";
	}
	
	public String borrarAction() {
		try {
			BigDecimal id=new BigDecimal(txtIdentificacion.getValue().toString());
			Long tdocCodigo=Long.parseLong(somTipoDocumento.getValue().toString());
			
			Cliente cliente=new Cliente();
			cliente.setActivo('S');
			cliente.setClieId(id);
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtEmail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			
			TipoDocumento tipoDocumento=delegadoDeNegocio.consultarTipoDocumentoPorId(tdocCodigo);
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.borrarCliente(cliente);
			
			FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se borro con exito", "");
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", facesMessage);
		}
		return "";
	}
	
	public String limpiarAction() {
		txtDireccion.resetValue();
		txtEmail.resetValue();
		txtNombre.resetValue();
		txtTelefono.resetValue();
		somTipoDocumento.resetValue();
		txtIdentificacion.resetValue();
		
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		btnBorrar.setDisabled(true);
		
		return "";
	}
	
	public void txtIdentificacionListener() {
		try {
			BigDecimal cliId=new BigDecimal(txtIdentificacion.getValue().toString());
			Cliente cliente=delegadoDeNegocio.consultarClientePorId(cliId);
			
			if(cliente==null) {
				
				txtDireccion.resetValue();
				txtEmail.resetValue();
				txtNombre.resetValue();
				txtTelefono.resetValue();
				somTipoDocumento.resetValue();
				
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
				btnBorrar.setDisabled(true);				
			}else {
				txtDireccion.setValue(cliente.getDireccion());
				txtEmail.setValue(cliente.getEmail());
				txtNombre.setValue(cliente.getNombre());
				txtTelefono.setValue(cliente.getTelefono());
				
				somTipoDocumento.setValue(cliente.getTipoDocumento().getTdocId());
				
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				btnBorrar.setDisabled(false);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<SelectItem> getLosTiposDocumentosItems() {
		if(losTiposDocumentosItems==null) {
			List<TipoDocumento> losDocumentos=delegadoDeNegocio.consultarTipoDocumentoTodos();
			losTiposDocumentosItems=new ArrayList<>();
			for (TipoDocumento tipoDocumento : losDocumentos) {
				SelectItem selectItem=new SelectItem();
				
				selectItem.setValue(tipoDocumento.getTdocId());
				selectItem.setLabel(tipoDocumento.getNombre());
				
				losTiposDocumentosItems.add(selectItem);
			}
		}
		return losTiposDocumentosItems;
	}

	public void setLosTiposDocumentosItems(List<SelectItem> losTiposDocumentosItems) {
		this.losTiposDocumentosItems = losTiposDocumentosItems;
	}

	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public InputText getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public InputText getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(InputText txtEmail) {
		this.txtEmail = txtEmail;
	}

	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public List<Cliente> getListaClientes() {
		if(listaClientes==null) {
			listaClientes=delegadoDeNegocio.consultarClienteTodos();
		}
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}	
	

}
