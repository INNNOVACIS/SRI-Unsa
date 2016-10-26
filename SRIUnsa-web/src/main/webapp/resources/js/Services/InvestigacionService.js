/**
 * Investigacion Service
 */
investigacionApp.service("InvestigacionService", function(SRIUnsaConfig, $log, $http, $q) {

	this.registrarInvestigacion = function(request) {
		$log.debug("Investigacion Service - Registrar Investigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/investigacion/registrarInvestigacion',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

});