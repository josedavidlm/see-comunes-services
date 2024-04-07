package pe.com.cayetano.see.comunes.model.request;



import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpresaListRequest {

  private Long codempresa;
  private String desempresa;
  private String deaempresa;
  private String fecCreacion;
  private Integer page;
  private Integer pageSize;
}
