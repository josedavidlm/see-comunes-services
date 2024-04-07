package pe.com.cayetano.see.comunes.model.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmpresaResponse {
  private Long codEmpresa;
  private String desEmpresa;
  private String deAEmpresa;
  private Boolean activo;
  private String estado;
  private LocalDateTime fecCreacion;
  private Long codUsuarioCreacion;
  private  String nomTerCreacion;
  private LocalDateTime fecModificacion;
  private Long codUsuarioModificacion;
  private  String nomTerModificacion;
  private LocalDateTime fecEliminacion;
  private Long codUsuarioEliminacion;
  private  String nomTerEliminacion;
}
