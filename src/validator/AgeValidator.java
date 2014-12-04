package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "agevalidator")
public class AgeValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{
		int name =(int)value;
    	
        if(name>100)	
        {
        	 throw new ValidatorException(new FacesMessage("'"+value+"' is not a correct age  "));
        }
	    	
	 }

}
