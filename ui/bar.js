function update_histograms() {
//setInterval(function(){
      // method to be executed;
		var w = 250,
		    h = 125;
        d3.select('svg').remove();
        d3.select('svg').remove();
        d3.select('svg').remove();
        d3.select('svg').remove();
		
        var tweet_nutrition_svg = d3.select("#tweet_nutrition_chart")
			.append("svg")
			.attr("width", w)
			.attr("height", h);


        var user_nutrition_svg = d3.select("#user_nutrition_chart")
			.append("svg")
			.attr("width", w)
			.attr("height",h);

        var tweet_health_svg = d3.select("#tweet_health_chart")
			.append("svg")
			.attr("width", w)
			.attr("height", h);

        var user_health_svg = d3.select("#user_health_chart")
			.append("svg")
			.attr("width", w)
			.attr("height", h);

        d3.json("http://localhost/histograms.json", function(json) {
	
			var data = json.tweet_nutrition_chart;
			//var data = json.items;
	
			var max_n = 0;
			for (var d in data) {
				//max_n = Math.max(data[d].n, max_n);
				max_n = Math.max(data[d].value, max_n);
			}
		
			var dx = w / max_n;
			var dy = h / data.length;
	
			// bars
			var bars = tweet_nutrition_svg.selectAll(".bar")
				.data(data)
				.enter()
				.append("rect")
				//.attr("class", function(d, i) {return "bar " + d.label;})
				.attr("class", function(d, i) {return "bar " + d.name;})
				.attr("x", function(d, i) {return 0;})
				.attr("y", function(d, i) {return dy*i;})
				//.attr("width", function(d, i) {return dx*d.n})
				.attr("width", function(d, i) {return dx*(Math.round(d.value * 100) / 100)})
				.attr("height", dy);
	
			// labels
			var text = tweet_nutrition_svg.selectAll("text")
				.data(data)
				.enter()
				.append("text")
				//.attr("class", function(d, i) {return "label " + d.label;})
				.attr("class", function(d, i) {return "label " + d.name;})
				.attr("x",  5 )
				.attr("y", function(d, i) {return dy*i + 15;})
				//.text( function(d) {return d.label + " (" + d.n  + ")";})
				.text( function(d) {return d.name + " (" + (Math.round(d.value * 100) / 100)  + ")";})
				.attr("font-size", "15px")
				.style("font-weight", "bold")
                .style("align","right");


            data = json.tweet_health_chart;
			//var data = json.items;
	
		    max_n = 0;
			for (var d in data) {
				//max_n = Math.max(data[d].n, max_n);
				max_n = Math.max(data[d].value, max_n);
			}
			 //h = h + 100;
			 h = h;
			 dx = w / max_n;
			 dy = h / data.length;
	
			// bars
			 bars = tweet_health_svg.selectAll(".bar")
				.data(data)
				.enter()
				.append("rect")
				//.attr("class", function(d, i) {return "bar " + d.label;})
				.attr("class", function(d, i) {return "bar " + d.name;})
				.attr("x", function(d, i) {return 0;})
				.attr("y", function(d, i) {return dy*i;})
				//.attr("width", function(d, i) {return dx*d.n})
				.attr("width", function(d, i) {return dx*(Math.round(d.value * 100) / 100)})
				.attr("height", dy);
	
			// labels
			 text = tweet_health_svg.selectAll("text")
				.data(data)
				.enter()
				.append("text")
				//.attr("class", function(d, i) {return "label " + d.label;})
				.attr("class", function(d, i) {return "label " + d.name;})
				.attr("x",  5)
				.attr("y", function(d, i) {return dy*i + 15;})
				//.text( function(d) {return d.label + " (" + d.n  + ")";})
				.text( function(d) {return d.name + " (" + (Math.round(d.value * 100) / 100)  + ")";})
				.attr("font-size", "15px")
				.style("font-weight", "bold")
                .style("align","right");



            data = json.user_nutrition_chart;
			//var data = json.items;
	
		    max_n = 0;
			for (var d in data) {
				//max_n = Math.max(data[d].n, max_n);
				max_n = Math.max(data[d].value, max_n);
			}
			 h= h;
			 dx = w / max_n;
			 dy = h / data.length;
	
			// bars
			 bars = user_nutrition_svg.selectAll(".bar")
				.data(data)
				.enter()
				.append("rect")
				//.attr("class", function(d, i) {return "bar " + d.label;})
				.attr("class", function(d, i) {return "bar " + d.name;})
				.attr("x", function(d, i) {return 0;})
				.attr("y", function(d, i) {return dy*i;})
				//.attr("width", function(d, i) {return dx*d.n})
				.attr("width", function(d, i) {return dx*(Math.round(d.value * 100) / 100)})
				.attr("height", dy);
	
			// labels
			 text = user_nutrition_svg.selectAll("text")
				.data(data)
				.enter()
				.append("text")
				//.attr("class", function(d, i) {return "label " + d.label;})
				.attr("class", function(d, i) {return "label " + d.name;})
				.attr("x",  5)
				.attr("y", function(d, i) {return dy*i + 15;})
				//.text( function(d) {return d.label + " (" + d.n  + ")";})
				.text( function(d) {return d.name + " (" + (Math.round(d.value * 100) / 100)  + ")";})
				.attr("font-size", "15px")
				.style("font-weight", "bold")
                .style("align","right");

            data = json.user_health_chart;
			//var data = json.items;
	
		    max_n = 0;
			for (var d in data) {
				//max_n = Math.max(data[d].n, max_n);
				max_n = Math.max(data[d].value, max_n);
			}
			 h= h;
			 dx = w / max_n;
			 dy = h / data.length;
	
			// bars
			 bars = user_health_svg.selectAll(".bar")
				.data(data)
				.enter()
				.append("rect")
				//.attr("class", function(d, i) {return "bar " + d.label;})
				.attr("class", function(d, i) {return "bar " + d.name;})
				.attr("x", function(d, i) {return 0;})
				.attr("y", function(d, i) {return dy*i;})
				//.attr("width", function(d, i) {return dx*d.n})
				.attr("width", function(d, i) {return dx*(Math.round(d.value * 100) / 100)})
				.attr("height", dy);
	
			// labels
			 text = user_health_svg.selectAll("text")
				.data(data)
				.enter()
				.append("text")
				//.attr("class", function(d, i) {return "label " + d.label;})
				.attr("class", function(d, i) {return "label " + d.name;})
				.attr("x",  5)
				.attr("y", function(d, i) {return dy*i + 15;})
				//.text( function(d) {return d.label + " (" + d.n  + ")";})
				.text( function(d) {return d.name + " (" + (Math.round(d.value * 100) / 100)  + ")";})
				.attr("font-size", "15px")
				.style("font-weight", "bold")
                .style("align","right");

		});

//    },5000);
}
