
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoInvestigadorDao;
import com.innnovacis.unsa.model.SRITipoInvestigador;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;



@Dependent
public class TipoInvestigadorDaoImp implements ITipoInvestigadorDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoInvestigador  Insert(SRITipoInvestigador entidad) {
         em.persist(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public SRITipoInvestigador Update(SRITipoInvestigador entidad) {
         em.merge(entidad);
         return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoInvestigador entidad) {
        entidad.setSEstado("I");
        em.merge(entidad);
        return true;
    }

    @Override
    public SRITipoInvestigador GetById(int idEntidad) {
        SRITipoInvestigador entidad = em.createNamedQuery("SRITipoInvestigador.GetById", SRITipoInvestigador.class).setParameter("idEntidad", idEntidad).getSingleResult();
        return entidad;
    }

    @Override
    public List<SRITipoInvestigador> GetAll() {
        List<SRITipoInvestigador> olistaRespuesta = em.createNamedQuery("SRITipoInvestigador.GetAll",SRITipoInvestigador.class).getResultList();
        return olistaRespuesta;
    }

}
