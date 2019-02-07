

var myModule = angular.module('YourApp', ['ngMaterial','mdSteppers']);
var ctrl = myModule.controller('HamfekranController', function($scope,$mdStepper,$timeout) {
        this.$mdStepper = $mdStepper;
        this.$timeout = $timeout;
        this.isLinear = true;
        this.isAlternative = true;
		var steppers=$mdStepper;
		
		$scope.goToStep=function (step) {	
			steppers('main-stepper').goto(step);
		};
		$scope.goBackStep=function (){
			steppers('main-stepper').back();
		}
});










