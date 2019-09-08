package br.edu.ifpb.infra.jsf.Converter;


import javax.faces.component.UIComponent;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.infra.dao.interfaces.DependenteDao;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

@FacesConverter(value = "convert.dependente")
public class ConvertDep implements Converter {

    private DependenteDao depDao = CDI.current().select(DependenteDao.class).get();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return depDao.buscar(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Dependente dependente = (Dependente) value;
        return dependente.getUuid();
    }
}
