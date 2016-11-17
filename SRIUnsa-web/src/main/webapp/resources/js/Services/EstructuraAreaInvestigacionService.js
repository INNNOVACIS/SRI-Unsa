/**
 * EstructuraAreaInvestigacion  Service
 */
investigacionApp.service("EstructuraAreaInvestigacionService", function(SRIUnsaConfig, $log, $http, $q) {
	
	this.getAreaInvestigacionByPagina = function(request) {
            $log.debug("Area Investigacion Service - getAreaInvestigacionByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/areaInvestigacion/paginacionAreaInvestigacion',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.registrarAreaInvestigacion = function(request) {
		$log.debug("AreaInvestigacion Service - Registrar AreaInvestigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'POST',
			url : SRIUnsaConfig.SRIUnsaUrlServicio +  '/areaInvestigacion/registrarAreaInvestigaciones',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.updateAreaInvestigacion = function(request) {
		$log.debug("AreaInvestigacion Service - Update AreaInvestigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/areaInvestigacion/updateAreaInvestigaciones',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};

	this.deleteAreaInvestigacion = function(request) {
		$log.debug("AreaInvestigacion Service - Delete AreaInvestigacion");
		
		var deferred = $q.defer();
		$http({
			method : 'PUT',
			url : SRIUnsaConfig.SRIUnsaUrlServicio + '/areaInvestigacion/deleteAreaInvestigaciones',
			data : request
		}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {			
			deferred.reject(response);
		});
		return deferred.promise;
	};
});