import React, { Component } from 'react'
import { useState,useEffect } from 'react'
import axios from 'axios'
import { useTable } from 'react-table'
import {useHistory} from 'react-router-dom'


export const Dashboard = () => {

    const [userData,setuserData]=useState({})
    const history = useHistory();
    

    useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        

        //change api call as per backend
        const fetchUserData = async() =>{
            try {
                const response  =await axios.get(`http://localhost:3000/api/users/${userId}`)
                console.log(response.data)
                setuserData(response.data)
            } catch(errror){
                console.log("invalid user")
            }
        }

        fetchUserData();

    },[]);
    
    //change the accessor links as per backend data structure

    const data = userData;
    const finaldata = React.useMemo(() => data, []);
    const columns = React.useMemo(() =>[
        {
            Header : "Paper Name",
            accessor : "pname"

        },

        {
            Header : "Author",
            accessor : "author"

        },

        {
            Header : "Tags",
            accessor : "tags"

        },

        {
            Header : "Abstract",
            accessor : "abstract"

        },

        {
            Header : "Version",
            accessor : "version"

        },

        {
            Header : "Status",
            accessor : "status"

        }, 

        {
            Header : "Comments",
            accessor : "comments",
            Cell: ({ row }) => {
                  return <button onClick={() => handleComment(userData.paperId)}>View comments</button>;
               
            }
        },
        
        {
            Header : "Re-upload",
            accessor : "reupload",
            Cell: ({ row }) => {
                if (row.original.status === "rejected") {
                  return <button onClick={() => handleReupload(row.original.pname)}>Reupload</button>;
                } else {
                  return null;

        }}}


        
    ]);

    const {getTableProps , getTableBodyProps , headerGroups , rows , prepareRow} = useTable({columns,finaldata});

    const handleReupload = () => {
        //reupload logic here
        localStorage.setItem('paperId',userData.paperId)
        history.push('/reupload')
        }
    const handleComment = () => {
        //comment logic logic here
        localStorage.setItem('paperId',userData.paperId)
        history.push('/comments')
        }
  
    return (


     <div className='container'>



        
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
                    prepareRow(row)
                    return(
                        <tr {...row.getRowProps()}>
                            {row.cells.map((cell) => {
                                return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                            })}
                        </tr>
                    )
                })}
            </tbody>
        </table>

        
      </div>
      </div>
    )
  
}
