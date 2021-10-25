package co.usa.ciclo3.ciclo3.service;



import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ReservationRepository reservationRepository;

    /**
     * Función para obtener los elementos de la tabla reservation
     * @return todos los elementos
     */
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    /**
     * Función para obtener una reserva en especifico
     * @param idReservation variable para indicar cual deseo obtener
     * @return el elemento que pertenece a ese id
     */
    public Optional<Reservation> getReservation(int idReservation){
        return reservationRepository.getReservation(idReservation);
    }

    /**
     * Función para guardar elementos
     * @param r elemento a guardar
     * @return elemento guardado
     */
    public Reservation save(Reservation r){
        if(r.getIdReservation()==null){
            return reservationRepository.save(r);
        }else{
            Optional<Reservation> raux = reservationRepository.getReservation(r.getIdReservation());
            if(raux.isEmpty()){
                return reservationRepository.save(r);
            }else{
                return r;
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
            Optional<Reservation>g= reservationRepository.getReservation(reservation.getIdReservation());
            if (!g.isEmpty()){
                if (reservation.getStartDate()!=null){
                    g.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    g.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                return reservationRepository.save(g.get());
            }
        }
        return reservation;
    }

    /**
     * Función para borrar un elemento
     * @param id indica el id del elemento que se va a borrar
     * @return lugar vacio del elemento que fue borrado
     */

    public boolean deleteReservation(int id){
        Boolean del = getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return del;
    }
}
