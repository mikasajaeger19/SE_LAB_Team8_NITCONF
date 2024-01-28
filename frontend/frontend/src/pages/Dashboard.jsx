import React, { useState, useMemo } from 'react';
import { useTable } from 'react-table';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar.jsx';
// import axios from 'axios'; // uncomment if you need axios

import dummy from '../dummy.json';

export const Dashboard = () => {
    const [userData, setUserData] = useState(dummy);
    const history = useNavigate();

    const data = userData;
    
    const columns = useMemo(
        () => [
            {
                Header: 'Paper Name',
                accessor: 'title',
            },
            {
                Header: 'Description',
                accessor: 'shortdesc',
            },
            {
                Header: 'Tags',
                accessor: 'tags',
            },
            {
                Header: 'Abstract',
                accessor: 'abstractUrl',
            },
            {
                Header: 'Upload Date',
                accessor: 'uploadDate',
            },
            {
                Header: 'Status',
                accessor: 'approved',
            },
        ],
        [] 
    );

    const finaldata = useMemo(() => data, [data]);

    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = useTable({ columns, data: finaldata });

    const handleReupload = () => {
        // reupload logic here
        localStorage.setItem('paperId', userData.paperId);
        history('/reupload');
    };

    const handleComment = () => {
        // comment logic here
        localStorage.setItem('paperId', userData.paperId);
        history('/comments');
    };

    return (
       
        <div className='container'>
             <Navbar />
            <div className='table'>
                <table {...getTableProps()} className='table'>
                    <thead>
                        {headerGroups.map((headerGroup) => (
                            <tr {...headerGroup.getHeaderGroupProps()}>
                                {headerGroup.headers.map((column) => (
                                    <th {...column.getHeaderProps()}>{column.render('Header')}</th>
                                ))}
                            </tr>
                        ))}
                    </thead>
                    <tbody {...getTableBodyProps()}>
                        {rows.map((row) => {
                            prepareRow(row);
                            return (
                                <tr {...row.getRowProps()}>
                                    {row.cells.map((cell) => (
                                        <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                                    ))}
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Dashboard;
