package pe.com.cayetano.see.comunes.service;

import pe.com.cayetano.see.comunes.model.response.ResponseBase;
import pe.com.cayetano.see.comunes.model.response.ResponseBasePage;
import pe.com.cayetano.see.comunes.model.request.EmpresaListRequest;
import pe.com.cayetano.see.comunes.model.request.EmpresaRequest;

public interface EmpresaService {

  ResponseBase create(EmpresaRequest empresa);
  ResponseBase findById(Long id);
  ResponseBase deleteById(EmpresaRequest empresa);
  ResponseBase update(EmpresaRequest empresa);
  ResponseBase findAll();
  ResponseBasePage listarEmpresa(EmpresaListRequest request);


}
