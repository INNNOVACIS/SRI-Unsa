investigacionApp.controller('generarDocenteController', function($scope) {
	
	$scope.actividadesInvestigacion = [
		'Investigación Formativa',
		'Asesoria de Tesis',
		'Investigaciones Básicas y Aplicadas',
		'Producción Intelectual'
	];
	$scope.panelGenerados = true;
	$scope.panelVer = false;
	$scope.panelEditar = false;
	$scope.actividad= "primer valor";

	$scope.panelChange = function(panel){
		if(panel == 1){
			$scope.panelGenerados = true;
			$scope.panelVer = false;
			$scope.panelEditar = false;
		}else{
			if(panel == 2){
				$scope.panelGenerados = false;
				$scope.panelVer = true;
				$scope.panelEditar = false;
			}else{
				$scope.panelGenerados = false;
				$scope.panelVer = false;
				$scope.panelEditar = true;
			}
		}  
	}
});
