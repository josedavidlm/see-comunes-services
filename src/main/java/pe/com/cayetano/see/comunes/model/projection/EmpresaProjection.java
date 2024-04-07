package pe.com.cayetano.see.comunes.model.projection;


import java.util.Date;

public interface EmpresaProjection {


   Long getCodEmpresa();
   String getDesEmpresa();
   String getDeAEmpresa();
   Boolean getActivo();
   String getDesActivo();
   Date getFecCreacion();
   Long getCodUsuarioCreacion();
   String getNomTerCreacion();
   Date getFecModificacion();
   Long getCodUsuarioModificacion();
   String getNomTerModificacion();
   Date getFecEliminacion();
   Long getCodUsuarioEliminacion();
   String getNomTerEliminacion();

}
