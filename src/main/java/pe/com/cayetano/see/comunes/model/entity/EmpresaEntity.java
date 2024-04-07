package pe.com.cayetano.see.comunes.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@Entity
@Table(name = "empresa", schema = "dse")
public class EmpresaEntity extends  AuditoriaEntidadEntity implements Serializable {

  @Id
  @Column(name = "codempresa")
  private Long codEmpresa;

  @Column(name = "desempresa")
  private String desEmpresa;

  @Column(name = "deaempresa")
  private String deAEmpresa;

  @Column(name = "activo")
  private Boolean activo;



}
