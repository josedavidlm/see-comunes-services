package pe.com.cayetano.see.comunes.api;



import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import pe.com.cayetano.see.comunes.api.constant.Constantes;
import pe.com.cayetano.see.comunes.model.response.ResponseBase;
import pe.com.cayetano.see.comunes.model.response.ResponseBasePage;
import pe.com.cayetano.see.comunes.model.request.EmpresaListRequest;
import pe.com.cayetano.see.comunes.model.request.EmpresaRequest;
import pe.com.cayetano.see.comunes.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empresa")
public class EmpresaController {
    @Autowired
    private EmpresaServiceImpl empresaService;

    @PostMapping(value ="save-empresa", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "registrar Empresa",
        description = "registrar Empresa")
    public ResponseBase create(@RequestBody EmpresaRequest empresa)
    {
        empresa.setCodUsuarioCreacion(1L);
        empresa.setNomTerCreacion(Constantes.IP_TERMINAL);
        return empresaService.create(empresa);
    }

    @PutMapping(value ="/update-empresa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "actualización de empresa",
        description = "actualización de empresa")
    public ResponseBase update(@PathVariable("id") Long id,@RequestBody EmpresaRequest empresa)
    {
        empresa.setCodUsuarioModificacion(1L);
        empresa.setNomTerModificacion(Constantes.IP_TERMINAL);
        empresa.setCodEmpresa(id);
        return empresaService.update(empresa);
    }
    @DeleteMapping(value ="/delete-empresa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "eliminación lógica de empresa",
        description = "eliminación lógica de empresa")
    public ResponseBase delete(@PathVariable("id") Long id)
    {
        EmpresaRequest empresa = new EmpresaRequest();
        empresa.setCodEmpresa(id);
        empresa.setCodUsuarioEliminacion(1L);
        empresa.setNomTerEliminacion(Constantes.IP_TERMINAL);

        return empresaService.deleteById(empresa);
    }
    @GetMapping(value ="/get-empresa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "obtener empresa Id",
        description = "obtener empresa Id")
    public ResponseBase buscarPorId(@PathVariable Long id)
    {
        return empresaService.findById(id);
    }
    @GetMapping(value ="/list-empresa", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista de empresa",
        description = "Lista de empresa")
    public ResponseBase listar()
    {
        return empresaService.findAll();
    }

    @GetMapping(value = "to-list-empresa", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista paginable empresa",
        description = "Lista paginable empresa")
    public ResponseBasePage listarBimestre (
        @RequestParam(name="empresa_Id", required = false) Long empresaId,
        @RequestParam(name="descripcion", required = false) String descripcion,
        @RequestParam(name="desCorta", required = false) String desCorta,
        @RequestParam(name="page", defaultValue = "1") Integer page,
        @RequestParam(name="page_size", defaultValue = "10") Integer pageSize
    ) {
        var request = new EmpresaListRequest();
        request.setCodempresa(empresaId);
        request.setDeaempresa(descripcion);
        request.setDeaempresa(desCorta);
        request.setPage(page);
        request.setPageSize(pageSize);

        return empresaService.listarEmpresa(request);
    }


}
