investigacionApp.controller('RelacionDocentesController', function($scope) {
	
	$scope.actividadesInvestigacion = [
		'Investigación Formativa',
		'Asesoria de Tesis',
		'Investigaciones Básicas y Aplicadas',
		'Producción Intelectual'
	];
	$scope.panelGenerados = false;
	$scope.panelVer = true;
	$scope.actividad= "primer valor";

	$scope.panelChange = function(panel){
		if(panel == 1){
			$scope.panelGenerados = false;
			$scope.panelVer = true;			
		}else{
			if(panel == 2){
				$scope.panelGenerados = true;
				$scope.panelVer = false;
			}else{
				$scope.panelGenerados = true;
				$scope.panelVer = true;				
			}
		}
	}
});
