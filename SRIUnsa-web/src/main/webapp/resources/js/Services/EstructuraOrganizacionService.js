/**
 * EstructuraOrganizacion  Service
 */
investigacionApp.service("EstructuraOrganizacionService", function(SRIUnsaConfig, $log, $http, $q) {
	
	this.getEstructuraOrganizaciones = function() {
		$log.debug("EstructuraOrganizacion Service - get EstructuraOrganizaciones");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estructuraOrganizacion/listarEstructuraOrganizaciones',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.registrarEstructuraOrganizacion = function(request) {
		$log.debug("EstructuraOrganizacion Service - Registrar EstructuraOrganizacion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio +  '/estructuraOrganizacion/registrarEstructuraOrganizaciones',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateEstructuraOrganizacion = function(request) {
		$log.debug("EstructuraOrganizacion Service - Update EstructuraOrganizacion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estructuraOrganizacion/updateEstructuraOrganizaciones',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteEstructuraOrganizacion = function(request) {
		$log.debug("EstructuraOrganizacion Service - Delete EstructuraOrganizacion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estructuraOrganizacion/deleteEstructuraOrganizaciones',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});