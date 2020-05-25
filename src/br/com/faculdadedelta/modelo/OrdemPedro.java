package br.com.faculdadedelta.modelo;

public class OrdemPedro {
	
	private Long id;
	private ClientePedro clientePedro;
	private ServicoPedro servicoPedro;
	private double valorUnitario;
	private  int quantidade;
	private  double valorDesconto;
	private double valorTotal;
	public OrdemPedro() {
		super();
	}
	public OrdemPedro(Long id, ClientePedro clientePedro, ServicoPedro servicoPedro, double valorUnitario, int quantidade, double valorDesconto,
			double valorTotal) {
		super();
		this.id = id;
		this.clientePedro = clientePedro;
		this.servicoPedro = servicoPedro;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.valorDesconto = valorDesconto;
		this.valorTotal = valorTotal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ClientePedro getCliente() {
		return clientePedro;
	}
	public void setCliente(ClientePedro clientePedro) {
		this.clientePedro = clientePedro;
	}
	public ServicoPedro getServico() {
		return servicoPedro;
	}
	public void setServico(ServicoPedro servicoPedro) {
		this.servicoPedro = servicoPedro;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemPedro other = (OrdemPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
