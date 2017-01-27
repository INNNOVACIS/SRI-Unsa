
package com.innnovacis.unsa.dao.imp;

import com.innnovacis.unsa.dao.ITipoInvestigadorDao;
import com.innnovacis.unsa.model.SRITipoInvestigador;
import com.innnovacis.unsa.util.SRIPaginacionObject;
import java.math.BigInteger;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;



@RequestScoped
public class TipoInvestigadorDaoImp implements ITipoInvestigadorDao {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public SRITipoInvestigador  Insert(SRITipoInvestigador entidad) {
        entidad.setDFechaCreacion(new Date());
        em.persist(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public SRITipoInvestigador Update(SRITipoInvestigador entidad) {
        entidad.setDFechaModificacion(new Date());
        em.merge(entidad);
        return entidad;
    }

    @Override
    @Transactional
    public boolean Delete(SRITipoInvestigador entidad) {
        entidad.setDFechaModificacion(new Date());
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

    @Override
    public int GetTotalPaginacion(SRIPaginacionObject entidad) {
        Query query = em.createNativeQuery("{call GetTotalTipoInvestigador(?1)}")
                        .setParameter(1, entidad.getFiltro());
        BigInteger total = (BigInteger) query.getSingleResult();
        return total.intValue();
    }

    @Override
    public List<SRITipoInvestigador> GetPagina(SRIPaginacionObject entidad) {
        Query query = em.createNativeQuery("{call GetTipoInvestigador(?1,?2,?3)}", SRITipoInvestigador.class)
                        .setParameter(1, entidad.getFiltro())
                        .setParameter(2, entidad.getRango())
                        .setParameter(3, entidad.getCurrentPage());
        List<SRITipoInvestigador> listTipoInvestigador = query.getResultList();
        return listTipoInvestigador;
    }

}
