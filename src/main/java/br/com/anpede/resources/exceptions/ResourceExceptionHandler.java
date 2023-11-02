package br.com.anpede.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.anpede.services.exceptions.DataBaseException;
import br.com.anpede.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		//Instanciamos uma mensagem de erro com base no padrão que criamos
		StandartError error = new StandartError();
		//Horário da ocorrencia
		error.setTimestamp(Instant.now());
		//Código HTTP que enviado, nesse caso 404.
		error.setStatus(HttpStatus.NOT_FOUND.value());
		//Titulo da exceção
		error.setError("Recurso não encontrado");
		//Captura da mensagem de erro interceptada e que foi definida na camada de servico 
		error.setMessage(e.getMessage());
		//Captura da URI que gerou a exceção
		error.setPath(request.getRequestURI());
		//Empacota a exceção no formato HTTP e entrega ao cliente
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandartError> database(DataBaseException e, HttpServletRequest request){
	      HttpStatus status = HttpStatus.BAD_REQUEST;
	      
	      StandartError error = new StandartError();
	      error.setTimestamp(Instant.now());
	      error.setStatus(status.value());
	      error.setError("Database exception");
	      error.setMessage(e.getMessage());
	      error.setPath(request.getRequestURI());
	      return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
	      HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; //422
	      
	      ValidationError error = new ValidationError();
	      error.setTimestamp(Instant.now());
	      error.setStatus(status.value());
	      error.setError("Validation exception");
	      error.setMessage(e.getMessage());
	      error.setPath(request.getRequestURI());
	      	      
	      for (FieldError f : e.getBindingResult().getFieldErrors()) {
	    	  error.addError(f.getField(), f.getDefaultMessage());
	      }
	      
	      return ResponseEntity.status(status).body(error);
	}
}
