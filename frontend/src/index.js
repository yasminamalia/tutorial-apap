import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import AppFunc from "./AppFunc";
// import App from './App';
import * as serviceWorker from './serviceWorker';
import { Greeting } from "./Greeting";
// import { Button } from './Button';
import { List } from './List';

/**
 * 6
 */
// class PropsDisplayer extends React.Component {
//         render() {
//                 const stringProps = JSON.stringify(this.props);
//
//                 return (
//                     <div>
//                             <h1>CHECK OUT MY PROPS OBJECT</h1>
//                             <h2>{myProp}</h2>
//                     </div>
//                 );
//         }
// }
//
// // ReactDOM.render goes here:
// ReactDOM.render(
//     //create attribute myProp with "hello" as value
//     <PropsDisplayer myProp = "hello"/>,
//     document.getElementById('root')
// );

/**
 * 7
 */
// class Greeting extends React.Component {
//         render() {
//                 return <h1>Hi there, {this.props.firstName}</h1>;
//         }
// }
//
// ReactDOM.render(
//     <Greeting firstName='Ror' />,
//     document.getElementById('root')
// );

/**
 * 8
 */
// class App extends React.Component {
//         render() {
//                 return (
//                     <div>
//                             <h1>
//                                     Hullo and, "Welcome to The Newzz," "On Line!"
//                             </h1>
//                             <Greeting name="Yasmin"/>
//                             <article>
//                                     Latest newzz:  where is my phone?
//                             </article>
//                     </div>
//                 );
//         }
// }
//
// ReactDOM.render(
//     <App />,
//     document.getElementById('root')
// );

/**
 * 9
 */
// class App extends React.Component {
//         render() {
//                 return (
//                     <div>
//                             <h1>
//                                     Hullo and, "Welcome to The Newzz," "On Line!"
//                             </h1>
//                             <Greeting name = "Allison"/>
//
//                             <article>
//                                     Latest:  where is my phone?
//                             </article>
//                     </div>
//                 );
//         }
// }
//
// ReactDOM.render(
//     <App signedIn = {true}/>,
//     document.getElementById('root')
// );

/**
 * 10
 */
// function talk () {
//         let speech = '';
//         for (let i = 0; i < 10000; i++) {
//                 speech += 'blah ';
//         }
//         alert(speech);
// }
//
// class Talker extends React.Component {
//         render() {
//                 return <Button talk/>;
//         }
// }
//
// ReactDOM.render(
//     <Talker
//         talks = {talk()}
//     />,
//     document.getElementById('root')
// );

/**
 * 11
 */
// class App extends React.Component {
//         render() {
//                 return (
//                     <div>
//                             <List type='Living Musician'>
//                                     <li>Sachiko M</li>
//                                     <li>Harvey Sid Fisher</li>
//                             </List>
//                             <List type='Living Cat Musician'>
//                                     <li>Nora the Piano Cat</li>
//                             </List>
//                     </div>
//                 );
//         }
// }
//
// ReactDOM.render(
//     <App />,
//     document.getElementById('root')
// );

/**
 * 12
 */
class Button extends React.Component {
        render() {
                return (
                    <button>
                            {this.props.text}
                    </button>
                );
        }
}

// defaultProps goes here:
Button.defaultProps {text: "I am a button" }

ReactDOM.render(
    <Button />,
    document.getElementById('root')
);

/**
 * 13
 */
class App extends React.Component {
        // constructor method begins here:


        render() {
                return (
                    <h1>
                            Let's learn state
                    </h1>
                );
        }
}

ReactDOM.render(
    <App />,
    document.getElementById("root")
);