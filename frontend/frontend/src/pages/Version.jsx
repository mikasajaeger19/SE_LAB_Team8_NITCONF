import React from 'react';
import {useEffect, useState } from 'react';
import  axios from 'axios';
import dummy from '../dummy.json';
import Navbar from './Navbar.jsx';
import Comments from './Comments.jsx';







const Version = () => {

    const [versionPaperId, setVersionPaperId] = useState('')









    //set up an api call to show all papers in dropdown and set the versionpaperId 
    //as that (change from const to state in useEffect)

    //add the dropdown in the return section and check if comments component is correct.


    const [versionPapers, setVersionPapers] = useState([]);

    const [selectedPaper, setSelectedPaper] = useState([]);
    
    
    useEffect( () => {
    
        const fetchUserData = async() =>{
            try {
                const response  = await axios.get(`http://localhost:8080/version/${versionpaperId}`, {
                    headers: {
                        Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWVlbV9iMjEwNDc0Y3NAbml0Yy5hYy5pbiIsImlhdCI6MTcwNjU5NjMwOCwiZXhwIjoxNzA3NjM1NTM3fQ.SJF7Vapwc6sMO4ouPnRjaDjhf5STQtNlnnRsunxrumk`
                    }
                })
                console.log(response.data)
                setVersionPapers(response.data)
            } catch(errror){
                console.log(error);
            }
        
        }
    },[])
    
    
    const handleDropdownChange = (e) => {
        setSelectedPaper(e.target.value);
    }
    
    

    


    return (
        <div>
            <Navbar />
            <h1>Version History</h1>
            <div className='dropdown--div'>  
                    <select id="dropdown" value={selectedOption} onChange={handleDropdownChange}>
                    <option value=''>Select a paper</option>  
                        {userData.map((option, index) => option.approved === false && (
                            <option key={index} value={option.title}>
                                {option.title}
                            </option>
                        ))}
                    </select>
                </div>

                <div>
                    <Comments versionId={selectedPaper.versionId} />

                </div>




            
        </div>
    );
};

export default Version;