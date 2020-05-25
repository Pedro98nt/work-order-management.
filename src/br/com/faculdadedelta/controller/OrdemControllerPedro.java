package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.OrdemDaoPedro;
import br.com.faculdadedelta.modelo.ClientePedro;
import br.com.faculdadedelta.modelo.OrdemPedro;
import br.com.faculdadedelta.modelo.ServicoPedro;

@ManagedBean
@SessionScoped
public class OrdemControllerPedro {
	
	private OrdemPedro ordemPedro = new OrdemPedro();
	private OrdemDaoPedro dao = new OrdemDaoPedro();
	private ClientePedro clienteSelecionado = new ClientePedro();
	private ServicoPedro servicoSelecionado = new ServicoPedro();

	public OrdemPedro getOrdem() {
		return ordemPedro;
	}

	public void setOrdem(OrdemPedro ordemPedro) {
		this.ordemPedro = ordemPedro;
	}

	public ClientePedro getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClientePedro clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ServicoPedro getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(ServicoPedro servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}
	
	public void limparCampo() {
		ordemPedro= new OrdemPedro();
		clienteSelecionado = new ClientePedro();
		servicoSelecionado = new ServicoPedro();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
	 if(ordemPedro.getId()==null) {
	ordemPedro.setCliente(clienteSelecionado);
	ordemPedro.setServico(servicoSelecionado);
	dao.incluir(ordemPedro);
	limparCampo();
	exibirMensagem("inclusão realizada com sucesso");

	 }else {
		 ordemPedro.setCliente(clienteSelecionado);
			ordemPedro.setServico(servicoSelecionado);
		 dao.alterar(ordemPedro);
		 limparCampo();
		 exibirMensagem("alteração realizada com sucesso");
		
	}
	 
	} catch (Exception e) {
		e.printStackTrace();
		exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		 
	}
		
		return "cadastroOrdem.xhtml";
		
	}
	
	public String editar() {
		clienteSelecionado =ordemPedro.getCliente();
		servicoSelecionado = ordemPedro.getServico();
		return "cadastroOrdem.xhtml";
		
	}
	
	public String excluir() {
		try {
			dao.excluir(ordemPedro);
			limparCampo();
			exibirMensagem("exclusão realizada com sucesso");
	
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
		}
	
		return "listaOrdem.xhtml";
		
	}
	
	public List<OrdemPedro> getListar(){
	List<OrdemPedro> listaRetorno = new ArrayList<OrdemPedro>();
	
	try {
		listaRetorno = dao.listar();
	} catch (Exception e) {
		e.printStackTrace();
		exibirMensagem("erro no processo"+"tente mais tarde"+e.getMessage());
	}
		
		
		return listaRetorno;
		
	}
	
	
}
