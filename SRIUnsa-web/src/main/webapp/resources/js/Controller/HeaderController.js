investigacionApp.controller('HeaderController',['$scope', '$sce', '$location', 'SharedService', '$window', '$localStorage', 'ngToast',
    '$log', 'HeaderService', 'UsuariosService', function($scope, $sce, $location, SharedService, $window, $localStorage, ngToast, $log, HeaderService,
        UsuariosService) {

    $scope.sharedService = SharedService;
    $scope.isActivo = "Actividades de Investigacion";
    $scope.sharedService.htmlMenu = $sce.trustAsHtml($scope.sharedService.stringMenu);
    $scope.sharedService.htmlMenuVertical = $sce.trustAsHtml($scope.sharedService.stringMenuVertical);

    $scope.nombreUsuario = $scope.sharedService.usuarioLogin === undefined ? "" : $scope.sharedService.usuarioLogin.nombreUsuario;
    $scope.submitted = false;
    
    if($scope.sharedService.usuarioLogin !== undefined){
        if($scope.sharedService.usuarioLogin.codigo === null ||$scope.sharedService.usuarioLogin.codigo === ""){
            $('#modalDni').modal('show');
        }
    }
    
    var updatePersonaSuccess = function(response){
        console.log("UpdatePersona - Success")
        console.log("Respuesta :: ", response);
        $('#modalDni').modal('hide');
        $scope.sharedService.usuarioLogin.codigo = $scope.dniUsuario;
    };
    
    var updatePersonaError = function(response){
        console.log("UpdatePersona - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.guardarDni = function(){
        if($scope.formDni.$valid){
            var persona = {
                nidPersona : $scope.sharedService.usuarioLogin.idPersona,
                sapellido : $scope.sharedService.usuarioLogin.idUsuario.toString(),
                ndni : $scope.dniUsuario
            };
            UsuariosService.updatePersona(persona).then(updatePersonaSuccess, updatePersonaError);
        } else {
            $scope.submitted = true;
        }
    };
    
    $scope.logout = function(){
        
        $window.localStorage.clear();
        $localStorage.usuarioLogin = "";
        $localStorage.autenticado = false;
        $localStorage.privilegios = "";
        $localStorage.htmlMenu = "";
        $localStorage.stringMenu = "";
        $localStorage.crearActividadHome = "";
        $localStorage.locationHome = "";
        $localStorage.idUsuarioRegistrar = "";
        $localStorage.docente = "";
        $localStorage.menuvertical = {};
        
        $scope.sharedService.usuarioLogin.nombreUsuario = "";
    	$scope.sharedService.userAutenticado = false;
        $scope.sharedService.privilegios = "";
        $scope.sharedService.htmlMenu = "";
        $scope.sharedService.stringMenu = "";
        $scope.sharedService.crearActividadHome = "";
        $scope.sharedService.locationHome = "";
        $scope.sharedService.idUsuarioRegistrar = "";
        $scope.sharedService.docente = "";
        $scope.sharedService.menuvertical = {};
        
    	$location.path("/");
    };
    
    $scope.setMenuTab = function (event) {
        $scope.isActivo = event.target.innerText;
    };
    
    /******************* CAMBIAR CONTRASEÑA **********************/
    
    $scope.titulo = "Ingrese el código de seguridad";
    $scope.mensaje = "Comprueba si recibiste un correo electrónico con tu código, que debe tener seis dígitos.";
    $scope.showContrasena = false;
    $scope.showCodigoSeguridad = true;
    
    var verificarCodigoSuccess = function(response){
        $log.debug("verificarCodigo - Success");
        console.log("Respuesta :: ", response);
        if(response !== null){
            $scope.showContrasena = true;
            $scope.showCodigoSeguridad = false;
            $scope.titulo = "Ingrese su nueva Contraseña";
            $scope.mensaje = "Una contraseña segura combina letras y signos de puntuación. Recuerda que la contraseña debe tener 5 caracteres como mínimo."
            $scope.usuario = response;
        }
    };
    var verificarCodigoError = function(response){
        $log.debug("verificarCodigo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var enviarCodigoSuccess = function(response){
        $log.debug("enviarCodigo - Success");
        console.log("Respuesta :: ", response);
    };
    var enviarCodigoError = function(response){
        $log.debug("enviarCodigo - Error");
        console.log("Respuesta :: ", response);
    };
    
    var updateUsuarioSuccess = function(response){
        $log.debug("updateUsuario - Success");
        console.log("Respuesta :: ", response);
        openNotice('Cambio de Contraseña correcto!','success');
        $('#modalCambiarContrasena').modal('hide');
    };
    var updateUsuarioError = function(response){
        $log.debug("updateUsuario - Error");
        console.log("Respuesta :: ", response);
    };
    
    $scope.verificarCodigo = function(){
        if($scope.formCodigoSeguridad.$valid){
            var usuario = {
                nidUsuario : $scope.sharedService.usuarioLogin.idUsuario,
                scodigo : $scope.codigoSeguridad
            };
            HeaderService.verificarCodigo(usuario).then(verificarCodigoSuccess, verificarCodigoError);
        } else {
//            openNotice('Error al registrar!','danger');
            $scope.submitted = true;
            $scope.cancel();
        }
    };
    
    $scope.guardarContrasena = function(){
        if($scope.formCambiarContrasena.$valid){
            $scope.usuario.susuarioPassword = $scope.nuevaContrasena;
            HeaderService.updateUsuario($scope.usuario).then(updateUsuarioSuccess, updateUsuarioError);
        } else {
            $scope.submitted = true;
            $scope.cancel();
        }
    };
    
    $scope.enviarCodigo = function(){
        HeaderService.enviarCodigo($scope.sharedService.usuarioLogin.idUsuario).then(enviarCodigoSuccess, enviarCodigoError);
    };
    
    $scope.$watch('contrasena2', function() {
        console.log("no coincide", $scope.contrasena2);
    });
    
    /**************** NOTIFICACIONES *****************/
    var openNotice = function (text, type) {
        ngToast.create({
            className: type,
            content: '<span class="alert-link">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + text +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>'
        });
    };
    
    $scope.cancel = function(){
        $scope.codigoSeguridad = "";
    };
    
    /*
     * Menu Vertical
     *  
     * */
    
    $scope.openReportes = false;
    $scope.openPermisos = false;
    $scope.openConfiguracion = false;
    
    $scope.openDropdownReportes = function(){
        if($scope.openReportes === false) {
            $scope.openReportes = true;
        } else {
            $scope.openReportes = false;
        }   
    };
    $scope.openDropdownConfiguracion = function(){
        if($scope.openConfiguracion === false) {
            $scope.openConfiguracion = true;
        } else {
            $scope.openConfiguracion = false;
        }   
    };
    $scope.openDropdownPermisos = function(){
        if($scope.openPermisos === false) {
            $scope.openPermisos = true;
        } else {
            $scope.openPermisos = false;
        }   
    };
    

}]);