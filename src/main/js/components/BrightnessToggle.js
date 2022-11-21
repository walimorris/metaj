const React = require("react");

const BrightnessToggle = () => {

    /**
     * The BrightnessToggle uses a checkbox input under the hood. The toggle will work as a
     * standard checkbox. If the toggle is checked then it is on (true). If a toggle is not
     * checked then it is off (false). When toggled on, the metaj's background will change to
     * light theme and when toggled off will stay as default dark theme.
     *
     * @param event toggle change event
     */
    const handleToggle = (event) => {
        const isChecked = event.target.checked;
        console.log(isChecked);
    }

    return (
        <label className={'brightness-toggle'}>
            <input type={'checkbox'} value={'on'} onChange={handleToggle}/>
            <span className={'slider'}></span>
        </label>
    );
};

export default BrightnessToggle;
