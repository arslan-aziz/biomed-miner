import React from 'react';

import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';

import backdrop from './static/img/backdrop.jpeg';

class SideBar extends React.Component {

    render () {
        return (
            <div style={sidebarStyle}>
                <div style={{marginBottom: '1em'}}>
                    <h1>You have lots on your plate already.<br></br>
                        Don't let choosing the right food be another.</h1>
                </div>
                <div style={{fontSize: '20px', marginBottom: '1em'}}>
                    Food for Thought uses Machine Learning to collect and visualize nutritional
                    information so you can achieve your diet goals and needs.
                </div>
                <DropdownButton id="demo-button" title="Select Article" onSelect={(evt) => {
                    this.props.selectData(evt)
                }}>
                    <Dropdown.Item eventKey="1">Article 1</Dropdown.Item>
                    <Dropdown.Item eventKey="2">Article 2</Dropdown.Item>
                    <Dropdown.Item eventKey="3">Article 3</Dropdown.Item>
                </DropdownButton>
            </div>
        );
    }
}

const sidebarStyle = {
    backgroundImage: `url(${backdrop})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center center',
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    paddingRight: '1em',
    paddingLeft: '1em'
}

export default SideBar;