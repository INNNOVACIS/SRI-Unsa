/**
 * SRIUnsaConfig - factory
 */
investigacionApp.factory("SRIUnsaConfig", function() {
	
    // Servicio para pasar la URL
    return {
        //Modo Local
        SRIUnsaUrlServicio: 'http://localhost:8080/SRIUnsa-web/rest',
        /*Codigo Actores*/
        DOCE : 1, //Docente Actor
        DIDE : 2, //Director Departamento
        DIUN : 3, //Director Unidad
        DECA : 4, //Decano
        DIGE : 5, //Director General
        
        /*Codigo Estados*/
        CREADO : 1,
        REVISADO : 2
        
    };
        
});