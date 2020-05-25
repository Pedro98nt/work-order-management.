package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.ServicoDaoPedro;
import br.com.faculdadedelta.modelo.ServicoPedro;

@FacesConverter (value = "servicoConverter")
public class ServicoConverterPedro implements Converter {

	private ServicoDaoPedro dao = new ServicoDaoPedro();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if(valor!=null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		
		if(valor!=null) {
			return String.valueOf(((ServicoPedro)valor).getId());
		}
		return null;
	}

}
