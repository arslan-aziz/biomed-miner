import React, { useState } from 'react';

import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

import axios from 'axios';

import SideBar from '../SideBar/Sidebar';
import NavBar from '../NavBar/NavBar';
import Graph from '../Graph/Graph';

import defaultGraph from '../data/default_graph.json';
import selectionToIdDict from '../config/selection_to_id_dict';
import net_config from '../config/net_config.json';

import parseResponse from './parsing';
import mergeGraphs from '../Graph/graphUtils';

const Home = () => {

    const [graphData, setGraphData] = useState(defaultGraph)

    const delay = (ms) => new Promise(resolved => setTimeout(resolved, ms))

    const poll = (fn, retries = Infinity, timeoutBetween = 1000) => {
        return Promise.resolve()
            .then(fn)
            // on status 4xx try again
            .catch(function retry(err) {
                if (retries-- > 0) {
                    return delay(timeoutBetween)
                        .then(fn)
                        .catch(error => {
                            console.log(error)
                            retry()
                        });
                }
            })
    }

    const nlpExtractionRequest = (queryValue, queryId, doMerge) => {
        let url = 'http://127.0.0.1:8080/nlpextraction'
        const postQueryOptions = {
            url: url,
            method: 'POST',
            params: {
                querykey: queryValue,
                querynodeid: queryId,
                maxnodeid: graphData.nodes.length
            }
        }
        const getQueryOptions = {
            url: url,
            method: 'GET',
            params: { querykey: "" }
        }

        axios(postQueryOptions)
            .then(response => {
                console.log(response)
                // load get request with normalized query key
                getQueryOptions.params.querykey = response.data
                console.log(getQueryOptions)
                poll(() => axios(getQueryOptions).then(
                    response => {
                        // TODO: why is the graph object referred to as "entityGraph" in this specific endpoint?
                        let parsedEntityGraph = JSON.parse(response.data.entityGraph)
                        let temp = parseResponse(parsedEntityGraph)
                        console.log(temp)
                        if (doMerge) {
                            setGraphData(mergeGraphs(graphData, temp))
                        }
                        else {
                            setGraphData(temp)
                        }
                    }
                ), 5, 1000)
            })
            .catch(error => {
                console.log(error)
            })
    }

    const searchHandler = (evt) => {
        evt.preventDefault()
        let queryValue = evt.target[0].value
        console.log(queryValue)

        nlpExtractionRequest(queryValue, -1, false)

    }

    const clickHandler = (clickEvent) => {
        let nodeId = clickEvent.id
        let queryValue = clickEvent.name
        console.log(queryValue)

        nlpExtractionRequest(queryValue, nodeId, true)
    }

    const selectHandler = (selector) => {
        console.log(selector);

        // get resource id from selection
        let articleId = selectionToIdDict[selector];
        // let url = `http://${net_config.server_ip}/article/${articleId}`
        let url = `http://127.0.0.1:8080/article/${articleId}`

        const options = {
            url: url,
            method: 'GET'
        }

        axios(options)
            .then(response => {
                console.log(response)
                let temp = parseResponse(response.data.graph)
                console.log(temp)
                setGraphData(temp)
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
                    <SideBar selectHandler={selectHandler} searchHandler={searchHandler} />
                </Col>
                <Col lg={9}>
                    <Graph data={graphData} clickHandler={clickHandler} />
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