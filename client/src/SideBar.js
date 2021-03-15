import React from 'react';

import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

class SideBar extends React.Component {

    render () {
        return (
            <div style={sidebarStyle}>
                <div style={{marginBottom: '1em'}}>
                    <h1>Stay abreast of biomedical research developments.<br></br>
                        Produce cutting edge insights, therapies, and technologies.</h1>
                </div>
                <div style={{fontSize: '20px', marginBottom: '1em'}}>
                    Biomed-Miner uses Natural Language Processing to collect and visualize biomedical research 
                    so you can stay on the cutting edge of your field.
                </div>
                <div style={{marginBottom: '1em'}}>
                    <DropdownButton id="demo-button" title="Select Article" onSelect={(evt) => {
                        this.props.selectData(evt)
                    }}>
                        <Dropdown.Item eventKey="1">Article 1</Dropdown.Item>
                        <Dropdown.Item eventKey="2">Article 2</Dropdown.Item>
                        <Dropdown.Item eventKey="3">Article 3</Dropdown.Item>
                    </DropdownButton>
                </div>
                <Form>
                    <Form.Group controlId="formQuery">
                        <Form.Label>Search Query</Form.Label>
                        <Form.Control/>
                    </Form.Group>
                    <Button variant="primary" type="submit" onChange={this.props.handleClick()}>
                        Search
                    </Button>
                </Form>
            </div>
        );
    }
}

const sidebarStyle = {
    backgroundColor: 'black',
    color: 'green',
    fontFamily: 'courier',
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