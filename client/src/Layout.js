import React from 'react';

import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

import NavBar from './NavBar';

const Layout = (props) => {
    return (
        <Container fluid style={layoutStyle}>
            <NavBar />
            <div>
                {props.children}
            </div>
        </Container>
    )
}

const layoutStyle = {
    height: '100%'
}


export default Layout;