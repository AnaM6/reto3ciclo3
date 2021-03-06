package co.usa.ciclo3.ciclo3.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase Car
 */
@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Variable id
     */
    private Integer idCar;
    /**
     * Variable nombre del carro
     */
    private String name;
    /**
     * Variable marca de carro
     */
    private String brand;
    /**
     * Variable del año del carro
     */
    private Integer year;
    /**
     * Variable de la descripción del carro
     */
    private String description;
    /**
     * variable de la fecha
     *  private Date startDate;
     */



    @ManyToOne
    @JoinColumn(name = "gamaId")
    @JsonIgnoreProperties("cars")
    /**
     * Relación de gama con carro
     */
    private Gama gama;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "car")
    @JsonIgnoreProperties({"car","client"})
    /**
     * Relación de mensajes con carro
     */
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "car")
    @JsonIgnoreProperties({"car","client"})
    /**
     * Relación de reservaciones con carro
     */
    private List<Reservation> reservations;

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
