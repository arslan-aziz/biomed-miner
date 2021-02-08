import React from 'react';
import * as d3 from 'd3';

class GraphViz extends React.Component {

    componentDidMount() {
        this.draw();
    }

    draw() {

        let width = this.props.width;
        let height = this.props.height;
        let margin = this.props.margin;
        let data = this.props.data;

        let svg = d3.select("body")
        .append('svg')
        .attr('width', width)
        .attr('height', height)
        .attr("preserveAspectRatio", "xMinYMin meet")
        .attr("viewBox", `0 0 ${width} ${height}`)
        .append('g')
        .attr('transform', `translate(${-1*margin.left}, ${-1*margin.top})`);
        
        let link = svg
        .selectAll("line")
        .data(data.links)
        .enter()
        .append("line")
        .style("stroke", "#aaa")
        
        let node = svg
        .selectAll("circle")
        .data(data.nodes)
        .enter()
        .append("circle")
            .attr("r", 30)
            .style("fill", "#69b3a2")
        
        let nodeText = svg
        .selectAll("text")
        .data(data.nodes)
        .enter()
        .append("text")
        
        let simulation = d3.forceSimulation(data.nodes)                 // Force algorithm is applied to data.nodes
        .force("link", d3.forceLink()                               // This force provides links between nodes
                .id(function(d) { return d.id; })                     // This provide  the id of a node
                .links(data.links)                                    // and this the list of links
        )
        .force("charge", d3.forceManyBody().strength(-800))         // This adds repulsion between nodes. Play with the -400 for the repulsion strength
        .force("center", d3.forceCenter(width / 4, height / 2 - 100))     // This force attracts nodes to the center of the svg area
        .on("end", ticked);
        
        function ticked() {
            link
                .attr("x1", function(d) { return d.source.x; })
                .attr("y1", function(d) { return d.source.y; })
                .attr("x2", function(d) { return d.target.x; })
                .attr("y2", function(d) { return d.target.y; });
            
            node
                .attr("cx", function (d) { return d.x+6; })
                .attr("cy", function(d) { return d.y-6; });
            
            nodeText
                .attr("x", function(d){return d.x})
                .attr("y", function(d){return d.y})
                .text(function(d){return d.name})
        }
    }

    render() {
        return <div id={"#" + this.props.id}></div>
    }
}

export default GraphViz; 

