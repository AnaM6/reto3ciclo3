package co.usa.ciclo3.ciclo3.repository;


import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int idReservation){

        return reservationCrudRepository.findById(idReservation);
    }

    public Reservation save(Reservation re){

        return reservationCrudRepository.save(re);
    }

    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }

    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTwo);
    }

    public List<CountClient> getTopClients(){
        List<CountClient> res = new ArrayList<>();
        List<Object[]> report =reservationCrudRepository.countReservationByClient();
        for (int i=0; i<report.size();i++){
            /*

            Client client= (Client) report.get(i)[0];
            Long cantidad =(Long)  report.get(i)[1];
            CountClient countC = new CountClient(cantidad,client);
            res.add(countC);
            */
            res.add(new CountClient((Long) report.get(i)[1],(Client) report.get(i)[0]));
        }
        return res;
    }

}
