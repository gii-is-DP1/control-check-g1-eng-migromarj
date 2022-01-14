package org.springframework.samples.petclinic.vacination;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {
    
    @Autowired
    private VaccinationService vs;

    @Autowired
    private PetService ps;

    @GetMapping(value = "/create")
	public String initCreationForm(ModelMap model) {
		Vaccination vaccination = new Vaccination();
		model.put("vaccination", vaccination);
        model.put("vaccines",vs.getAllVaccines());
		model.put("pets",ps.findAllPets());
        return "vaccination/createOrUpdateVaccinationForm";
	}

    @PostMapping(value = "/create")
	public String processCreationForm(@Valid Vaccination vaccination, BindingResult result, ModelMap model){		
		if (result.hasErrors()) {
			model.put("vaccination", vaccination);
			return "vaccination/createOrUpdateVaccinationForm";
		}
		else {

            try{
                this.vs.save(vaccination);
                return "welcome";
            }catch(UnfeasibleVaccinationException ex){
                result.rejectValue("vaccine", "La mascota seleccionada no puede recibir la vacuna especificada.", "La mascota seleccionada no puede recibir la vacuna especificada.");
                //model.put("message", "La mascota seleccionada no puede recibir la vacuna especificada.");
                return "vaccination/createOrUpdateVaccinationForm";
            }
                
            
		}
	}

}
