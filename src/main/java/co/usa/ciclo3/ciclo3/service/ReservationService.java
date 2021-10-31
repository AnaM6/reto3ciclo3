package co.usa.ciclo3.ciclo3.service;



import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.model.custom.StatusAmount;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Clase Reservation Service
 * Realizado por Ana Mendoza grupo G1
 */
@Service
public class ReservationService {

    @Autowired
    /**
     * Definición de variable reservationRepository
     */
    private ReservationRepository resRepository;

    /**
     * Función para obtener los elementos de la tabla reservation
     * @return todos los elementos
     */
    public List<Reservation> getAll(){
        return resRepository.getAll();
    }

    /**
     * Función para obtener una reserva en especifico
     * @param idReservation variable para indicar cual deseo obtener
     * @return el elemento que pertenece a ese id
     */
    public Optional<Reservation> getReservation(int idReservation){
        return resRepository.getReservation(idReservation);
    }

    /**
     * Función para guardar elementos
     * @param reser elemento a guardar
     * @return elemento guardado
     */
    public Reservation save(Reservation reser){
        if(reser.getIdReservation()==null){
            return resRepository.save(reser);
        }else{
            Optional<Reservation> raux = resRepository.getReservation(reser.getIdReservation());
            if(raux.isEmpty()){
                return resRepository.save(reser);
            }else{
                return reser;
            }
        }
    }

    /**
     * Función para actualizar un elemento que ya exista
     * @param reservation elemento a actualizar
     * @return el elemento actualizado
     */
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation>reservation1= resRepository.getReservation(reservation.getIdReservation());
            if (!reservation1.isEmpty()){
                if (reservation.getStartDate()!=null){
                    reservation1.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    reservation1.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                return resRepository.save(reservation1.get());
            }
        }
        return reservation;
    }

    /**
     * Función para borrar un elemento
     * @param idRes indica el id del elemento que se va a borrar
     * @return lugar vacio del elemento que fue borrado
     */

    public boolean deleteReservation(int idRes){
        Boolean delet = getReservation(idRes).map(reservation -> {
            resRepository.delete(reservation);
            return true;
        }).orElse(false);
        return delet;
    }

    /**
     * Conteo de clientes, para saber que cliente tiene mas reservas
     * @return repository
     */
    public List<CountClient> getTopClients(){
        return resRepository.getTopClients();
    }

    /**
     * Función del status
     * @return retorno el status para saber cuantos cancelados y completos existen
     */
    public StatusAmount getStatusReport(){
        List<Reservation> completed=resRepository.getReservationByStatus("completed");
        List<Reservation> cancelled=resRepository.getReservationByStatus("cancelled");

        StatusAmount stAmount = new StatusAmount(completed.size(),cancelled.size());
        return stAmount;
    }

    /**
     * Funcion para el periodo que deseo consultar
     * @param date1 fecha numero uno
     * @param date2 fecha numero dos
     * @return el periodo de tiempo que quiero consultar
     */
    public List<Reservation> getReservationPeriod(String date1, String date2){
        SimpleDateFormat parser= new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        try{
            dateOne = parser.parse(date1);
            dateTwo = parser.parse(date2);
        }catch (ParseException excep){
            excep.printStackTrace();
        }
        if(dateOne.before(dateTwo)){
            return resRepository.getReservationPeriod(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
    }
}
