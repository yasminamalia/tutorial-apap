import React from 'react';
import ReactDOM from 'react-dom';

export class Greeting extends React.Component {
    render() {
        /**
         * 8
         */
        // class Greeting extends React.Component {
        //     render() {
        //         return <h1>Hi there, {this.props.name}!</h1>;
        //     }
        // }

        /**
         * 9
         */
        if (this.props.signedIn === false) {
            return <h1>GO AWAY</h1>;
        } else {
            return <h1>Hi there, {this.props.name}!</h1>;
        }
    }
}