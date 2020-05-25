package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ClienteDaoPedro;
import br.com.faculdadedelta.modelo.ClientePedro;

@ManagedBean
@SessionScoped
public class ClienteControllerPedro {
	
	private ClientePedro clientePedro = new ClientePedro();
	private ClienteDaoPedro dao = new ClienteDaoPedro();

	public ClientePedro getCliente() {
		return clientePedro;
	}

	public void setCliente(ClientePedro clientePedro) {
		this.clientePedro = clientePedro;
	}
	
	public void limparCampo() {
		clientePedro = new ClientePedro();
		
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
	
		if(clientePedro.getId()==null) {
				dao.incluir(clientePedro);
				limparCampo();
				exibirMensagem("inclusão realizada com sucesso");
			}else {
				dao.alterar(clientePedro);
				limparCampo();
				exibirMensagem("alteção realizada com sucesso");
			}
			} catch (Exception e) {
				e.printStackTrace();
				exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		return "cadastroCliente.xhtml";
	}
	
	public String editar() {
		return "cadastroCliente.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(clientePedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return "listaCliente.xhtml";
		
	}
	
	public List<ClientePedro> getLista(){
		List<ClientePedro>listaRetorno = new ArrayList<ClientePedro>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		
		return listaRetorno;
		
	}
	
	

}
