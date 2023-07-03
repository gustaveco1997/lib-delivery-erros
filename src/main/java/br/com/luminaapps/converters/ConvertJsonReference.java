package br.com.luminaapps.converters;

import br.com.luminaapps.entities.DetalheErroVo;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;
import java.util.Objects;

public class ConvertJsonReference {

    public static List<DetalheErroVo> convert(List<JsonMappingException.Reference> references, String defaultMessageDetail) {
        var builder = new StringBuilder();
        var iterator = references.stream()
                .filter(item -> Objects.nonNull(item.getFieldName()))
                .iterator();

        while (iterator.hasNext()) {
            var reference = iterator.next();
            builder.append(reference.getFieldName());

            if (iterator.hasNext()) {
                builder.append(" -> ");
            }
        }

        return List.of(new DetalheErroVo(defaultMessageDetail, builder.toString()));
    }

}
