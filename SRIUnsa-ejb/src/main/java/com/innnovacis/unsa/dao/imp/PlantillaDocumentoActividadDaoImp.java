
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPlantillaDocumentoActividadDao;
import com.innnovacis.unsa.model.SRIPlantillaDocumentoActividad;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class PlantillaDocumentoActividadDaoImp implements IPlantillaDocumentoActividadDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIPlantillaDocumentoActividad  Insert(SRIPlantillaDocumentoActividad entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIPlantillaDocumentoActividad Update(SRIPlantillaDocumentoActividad entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIPlantillaDocumentoActividad entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

}
