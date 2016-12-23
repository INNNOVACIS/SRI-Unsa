var investigacionApp = angular.module('investigacionApp', [
	'ngRoute',
        'ngAnimate',
        'ngSanitize',
	'ui.bootstrap',
	'angularFileUpload'
]);

investigacionApp.config(function($routeProvider){
	$routeProvider
	.when('/home', {
		templateUrl : 'resources/views/Home.html',
		controller : 'HomeController'
	})
        .when('/homedocente', {
		templateUrl : 'resources/views/HomeDocente.html',
		controller : 'HomeDocenteController'
	})
        .when('/homeDirectorUnidad', {
		templateUrl : 'resources/views/HomeDirectorUnidad.html',
		controller : 'HomeDirectorUnidadController'
	})
//        .when('/homeVicerector', {
//		templateUrl : 'resources/views/HomeVicerector.html',
//		controller : 'VicerectorController'
//	})
        .when('/actividad/:ESTADO/:ID',{
		templateUrl : 'resources/views/ActividadGenerada.html',
		controller : 'ActividadGeneradaController'
	})
        .when('/actividad/Generadas/update/:ID',{
		templateUrl : 'resources/views/UpdateActividadGenerada.html',
		controller : 'UpdateActividadGeneradaController'
	})
	.when('/actividad/Generadas',{
		templateUrl : 'resources/views/ActividadesGeneradas.html',
		controller : 'ActividadesGeneradasController'
	})
	.when('/actividad/Revisadas',{
		templateUrl : 'resources/views/ActividadesRevisadas.html',
		controller : 'ActividadesRevisadasController'
	})
	.when('/actividad/Pendientes',{
		templateUrl : 'resources/views/ActividadesPendientes.html',
		controller : 'ActividadesPendientesController'
	})
        .when('/actividadesDocentes',{
		templateUrl : 'resources/views/ActividadesDocentes.html',
		controller : 'ActividadesDocentesController'
	})
	.when('/relacionDocentes',{
		templateUrl : 'resources/views/RelacionDocentes.html',
		controller : 'RelacionDocentesController'
	})
        .when('/relacionDocentesColaboradores',{
		templateUrl : 'resources/views/RelacionDocentesColaboradores.html',
		controller : 'RelacionDocentesColaboradoresController'
	})
	.when('/generarDocente',{
		templateUrl : 'resources/views/GenerarDocente.html',
		controller : 'GenerarDocenteController'
	})
	.when('/uploadFile',{
		templateUrl : 'resources/views/UploadFile.html',
		controller : 'uploadFileController'
	})
	.when('/usuarios',{
		templateUrl : 'resources/views/Usuarios.html',
		controller : 'UsuariosController'
	})
        .when('/roles',{
		templateUrl : 'resources/views/Rol.html',
		controller : 'RolController'
	})
        .when('/privilegios',{
		templateUrl : 'resources/views/Privilegio.html',
		controller : 'PrivilegioController'
	})
        .when('/tipoinvestigacion',{
		templateUrl : 'resources/views/TipoInvestigacion.html',
		controller : 'TipoInvestigacionController'
	})
        .when('/tiponivel',{
		templateUrl : 'resources/views/TipoNivel.html',
		controller : 'TipoNivelController'
	})
        .when('/tipoinvestigador',{
		templateUrl : 'resources/views/TipoInvestigador.html',
		controller : 'TipoInvestigadorController'
	})
        .when('/tipoproduccion',{
		templateUrl : 'resources/views/TipoProduccion.html',
		controller : 'TipoProduccionController'
	})
        .when('/tipoasesoria',{
		templateUrl : 'resources/views/TipoAsesoria.html',
		controller : 'TipoAsesoriaController'
	})
        .when('/semestre',{
		templateUrl : 'resources/views/Semestre.html',
		controller : 'SemestreController'
	})
        .when('/areainvestigacion',{
		templateUrl : 'resources/views/EstructuraAreaInvestigacion.html',
		controller : 'EstructuraAreaInvestigacionController'
	})
        .when('/fondoconcursable',{
		templateUrl : 'resources/views/FondoConcursable.html',
		controller : 'FondoConcursableController'
	})
        .when('/archivos',{
		templateUrl : 'resources/views/Archivos.html',
		controller : 'archivosController'
	})
        .when('/estructuraorganizacion',{
		templateUrl : 'resources/views/EstructuraOrganizacion.html',
		controller : 'EstructuraOrganizacionController'
	})
        .when('/usuariorol',{
		templateUrl : 'resources/views/UsuarioRol.html',
		controller : 'UsuarioRolController'
	})
        .when('/flujoactor',{
		templateUrl : 'resources/views/FlujoActor.html',
		controller : 'FlujoActorController'
	})
        .when('/usuarioflujo',{
		templateUrl : 'resources/views/UsuarioFlujo.html',
		controller : 'UsuarioFlujoController'
	})
        .when('/flujoarista',{
		templateUrl : 'resources/views/FlujoArista.html',
		controller : 'FlujoAristaController'
	})
        .when('/generarCampos',{
		templateUrl : 'resources/views/PlantillaDocumento.html',
		controller : 'PlantillaDocumentoController'
	})
        .when('/actividadesRevisadasMasivas',{
		templateUrl : 'resources/views/ActividadesRevisadasMasivas.html',
		controller : 'ActividadesRevisadasMasivasController'
	})
	.when('/',{
		templateUrl : 'resources/views/Login.html',
		controller : 'LoginController'
	})
        .otherwise({
            redirectTo: '/homeDirectorUnidad'
        });
});

investigacionApp.run(['$rootScope', '$location', 'SharedService', function ($rootScope, $location,SharedService) {
    $rootScope.$on('$routeChangeStart', function (event) {
//        $scope.sharedService = SharedService;
        if (!SharedService.isAutenticado()) {
            console.log('DENEGADO');
            event.preventDefault();
            $location.path('/');
        }
        else {
            console.log("location ", $location.path());
            var cadena = $location.path();
            if(!SharedService.isPermitido(cadena)){
                alert("no tiene permisos para esta pagina");
                $location.path(SharedService.locationHome);
            }
        }
    });
}]);
