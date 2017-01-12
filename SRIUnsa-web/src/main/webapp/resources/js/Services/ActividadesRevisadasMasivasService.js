/**
 * Actividades Revisadas Service
 */
investigacionApp.service("ActividadesRevisadasMasivasService", function(SRIUnsaConfig, $log, $http, $q) {
	
    this.AprobarActividades = function(request) {
        $log.debug("ActividadesRevisadasMasivasService - AprobarActividades");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/aprobarActividades',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetCabeceraMasiva = function(id){
        $log.debug("ActividadesRevisadasMasivasService - GetCabeceraMasiva");
		
        var deferred = $q.defer();
        $http({
                method : 'GET',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/getCabeceraMasiva/' + id
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {			
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetDetalleMasiva = function(request){
        $log.debug("ActividadesRevisadasMasivasService - GetDetalleMasiva");
		
        var deferred = $q.defer();
        $http({
                method : 'POST',
                url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/getDetalleMasiva',
                data : request
        }).success(function(response) {
                deferred.resolve(response);
        }).error(function(response) {			
                deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.GetActividadesRevisadasMasivas = function(request) {
        $log.debug("ActividadesRevisadasService - GetActividadesRevisadasMasivas");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/getActividadesRevisadasMasivas',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.descargarPDF = function (request) {
        $log.debug("ActividadesGeneradasService - descargarPDF");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/descargarPdf',
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
        $log.debug("ReportesService - descargarExcel");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionRevisada/descargarExcel',
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