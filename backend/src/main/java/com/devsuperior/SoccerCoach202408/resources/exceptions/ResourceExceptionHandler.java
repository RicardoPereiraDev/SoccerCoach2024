package com.devsuperior.SoccerCoach202408.resources.exceptions;

import com.devsuperior.SoccerCoach202408.services.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.InstanceAlreadyExistsException;
import java.time.Instant;

@ControllerAdvice // É isto que vai permitir que essa classe intercepte alguma excepção lá na camada de resource, na camada de controlador REST e é ele que vai controlar agora
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError>entityNotFound (EntityNotFoundException e, HttpServletRequest request){

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value()); //pega o numero inteiro
        err.setError("Resource not found"); //esse é o erro que sempre vai acontecer quado voce tentar buscar uma entidade e der um entityNotFoundException
        err.setMessage(e.getMessage()); //Aqui vai pegar a mensagem que já foi definida lá no meu service
        err.setPath(request.getRequestURI()); // Aqui vai pegar o caminho da requesição
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


}
