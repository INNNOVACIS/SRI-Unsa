
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPlantillaDocumentoDao;
import com.innnovacis.unsa.model.SRIEstructuraOrganizacion;
import com.innnovacis.unsa.model.SRIPlantillaDocumento;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;



@RequestScoped
public class PlantillaDocumentoDaoImp implements IPlantillaDocumentoDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIPlantillaDocumento  Insert(SRIPlantillaDocumento entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRIPlantillaDocumento Update(SRIPlantillaDocumento entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRIPlantillaDocumento entidad) {
        entidad.setDFechaModificacion(new Date());
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        BigInteger total = null;
        try {
            Query query = em.createNativeQuery("{call GetTotalPlantillaDocumento(?1)}")
                        .setParameter(1, entidad.getFiltro());
            total = (BigInteger) query.getSingleResult();
        } catch(Exception ex) {
            throw ex;
        }
        return total.intValue();
    }

    @Override
    public List<SRIPlantillaDocumento> GetPagina(SRIPaginacionObject entidad) {
        List<SRIPlantillaDocumento> plantillaDocumento = null;
        try {
            Query query = em.createNativeQuery("{call GetPlantillaDocumento(?1,?2,?3)}", SRIPlantillaDocumento.class)
                        .setParameter(1, entidad.getFiltro())
                        .setParameter(2, entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage());
            plantillaDocumento = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return plantillaDocumento;
    }

    @Override
    public List<SRIPlantillaDocumento> GetPlantillaDocumentoByFacultad(SRIEstructuraOrganizacion entidad) {
        List<SRIPlantillaDocumento> plantillaDocumento = null;
        try {
            Query query = em.createNativeQuery("{call GetPlantillaDocumentoByFacultad(?1)}", SRIPlantillaDocumento.class)
                        .setParameter(1, entidad.getSNombreEstructuraOrganizacion());
            plantillaDocumento = query.getResultList();
        } catch(Exception ex) {
            throw ex;
        }
        return plantillaDocumento;
    }

}
