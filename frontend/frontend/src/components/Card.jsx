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

          
        </div>
    )
    
};

export default Card;