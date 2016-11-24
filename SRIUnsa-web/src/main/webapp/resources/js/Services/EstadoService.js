/**
 * Rol  Service
 */
investigacionApp.service("EstadoService", function(SRIUnsaConfig, $log, $http, $q) {
        
	this.getEstados = function() {
		$log.debug("Estado Service - Get Estados");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estado/listarEstados',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarEstado = function(request) {
//		$log.debug("Rol Service - Registrar Rol");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'POST',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/roles/registrarRoles',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};

	this.actualizarEstado = function(request) {
//		$log.debug("Rol Service - Update Rol");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'PUT',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/roles/updateRoles',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};

	this.eliminarEstado = function(request) {
//		$log.debug("Rol Service - Delete Rol");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'PUT',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/roles/deleteRoles',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};
});