import React from 'react';

import Card from 'react-bootstrap/Card';
import LogoBackground from './static/img/backdrop.jpeg';

class SideBar extends React.Component {

    render () {
        return (
            <React.Fragment>
                <Card className="bg-dark">
                    <Card.Img src={LogoBackground} alt="Card image" />
                    <Card.ImgOverlay className='text-center center-block'>
                        <Card.Title><h2>Home</h2></Card.Title>
                    </Card.ImgOverlay>
                </Card>
            </React.Fragment>

        );
    }
}

export default SideBar;