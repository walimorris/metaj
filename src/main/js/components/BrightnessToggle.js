import {useState} from "react";

const React = require("react");

const BrightnessToggle = () => {
    const darkTheme = [`linear-gradient(to right, #414141, #000000)`, `#fff`];
    const lightTheme = [`linear-gradient(to right, #fff, #414141)`, `#000`];

    const [theme, setTheme] = useState(localStorage.getItem('metaj-theme') || 'dark');
    const [defaultChecked, setDefaultChecked] = useState(theme === 'light');

    // On page load, grab theme from local storage and set mood. Remove the event listener
    // when the component unmounts
    React.useEffect(() => {
        localStorage.setItem('metaj-theme', theme);
        const onPageLoad = () => {
            const root = document.querySelector(':root');
            const metaTable = document.querySelector('#meta-table');
            if (theme === 'light') {
                setLightTheme(root, metaTable);
            } else {
                setDarkTheme(root, metaTable);
            }
        };

        if (document.readyState === 'complete') {
            onPageLoad();
        } else {
            window.addEventListener('load', onPageLoad);
            return () => window.removeEventListener('load', onPageLoad);
        }
    }, [theme]);

    /**
     * The BrightnessToggle uses a checkbox input under the hood. The toggle will work as a
     * standard checkbox. If the toggle is checked then it is on (true). If a toggle is not
     * checked then it is off (false). When toggled on, the metaj's background will change to
     * light theme and when toggled off will stay as default dark theme.
     *
     * @param event toggle change event
     */
    const handleToggle = (event) => {
        const root = document.querySelector(':root');
        const metaTable = document.querySelector('#meta-table');
        const isActive = event.target.checked;
        console.log(`isActive=${isActive}`);

        if (isActive) {
            setLightTheme(root, metaTable);
        } else {
            setDarkTheme(root, metaTable);
        }
    }

    /**
     * Set light theme and following properties. This includes setting the checked attribute
     * on the theme input element. Light theme should be checked which results in the slider
     * transformation value.
     *
     * @param root the root element
     * @param table the meta-data table element
     */
    function setLightTheme(root, table) {
        root.style.backgroundImage = lightTheme[0];
        table.style.color = lightTheme[1];
        setTheme('light');
        setDefaultChecked(true);
        localStorage.setItem('metaj-theme', 'light');
    }

    /**
     * Set dark theme and following properties. This includes setting the checked attribute
     * on the theme input element. Dark theme should be unchecked which results in the slider
     * transformation value.
     *
     * @param root the root element
     * @param table the meta-data table element
     */
    function setDarkTheme(root, table) {
        root.style.backgroundImage = darkTheme[0];
        table.style.color = darkTheme[1];
        setTheme('dark');
        setDefaultChecked(false);
        localStorage.setItem('metaj-theme', 'dark');
    }

    return (
        <label className={'brightness-toggle'}>
            <input type={'checkbox'} checked={defaultChecked} onChange={handleToggle}/>
            <span className={'slider'}></span>
        </label>
    );
};

export default BrightnessToggle;
