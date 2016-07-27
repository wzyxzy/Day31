$(document).ready(
	function(){
		function zoom() {
				var zoom = $(window).width() / 640;
				$("body").css("zoom", zoom).show();
			}
			zoom();
			window.onresize = zoom;
	}
)