investigacionApp.directive('passwordCheck', ['$parse', function ($parse) {
    return {
        require: 'ngModel',
        link: function(scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.pwCheck;
            elem.add(firstPassword).on('keyup', function () {
                scope.$apply(function () {                    
                    ctrl.$setValidity('pwmatch', elem.val() === $(firstPassword).val());
                });
            });
      }
    };
}]);
