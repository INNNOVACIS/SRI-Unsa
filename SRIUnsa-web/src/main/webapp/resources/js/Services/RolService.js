/**
 * Rol  Service
 */
investigacionApp.service("RolService", function($log, $http, $q) {
	
	this.getRoles = function() {
		$log.debug("Rol Service - get Roles");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : 'http://localhost:8080/SRIUnsa-web/rest/roles/listarRoles',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarRol = function(request) {
		$log.debug("Rol Service - Registrar Rol");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : 'http://localhost:8080/SRIUnsa-web/rest/roles/registrarRoles',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateRol = function(request) {
		$log.debug("Rol Service - Update Rol");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/SRIUnsa-web/rest/roles/updateRoles',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteRol = function(request) {
		$log.debug("Rol Service - Delete Rol");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : 'http://localhost:8080/SRIUnsa-web/rest/roles/deleteRoles',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});