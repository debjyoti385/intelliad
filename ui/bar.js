setInterval(function(){
      // method to be executed;
		var w = 300,
		    h = 100;
        d3.select('svg').remove();
        d3.select('svg').remove();
        d3.select('svg').remove();
		
//        var ad_svg = d3.select("#ad_chart")
//			.append("svg")
//			.attr("width", w)
//			.attr("height", h);


//        var user_svg = d3.select("#user_chart")
//			.append("svg")
//			.attr("width", w)
//			.attr("height", (h+50) );

        var tweet_svg = d3.select("#tweet_chart")
			.append("svg")
			.attr("width", w)
			.attr("height", (h + 150) );

        d3.json("http://localhost/histograms.json", function(json) {
	
//			var data = json.ad_histogram;
//			//var data = json.items;
//	
//			var max_n = 0;
//			for (var d in data) {
//				//max_n = Math.max(data[d].n, max_n);
//				max_n = Math.max(data[d].value, max_n);
//			}
//		
//			var dx = w / max_n;
//			var dy = h / data.length;
//	
//			// bars
//			var bars = ad_svg.selectAll(".bar")
//				.data(data)
//				.enter()
//				.append("rect")
//				//.attr("class", function(d, i) {return "bar " + d.label;})
//				.attr("class", function(d, i) {return "bar " + d.name;})
//				.attr("x", function(d, i) {return 0;})
//				.attr("y", function(d, i) {return dy*i;})
//				//.attr("width", function(d, i) {return dx*d.n})
//				.attr("width", function(d, i) {return dx*(Math.round(d.value * 100) / 100)})
//				.attr("height", dy);
//	
//			// labels
//			var text = ad_svg.selectAll("text")
//				.data(data)
//				.enter()
//				.append("text")
//				//.attr("class", function(d, i) {return "label " + d.label;})
//				.attr("class", function(d, i) {return "label " + d.name;})
//				.attr("x",  5 )
//				.attr("y", function(d, i) {return dy*i + 15;})
//				//.text( function(d) {return d.label + " (" + d.n  + ")";})
//				.text( function(d) {return d.name + " (" + (Math.round(d.value * 100) / 100)  + ")";})
//				.attr("font-size", "15px")
//				.style("font-weight", "bold")
//                .style("align","right");


            data = json.tweet_histogram;
			//var data = json.items;
	
		    max_n = 0;
			for (var d in data) {
				//max_n = Math.max(data[d].n, max_n);
				max_n = Math.max(data[d].value, max_n);
			}
			 h = h + 100;
			 dx = w / max_n;
			 dy = h / data.length;
	
			// bars
			 bars = tweet_svg.selectAll(".bar")
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
			 text = tweet_svg.selectAll("text")
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



//            data = json.user_histogram;
//			//var data = json.items;
//	
//		    max_n = 0;
//			for (var d in data) {
//				//max_n = Math.max(data[d].n, max_n);
//				max_n = Math.max(data[d].value, max_n);
//			}
//			 h= h - 50;
//			 dx = w / max_n;
//			 dy = h / data.length;
//	
//			// bars
//			 bars = user_svg.selectAll(".bar")
//				.data(data)
//				.enter()
//				.append("rect")
//				//.attr("class", function(d, i) {return "bar " + d.label;})
//				.attr("class", function(d, i) {return "bar " + d.name;})
//				.attr("x", function(d, i) {return 0;})
//				.attr("y", function(d, i) {return dy*i;})
//				//.attr("width", function(d, i) {return dx*d.n})
//				.attr("width", function(d, i) {return dx*(Math.round(d.value * 100) / 100)})
//				.attr("height", dy);
//	
//			// labels
//			 text = user_svg.selectAll("text")
//				.data(data)
//				.enter()
//				.append("text")
//				//.attr("class", function(d, i) {return "label " + d.label;})
//				.attr("class", function(d, i) {return "label " + d.name;})
//				.attr("x",  5)
//				.attr("y", function(d, i) {return dy*i + 15;})
//				//.text( function(d) {return d.label + " (" + d.n  + ")";})
//				.text( function(d) {return d.name + " (" + (Math.round(d.value * 100) / 100)  + ")";})
//				.attr("font-size", "15px")
//				.style("font-weight", "bold")
//                .style("align","right");



		});

    },5000);

