package br.edu.ifpb.infra.jsf.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.flow.builder.FlowCallBuilder;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDate;

@FacesValidator(value = "validator.dataNascimento")
public class ValidatorNacimento implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        LocalDate data = (LocalDate) value;
        if(data == null){
            throw new ValidatorException(new FacesMessage("Data inválida"));
        }
        if(data.compareTo(LocalDate.now()) >= 0){
            throw new ValidatorException(new FacesMessage("A data de nascimente não pode ser depois da data atual"));
        }
    }
}
