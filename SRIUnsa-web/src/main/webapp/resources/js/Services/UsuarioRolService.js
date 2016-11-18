/**
 * Usuario Service
 */
investigacionApp.service("UsuarioRolService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getUsuariosRolByPagina = function(request) {
		$log.debug("Usuario Rol Service - PaginacionUsuario");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioRoles/paginacionUsuarioRol',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
        
	this.getUsuarios = function() {
//		$log.debug("Usuario Service - get Usuarios");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'GET',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/listarUsuarios',
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};

	this.registrarUsuario = function(request) {
//		$log.debug("Usuario Service - Registrar Usuario");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'POST',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/registrarUsuarios',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};

	this.updateUsuarioRol = function(request) {
		$log.debug("UsuarioRol Service - Update UsuarioRol");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarioRoles/updateUsuarioRol',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteUsuario = function(request) {
//		$log.debug("Usuario Service - Delete Usuario");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'PUT',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/deleteUsuarios',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};
});