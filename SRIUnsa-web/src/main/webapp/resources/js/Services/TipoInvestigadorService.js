/**
 * Tipo Nivel Service
 */
investigacionApp.service("TipoInvestigadorService", function(SRIUnsaConfig, $log, $http, $q) {
	
        this.getTipoInvestigadorByPagina = function(request) {
            $log.debug("TipoInvestigadorService - getTipoInvestigadorByPagina");

            var deferred = $q.defer();
            $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigador/getTipoInvestigadorByPagina',
                data : request
            }).success(function(response) {
                deferred.resolve(response);
            }).error(function(response) {			
                deferred.reject(response);
            });
            return deferred.promise;
	};
        
	this.GetTipoInvestigador = function() {
            $log.debug("TipoInvestigador Service - GetTipoInvestigador");

            var deferred = $q.defer();
            $http({
                    method : 'GET',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigador/listarTipoInvestigador',
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.registrarTipoInvestigador = function(request) {
            $log.debug("TipoInvestigador Service - Registrar Tipo Investigador");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigador/registrarTipoInvestigador',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.updateTipoInvestigador = function(request) {
            $log.debug("TipoInvestigador Service - Update Tipo Investigador");

            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigador/updateTipoInvestigador',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};

	this.deleteTipoInvestigador = function(request) {
            $log.debug("Tipo Investigador Service - Delete TipoInvestigador");

            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/tipoInvestigador/deleteTipoInvestigador',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
});