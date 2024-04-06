package pe.com.cayetano.see.comunes.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.cayetano.see.comunes.util.CustomPage;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBasePage {
  private Integer codigo;
  private String mensaje;
  private Boolean exito;
  private CustomPage<Object> data;
}