AddWidget.$inject = ['WidgetInstances', '$compile', 'WidgetTemplates', '$http'];

export default function AddWidget(WidgetInstances, $compile, WidgetTemplates, $http) {

	function AddWidget($scope, $mdDialog, visualizablePipelines, rerenderDashboard, layoutId) {
		$scope.page = 'select-viz';

		$scope.pages = [{
			type : "select-viz",
			title : "Data Stream",
			description : "Select a data stream you'd like to visualize"
		},{
			type : "select-type",
			title : "Visualization Type",
			description : "Select a visualization type"
		},{
			type : "select-scheme",
			title : "Visualization Settings",
			description : "Customize your visualization"
		}];

		$scope.getTabCss = function(page) {
			if (page == $scope.page) return "md-fab md-accent";
			else return "md-fab md-accent wizard-inactive";
		}

		$scope.visualizablePipelines = angular.copy(visualizablePipelines);

		// This is the object that the user manipulates
		$scope.selectedVisualisation = {};

		$scope.possibleVisualisationTypes = WidgetTemplates.getAllNames();
		$scope.selectedVisualisationType = '';

		$scope.next = function() {
			if ($scope.page == 'select-viz') {
				$scope.page = 'select-type';
			} else if ($scope.page == 'select-type') {
				$scope.page = 'select-scheme';

				var directiveName = 'sp-' + $scope.selectedType + '-widget-config'
				var widgetConfig = $compile( '<'+ directiveName + ' wid=selectedVisualisation></' + directiveName + '>')( $scope );

				var schemaSelection = angular.element( document.querySelector( '#scheme-selection' ) );
				schemaSelection.append( widgetConfig );

			} else {

				var widget = {};
				widget.visualisationType = $scope.selectedType;
				widget.visualisation = $scope.selectedVisualisation;
				widget.layoutId = layoutId;


				widget.visualisationId = $scope.selectedVisualisation._id;
				WidgetInstances.add(widget);
				rerenderDashboard();
				$mdDialog.cancel();

			}
		}

		$scope.cancel = function() {
			$mdDialog.cancel();
		};
	};

	return AddWidget;
};
