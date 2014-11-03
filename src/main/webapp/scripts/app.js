'use strict';

/**
 * @ngdoc overview
 * @name anliantestApp
 * @description
 * # anliantestApp
 *
 * Main module of the application.
 */
angular
  .module('anliantestApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    'dialogs.main',
    'angularFileUpload',
    'ui.bootstrap',
    'datatables'
  ])
  .config(function ($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/project', {
        templateUrl: 'views/project.html',
        controller: 'ProjectCtrl'
      })
      .when('/employee', {
        templateUrl: 'views/employee.html',
        controller: 'EmployeeCtrl'
      })
      .when('/project/:id', {
        templateUrl: 'views/project-detail.html',
        controller: 'ProjectDetailCtrl'
      })
      .when('/customer', {
        templateUrl: 'views/customer.html',
        controller: 'CustomerCtrl'
      })
      .when('/test', {
        templateUrl: 'views/test.html',
        controller: 'TestCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
      $locationProvider.html5Mode(true); 
  });
