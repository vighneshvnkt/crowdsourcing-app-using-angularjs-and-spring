/**
 * 
 */
var creatorDashboardModule = angular.module('creatorDashboardModule');
creatorDashboardModule.controller('creatorDashboardController', function($scope, $rootScope,$location,creatorDashboardService,creatorDashboardService1,creatorDashboardService2) {
	var creatorDashboardCtrl = this;
	creatorDashboardCtrl.messageDash = "Welcome to creator dashboard, " + $rootScope.userSession.name;
	

	creatorDashboardCtrl.newProject = {
			 projectName : "",
			 projectDescription : "",
			 projectDeadline : "",
			 amountRequired : "",
			 projectCategory : "creatorDashboardCtrl.categories[0].catName"
	};
	

	creatorDashboardCtrl.message = "";
	creatorDashboardCtrl.error = false;
	
	creatorDashboardCtrl.addProject = function() {
		console.log(creatorDashboardCtrl.newProject);
		creatorDashboardService1.addProject($rootScope.userSession.id,creatorDashboardCtrl.newProject, callbackSuccess,
				callbackError);
		
	}

	

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			creatorDashboardCtrl.openComponentModal('Successfully added category');
			creatorDashboardCtrl.message = "Added project";
			creatorDashboardCtrl.error = true;
			
			

		} else {
			creatorDashboardCtrl.message = data.message;
			creatorDashboardCtrl.error = true;
		}

	};

	var callbackError = function(data, headers) {
		creatorDashboardCtrl.message = data.message;
		creatorDashboardCtrl.error = true;

	};

	
	
	creatorDashboardCtrl.init = function(){
		
		console.log('init called')
		creatorDashboardService.listProject($rootScope.userSession.id,null,
				
		function(reponseData) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(reponseData);
			creatorDashboardCtrl.projects = reponseData;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	creatorDashboardCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		$location.path('/project/view');

	};
	
	creatorDashboardCtrl.init1 = function(){
		
		console.log('init1 called')
		creatorDashboardService2.listCategory($rootScope.userSession.id,null,
				
		function(reponseData) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(reponseData);
			creatorDashboardCtrl.categories = reponseData;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	


});

creatorDashboardModule.factory('creatorDashboardService', function($http,$timeout,APP_CONSTANT) {
	var creatorDashboardService = {};
	
	creatorDashboardService.listProject = function (id,data, callback,callbackError) {
	
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
         			APP_CONSTANT.REMOTE_HOST+'/user/'+id+'/project'
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
	
	
	return creatorDashboardService;
});

creatorDashboardModule.factory('creatorDashboardService1', function($http,$timeout,APP_CONSTANT) {
	var creatorDashboardService1 = {};
	
	creatorDashboardService1.addProject = function (id,data, callback,callbackError) {
	
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
			
			/*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
			$http.post(APP_CONSTANT.REMOTE_HOST + '/user/'+id+'/project/addProject/', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				callbackError(data, headers);
			});
			
		}
	};
	
	
	return creatorDashboardService1;
});


creatorDashboardModule.factory('creatorDashboardService2', function($http,$timeout,APP_CONSTANT) {
	var creatorDashboardService2 = {};
	
	creatorDashboardService2.listCategory = function (id,data, callback,callbackError) {
	
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
         			APP_CONSTANT.REMOTE_HOST+'/creator/'+id
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
	
	
	return creatorDashboardService2;
});
