
/**
 * Usuario Service
 */
investigacionApp.service("UsuariosService", function (SRIUnsaConfig, $log, $http, $q) {

    this.paginacionUsuario = function (request) {
        $log.debug("Usuario Service - PaginacionUsuario");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/paginacionUsuarios',
            data: request
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.getUsuarios = function () {
        $log.debug("Usuario Service - get Usuarios");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/listarUsuarios',
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.registrarUsuario = function (request) {
        $log.debug("Usuario Service - Registrar Usuario");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/registrarUsuarios',
            data: request
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.updateUsuario = function (request) {
        $log.debug("Usuario Service - Update Usuario");

        var deferred = $q.defer();
        $http({
            method: 'PUT',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/updateUsuarioPersona',
            data: request
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.deleteUsuario = function (request) {
        $log.debug("Usuario Service - Delete Usuario");

        var deferred = $q.defer();
        $http({
            method: 'PUT',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/deleteUsuarioPersona',
            data: request
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.updatePersona = function (request) {
        $log.debug("Usuario Service - UpdatePersona");

        var deferred = $q.defer();
        $http({
            method: 'PUT',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/persona/updatePersona',
            data: request
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.GetByIdUsuario = function (idUsuario) {
        $log.debug("Usuario Service - get Usuarios");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/getByIdUsuario/' + idUsuario
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };


    this.GetActoresByIdUsuario = function (idUsuario) {
        $log.debug("Usuario Service - GetActoresByIdUsuario");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetActoresByIdUsuario/' + idUsuario
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.GetUsuariosColor = function (request) {
        $log.debug("UsuarioService - GetUsuariosColor");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetUsuariosColor',
            data: request
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.GetTotalDocentesActivosInactivos = function (idSemestre) {
        $log.debug("Usuario Service - GetTotalDocentesActivosInactivos");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetTotalDocentesActivosInactivos/' + idSemestre,
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.GetTotalActivosInactivosByFacultad = function (idFacultad,idSemestre) {
        $log.debug("Usuario Service - GetTotalDocentesActivosInactivos");
 
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetTotalActivosInactivosByFacultad/' + idFacultad+'/'+idSemestre
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.GetUsuarioHome = function (idUsuario, idUsuarioDirector) {
        $log.debug("Usuario Service - GetUsuarioHome");

        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/GetUsuarioHome/' + idUsuario + '/' + idUsuarioDirector
        }).success(function (response) {
            deferred.resolve(response);
        }).error(function (response) {
            deferred.reject(response);
        });
        return deferred.promise;
    };

    this.descargarPDF = function (request) {
        $log.debug("Usuarios - descargarPDF");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarPdf',
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
    
    this.descargarHomeDirectorUnidadPDF = function (request) {
        $log.debug("Usuarios - descargarHomeDirectorUnidadPDF");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarHomeDirectorUnidadPDF',
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
    
    this.descargarHomeDirectorDepartamentoPDF = function (request) {
        $log.debug("Usuarios - descargarHomeDirectorDepartamentoPDF");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarHomeDirectorDepartamentoPDF',
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
        $log.debug("Usuarios - descargarExcel");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarExcel',
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
    
    this.descargarHomeDirectorUnidadExcel = function (request) {
        $log.debug("Usuarios - descargarHomeDirectorUnidadExcel");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarHomeDirectorUnidadExcel',
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
    
    this.descargarHomeDirectorDepartamentoExcel = function (request) {
        $log.debug("Usuarios - descargarExcel");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarHomeDirectorDepartamentoExcel',
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

    this.descargarUsuariosPDF = function (request) {
        $log.debug("UsuariosService - descargarUsuariosPDF");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarUsuariosPdf',
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

    this.descargarUsuariosExcel = function (request) {
        $log.debug("UsuariosService - descargarUsuariosExcel");

        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarUsuariosExcel',
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
    
    this.imprimirExcelDocentesInvestigando = function (request) {
        $log.debug("Usuarios - imprimirExcelDocentesInvestigando");
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/imprimirExcelDocentesInvestigando',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            var filename = headers['content-disposition'];
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
    
    this.descargarPdfDocentesActivosInactivos = function (request) {
        $log.debug("Usuarios - imprimirExcelDocentesInvestigando");
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarPdfDocentesActivosInactivos',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            var filename = headers['content-disposition'];
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
    
    this.descargarExcelDocentesActivosInactivos = function (request) {
        $log.debug("Usuarios - imprimirExcelDocentesInvestigando");
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/descargarExcelDocentesActivosInactivos',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            var filename = headers['content-disposition'];
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
    
    this.imprimirDocentesInvestigando = function (request) {
        $log.debug("Usuarios - imprimirDocentesInvestigando");
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/imprimirDocentesInvestigando',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            var filename = headers['content-disposition'];
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
    
    this.imprimirDocentesNoInvestigando = function (request) {
        $log.debug("Usuarios - imprimirDocentesNoInvestigando");
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: SRIUnsaConfig.SRIUnsaUrlServicio + '/usuarios/imprimirDocentesNoInvestigando',
            data: request,
            responseType: 'arraybuffer'
        }).success(function (data, status, headers) {
            headers = headers();
            var filename = headers['content-disposition'];
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