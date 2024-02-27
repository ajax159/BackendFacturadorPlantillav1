package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacPermisosMenuDTO;
import com.servicex.facturador.models._m_facturador.FacUsuarioModel;
import com.servicex.facturador.services._m_facturador.FacPermisosService;
import com.servicex.facturador.services._m_facturador.FacUsuarioService;
// import com.servicex.facturador.validators.FacUsuarioValidator;
@RestController
@RequestMapping("/usuario")
public class FacUsuarioController implements ApiResponse{
    @Autowired
    private FacUsuarioService facUsuarioservice;

    @Autowired
    private FacPermisosService facPermisosService;

    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facUsuario", facUsuarioservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/modulos/{id}")
    public ResponseEntity<Object> getModulos(@PathVariable Long id) {
        ArrayList<FacPermisosMenuDTO> modulos = facPermisosService.getModulos(id);
        return showOne(modulos);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        ArrayList<FacUsuarioModel> usuarios = facUsuarioservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    // private void validateFacUsuario(FacUsuarioModel facUsuarioModel, BindingResult result) throws NotValidException {
    //     facUsuarioValidator.validate(facUsuarioModel, result);
    //     if (result.hasErrors()) {
    //         throw new NotValidException(result.getAllErrors());
    //     }
    // }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacUsuarioModel facUsuarioModel, BindingResult result)
            throws NotValidException, SaveException {
                // validateFacUsuario(facUsuarioModel, result);
                FacUsuarioModel newFacCaja = facUsuarioservice.save(facUsuarioModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacUsuarioModel existingFacUsuario = facUsuarioservice.findOne(id);
        //BindingResult result = new BeanPropertyBindingResult(existingFacUsuario, "existingFacUsuario");
        try {
            BeanUtils.populate(existingFacUsuario, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        // validateFacUsuario(existingFacUsuario, result); 
        existingFacUsuario = facUsuarioservice.update(id, existingFacUsuario);
        return updateSuccessfully(existingFacUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facUsuarioservice.delete(id);
        return deleteSuccessfully();
    }
}
