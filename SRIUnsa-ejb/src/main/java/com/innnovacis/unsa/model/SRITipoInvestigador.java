package com.innnovacis.unsa.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "tipoinvestigador" )
@NamedQueries({
    @NamedQuery(name="SRITipoInvestigador.GetAll",query="SELECT p FROM SRITipoInvestigador p"),
    @NamedQuery(name="SRITipoInvestigador.GetById",query="SELECT p FROM SRITipoInvestigador p WHERE p.NIdTipoInvestigador = :idEntidad")
})
public class SRITipoInvestigador  extends SRIEntidad implements Serializable  {
    

    @Id
    @GeneratedValue
    @Column(name = "idtipoinvestigador" )
    private int NIdTipoInvestigador;
    
    
    
    @Column(name = "nombretipoinvestigador")
    private String SNombreTipoInvestigador;

    

    public int getNIdTipoInvestigador() {
        return NIdTipoInvestigador;
    }

    public void setNIdTipoInvestigador(int NIdTipoInvestigador) {
        this.NIdTipoInvestigador = NIdTipoInvestigador;
    }

    public String getSNombreTipoInvestigador() {
        return SNombreTipoInvestigador;
    }

    public void setSNombreTipoInvestigador(String SNombreTipoInvestigador) {
        this.SNombreTipoInvestigador = SNombreTipoInvestigador;
    }
    







    

    
    

}

