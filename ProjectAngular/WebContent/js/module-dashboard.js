/**
 * 
 */
var dashboardModule = angular.module('dashboardModule');
dashboardModule.controller('dashboardController', function($scope, $rootScope,$location,dashboardService,dashboardService1,dashboardService2) {
	var dashboardCtrl = this;
	dashboardCtrl.messageDash = "Welcome to admin dashboard, " + $rootScope.userSession.name;
	

	dashboardCtrl.category = {
			catName : "",
			catDescription : ""
	};
	
	dashboardCtrl.addCategory = function() {
		console.log(dashboardCtrl.category);
		dashboardService2.addCategory($rootScope.userSession.id,dashboardCtrl.category, callbackSuccess,
				callbackError);
		
	}

	dashboardCtrl.message = "";
	dashboardCtrl.error = false;
	

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			dashboardCtrl.openComponentModal('Successfully added category');
			dashboardCtrl.message = "Added category";
			dashboardCtrl.error = true;
			
			

		} else {
			dashboardCtrl.message = data.message;
			dashboardCtrl.error = true;
		}

	};

	var callbackError = function(data, headers) {
		dashboardCtrl.message = data.message;
		dashboardCtrl.error = true;

	};

	
	
	dashboardCtrl.init = function(){
		
		console.log('init called')
		dashboardService.listProject(null,
				
		function(reponseData) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(reponseData);
			dashboardCtrl.projects = reponseData;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	dashboardCtrl.init1 = function(){
		
		console.log('init1 called')
		dashboardService1.listCategory($rootScope.userSession.id,null,
				
		function(reponseData) {
			$scope.message = "Success";
    		$scope.error = false;
			console.log(reponseData);
			dashboardCtrl.categories = reponseData;
		},
		function(reponseData) {
			$scope.message = "Failed to load data";
    		$scope.error = true;   
		}
		);
	}
	
	dashboardCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		$location.path('/viewProject');

	};
	


});

dashboardModule.factory('dashboardService', function($http,$timeout,APP_CONSTANT) {
	var dashboardService = {};
	
	dashboardService.listProject = function (data, callback,callbackError) {
	
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
	
	
	return dashboardService;
});

dashboardModule.factory('dashboardService1', function($http,$timeout,APP_CONSTANT) {
	var dashboardService1 = {};
	
	dashboardService1.listCategory = function (id,data, callback,callbackError) {
	
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
         			APP_CONSTANT.REMOTE_HOST+'/admin/'+id
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
	
	
	return dashboardService1;
});

dashboardModule.factory('dashboardService2', function($http,$timeout,APP_CONSTANT) {
	var dashboardService2 = {};
	
	dashboardService2.addCategory = function (id,data, callback,callbackError) {
	
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
			$http.post(APP_CONSTANT.REMOTE_HOST + '/admin/'+id+'/addCategory/', data

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
	
	
	return dashboardService2;
});


