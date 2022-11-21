const React = require("react");

const MetaTable = ({data}) => {
    return (
        <table id={'meta-table'}>
            <thead>
            <tr>
                <th colSpan={2}>EC2 Meta Data</th>
            </tr>
            </thead>
            <tbody>
                {Object.entries(data).map((item, index) => (
                    <tr>
                        <td>{index}</td>
                        <td>{item}</td>
                    </tr>)
                )}
            </tbody>
        </table>
    );
};

export default MetaTable;
