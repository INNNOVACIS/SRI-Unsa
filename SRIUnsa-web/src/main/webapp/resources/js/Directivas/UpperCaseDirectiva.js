investigacionApp.directive('uppercase', ['$parse', function ($parse) {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            var uppercase = function(inputValue) {
                if (inputValue === undefined) inputValue = '';
                    var capitalized = inputValue.toUpperCase();
                if (capitalized !== inputValue) {
                    modelCtrl.$setViewValue(capitalized);
                    modelCtrl.$render();
                }
            return capitalized;
        };
        modelCtrl.$parsers.push(uppercase);
        uppercase(scope[attrs.ngModel]); // capitalize initial value
      }
    };
}]);
