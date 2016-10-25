/**
 * Usuario Service
 */
investigacionApp.service("UsuariosService", function($log, $http, $q) {
	
	this.getUsuarios = function() {
		$log.debug("Usuario Service - get Usuarios");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : 'http://localhost:8080/SRIUnsa-web/rest/usuarios/listarUsuarios',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarUsuario = function(request) {
		$log.debug("Usuario Service - Registrar Usuario");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : 'http://localhost:8080/SRIUnsa-web/rest/usuarios/registrarUsuarios',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateUsuario = function(request) {
		$log.debug("Usuario Service - Update Usuario");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/SRIUnsa-web/rest/usuarios/updateUsuarios',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteUsuario = function(request) {
		$log.debug("Usuario Service - Delete Usuario");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/SRIUnsa-web/rest/usuarios/deleteUsuarios',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});