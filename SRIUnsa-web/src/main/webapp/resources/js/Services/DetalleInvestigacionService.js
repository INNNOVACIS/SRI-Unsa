/**
 * DetalleInvestigacion  Service
 */
investigacionApp.service("DetalleInvestigacionService", function(SRIUnsaConfig, $log, $http, $q) {
        
        this.getEstadoById = function(id) {
//		$log.debug("Estado Service - GetEstadoById");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'GET',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estado/listarEstado/' + id,
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};
        
	this.getDetalleInvestigacion = function() {
//		$log.debug("DetalleInvestigacion Service - GetDetalleInvestigacion");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'GET',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/detalleinvestigacion/listarEstados',
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};

	this.RegistrarDetalleInvestigacion = function(request) {
		$log.debug("DetalleInvestigacion Service - RegistrarDetalleInvestigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/detalleinvestigacion/registrarDetalleInvestigacion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.actualizarDetalleInvestigacion = function(request) {
//		$log.debug("Estado Service - ActualizarEstado");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'PUT',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estado/actualizarEstado',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};

	this.eliminarDetalleInvestigacion = function(request) {
//		$log.debug("Estado Service - EliminarEstado");
//		
//		var deferred = $q.defer();
//		$http({
//			method : 'PUT',
//			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estado/eliminarEstado',
//			data : request
//		}).success(function(response) {
//			deferred.resolve(response);
//		}).error(function(response) {			
//			deferred.reject(response);
//		});
//		return deferred.promise;
	};
});