/**
 * Usuario Service
 */
investigacionApp.service("PersonasService", function(SRIUnsaConfig, $log, $http, $q) {
	

        
	this.getPersonas = function() {
		$log.debug("Personas Service - getPersonas");
		
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/persona/getPersonas',
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

});