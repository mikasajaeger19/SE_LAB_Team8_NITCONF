import React, { useEffect } from 'react';
import { useState } from 'react';
import axios from 'axios';
import './Card.css';

const Card = (props) => {


    const [tags, setTags] = useState([])

    useEffect (() => {

        const fetchdata = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/paper/tags/${props.paperId}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token')
                    }
                })
                console.log(response.data)
                setTags(response.data)
            }
            catch(error){
                console.log(error)
            }
        }
        fetchdata();
    }
    ,[])
           

    return (
        <div className = 'card--container'>
            <div className='card--left'>
                <h1>{props.title}</h1>
                <p>{props.abstractUrl}</p>
            </div>

            <div className='card--right'>

                <div className='card--right--top'>
                {(() => {if (props.status === 2 || props.status === true){
                    return <h1>Approved</h1>
                }
                else if(props.status === 1){
                    return <h1>Under Review</h1>
                }
                else if(props.status === 0 || props.status === false){
                    return <h1>Rejected</h1>
                }
                })()}

                <h1>{props.uploadDate}</h1>
                </div>


                <div className='card--right--bottom'>
                    <ul>
                        {tags.map((tag) => (
                            <li key={tag.id}>{tag.name}</li>
                        ))}
                    </ul>
                </div>


            </div>
        </div>
    )
    
};

export default Card;