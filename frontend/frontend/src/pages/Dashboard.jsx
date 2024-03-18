import React, { useState, useMemo, useEffect} from 'react';
import { useTable } from 'react-table';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar.jsx';
 import axios from 'axios'; // uncomment if you need axios

import './Dashboard.css';


import dummy from '../dummy.json';
import Card from '../components/Card.jsx';

const  Dashboard = () => {
    const [userData, setUserData] = useState([]);
    const history = useNavigate();

    const data = userData;
    const authorId = localStorage.getItem('authorId');

    
    useEffect(() => {
        const fetchUserData = async () =>{
            try {

                const response  = await axios.get(`http://localhost:8080/paper/author/${authorId}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token')
                    }
                })
                console.log(response.data)
                setUserData(response.data)
            } catch(errror){
                //print("invalid user")
            
            }
        }

        fetchUserData();

    },[]);


console.log(authorId)


const handleReupload = () => {
    history('/reupload')
}

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
                Cell: ({ value }) => {
                    return (
                        <a href={value} target='_blank' rel='noreferrer'>
                            {value}
                        </a>
                    );
                }
            },
            {
                Header: 'Upload Date',
                accessor: 'uploadDate',
            },
            {
                Header: 'Status',
                accessor: 'approved',
                Cell : ({value}) => {
                    if(value === true){
                        return <p>Approved</p>
                    }else{
                        return (<div>
                                    <p>Rejected</p>
                                    <button onClick={handleReupload} className='reupload--button'>Reupload</button>
                                </div>)
                    }
                }
            },
        ],
        [] 
    );

    const finaldata = useMemo(() => data, [data]);

    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = useTable({ columns, data: finaldata });
    

    
    return (

        <div className='container'>
             <Navbar />
          
            <h1 className='dashboard--header'>Dashboard</h1>
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
}

export default Dashboard