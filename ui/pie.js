	function update_pieChart(){
//setInterval(function(){
	var w = 300;
	var h = 300;
	var r = h/2;
	var color = d3.scale.category20();
	d3.select('svg').remove();
	d3.select('svg').remove();
	/*
	var data = [{"label":"Category A", "value":20}, 
	    {"label":"Category B", "value":50}, 
	    {"label":"Category C", "value":30}];
	*/
	d3.json("histograms.json", function(json){
		var data = json.user_health_chart;
		var vis = d3.select("#pie_chart").append("svg:svg").data([data]).attr("width", w).attr("height", h).append("svg:g").attr("transform", "translate(" + r + "," + r + ")");
		var pie = d3.layout.pie().value(function(d){return d.value;});

		// declare an arc generator function
		var arc = d3.svg.arc().outerRadius(r);

		// select paths, use arc generator to draw
		var arcs = vis.selectAll("g.slice").data(pie).enter().append("svg:g").attr("class", "slice");
		arcs.append("svg:path")
		.attr("fill", function(d, i){
			return color(i);
			})
		.attr("d", function (d) {
			// log the result of the arc generator to show how cool it is :)
		console.log(arc(d));
		return arc(d);
		});

	// add the text
	arcs.append("svg:text").attr("transform", function(d){
			d.innerRadius = r-110;
			d.outerRadius = r;

			return "translate(" + arc.centroid(d) + ") rotate(75)";}).attr("font-size","10px").attr("font-weight","bold").attr("text-anchor","middle").text( function(d, i) {
			return data[i].name + "(" + (Math.round(data[i].value * 100) / 100)  + "%)";}
			);
			
		data = json.user_nutrition_chart;
		vis = d3.select("#pie_chart1").append("svg:svg").data([data]).attr("width", w).attr("height", h).append("svg:g").attr("transform", "translate(" + r + "," + r + ")");
		pie = d3.layout.pie().value(function(d){return d.value;});

		// declare an arc generator function
		arc = d3.svg.arc().outerRadius(r);

		// select paths, use arc generator to draw
		arcs = vis.selectAll("g.slice").data(pie).enter().append("svg:g").attr("class", "slice");
		arcs.append("svg:path")
		.attr("fill", function(d, i){
			return color(i);
			})
		.attr("d", function (d) {
			// log the result of the arc generator to show how cool it is :)
		console.log(arc(d));
		return arc(d);
		});

	// add the text
	arcs.append("svg:text").attr("transform", function(d){
			d.innerRadius = r-110;
			d.outerRadius = r;

			return "translate(" + arc.centroid(d) + ") rotate(75)";}).attr("font-size","10px").attr("font-weight","bold").attr("text-anchor","middle").text( function(d, i) {
			return data[i].name + "(" + (Math.round(data[i].value * 100) / 100)  + "%)";}
			);			
	});
}
