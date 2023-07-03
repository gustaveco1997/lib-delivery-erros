package br.com.luminaapps;

import br.com.luminaapps.converters.ConvertJsonReference;
import br.com.luminaapps.entities.DetalheErroVo;
import br.com.luminaapps.entities.ErrorVo;
import br.com.luminaapps.exceptions.AcessoNegadoException;
import br.com.luminaapps.exceptions.ErroGenericoExcepion;
import br.com.luminaapps.exceptions.NaoEncontradoException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

public class ExceptionAdviceLib {
    private static final String VERIFIQUE_DADOS = "Verifique os dados enviados no payload da requisição";
    private static final String VALORES_INCORRETOS = "Valores incorretos definidos no payload da requisição";
    private static final String VERIFIQUE_OBJETO_PAYLOAD = "Verifique o objeto do payload";
    private static final String VERIFIQUE_TIPO_OBJETO_PAYLOAD = "Verifique o tipo dos objetos passados na requisição";
    private static final String ACESSO_NEGADO = "Acesso Negado";
    private static final String NAO_ENCONTRADO = "Não Encontrado";
    private static final String ERRO_GENERICO = "Erro Genérico";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorVo> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var error = new ErrorVo();
        error.setCode(BAD_REQUEST.value());
        error.setMessage(VERIFIQUE_DADOS);

        var fieldErrors = exception.getFieldErrors();
        var allErros = exception.getAllErrors();

        if (CollectionUtils.isEmpty(fieldErrors)) {
            allErros.forEach(itemError -> error.addError(new DetalheErroVo(itemError.getDefaultMessage(), itemError.getObjectName())));
        } else {
            fieldErrors.forEach(fieldError -> error.addError(new DetalheErroVo(fieldError.getDefaultMessage(), fieldError.getField())));
        }

        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorVo> jsonMappingException(JsonMappingException exception) {
        var error = new ErrorVo();
        error.setCode(BAD_REQUEST.value());
        error.setMessage(VALORES_INCORRETOS);

        error.setErrors(ConvertJsonReference.convert(exception.getPath(), VERIFIQUE_OBJETO_PAYLOAD));
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorVo> invalidFormatException(InvalidFormatException exception) {
        var error = new ErrorVo();
        error.setCode(BAD_REQUEST.value());
        error.setMessage(VALORES_INCORRETOS);

        error.setErrors(ConvertJsonReference.convert(exception.getPath(), VERIFIQUE_TIPO_OBJETO_PAYLOAD));
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(AcessoNegadoException.class)
    public ResponseEntity<ErrorVo> acessoNegadoException(AcessoNegadoException exception) {
        var error = new ErrorVo();
        error.setCode(UNAUTHORIZED.value());
        error.setMessage(ACESSO_NEGADO);

        error.addError(new DetalheErroVo(exception.getMessage(), null));
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErrorVo> naoEncontradoException(NaoEncontradoException exception) {
        var error = new ErrorVo();
        error.setCode(NOT_FOUND.value());
        error.setMessage(NAO_ENCONTRADO);

        error.addError(new DetalheErroVo(exception.getMessage(), null));
        return ResponseEntity.status(error.getCode()).body(error);
    }

    @ExceptionHandler(ErroGenericoExcepion.class)
    public ResponseEntity<ErrorVo> erroGenericoException(ErroGenericoExcepion exception) {
        var error = new ErrorVo();
        error.setCode(INTERNAL_SERVER_ERROR.value());
        error.setMessage(ERRO_GENERICO);

        error.addError(new DetalheErroVo(exception.getMessage(), null));
        return ResponseEntity.status(error.getCode()).body(error);
    }
}
