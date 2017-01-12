/**
 * Actividades Pendientes Service
 */
investigacionApp.service("ActividadesPendientesService", function(SRIUnsaConfig, $log, $http, $q) {
    
    this.paginacionActividades = function(request) {
        $log.debug("Actividades Generadas Service - paginacionActividades");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/actividadesPaginacion',
            data : request
        }).success(function(response) {
            deferred.resolve(response);
        }).error(function(response) {			
            deferred.reject(response);
        });
        return deferred.promise;
    };
    
    this.AprobarActividad = function(request) {
        $log.debug("ActividadesPendientesService - AprobarActividad");

        var deferred = $q.defer();
        $http({
            method : 'POST',
            url : SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/aprobarActividad',
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
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/descargarPdf',
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
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/actividadInvestigacionPendiente/descargarExcel',
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