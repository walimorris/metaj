import {useEffect} from "react";

const React = require('react');
const ReactDOM = require('react-dom');
const {useState} = require("react");

import MetaTable from "./components/MetaTable";
import ThemeToggle from "./components/ThemeToggle";
import MetaJLogo from "./components/MetaJLogo";

const App = () => {
    const [allMetaData, setAllMetaData] = useState([]);

    useEffect(() => {
        fetch ('/meta/instance')
            .then(response => response.json())
            .then(data => {
                setAllMetaData(data);
                console.log(data);
            });
        }, []);

    return (
        <div id={'app'}>
            <ThemeToggle/>
            <MetaJLogo/>
            <MetaTable data={allMetaData}/>
        </div>
    );
};

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
