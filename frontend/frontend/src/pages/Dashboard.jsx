import React, { useState, useMemo, useEffect} from 'react';
import { useTable } from 'react-table';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar.jsx';
 import axios from 'axios'; // uncomment if you need axios
import './Dashboard.css';


import dummy from '../dummy.json';

export const Dashboard = () => {
    const [userData, setUserData] = useState([]);
    const history = useNavigate();

    const data = userData;
    const authorId = localStorage.getItem('authorId');
    
    useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        


        const fetchUserData = async() =>{
            try {
                 
                const response  = await axios.get(`http://localhost:8080/paper/author/${authorId}`, {
                    headers: {
                        Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWVlbV9iMjEwNDc0Y3NAbml0Yy5hYy5pbiIsImlhdCI6MTcwNjU5NjMwOCwiZXhwIjoxNzA3NjM1NTM3fQ.SJF7Vapwc6sMO4ouPnRjaDjhf5STQtNlnnRsunxrumk`
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
};

export default Dashboard;
