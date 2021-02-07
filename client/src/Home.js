import React from 'react';

import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'

import backdrop from './static/img/backdrop.jpeg';

const Home = () => {

    return (
        <Container fluid style={backdropStyle}>
            <div style={{paddingBottom: '1%'}}>
                <h1>You have lots on your plate already.<br></br>
                    Don't let choosing the right food be another.</h1>
            </div>
            <div style={{fontSize: '20px', paddingBottom: '1%'}}>
                Food for Thought uses Machine Learning to collect and visualize nutritional
                information so you can achieve your diet goals and needs.
            </div>
            <Button variant="primary" style={{width: '20%'}}>Start Here!</Button>
        </Container>
    );
}

const backdropStyle = {
    backgroundImage: `url(${backdrop})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center center',
    height: '400px',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    paddingRight: '40%',
    paddingLeft: '20%'
}

export default Home;