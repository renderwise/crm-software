/*
 * chart2
 * https://github.com/jannyK/chart2
 *
 * Copyright (c) 2014 Janny Kinende
 * Licensed under the MIT license.
 *
 *
 *Insuline Data format for a day:
 *
 *        {
            day: 'Mandag'   
            values: [
                { x: 'Starting point', y: 27.8},
                { x: 'Befor breakfast', y: 3.9 },
                { x: 'After Beakfast', y: 203.2 },
                { x: 'Before Lunch', y: 248.7 },
                { x: 'After Lunch', y: 308.7},
                { x: 'Afternoo', y: 234.5},
                ...
            ]
        }
        


 */

(function($) {

    $.widget('haii.chart2', {
        options: {
            xPadding: 30,
            yPadding: 30,
            chartHeight: 400,
            chartWidth: 800,
            data: [],
            renderPoints: true
        },

        //Widgets lifecycle
        _create: function(){
            //initialize first
            this.element.addClass('chart-container');

            var canvas = $('<canvas></canvas>')
                .attr({
                    'width': this.options.chartWidth,
                    'height': this.options.chartHeight,
                    'id': 'canvas',
                    'class': 'chart'
                });
            this.element.append(canvas);

            //updating graph initial options
            this._on('chart2dataSourceUpdated', function(event, data){
                //
            });

            //graph rendering 
            this._renderChart();
        },

        _destroy: function(){
            this._cleanUp();
            $.Widget.prototype.destroy.call(this);
        },

        _cleanUp: function(){
            //find the canvas and reset the drawing
            this.element.find('#canvas').removeClass('.chart');
            this.element.removeClass('.chart-container');
            this.element.empty();
        },

        _update: function(newDataSet){
            //Redrawing the graph based on newly provided dataset
            //this._cleanUp();
            this._setOption('data', newDataSet);
        },

        _setOption: function(key, value){
            this.options.key = value;

            //firing the callback for redrawing the chart
            this._trigger('dataUpdated', null, {data: this.options.data});
        },

        _getMaxYValue: function(){
            var maxYValue = 0;

            for (var i = 0; i < this.options.data.length; i++){
                var currentData = this.options.data[i];

                for (var j = 0; j < currentData.values.length; j++){
                    if (currentData.values[j].y > maxYValue){
                        this.options.maxYValue = currentData.values[j].y;
                        maxYValue = currentData.values[j].y;
                    }
                }
            }
            maxYValue += 10 - maxYValue % 10;

            console.log('maxYValue: '+ maxYValue);

            return maxYValue;

        },

        _getXPixel: function(value){
            var opt = this.options;
            return ((opt.chartWidth - opt.xPadding) / opt.data[0].values.length) * value + (opt.xPadding * 1.5);
        },

        _getYPixel: function(value){
            var opt = this.options;
            return opt.chartHeight - (((opt.chartHeight - opt.yPadding) / this._getMaxYValue()) * value) - opt.yPadding;
        },

        _getDrawingContext: function(){
            //find the canvas element and return its context, or
            //create a new canvas and return the context
            var canvas = this.element.find('#canvas');
            console.log('canvas: '+ canvas[0]);

            var ctx = canvas[0].getContext('2d');

            //setting the context's default options
            ctx.lineWidth = 1.5;
            ctx.strokeStyle = '#333';
            ctx.font = 'italic 8pt sans-serif';
            ctx.textAlign = "center";
            
            return ctx;
        },

        _renderAxisesAndLabels: function(){
            //Draw the axises
            var ctx = this._getDrawingContext();
            var opt = this.options

            ctx.beginPath();
            ctx.moveTo(opt.xPadding, 0);
            ctx.lineTo(opt.xPadding, opt.chartHeight - opt.yPadding);
            ctx.lineTo(opt.chartWidth, opt.chartHeight - opt.yPadding);
            ctx.stroke()

            //render x labels and vertical guides
            for(var i = 0; i < opt.data[0].values.length; i++){
                ctx.fillText(opt.data[0].values[i].x, this._getXPixel(i), opt.chartHeight - opt.yPadding + 20);

                ctx.save();
                //draw the vertical guides
                if (!ctx.setLineDash) {
                    ctx.setLineDash = function(){};
                }
                ctx.setLineDash([8, 2]);
                ctx.strokeStyle = 'gray';
                ctx.beginPath();
                ctx.moveTo(this._getXPixel(i), opt.chartHeight - opt.yPadding);
                ctx.lineTo(this._getXPixel(i), 0);
                ctx.stroke();

                ctx.restore();

            }

            //render Y labels
            ctx.textAlign = 'right';
            ctx.textBaseline = 'middle';

            console.log('options maxYValue '+ this._getMaxYValue());

            for(var i = 0; i < this._getMaxYValue(); i += 10){
                console.log('writting label y: '+ i);
                ctx.fillText(i, opt.xPadding - 10, this._getYPixel(i));
            }

            ctx.strokeStyle = '#f00';
        },

        _drawLineGraph: function(dataSerie){
            console.log('Draw line called');

            var ctx = this._getDrawingContext()

            ctx.strokeStyle = dataSerie.style.stroke;
            //ctx.fillStyle = dataSerie.style.fill;
            ctx.lineWidth = 2;
        
            ctx.beginPath();
            ctx.moveTo(this._getXPixel(0), this._getYPixel(dataSerie.values[0].y));

            for(var i = 1; i < dataSerie.values.length; i++){
                ctx.lineTo(this._getXPixel(i), this._getYPixel(dataSerie.values[i].y));
            }

            ctx.stroke();

            if (this.options.renderPoints == true) {
                //render the dot
                //must be updated
                ctx.fillStyle = dataSerie.style.stroke;

                for(var i = 0; i < dataSerie.values.length; i++){

                    ctx.beginPath();
                    ctx.arc(this._getXPixel(i), this._getYPixel(dataSerie.values[i].y), 4, 0, Math.PI * 2, true);
                    ctx.fill();
                }
                ctx.fill();
            }

        },

        _renderChart: function(){
            var opt = this.options;

            this._renderAxisesAndLabels();

            //this._drawLineGraph(opt.data[0]);
            
            for(var i = 0; i < opt.data.length; i++){
                //draw the linegraph for every dataserie
                var current = opt.data[i];
                this._drawLineGraph(current);
            }
        },

        //Defining callback
    });
}(jQuery));
