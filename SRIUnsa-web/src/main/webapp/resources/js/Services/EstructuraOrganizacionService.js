/**
 * EstructuraOrganizacion  Service
 */
investigacionApp.service("EstructuraOrganizacionService", function(SRIUnsaConfig, $log, $http, $q) {
        
        this.getEstructuraOrganizacionByPagina = function(request) {
            $log.debug("Estructura Organizacion Service - getEstructuraOrganizacionByPagina");

            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url : SRIUnsaConfig.SRIUnsaUrlServicio + '/estructuraOrganizacion/paginacionEstructuraOrganizacion',
                    data : request
            }).success(function(response) {
                    deferred.resolve(response);
            }).error(function(response) {			
                    deferred.reject(response);
            });
            return deferred.promise;
	};
        
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
        
    this.descargarPDF = function (request) {
        $log.debug("EstructuraOrganizacionService - descargarPDF");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/estructuraOrganizacion/descargarPdf',
            //url : 'generadorpdfss/descargarPdf',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();

            var filename = headers['content-disposition'];//['x-filename'];
            var contentType = headers['content-type'];

            var linkElement = document.createElement('a');
            try {
                var blob = new Blob([data], {type: contentType});
                var url = window.URL.createObjectURL(blob);

                linkElement.setAttribute('href', url);
                linkElement.setAttribute("download", filename);

                var clickEvent = new MouseEvent("click", {
                    "view": window,
                    "bubbles": true,
                    "cancelable": false
                });
                linkElement.dispatchEvent(clickEvent);
            } catch (ex) {
                console.log(ex);
            }
            deferred.resolve(data);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.descargarExcel = function (request) {
        $log.debug("EstructuraOrganizacionService - descargarExcel");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/estructuraOrganizacion/descargarExcel',
            //url : 'generadorpdfss/descargarPdf',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();

            var filename = headers['content-disposition'];//['x-filename'];
            var contentType = headers['content-type'];

            var linkElement = document.createElement('a');
            try {
                var blob = new Blob([data], {type: contentType});
                var url = window.URL.createObjectURL(blob);

                linkElement.setAttribute('href', url);
                linkElement.setAttribute("download", filename);

                var clickEvent = new MouseEvent("click", {
                    "view": window,
                    "bubbles": true,
                    "cancelable": false
                });
                linkElement.dispatchEvent(clickEvent);
            } catch (ex) {
                console.log(ex);
            }
            deferred.resolve(data);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };
});