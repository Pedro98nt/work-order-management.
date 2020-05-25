package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ServicoDaoPedro;
import br.com.faculdadedelta.modelo.ServicoPedro;

@ManagedBean
@SessionScoped
public class ServicoControllerPedro {
	
	private ServicoPedro servicoPedro= new ServicoPedro();
	private ServicoDaoPedro dao = new ServicoDaoPedro();

	public ServicoPedro getServico() {
		return servicoPedro;
	}

	public void setServico(ServicoPedro servicoPedro) {
		this.servicoPedro = servicoPedro;
	}
	
	public void limparCampo() {
		servicoPedro = new ServicoPedro();
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
	
		if(servicoPedro.getId()==null) {
			dao.incluir(servicoPedro);
			limparCampo();
			exibirMensagem("inclusão realizada com sucesso");
		} else {
			dao.alterar(servicoPedro);
			limparCampo();
			exibirMensagem("alteração realizada com sucesso");
		}
		}catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
	}
		return "cadastroServico.xhtml";
		
	}
	
	public String editar() {
		return "cadastroServic.xhtml";
		
	}
	
	public String excluir() {
		try {
			dao.excluir(servicoPedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		return "listaServico.xhtml";
		
	}
	
	public List<ServicoPedro> getLista(){
		List<ServicoPedro> listaRetorno = new ArrayList<ServicoPedro>();
		
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
		
		return listaRetorno;
		
	}
	
	
	

}
