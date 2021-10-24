package co.usa.ciclo3.ciclo3.service;


import co.usa.ciclo3.ciclo3.model.Admin;
import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int idAdmin){
        return adminRepository.getAdmin(idAdmin);
    }

    public Admin save(Admin a){
        if(a.getIdAdmin()==null){
            return adminRepository.save(a);
        }else{
            Optional<Admin> adaux = adminRepository.getAdmin(a.getIdAdmin());
            if(adaux.isEmpty()){
                return adminRepository.save(a);
            }else{
                return a;
            }
        }
    }

    public Admin update(Admin admin){
        if(admin.getIdAdmin()!=null){
            Optional<Admin>g= adminRepository.getAdmin(admin.getIdAdmin());
            if (!g.isEmpty()){
                if(admin.getName()!=null){
                    g.get().setName(admin.getName());
                }
                if (admin.getEmail()!=null){
                    g.get().setEmail(admin.getEmail());
                }
                if (admin.getPassword()!=null){
                    g.get().setPassword(admin.getPassword());
                }
                return adminRepository.save(g.get());
            }
        }
        return admin;
    }

    public boolean deleteAdmin(int idAdmin){
        Boolean del = getAdmin(idAdmin).map(admin -> {
            adminRepository.delete(admin);
            return true;
        }).orElse(false);
        return del;
    }
}
