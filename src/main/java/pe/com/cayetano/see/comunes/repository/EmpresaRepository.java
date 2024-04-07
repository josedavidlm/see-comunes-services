package pe.com.cayetano.see.comunes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import pe.com.cayetano.see.comunes.model.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.cayetano.see.comunes.model.projection.EmpresaProjection;
import pe.com.cayetano.see.comunes.model.request.EmpresaListRequest;

import java.util.Optional;


public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

  @Query(value = """
      Select COALESCE(Max(codempresa),0)+1
      from dse.empresa
      """, nativeQuery = true)
  Long obtenerId();
  Optional<EmpresaEntity> findByDesEmpresa(String desEmpresa);

  String QUERY_TABLA = """
                WHERE (CAST(:#{#rq.codempresa}  AS INTEGER) IS NULL OR codempresa = :#{#rq.codempresa})
                  AND (CAST(:#{#rq.desempresa} AS TEXT) IS NULL OR desempresa LIKE '%'||:#{#rq.desempresa}||'%')
                  AND (CAST(:#{#rq.deaempresa} AS TEXT) IS NULL OR deaempresa LIKE '%'||:#{#rq.deaempresa}||'%' )
      """;
  @Query(value = """
            SELECT
                codempresa,
                desempresa,
                deaempresa,
                activo,
                CASE WHEN activo THEN 'ACTIVO' ELSE 'INACTIVO' END as desActivo,
                feccreacion,
                codusuariocreacion,
                nomtercreacion,
                codusuariomodificacion,
                fecmodificacion,
                nomtermodificacion,
                codusuarioeliminacion,
                feceliminacion,
                nomtereliminacion
            FROM dse.empresa            
          """ + QUERY_TABLA,
      countQuery = """
          SELECT COUNT(1)
          """ + QUERY_TABLA,
      nativeQuery = true)
  Page<EmpresaProjection> listarEmpresa(
      @Param("rq") EmpresaListRequest rq,
      @PageableDefault(page = 0, size = 10) Pageable pageable
  );


}