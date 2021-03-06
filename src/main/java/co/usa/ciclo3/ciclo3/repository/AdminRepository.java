package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Admin;
import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.repository.crud.AdminCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {

    @Autowired
    private AdminCrudRepository adminCrudRepository;

    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();
    }

    public Optional<Admin> getAdmin(int IdAdmin){
        return adminCrudRepository.findById(IdAdmin);
    }

    public Admin save(Admin a){
        return adminCrudRepository.save(a);
    }

    public void delete(Admin admin){
        adminCrudRepository.delete(admin);
    }
}
