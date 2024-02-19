package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacCajaSerieDto;
import com.servicex.facturador.dtos.FacCajaSerieNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaSerieModel;
import com.servicex.facturador.services._m_facturador.FacCajaSerieService;
//import com.servicex.facturador.validators.FacSerieValidatorApi;
@RestController
@RequestMapping("/cajaserie")
public class FacCajaSerieApiController implements ApiResponse{
    @Autowired
    private FacCajaSerieService facCajaSerieservice;
    // @Autowired
    // private FacSerieValidatorApi facSerieValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facSerie", facCajaSerieservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/serie")
    public ResponseEntity<ArrayList<FacCajaSerieDto>> getIdCaja(@RequestParam("idcaja") Long idcaja){
        ArrayList<FacCajaSerieDto> facdocumentos = facCajaSerieservice.getIdCaja(idcaja);
        return ResponseEntity.ok(facdocumentos);
    }
    @GetMapping("/notserie")
    public ResponseEntity<ArrayList<FacCajaSerieNotDTO>> getnotIdCaja(@RequestParam("idcaja") Long idcaja){
        ArrayList<FacCajaSerieNotDTO> facdocumentos = facCajaSerieservice.getnotIdCaja(idcaja);
        return ResponseEntity.ok(facdocumentos);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacCajaSerieModel usuarios = facCajaSerieservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    // private void validateFacSerie(FacCajaSerieModel facCajaSerieModel, BindingResult result) throws NotValidException {
    //     facSerieValidator.validate(facCajaSerieModel, result);
    //     if (result.hasErrors()) {
    //         throw new NotValidException(result.getAllErrors());
    //     }
    // }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacCajaSerieModel facCajaSerieModel, BindingResult result)
            throws NotValidException, SaveException {
                //validateFacSerie(facCajaSerieModel, result);
                FacCajaSerieModel newFacCaja = facCajaSerieservice.save(facCajaSerieModel);
        return savedSuccessfully(newFacCaja);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
    //         throws NotValidException, SaveException {
    //             FacCajaSerieModel existingFacSerie = facCajaSerieservice.findOne(id);
    //     BindingResult result = new BeanPropertyBindingResult(existingFacSerie, "existingFacSerie");
    //     try {
    //         BeanUtils.populate(existingFacSerie, updates);
    //     } catch (Exception e) {
    //         throw new SaveException(e);
    //     }
    //     validateFacSerie(existingFacSerie, result); 
    //     existingFacSerie = facCajaSerieservice.update(id, existingFacSerie);
    //     return updateSuccessfully(existingFacSerie);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facCajaSerieservice.delete(id);
        return deleteSuccessfully();
    }
}
