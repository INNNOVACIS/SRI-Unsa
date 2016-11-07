
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.IActividadInvestigacionRevisadaDao;
import com.innnovacis.unsa.util.SRIActividadesRevisadas;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.Query;


@Dependent
public class ActividadInvestigacionRevisadaDaoImp implements IActividadInvestigacionRevisadaDao {

    @Inject
    private EntityManager em;

    @Override
    public List<SRIActividadesRevisadas> GetAll() {
        
        Query query = em.createNativeQuery("{call actividadesRevisadas()}", SRIActividadesRevisadas.class);
        List<SRIActividadesRevisadas> listActividades = (List<SRIActividadesRevisadas>)query.getResultList();
        return listActividades;
    }

}
