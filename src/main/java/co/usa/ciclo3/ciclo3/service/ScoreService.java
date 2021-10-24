package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.model.Score;
import co.usa.ciclo3.ciclo3.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int idScore){
        return scoreRepository.getScore(idScore);
    }

    public Score save(Score s){
        if(s.getIdScore()==null){
            return scoreRepository.save(s);
        }else{
            Optional<Score> saux = scoreRepository.getScore(s.getIdScore());
            if(saux.isEmpty()){
                return scoreRepository.save(s);
            }else{
                return s;
            }
        }
    }

    public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score>g= scoreRepository.getScore(score.getIdScore());
            if (!g.isEmpty()){
                if(score.getScore()!=null){
                    g.get().setScore(score.getScore());
                }
                return scoreRepository.save(g.get());
            }
        }
        return score;
    }

    public boolean deleteScore(int idScore){
        Boolean del = getScore(idScore).map(score -> {
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
        return del;
    }


}
