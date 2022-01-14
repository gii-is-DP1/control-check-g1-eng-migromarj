package org.springframework.samples.petclinic.vacination;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VaccinationRepository extends CrudRepository<Vaccination,Integer>{
    List<Vaccination> findAll();

    @Query("Select v From Vaccine v")
    List<Vaccine> findAllVaccines();

    @Query("Select v From Vaccine v where v.name LIKE :name")
    Vaccine findVaccine(String name);
    
    Optional<Vaccination> findById(int id);
    Vaccination save(Vaccination p);
}
