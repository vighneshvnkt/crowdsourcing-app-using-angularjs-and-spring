/**
 * 
 */
 
var donorProjectViewModule = angular.module('donorProjectViewModule');
donorProjectViewModule.controller('donorProjectViewController', function($scope,$rootScope,$location,donorProjectViewService,donorCommentService) {
	
	
	var donorProjectViewCtrl = this;
	var project1 = $rootScope.project;
	console.log(project1);
	
	donorProjectViewCtrl.donation = {
			cardNumber : "",
				cvv : "",
				expiryDate : "",
			donationAmount : 0,	
				projectName : project1.name
	};
	
	var d = new Date();
	
	donorProjectViewCtrl.comments = {
			commentName : "",
			commentDate : d,
			projectName : project1.name
	};
	
	
	donorProjectViewCtrl.addDonation = function() {
		console.log(donorProjectViewCtrl.donation);
		
		donorProjectViewService.addDonation($rootScope.userSession.id,donorProjectViewCtrl.donation, callbackSuccess
				,callBackError);
	}
	
	donorProjectViewCtrl.addComment = function() {
		console.log(donorProjectViewCtrl.comments);
		
		donorCommentService.addComment($rootScope.userSession.id,donorProjectViewCtrl.comments, callbackSuccess
				,callBackError);
	}
	
		donorProjectViewCtrl.message = "";
		donorProjectViewCtrl.error = false;
		

		var callbackSuccess = function(data, headers) { // Status
			// Code:200
			if (data.success) {
				donorProjectViewCtrl.openComponentModal('Successfully added category');
				donorProjectViewCtrl.message = "Added donation";
				donorProjectViewCtrl.error = true;
				
				

			} else {
				donorProjectViewCtrl.message = data.message;
				donorProjectViewCtrl.error = true;
			}

		};

		var callBackError = function(data, headers) {
			donorProjectViewCtrl.message = data.message;
			donorProjectViewCtrl.error = true;

		};

		

	
});

donorProjectViewModule.factory('donorProjectViewService', function($http,$timeout,APP_CONSTANT) {
	var donorProjectViewService = {};
	
	donorProjectViewService.addDonation = function (id,data, callback,callBackError) {
		
	
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
			$http.post(APP_CONSTANT.REMOTE_HOST + '/donor/'+id+'/addDonation/', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				callBackError(data, headers);
			});
			
		}
	};
	
	
	return donorProjectViewService;
});


donorProjectViewModule.factory('donorCommentService', function($http,$timeout,APP_CONSTANT) {
	var donorCommentService = {};
	
	donorCommentService.addComment = function (id,data, callback,callBackError) {
		
	
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
			$http.post(APP_CONSTANT.REMOTE_HOST + '/donor/'+id+'/addComment/', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { // IF
				// STATUS
				// CODE
				// NOT
				// 200
				callBackError(data, headers);
			});
			
		}
	};
	
	
	return donorCommentService;
});