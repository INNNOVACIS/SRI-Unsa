/**
 * TipoInvestigacion  Service
 */
investigacionApp.service("TipoInvestigacionService", function(SRIUnsaConfig, $log, $http, $q) {
	
	this.getInvestigaciones = function() {
		$log.debug("Investigacion Service - get Investigaciones");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigacion/listarTipoInvestigacion',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarInvestigacion = function(request) {
		$log.debug("Investigacion Service - Registrar Investigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio +  '/tipoInvestigacion/registrarTipoInvestigacion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateInvestigacion = function(request) {
		$log.debug("Investigacion Service - Update Investigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigacion/updateTipoInvestigacion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteInvesigacion = function(request) {
		$log.debug("Investigacion Service - Delete Investigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigacion/deleteTipoInvestigacion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});