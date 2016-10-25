investigacionApp.controller('estadoActividadesController', function($scope) {
	
    /*Estado Acitvidad*/
    var buttonTemplate = '<button data-toggle="modal" data-target="#modalVer" type="submit" class="btn-sm btn-success">Revisar</button>';

    console.log(buttonTemplate);

    $scope.getContent = function(tmp){
    	return tmp.accion;
    }

    $scope.estado4 = 'Generado';

    $scope.actividades = [
    	{id: 1, nombre: "Tratamiento sostenible y a bajo costo de agua con arsénico", tipo:"investigacion",duracion: "100 horas", file: 4, estado:"Revisado", accion: "<button>1st</button>"}
    ];

	$scope.estadoChange = function(estado){
		$scope.estado4 = estado;
	};
});
