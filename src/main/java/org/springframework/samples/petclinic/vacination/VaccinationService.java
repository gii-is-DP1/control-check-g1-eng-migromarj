package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService {


    @Autowired
    VaccinationRepository vr;

    public List<Vaccination> getAll(){
        return vr.findAll();
    }

    public List<Vaccine> getAllVaccines(){
        return null;
    }

    public Vaccine getVaccine(String typeName) {
        return vr.findVaccine(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination p) throws UnfeasibleVaccinationException {
        
        if(p.getVaccinatedPet().getType()==p.getVaccine().getPetType()){
            
            vr.save(p);
            return p; 
        }
        
        throw new UnfeasibleVaccinationException();

    }

    
}
