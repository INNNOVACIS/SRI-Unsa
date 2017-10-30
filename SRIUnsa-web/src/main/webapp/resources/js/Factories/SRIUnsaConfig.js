/**
 * SRIUnsaConfig - factory
 */
investigacionApp.factory("SRIUnsaConfig", function() {
	
    // Servicio para pasar la URL
    return {
        //Modo Local
 //         SRIUnsaUrlServicio: 'http://104.131.8.31:8080/SRIUnsa-web/rest',
//        SRIUnsaUrlServicio: 'http://192.168.1.80:8080/SRIUnsa-web/rest',
        SRIUnsaUrlServicio: 'http://localhost:8080/SRIUnsa-web/rest',
//        SRIUnsaUrlServicio: 'http://siri.unsa.edu.pe/SRIUnsa-web/rest',
        /*Codigo Actores*/
        DOCE : 1, //Docente Actor
        DIDE : 2, //Director Departamento
        DIUN : 3, //Director Unidad
        DECA : 4, //Decano
        DIGE : 5, //Director General
        
        codeDOCE : "DOCE", //Docente Actor
        codeDIDE : "DIDE", //Director Departamento
        codeDIUN : "DIUN", //Director Unidad
        codeDECA : "DECA", //Decano
        codeDIGE : "DIGE", //Director General
        
        /*Codigo Estados*/
        PREVIO : 4,
        CREADO : 1,
        REVISADO : 2
        
    };
        
});