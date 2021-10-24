package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Gama;
import co.usa.ciclo3.ciclo3.repository.crud.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    @Autowired
    private ClientCrudRepository clientCrudRepository;

    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }

    public Optional<Client> getClient(int idClient){
        return clientCrudRepository.findById(idClient);
    }

    public Client save(Client cl){
        return clientCrudRepository.save(cl);
    }

    public void delete(Client cl){
        clientCrudRepository.delete(cl);
    }
}


