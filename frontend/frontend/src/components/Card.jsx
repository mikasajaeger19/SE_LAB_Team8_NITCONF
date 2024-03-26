import React from 'react'
import './Card.css'
import axios from 'axios'
import { useState,useEffect } from 'react'
import { saveAs } from 'file-saver';



const Card = (props) => {
   
   
    const [tags,setTags]=useState([])
    //getting comments for a paper.
    useEffect(() => {
        const fetchUserData = async () => {
            try {
                let response;
                if (!props.ver) {
                    response = await axios.get(`http://localhost:8080/tag/paper/${props.data.id}`, {
                        headers: {
                            Authorization: 'Bearer ' + localStorage.getItem('token')
                        }
                    });
                } else {
                    response = await axios.get(`http://localhost:8080/tag/paper/${props.data.paperId}` , {
                        headers: {
                            Authorization: 'Bearer ' + localStorage.getItem('token')
                        }
                    })
                }
                
                console.log(response.data);
                setTags(response.data);
            } catch (error) {
                console.log("invalid user:", error);
            }
        };
    
        fetchUserData();
    }, [props]);
    
    
                        
    const [isFlipped,setIsFlipped] = useState(true);

    const handleClick = () => {
        setIsFlipped(!isFlipped);
      };
    
      const Flipped = true

      

      //{props.data.tags.map((tag) => {
        //return <div className='tag'>{tag}</div>
    //})}


    const viewPdf = async () => {
        try {
            let response;
            if(!props.ver) {
            response = await axios.get(`http://localhost:8080/paper/doc/${props.data.id}`, {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                },
                responseType: 'arraybuffer' // Set the response type to arraybuffer
            });
        } else {
             response = await axios.get(`http://localhost:8080/paper/doc/${props.data.paperId}`, {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                }})}

    
            // Check if response status is 200 OK
            if (response.status === 200) {
                const pdfData = new Blob(
                    [response.data],
                    { type: 'application/pdf' }
                );
                saveAs(pdfData, 'paper.pdf'); // Use file-saver to save the PDF
                console.log('PDF downloaded successfully');
            } else {
                console.error('Error downloading PDF: Server returned status ' + response.status);
            }
        } catch (error) {
            console.error('Error downloading PDF:', error);
        }
    };
                    


  return (
        <div>
            {isFlipped ? (
        <div onClick  = {handleClick} className='card-front'>
        <div className='card-header'>
            <h2>{props.data.title}</h2><p>[click to view desc.]</p>
            <div className='card-rightside'>
            <p>{props.data.uploadDate}</p>
            <div className='paper-status' style={{ backgroundColor: props.data.approved ? "#00FF0A" : "#CCFF00" }}>
                <p>{props.data.approved ? <p>APPROVED</p> : <p>REVIEW</p>}</p>
            </div>
            </div>
        </div>
        <div className='card-body'>
            {props.data.abstractUrl ? <a href = {props.data.abstractUrl}>{props.data.abstractUrl}</a> : <a onClick = {viewPdf}>[View Uploaded PDF]</a>}
            <div className='tags'>
                 {tags.map((tag,index) => {
                     return <div className='tag'>{tag.tagName}</div>
                 })}

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