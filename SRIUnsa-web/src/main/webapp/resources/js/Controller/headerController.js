investigacionApp.controller('headerController', function($scope, $location, SharedService) {

    $scope.sharedService = SharedService;

    $scope.menuTab = 1;    
    $scope.message = 'José Herrera';
    /*Rol de Usuario*/
    $scope.rol_menu_1 = false; //registrar actividad
    $scope.rol_menu_2 = false; //actividades generadas
    $scope.rol_menu_3 = false; //acti revisadas
    $scope.rol_menu_4 = false; //Reportabilidad
    $scope.rol_menu_5 = false; //Actividades Pendientes
    $scope.rol_menu_6 = false; //Relacion docentes
    $scope.rol_menu_7 = false; ////configuracion
    $scope.rol_menu_8 = false; //generar docente

    $scope.usuario = {
    	rolDefault: "Docente",
    	roles: [
    		'Docente',
	        'Director de Departamento',
	        'Director de Unidad de Investigación',
	        'Decano de Facultad',
	        'Director de General de Administración',
	        'Vicerrector de Investigación',
	        'Rector'
    	]
    };

    //Funciones
    $scope.setMenuTab = function(tabToSet){
		$scope.menuTab = tabToSet;
		console.log("call function!!!");
	};

    $scope.logout = function(){
    	$scope.sharedService.userAutenticado = false;
    	$location.path("/");
    };

    $scope.rolChange = function(seleccionado){
        $scope.Actividad = seleccionado;
        switch ($scope.Actividad) {
            case 'Docente':
                $scope.rol_menu_1 = true; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = false; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = false; //Actividades Pendientes
                $scope.rol_menu_6 = false; //Relacion docentes
                $scope.rol_menu_7 = false; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                break;
            case 'Director de Departamento':
                $scope.rol_menu_1 = false; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = true; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = true; //Actividades Pendientes
                $scope.rol_menu_6 = true; //Relacion docentes
                $scope.rol_menu_7 = true; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                $scope.setMenuTab(2);
                $location.path("/actividadesGeneradas");
                break;
            case 'Director de Unidad de Investigación':
                $scope.rol_menu_1 = true; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = true; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = true; //Actividades Pendientes
                $scope.rol_menu_6 = false; //Relacion docentes
                $scope.rol_menu_7 = true; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                $scope.setMenuTab(1);
                $location.path("/home");
                break;
            case 'Decano de Facultad':
                $scope.rol_menu_1 = true; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = true; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = false; //Actividades Pendientes
                $scope.rol_menu_6 = false; //Relacion docentes
                $scope.rol_menu_7 = true; ////configuracion
                $scope.rol_menu_8 = true; //generar docente
                $scope.setMenuTab(1);
                $location.path("/home");
                break;
            case 'Director de General de Administración':
                $scope.rol_menu_1 = true; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = true; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = false; //Actividades Pendientes
                $scope.rol_menu_6 = true; //Relacion docentes
                $scope.rol_menu_7 = true; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                $scope.setMenuTab(1);
                $location.path("/home");
                break;
            case 'Vicerrector de Investigación':
                $scope.rol_menu_1 = true; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = true; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = false; //Actividades Pendientes
                $scope.rol_menu_6 = true; //Relacion docentes
                $scope.rol_menu_7 = true; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                $scope.setMenuTab(1);
                $location.path("/home");
                break;
            case 'Rector':
                $scope.rol_menu_1 = true; //registrar actividad
                $scope.rol_menu_2 = true; //actividades generadas
                $scope.rol_menu_3 = true; //acti revisadas
                $scope.rol_menu_4 = true; //Reportabilidad
                $scope.rol_menu_5 = false; //Actividades Pendientes
                $scope.rol_menu_6 = true; //Relacion docentes
                $scope.rol_menu_7 = true; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                $scope.setMenuTab(1);
                $location.path("/home");
                break;
            default:
            	$scope.rol_menu_1 = false; //registrar actividad
                $scope.rol_menu_2 = false; //actividades generadas
                $scope.rol_menu_3 = false; //acti revisadas
                $scope.rol_menu_4 = false; //Reportabilidad
                $scope.rol_menu_5 = false; //Actividades Pendientes
                $scope.rol_menu_6 = false; //Relacion docentes
                $scope.rol_menu_7 = false; ////configuracion
                $scope.rol_menu_8 = false; //generar docente
                $location.path("/");
        }
    };
    $scope.rolChange("Docente");
});