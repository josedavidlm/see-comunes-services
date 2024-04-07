package pe.com.cayetano.see.comunes.model.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpresaRequest {

  @JsonIgnore
  private Long codEmpresa;
  private String desEmpresa;
  private String deAEmpresa;

  @JsonIgnore
  private Boolean activo;
  @JsonIgnore
  private LocalDateTime fecCreacion;
  @JsonIgnore
  private Long codUsuarioCreacion;
  @JsonIgnore
  private  String nomTerCreacion;
  @JsonIgnore
  private LocalDateTime fecModificacion;
  @JsonIgnore
  private Long codUsuarioModificacion;
  @JsonIgnore
  private  String nomTerModificacion;
  @JsonIgnore
  private LocalDateTime fecEliminacion;
  @JsonIgnore
  private Long codUsuarioEliminacion;
  @JsonIgnore
  private  String nomTerEliminacion;
}
