import React, { useState } from 'react';

import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

import SideBar from '../SideBar/Sidebar';
import NavBar from '../NavBar/NavBar';
import Graph from '../Graph/Graph';
import defaultGraph from '../data/default_graph.json';
import axios from 'axios';
import selectionToIdDict from '../config/selection_to_id_dict';
import net_config from '../config/net_config.json';
import parseResponse from './parsing';

const Home = (props) => {

    const [ graphData, setGraphData ] = useState(defaultGraph)

    const openQuery = (evt) => {
        evt.preventDefault()
        let queryValue = evt.target[0].value
        console.log(queryValue)
        
        let url = 'http://127.0.0.1:8080/nlpextraction'
        const options = {
            url: url,
            method: 'GET',
            params: { querykey: queryValue }
        }
        
        axios(options)
        .then(response => {
            console.log(response)
        })
        .catch(error => {
            console.log(error)
        })
    }
    
    const selectData = (selector) => {
        console.log(selector);
        
        // get resource id from selection
        let articleId = selectionToIdDict[selector];
        let url = `http://${net_config.server_ip}/article/${articleId}`
        
        const options = {
            url: url,
            method: 'GET'
        }
        
        axios(options)
        .then(response => {
            setGraphData(parseResponse(response))
        })
        .catch(error => {
            console.log(error)
        })
    }

    return (
        <Container fluid className="vh-100" style={homeStyle}>
            <NavBar />
            <Row className="h-100">
                <Col lg={3}>
                    <SideBar selectData={ selectData } queryData={ openQuery }/>
                </Col>
                <Col lg={9}>
                    <Graph data={ graphData }/>
                </Col>
            </Row>
        </Container>
    )
}

const homeStyle = {
    height: '100%',
    overflowY: 'hidden',
    overflowX: 'hidden'
}


export default Home;