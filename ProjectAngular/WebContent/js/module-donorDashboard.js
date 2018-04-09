/**
 * 
 */
var donorDashboardModule = angular.module('donorDashboardModule');
donorDashboardModule.controller('donorDashboardController', function($scope, $rootScope,$location,donorDashboardService) {
	var donorDashboardCtrl = this;
	donorDashboardCtrl.messageDash = "Welcome to donor dashboard, " + $rootScope.userSession.name;
	

	
	donorDashboardCtrl.init = function(){
		
		console.log('init called')
		donorDashboardService.listProject(null,
				
		function(reponseData) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(reponseData);
			donorDashboardCtrl.projects = reponseData;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	donorDashboardCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		$location.path('/donorProjectView');

	};
	


});

donorDashboardModule.factory('donorDashboardService', function($http,$timeout,APP_CONSTANT) {
	var donorDashboardService = {};
	
	donorDashboardService.listProject = function (data, callback,callbackError) {
	
	if(APP_CONSTANT.DEMO){
		$timeout(function(){
	         	
	     		var response;
	     		
	     			response = [
	     						{
	     							"name": "C",
	     							"desc": null
	     						},
	     						{
	     							"name": "C++",
	     							"desc": null
	     						}
	     					];
	     	
	
	         callback(response);
	     }, 1000);
		}else{
			
			 $http.get(
         			APP_CONSTANT.REMOTE_HOST+'/user/'
         			).success(function (data, status, headers, config) {
    					callback(data);
        			})
        			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
        					if(status== 422){
        						callbackError(data);
        					}
        			});
			
		}
	};
	
	
	return donorDashboardService;
});
