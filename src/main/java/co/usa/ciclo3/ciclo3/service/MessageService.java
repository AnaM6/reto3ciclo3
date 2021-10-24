package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Car;
import co.usa.ciclo3.ciclo3.model.Gama;
import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int idMessage){
        return messageRepository.getMessage(idMessage);
    }

    public Message save(Message m){
        if(m.getIdMessage()==null){
            return messageRepository.save(m);
        }else{
            Optional<Message> maux = getMessage(m.getIdMessage());
            if(maux.isEmpty()){
                return messageRepository.save(m);
            }else{
                return m;
            }
        }
    }
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message>g= messageRepository.getMessage(message.getIdMessage());
            if (!g.isEmpty()){
                if (message.getMessageText()!=null){
                    g.get().setMessageText(message.getMessageText());
                }
                return messageRepository.save(g.get());
            }
        }
        return message;
    }

    public boolean deleteMessage(int id){
        Boolean del = getMessage(id).map(gama -> {
            messageRepository.delete(gama);
            return true;
        }).orElse(false);
        return del;
    }

}
