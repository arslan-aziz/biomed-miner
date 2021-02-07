import React from 'react';

class Explore extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            value: '',
            error: null
        }
    }

    componentDidMount () {
        let apiUrl = process.env.REACT_APP_API_URL
        console.log(apiUrl)

        fetch(apiUrl)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        value: result.value
                    })
                },
                (error) => {
                    this.setState({
                        value: '',
                        error: error
                    })
                }
            )   
    }

    render () {
        return (
            <React.Fragment>
                {this.state.value}
            </React.Fragment>
        )
    }
}

export default Explore;