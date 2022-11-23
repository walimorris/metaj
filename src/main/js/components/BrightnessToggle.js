import {useEffect, useState} from "react";

const React = require("react");

const BrightnessToggle = (callback, deps) => {
    const darkTheme = [`linear-gradient(to right, #414141, #000000)`, `#fff`];
    const lightTheme = [`linear-gradient(to right, #fff, #414141)`, `#000`];
    const F2 = 113;

    const [theme, setTheme] = useState(localStorage.getItem('metaj-theme') || 'dark');
    const [defaultChecked, setDefaultChecked] = useState(theme === 'light');
    const [defaultCheckedKeyboard, setDefaultCheckedKeyboard] = useState(theme === 'light');

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

    // utilized for monitoring theme state from keyboard theme change when f2 is pressed
    // to change the theme. Removes event listener so this event's process is triggered
    // only once
    useEffect(() => {
        window.addEventListener('keydown', handleKeyboardToggle);
        return () => {
            window.removeEventListener('keydown', handleKeyboardToggle);
        }
    }, [defaultCheckedKeyboard]);

    /**
     * The BrightnessToggle uses a checkbox input under the hood. The toggle will work as a
     * standard checkbox. If the toggle is checked then it is on (true). If a toggle is not
     * checked then it is off (false). When toggled on, the metaj's background will change to
     * light theme and when toggled off will stay as default dark theme.
     *
     * @param e toggle change event
     */
    const handleToggle = (e) => {
        const isActive = e.target.checked;
        const elements = getElementsList();
        setThemeAll(elements, isActive);
    }

    /**
     * Handle toggling light/dark theme from keyboard click. F2 key toggle between each theme.
     *
     * @param e toggle change event
     */
    const handleKeyboardToggle = (e) => {
        const elements = getElementsList();
        const { key, keyCode } = e;
        if (keyCode === F2) {
            let isActive = elements[2].checked;
            elements[2].checked = !isActive;
            setThemeAll(elements, elements[2].checked);
        }
    }

    /**
     * Get list of all component's elements in this order
     * 0: root
     * 1: meta-table
     * 2: input
     *
     * @return {*[]}
     */
    function getElementsList () {
        let list = [];
        list.push(document.querySelector(':root'));
        list.push(document.querySelector('#meta-table'));
        list.push(document.querySelector('input'));

        return list;
    }

    /**
     * Sets theme on all elements that are impacted by toggling light/dark mode.
     * Lightheme is active true and DarkTheme is not active false.
     *
     * @param elements all elements in component
     * @param isActive true or false
     */
    function setThemeAll(elements, isActive) {
        if (isActive) {
            setLightTheme(elements[0], elements[1]);
        } else {
            setDarkTheme(elements[0], elements[1]);
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
        setDefaultCheckedKeyboard(true);
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
        setDefaultCheckedKeyboard(false);
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
