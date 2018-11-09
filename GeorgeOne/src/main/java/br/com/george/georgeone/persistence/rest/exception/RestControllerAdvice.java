package br.com.george.georgeone.persistence.rest.exception;

import java.io.IOException;
import java.net.ConnectException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.george.georgeone.persistence.rest.AbstractController;

@ControllerAdvice(assignableTypes=AbstractController.class)
@RestController
@RequestMapping(produces = "application/vnd.error+json")
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

	public RestControllerAdvice() {
		  super();
	}
	  
	/**
	 * Tratamento das exceções inesperadas.
	 * Http Status 500.
	 * @param ex
	 * @param request
	 * @return
	 */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpBodyError>  handleAllExceptions(RuntimeException e) {
    	HttpBodyError httpBodyError = new HttpBodyError("500",e.getMessage(),"Erro inesperado.",HttpStatus.INTERNAL_SERVER_ERROR+"",null,null );
    	return new ResponseEntity<>(httpBodyError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<HttpBodyError>  handleConnect(ConnectException e) {
    	HttpBodyError httpBodyError = new HttpBodyError("500",e.getMessage() + " ; " + e.getCause().getMessage(),"Erro de conexão com Banco.",HttpStatus.INTERNAL_SERVER_ERROR+"",null,null );
    	return new ResponseEntity<>(httpBodyError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Tratamento das exceções esperadas/tratadas, provenientes da cada Service Layer.
     * Http Status 400
     * 
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<HttpBodyError> handleBusinessException(BusinessException e, WebRequest request) {
    	HttpBodyError httpBodyError = new HttpBodyError("400","Violação de regras de negócio.","Violação de regras de negócio.",HttpStatus.BAD_REQUEST+"",request.getDescription(false),e.getBusinessErrors() );
    	return new ResponseEntity<HttpBodyError>(httpBodyError, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Tratamento para exceções em que o ID não existe.
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpBodyError> handleNotFoundException(final NotFoundException e,WebRequest request) {
    	HttpBodyError httpBodyError = new HttpBodyError("404",e.getMessage(),e.getMessage(),HttpStatus.NOT_FOUND+"",request.getDescription(false),null );
    	return new ResponseEntity<HttpBodyError>(httpBodyError, HttpStatus.NOT_FOUND);
    }

    
    @ExceptionHandler({DataIntegrityViolationException.class,ConstraintViolationException.class})
    public ResponseEntity<HttpBodyError> handleDataIntegrityViolationException(Exception e,WebRequest request) throws IOException {
    	//TODO verificar getCause == null
    	Throwable throwable = e.getCause();
    	String message = "Violação de dados.";
    	if(throwable instanceof DataException) {
    		message = "Violação dos dados do Registro.";
    	}else {
    		if(throwable instanceof ConstraintViolationException) {
    			message = "Violação de Constraint.";	
    		}
    	}
    	HttpBodyError httpBodyError = new HttpBodyError("409",e.getCause().getCause().getMessage(),message,HttpStatus.CONFLICT+"",null,null );
    	return new ResponseEntity<HttpBodyError>(httpBodyError, HttpStatus.CONFLICT);
    }

}