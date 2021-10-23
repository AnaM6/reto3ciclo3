package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.model.Gama;
import co.usa.ciclo3.ciclo3.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.getAll();
    }

    public Optional<Car> getCar(int id){
        return carRepository.getCar(id);
    }

    public Car save(Car c){
        if(c.getIdCar()==null){
            return carRepository.save(c);
        }else{
            Optional<Car> caux = carRepository.getCar(c.getIdCar());
            if(caux.isEmpty()){
                return carRepository.save(c);
            }else{
                return c;
            }
        }
    }

    public Car update(Car car){
        if(car.getIdCar()!=null){
            Optional<Car>g= carRepository.getCar(car.getIdCar());
            if (!g.isEmpty()){
                if(car.getName()!=null){
                    g.get().setName(car.getName());
                }
                if (car.getBrand()!=null){
                    g.get().setBrand(car.getBrand());
                }
                if (car.getYear()!=null){
                    g.get().setYear(car.getYear());
                }
                if (car.getDescription()!=null){
                    g.get().setDescription(car.getDescription());
                }

                return carRepository.save(g.get());
            }
        }
        return car;
    }

    public boolean deleteCar(int idCar){
        Boolean del = getCar(idCar).map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
        return del;
    }


}
