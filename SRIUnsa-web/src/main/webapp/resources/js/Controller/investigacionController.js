investigacionApp.controller('investigacionController', function($scope) {
	
    $scope.actividades = [
        {
            id : 1,
            nombreInvestigacion : "Tratamiento sostenible",
            tipoInvestigacion : {id : 1, nombre: "Asesoria dde Tesis"},
            duracion : 80,
            totalArchivos : 4
        },
        {
            id : 2,
            nombreInvestigacion : "Reanimador de uso facil",
            tipoInvestigacion : {id : 1, nombre: "Asesoria dde Tesis"},
            duracion : 90,
            totalArchivos : 7
        }
    ];
        
    $scope.actividadesInvestigacion = [
        {id : 1, nombre : 'Investigación Formativa'},
        {id : 2, nombre : 'Asesoria de Tesis'},
        {id : 3, nombre : 'Investigaciones Básicas y Aplicadas'},
        {id : 3, nombre : 'Producción Intelectual'}
    ];
    $scope.facultades = [
        {id : 1, nombre : 'Facultad de Producción y Servicios'},
        {id : 2, nombre : 'Facultad de Procesos'},
        {id : 3, nombre : 'Facultad de Ingenieria Civil'}
    ];
    $scope.escuelas = [
        {id : 1, nombre : 'Escuela Profesional de Ingeniería de Sistemas'},
        {id : 2, nombre : 'Industrias Alimentarias'},
        {id : 3, nombre : 'Ingeniería Mecánica'}
    ];
    $scope.semestres = [
        {id : 1, nombre : '2016-I Semestre I'},
        {id : 2, nombre : '2016-II Semestre II'},
        {id : 3, nombre : '2016-II Semestre III'}        
    ];
    $scope.fondos = [
        {id : 1, nombre : 'CONCYTEC'},
        {id : 2, nombre : 'FINCyT'}
    ];
    
    $scope.panelGenerados = true;
    $scope.panelVer = false;
    $scope.panelEditar = false;
    $scope.actividad= "primer valor";

    $scope.panelChange = function(panel){
            if(panel == 1){
                    $scope.panelGenerados = true;
                    $scope.panelVer = false;
                    $scope.panelEditar = false;
            }else{
                    if(panel == 2){
                            $scope.panelGenerados = false;
                            $scope.panelVer = true;
                            $scope.panelEditar = false;
                    }else{
                            $scope.panelGenerados = false;
                            $scope.panelVer = false;
                            $scope.panelEditar = true;
                    }
            }
    }
});
