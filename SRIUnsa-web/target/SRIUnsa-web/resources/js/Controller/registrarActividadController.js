
investigacionApp.controller('registrarInvestigacionController', function($scope) {
	$scope.activeTab = 1;
	$scope.setActiveTab = function(tabToSet){
		$scope.activeTab = tabToSet;
		console.log("call function!!!");
	};

	$scope.listaProyectos = [
		'Tratamiento sostenible y a bajo costo de agua con arsénico',
		'Reanimador de Uso Fácil'
	];
});

