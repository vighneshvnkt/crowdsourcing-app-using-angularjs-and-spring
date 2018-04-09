/**
 * 
 */

var homeModule = angular.module('homeModule'); // Please dont not use [],
// dependency

homeModule.controller('homeController', function($scope, $rootScope,$location,homeService) {
	var homeCtrl = this;
	homeCtrl.messageDash = "Welcome home" ;
	

	
	homeCtrl.init = function(){
		
		console.log('init called')
		homeService.listProject(null,
				
		function(reponseData) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(reponseData);
			homeCtrl.projects = reponseData;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	homeCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		homeCtrl.projectError = "Please login to continue";

	};
});

homeModule.factory('homeService', function($http,$timeout,APP_CONSTANT) {
	var homeService = {};
	
	homeService.listProject = function (data, callback,callbackError) {
	
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
	
	
	return homeService;
});

