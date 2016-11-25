/**
 * SRIUnsaConfig - factory
 */
investigacionApp.factory("SRIUnsaConfig", function() {
	
    // Servicio para pasar la URL
    return {
        //Modo Local
        SRIUnsaUrlServicio: 'http://localhost:8080/SRIUnsa-web/rest',
        /*Codigo Actores*/
        DOCE : "DOCE", //Docente Actor
        DIDE : "DIDE", //Director Departamento
        DIUN : "DIUN", //Director Unidad
        DECA : "DECA", //Decano
        DIGE : "DIGE", //Director General
        
        /*Codigo Estados*/
        CREADO : 1,
        REVISADO : 2
        
    };
        
});