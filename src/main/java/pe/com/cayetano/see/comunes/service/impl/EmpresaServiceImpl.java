package pe.com.cayetano.see.comunes.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pe.com.cayetano.see.comunes.api.constant.Constantes;
import pe.com.cayetano.see.comunes.config.ConfigMessageProperty;
import pe.com.cayetano.see.comunes.model.response.EmpresaResponse;
import pe.com.cayetano.see.comunes.model.response.ResponseBase;
import pe.com.cayetano.see.comunes.model.response.ResponseBasePage;
import pe.com.cayetano.see.comunes.model.entity.EmpresaEntity;
import pe.com.cayetano.see.comunes.model.enums.Estado;
import pe.com.cayetano.see.comunes.model.request.EmpresaListRequest;
import pe.com.cayetano.see.comunes.model.request.EmpresaRequest;
import pe.com.cayetano.see.comunes.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.cayetano.see.comunes.service.EmpresaService;
import pe.com.cayetano.see.comunes.util.CustomPage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmpresaServiceImpl implements EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConfigMessageProperty config;
    @Override
    public ResponseBase create(EmpresaRequest empresa) {
        var lstResponse = new ArrayList<EmpresaResponse>();
        Optional<EmpresaEntity> empresaBd = empresaRepository.findByDesEmpresa(empresa.getDesEmpresa());


        if(empresaBd.isPresent())
        {
            lstResponse.add(modelMapper.map(empresaBd.get(), EmpresaResponse.class));
            return new ResponseBase(Constantes.API_STATUS_400,
                config.getMessage(Constantes.EXISTE),
                false,
                lstResponse);
        }
        EmpresaEntity entidad = modelMapper.map(empresa, EmpresaEntity.class);
        entidad.setCodEmpresa(empresaRepository.obtenerId());
        entidad.setActivo(true);
        entidad.setFecCreacion(LocalDateTime.now());
        EmpresaEntity empresaGuardado = empresaRepository.save(entidad);

        var obj = modelMapper.map(empresaGuardado, EmpresaResponse.class);

        var estado = obj.getActivo();

        if(Boolean.TRUE.equals(estado)){
            obj.setEstado(Estado.ACTIVO.name());
        }else{
            obj.setEstado(Estado.INACTIVO.name());
        }

        lstResponse.add(obj);

        return new ResponseBase(Constantes.API_STATUS_200,
            config.getMessage(Constantes.CREADO) ,
            true,
            lstResponse);
    }

    @Override
    public ResponseBase findById(Long id) {

        var lstResponse = new ArrayList<EmpresaResponse>();
        Optional<EmpresaEntity> empresaBd = empresaRepository.findById(id);

        var obj = modelMapper.map(empresaBd, EmpresaResponse.class);

        var estado = obj.getActivo();

        if( Boolean.TRUE.equals(estado)){
            obj.setEstado(Estado.ACTIVO.name());
        }else{
            obj.setEstado(Estado.INACTIVO.name());
        }
        lstResponse.add(obj);

        if(empresaBd.isPresent())
        {
            return new ResponseBase(Constantes.API_STATUS_200, config.getMessage(Constantes.ENCONTRADO), true, lstResponse);
        }
        return new ResponseBase(Constantes.API_STATUS_404, config.getMessage(Constantes.NO_REGISTRO), false, null);
    }

    @Override
    public ResponseBase deleteById(EmpresaRequest empresa) {
        var lstResponse = new ArrayList<EmpresaResponse>();
        Optional<EmpresaEntity> empresaBd = empresaRepository.findById(empresa.getCodEmpresa());

        if(empresaBd.isPresent())
        {
        empresaBd.get().setActivo(false);
        empresaBd.get().setCodUsuarioEliminacion(empresa.getCodUsuarioEliminacion());
        empresaBd.get().setFecEliminacion(LocalDateTime.now());
        empresaBd.get().setNomTerEliminacion(empresa.getNomTerEliminacion());

        EmpresaEntity empresaDelete = empresaRepository.save(empresaBd.get());

        var obj = modelMapper.map(empresaDelete, EmpresaResponse.class);

        var estado = obj.getActivo();

        if( Boolean.TRUE.equals(estado)){
            obj.setEstado(Estado.ACTIVO.name());
        }else{
            obj.setEstado(Estado.INACTIVO.name());
        }

        lstResponse.add(obj);
        return new ResponseBase(Constantes.API_STATUS_200,  config.getMessage(Constantes.ELIMINADO), true, lstResponse);
        }
        return new ResponseBase(Constantes.API_STATUS_404, config.getMessage(Constantes.NO_REGISTRO), false, null);
    }

    @Override
    public ResponseBase update(EmpresaRequest empresa) {
        Optional<EmpresaEntity> empresaBd = empresaRepository.findById(empresa.getCodEmpresa());

        var lstResponse = new ArrayList<EmpresaResponse>();

        if(empresaBd.isEmpty())
        {
            return new ResponseBase(Constantes.API_STATUS_404,
                config.getMessage(Constantes.NO_REGISTRO),
                false,
                null);
        }
        // verificamos que el dni tenga 8 digitos y que el correo tenga @

        EmpresaEntity entidad = modelMapper.map(empresaBd.get(), EmpresaEntity.class);

        entidad.setDesEmpresa(empresa.getDesEmpresa());
        entidad.setDeAEmpresa(empresa.getDeAEmpresa());
        entidad.setFecModificacion(LocalDateTime.now());
        entidad.setCodUsuarioModificacion(empresa.getCodUsuarioModificacion());
        entidad.setNomTerModificacion(empresa.getNomTerModificacion());

        EmpresaEntity empresaGuardado = empresaRepository.save(entidad);

        var obj = modelMapper.map(empresaGuardado, EmpresaResponse.class);

        var estado = obj.getActivo();

        if( Boolean.TRUE.equals(estado)){
            obj.setEstado(Estado.ACTIVO.name());
        }else{
            obj.setEstado(Estado.INACTIVO.name());
        }

        lstResponse.add(obj);

        return new ResponseBase(Constantes.API_STATUS_200,
            config.getMessage(Constantes.ACTUALIZADO),
            true,
            lstResponse);
    }

    @Override
    public ResponseBase findAll() {
        List<EmpresaEntity> lista = empresaRepository.findAll();

        var lstResponse = new ArrayList<EmpresaResponse>();

        lista.forEach(entidad -> {
            var obj = modelMapper.map(entidad, EmpresaResponse.class);
            if (entidad.getCodEmpresa() != null) {

                var estadoEntidad = entidad.getActivo();
                if(Boolean.TRUE.equals(estadoEntidad)){
                    obj.setEstado(Estado.ACTIVO.name());
                }else{
                    obj.setEstado(Estado.INACTIVO.name());
                }

            }
            lstResponse.add(obj);
        });

        if(!lista.isEmpty())
        {
            return new ResponseBase(Constantes.API_STATUS_200,  config.getMessage(Constantes.ENCONTRADO) , true, lstResponse);
        }
        return new ResponseBase(Constantes.API_STATUS_404, config.getMessage(Constantes.NO_REGISTRO), false, null);
    }

    @Override
    public ResponseBasePage listarEmpresa(EmpresaListRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getPageSize());
        var response =  new CustomPage(empresaRepository.listarEmpresa(request, pageable));
        if (response.getData().isEmpty()) {
            return new ResponseBasePage(Constantes.API_STATUS_404, config.getMessage(Constantes.NO_REGISTRO), false, response.getData());
        }
        return new ResponseBasePage(Constantes.API_STATUS_200,  config.getMessage(Constantes.LISTA_ENCONTRADO) , true, response.getData());

    }
}
