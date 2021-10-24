package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return (List<Client>) clientRepository.getAll();
    }

    public Optional<Client> getClient(int idClient){
        return clientRepository.getClient(idClient);
    }

    public Client save(Client cl){
        if(cl.getIdClient()==null){
            return clientRepository.save(cl);
        }else{
            Optional<Client> claux = clientRepository.getClient(cl.getIdClient());
            if(claux.isEmpty()){
                return clientRepository.save(cl);
            }else{
                return cl;
            }
        }
    }

    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client>g= clientRepository.getClient(client.getIdClient());
            if (!g.isEmpty()){
                if(client.getEmail()!=null){
                    g.get().setEmail(client.getEmail());
                }
                if (client.getPassword()!=null){
                    g.get().setPassword(client.getPassword());
                }
                if(client.getName()!=null){
                    g.get().setName(client.getName());
                }

                if (client.getAge()!=null){
                    g.get().setAge(client.getAge());
                }
                return clientRepository.save(g.get());
            }
        }
        return client;
    }

    public boolean deleteClient(int idClient){
        Boolean del = getClient(idClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return del;
    }

}
