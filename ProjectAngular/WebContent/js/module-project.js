/**
 * 
 */
 
var projectModule = angular.module('projectModule');
projectModule.controller('projectController', function($scope,$rootScope) {
	var project1 = $rootScope.project;
	console.log(project1);
});