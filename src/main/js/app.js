const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const {useState} = require("react");

const App = () => {
    const [title, setTitle] = useState('');
    fetch('/meta/instance')
        .then(response => response.json())
        .then(data => {
            console.log(data);
        });
    return (
        <div>MetaJ</div>
    )
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
