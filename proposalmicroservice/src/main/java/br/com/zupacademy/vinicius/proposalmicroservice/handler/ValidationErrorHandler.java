package br.com.zupacademy.vinicius.proposalmicroservice.handler;

import br.com.zupacademy.vinicius.proposalmicroservice.business.webclient.contas.cartao_biometria.Biometria;
import br.com.zupacademy.vinicius.proposalmicroservice.exception.RegraNegocioException;
import br.com.zupacademy.vinicius.proposalmicroservice.exception.ReturnError;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrorsOutputDto handleValidationError(BindException exception) {

        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return buildValidationErrors(globalErrors, fieldErrors);
    }

    private ValidationErrorsOutputDto buildValidationErrors(List<ObjectError> globalErrors,
                                                            List<FieldError> fieldErrors) {
        var validationErrors = new ValidationErrorsOutputDto();

        globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
    
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(RegraNegocioException.class)
    public ReturnError validationRegraNegocioDto(RegraNegocioException exception) {
        return exception.getErroRetorno();
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotReadablePropertyException.class)
    public ReturnError validacaoRegraNegocio2(NotReadablePropertyException exception) {
        ReturnError erros = new ReturnError();
        
        erros.addError(exception.getLocalizedMessage());
        
        return erros;
    }
    
    @ControllerAdvice
    public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
    
        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ResponseEntity<RegraNegocioException> handleMaxSizeException(MaxUploadSizeExceededException exc) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new RegraNegocioException("File too large!", "fingerprint", Biometria.class));
        }
    }
}
