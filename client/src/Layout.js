import React from 'react';

import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

import SideBar from './SideBar';
import NavBar from './NavBar';

const Layout = (props) => {
    return (
        <Container fluid className="vh-100" style={layoutStyle}>
            <NavBar />
            <Row className="h-100">
                <Col lg={3}>
                    <SideBar />
                </Col>
                <Col lg={9}>
                    { props.children }
                </Col>
            </Row>

        </Container>
    )
}

const layoutStyle = {
    height: '100%',
    overflowY: 'hidden'
}


export default Layout;