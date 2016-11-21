var investigacionApp = angular.module('investigacionApp', [
	'ngRoute',
        'ngSanitize',
	'ui.bootstrap',
	'angularFileUpload'
]);

investigacionApp.config(function($routeProvider){
	$routeProvider
	.when('/home', {
		templateUrl : 'resources/views/home.html',
		controller : 'homeController'
	})
        .when('/actividadInvestigacion/:ESTADO/:ID',{
		templateUrl : 'resources/views/ActividadGenerada.html',
		controller : 'ActividadGeneradaController'
	})
        .when('/updateActividadGenerada/:ID',{
		templateUrl : 'resources/views/UpdateActividadGenerada.html',
		controller : 'UpdateActividadGeneradaController'
	})
	.when('/actividadesGeneradas',{
		templateUrl : 'resources/views/ActividadesGeneradas.html',
		controller : 'ActividadesGeneradasController'
	})
	.when('/actividadesRevisadas',{
		templateUrl : 'resources/views/ActividadesRevisadas.html',
		controller : 'ActividadesRevisadasController'
	})
	.when('/actividadesPendientes',{
		templateUrl : 'resources/views/ActividadesPendientes.html',
		controller : 'ActividadesPendientesController'
	})
	.when('/relacionDocentes',{
		templateUrl : 'resources/views/relacionDocentes.html',
		controller : 'relacionDocentesController'
	})
	.when('/configuracion',{
		templateUrl : 'resources/views/configuracion.html',
		controller : 'configuracionController'
	})
	.when('/generarDocente',{
		templateUrl : 'resources/views/generarDocente.html',
		controller : 'generarDocenteController'
	})
	.when('/uploadFile',{
		templateUrl : 'resources/views/uploadFile.html',
		controller : 'uploadFileController'
	})
	.when('/usuarios',{
		templateUrl : 'resources/views/usuarios.html',
		controller : 'usuariosController'
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
		templateUrl : 'resources/views/archivos.html',
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
	.when('/',{
		templateUrl : 'resources/views/login.html',
		controller : 'loginController'
	})
        .otherwise({
            redirectTo: '/home'
        });
});
