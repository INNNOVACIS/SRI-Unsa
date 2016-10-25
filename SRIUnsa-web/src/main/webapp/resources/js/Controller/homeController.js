investigacionApp.controller('homeController', function($log, $scope, $location, $rootScope, $filter, 
    HomeService, SharedService, FileUploader) {
    
    /*
     * Parametros
     */
    $scope.usuario = {id : 1, nombre : "ali David", usuario:"admin", clave : "abcde12345", };

    
    $scope.tipoInvestigacion = {id : 0, nombre : ""};
    $scope.nombreInvestigacion = "";
    $scope.facultad = {id : 0, nombre : ""};
    $scope.departamento = {id : 0, nombre : ""};
    $scope.escuela = {id : 0, nombre : ""};
    $scope.duracionInvestigacion = 0;
    $scope.tipoInvestigador = {id : 0, nombre : ""};
    $scope.fondo = {id : 0, nombre : ""};
    $scope.asesoria = {id : 0, nombre : ""};
    $scope.tipoProduccion = {id : 0, nombre : ""};
    $scope.tipoLabor = {id : 0, nombre : ""};
    $scope.descripcion = "";
    $scope.colaborador = {id : 0, nombre : ""};
        
    /*********** Obj JSON ***********/
    $scope.actividadInvestigacion = {};
    
    var registrarInvestigacionSuccess = function(response){
        $log.debug(response);
    };
    
    var registrarInvestigacionError = function(response){
        $log.debug(response);
    };
    
    $scope.registrarInvestigacion = function(){
        $scope.actividadInvestigacion = {
            idTipoInvestigacion : $scope.tipoInvestigacion.id,
            nombreInvestigacion : $scope.nombreInvestigacion,
            idFacultad : $scope.facultad.id,
            idDepartamento : $scope.departamento.id,
            idEscuela : $scope.escuela.id,
            duracionInvestigacion : $scope.duracionInvestigacion,
            idTipoInvestigador : $scope.tipoInvestigador.id,
            idFondo : $scope.fondo.id,
            asesoria : $scope.asesoria.id,
            idTipoProduccion : $scope.tipoProduccion.id,
            idTipoLabor : $scope.tipoLabor.id,
            //administracionAreas : $scope.customer,
            descripcion : $scope.descripcion,
            colaborador : $scope.usuario
        };
        console.log("JSON :: ", $scope.actividadInvestigacion); 
        
        HomeService.registrarInvestigacion($scope.actividadInvestigacion).then(registrarInvestigacionSuccess, registrarInvestigacionError);        
    };

    // START example DataPicker
    $scope.dt = new Date(2020, 5, 22);

    $scope.open = function($event) {
    $scope.status.opened = true;
    };

    $scope.format = 'dd-MMMM-yyyy';

    $scope.status = {
    opened: false
    };
    // END example DataPicker

    $scope.Actividad = '';
    $scope.actividad1_show = false;
    $scope.actividad2_show = false;
    $scope.actividad3_show = false;
    $scope.actividad4_show = false;

    $scope.actividadesInvestigacion = [
        {id : 1, nombre : 'Investigación Formativa'},
        {id : 2, nombre : 'Asesoria de Tesis'},
        {id : 3, nombre : 'Investigaciones Básicas y Aplicadas'},
        {id : 3, nombre : 'Producción Intelectual'}
    ];

    $scope.facultades = [
        {id : 1, nombre : 'Facultad de Producción y Servicios'},
        {id : 2, nombre : 'Facultad de Procesos'},
        {id : 3, nombre : 'Facultad de Ingenieria Civil'}
    ];
    $scope.departamentos = [
        {id : 1, nombre : 'Departamento Académico FISICA'},
        {id : 2, nombre : 'Departamento Académico QUÍMICA'},
        {id : 3, nombre : 'Departamento Académico MATEMATICAS'}
    ];
    $scope.escuelas = [
        {id : 1, nombre : 'Escuela Profesional de Ingeniería de Sistemas'},
        {id : 2, nombre : 'Industrias Alimentarias'},
        {id : 3, nombre : 'Ingeniería Mecánica'}
    ];
    $scope.semestres = [
        {id : 1, nombre : '2016-I Semestre I'},
        {id : 2, nombre : '2016-II Semestre II'},
        {id : 3, nombre : '2016-II Semestre III'}        
    ];
    $scope.tipoInvestigadores = [
        {id : 1, nombre : 'Principal'},
        {id : 2, nombre : 'Co-Investigador'}
    ];
    $scope.fondos = [
        {id : 1, nombre : 'CONCYTEC'},
        {id : 2, nombre : 'FINCyT'}
    ];
    $scope.tipoAsesorias = [
        {id : 1, nombre : 'Pre Grado'},
        {id : 2, nombre : 'Post Grado'}
    ];
    $scope.tipoProducciones = [
        {id : 1, nombre : 'Libro'},
        {id : 2, nombre : 'Artículo'},
        {id : 3, nombre : 'Ponencia Congreso Nacional'},
        {id : 4, nombre : 'Ponencia Congreso Internacional'}
    ];
    $scope.tipoLabores = [
        {id : 1, nombre : 'Lectiva'},
        {id : 2, nombre : 'No Lectiva'}
    ];

    var areaList = [
        { "id": 1, "area": "Ciencias Agrícolas" },
        { "id": 2, "area": "Ciencias Médicas y de Salud" },
        { "id": 3, "area": "Ciencias Naturales" }
    ];

    var subArealist = [
        {"Id":1, "subArea":"Agricultura, Silvicultura y Pesca", "areaId": 1},
        {"Id":2, "subArea":"Ciencias Animales y lechería", "areaId": 1},
        {"Id":3, "subArea":"Ciencias Veterinarias", "areaId": 1},
        {"Id":4, "subArea":"Medicina Básica", "areaId": 2},
        {"Id":5, "subArea":"Medicina Clínica", "areaId": 2},
        {"Id":6, "subArea":"Ciencias de la Salud", "areaId": 2},
        {"Id":7, "subArea":"Matemáticas", "areaId": 3},
        {"Id":8, "subArea":"Computación y Ciencias de la Información  ", "areaId": 3},
        {"Id":9, "subArea":"Ciencias Físicas", "areaId": 3}
    ];

    var disciplinalist = [
        {"Id":1, "disciplina":"Agricultura", "subAreaId": 1},
        {"Id":2, "disciplina":"Forestal", "subAreaId": 1},
        {"Id":3, "disciplina":"Ciencias Animales y lechería", "subAreaId": 2},
        {"Id":4, "disciplina":"Crías y mascotas", "subAreaId": 2},
        {"Id":5, "disciplina":"Ciencias Veterinarias", "subAreaId": 3},
        {"Id":6, "disciplina":"Anatomía y Morfología", "subAreaId": 4},
        {"Id":7, "disciplina":"Genética humana", "subAreaId": 4},
        {"Id":8, "disciplina":"Toxicología", "subAreaId": 4},
        {"Id":9, "disciplina":"Obstetricia y Ginecología", "subAreaId": 5},
        {"Id":10, "disciplina":"Pediatría", "subAreaId": 5},
        {"Id":11, "disciplina":"Enfermería", "subAreaId": 6},
        {"Id":12, "disciplina":"Nutrición y Dietas", "subAreaId": 6},
        {"Id":13, "disciplina":"Matemáticas Puras", "subAreaId": 7},
        {"Id":14, "disciplina":"Matemáticas Aplicadas", "subAreaId": 7},
        {"Id":15, "disciplina":"Ciencias de la Computación", "subAreaId": 8},
        {"Id":16, "disciplina":"Ciencias de la Información y Bioinformática", "subAreaId": 8},
        {"Id":17, "disciplina":"Física Atómica, Molecular y Química", "subAreaId": 9},
        {"Id":18, "disciplina":"Física de la Materia", "subAreaId": 9},
        {"Id":19, "disciplina":"Física Nuclear", "subAreaId": 9},
        {"Id":20, "disciplina":"Acústica", "subAreaId": 9}
    ];

    $scope.customer ={
        Area:'', 
        SubArea:'', 
        Disciplina: ''
    };
	

    $scope.getArea = function(){
    	return areaList;
    };

    $scope.getSubArea = function(){
    	$scope.subAreas = ($filter('filter')(subArealist, {areaId: $scope.customer.Area}));
    	
    };
    $scope.getDisciplina = function(subAreaId){    	
    	$scope.disciplinas = ($filter('filter')(disciplinalist, {subAreaId: $scope.customer.SubArea}));      	
    };

    $scope.areas = $scope.getArea();

    $scope.actividadChange = function(seleccionado){
    	$scope.Actividad = seleccionado;
    	switch ($scope.Actividad) {
		    case 'Investigación Formativa':
		        $scope.actividad1_show = true;
			    $scope.actividad2_show = false;
			    $scope.actividad3_show = false;
			    $scope.actividad4_show = false;
		        break;
		    case 'Asesoria de Tesis':
		        $scope.actividad1_show = false;
			    $scope.actividad2_show = true;
			    $scope.actividad3_show = false;
			    $scope.actividad4_show = false;
		        break;
		    case 'Investigaciones Básicas y Aplicadas':
		        $scope.actividad1_show = false;
			    $scope.actividad2_show = false;
			    $scope.actividad3_show = true;
			    $scope.actividad4_show = false;
		        break;
		    case 'Producción Intelectual':
		        $scope.actividad1_show = false;
			    $scope.actividad2_show = false;
			    $scope.actividad3_show = false;
			    $scope.actividad4_show = true;
		        break;
		}
    	console.log($scope.Actividad);
    }
    
    

    /********** FILE UPLOAD **********/  

    $scope.files = [];

    var homeServiceSuccess = function(response) {        
        $log.debug(response);
    };

    var homeServiceError = function(response) {
        $log.debug(response);
    };

    $scope.uploadFile = function(){

        var file = $scope.archivo;
        var formData = new FormData();
        formData.append('file', file);
        
        console.log("archivo :: ", $scope.archivo);
        console.log("FILE :: ", file);
        console.log("FD :: ", formData);

        HomeService.sendFile(formData, true).then(homeServiceSuccess, homeServiceError);
    };

    var uploader = $scope.uploader = new FileUploader({
            url: 'http://localhost:8080/SRIUnsa-web/rest/files'
        });

        // FILTERS

    uploader.filters.push({
            name: 'customFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                return this.queue.length < 10;
            }
        });

    $scope.uploadAll = function(){
        console.log("cola :: ", uploader.queue);
        uploader.uploadAll();
    }

        // CALLBACKS

        uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
            console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function(fileItem) {
            console.info('onAfterAddingFile', fileItem);
        };
        uploader.onAfterAddingAll = function(addedFileItems) {
            console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function(item) {
            console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function(fileItem, progress) {
            console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function(progress) {
            console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function(fileItem, response, status, headers) {
            console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function(fileItem, response, status, headers) {
            console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function(fileItem, response, status, headers) {
            console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function() {
            console.info('onCompleteAll');
        };

        console.info('uploader', uploader);
});