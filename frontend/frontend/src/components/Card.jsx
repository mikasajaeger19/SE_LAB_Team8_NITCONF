import React from 'react'
import './Card.css'
import axios from 'axios'
import { useState,useEffect } from 'react'



const Card = (props) => {
    const [comments,setComments]=useState([])
    //getting comments for a paper.
    useEffect (() =>{
        const fetchUserData = async() =>{
            try {
                const response  = await axios.get(`http://localhost:8080/paper/author/${props.data.paperId}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token')
                    }
                })
                console.log(response.data)
                setComments(response.data)
            }
            catch(errror){
                console.log("invalid user")
            
            }
        }
        fetchUserData();
    },[]);
    
                        
    const [isFlipped,setIsFlipped] = useState(true);

    const handleClick = () => {
        setIsFlipped(!isFlipped);
      };
    


  return (
        <div>
            {isFlipped ? (
        <div onClick  = {handleClick} className='card-front'>
        <div className='card-header'>
            <h2>{props.data.title}</h2>
            <div className='paper-status'>
                <p>{props.data.approved ? <p>APPROVED</p> : <p>REJECTED</p>}</p>
            </div>
        </div>
        <div className='card-body'>
            <a href = {props.data.abstractUrl}>{props.data.abstractUrl}</a>
            <div className='tags'>
                <div className = 'tag'>
                    Machine Learning
                </div>
                <div className = 'tag'> 
                    Cyber Security
                </div>
                <div className='tag'>
                    Technology
                </div>
            </div>
        </div>
        <div className='card-footer'>
            <button>
                
                <p>comments</p>
            </button>
        </div>
        </div>
            ) : (
        <div onClick  = {handleClick} className='card-back'>
            <h1>DESCRIPTION</h1>
            <p>{props.data.shortdesc}</p>
        </div>
            )}
        </div>


  
  )
}

export default Card