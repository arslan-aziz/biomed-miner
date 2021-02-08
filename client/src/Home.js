import React, { useState } from 'react';

import GraphViz from './GraphViz';

const Home = () => {

    const nodeHoverTooltip = React.useCallback((node) => {
        return `<div>${node.name}</div>`;
      }, []);

    const [state, setState] = useState({
        width: 700,
        height: 500,
        id: "root",
        margin: {top: 10, right: 30, bottom: 30, left: 40},
        data: {
            nodes: [
                {
                    id: 1,
                    name: 'node1',
                    weight: 1
                },
                {
                    id: 2,
                    name: 'node2',
                    weight: 3
                }
            ],
            links: [
                {
                    source: 1,
                    target: 2,
                    weight: 2
                }
            ]
        }
    });

    return (
        <div>
            
        </div>
    );
}


export default Home;