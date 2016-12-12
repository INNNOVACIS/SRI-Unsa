
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IPersonaColaboradorDao;
import com.innnovacis.unsa.model.SRIPersonaColaborador;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class PersonaColaboradorDaoImp implements IPersonaColaboradorDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRIPersonaColaborador Insert(SRIPersonaColaborador entidad) {
        try {
            entidad.setDFechaCreacion(new Date());
            em.persist(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public SRIPersonaColaborador Update(SRIPersonaColaborador entidad) {
        try{
            entidad.setDFechaModificacion(new Date());
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return entidad;
    }

    @Override
    public boolean Delete(SRIPersonaColaborador entidad) {
        try{
            entidad.setDFechaModificacion(new Date());
            entidad.setSEstado("I");
            em.merge(entidad);
        } catch(Exception ex) {
            throw ex;
        }
        return true;
    }

}
