
import React,{useEffect, useState } from 'react';
import  axios from 'axios';
import dummy from '../dummy.json';
import Navbar from './Navbar.jsx';
import Comments from './Comments.jsx';
import './Version.css';
import { useNavigate } from 'react-router-dom';
import Card from '../components/Card.jsx';







const Version = () => {

   

    const [allPapers, setAllPapers] = useState([]);
    const [versionPaperId, setVersionPaperId] = useState([])

    
   

    const authorId = localStorage.getItem('authorId');
    
    useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        


        const fetchUserData = async() =>{
            try {
                 
                const response  = await axios.get(`http://localhost:8080/paper/author/${authorId}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token')
                    }
                })
                console.log(response.data)
                setAllPapers(response.data)
            } catch(errror){
                //print("invalid user")
            }
        }

        fetchUserData();

    },[]);






    //set up an api call to show all papers in dropdown and set the versionpaperId 
    //as that (change from const to state in useEffect)

    //add the dropdown in the return section and check if comments component is correct.


    const [versionPapers, setVersionPapers] = useState([]);

    const [selectedPaperVersion, setSelectedPaperVersion] = useState([]);
    
    
    useEffect( () => {
    
        const fetchUserData = async() =>{
            
            try {
                const response  = await axios.get(`http://localhost:8080/version/all/${versionPaperId}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token') 
                    }
                    
                })
                setVersionPapers(response.data)
                console.log('the versions of the selected paper are: ' + response.data)
                
            } catch(error){
                
                console.log(error)
            }
        
        }
        fetchUserData();
    },[versionPaperId])
    
    
    const handleDropdownChange = (e) => {
        console.log(e.target.value)
        
        setSelectedPaperVersion(e.target.value);
        
    }
    
  

    const handleDropdownChangePapers = (e) => {
       console.log('selected version id is :' + e.target.value)
        setVersionPaperId(e.target.value);
        setSelectedPaperVersion('');
    
    } 




    return (
        <div className='container'>
            <Navbar />

            <h1>Select Paper</h1>
            <div className='dropdown--div'>  
                    <select id="dropdown" value={versionPaperId} onChange={handleDropdownChangePapers}>
                    <option value=''>Select a paper</option>  
                        {allPapers.map((option) =>(
                            <option key={option.id} value={option.id}>
                                {option.title}
                            </option>
                        ))}
                    </select>
                </div>
                

                <div className='version--cards'>
                    {versionPapers.map((version) => (
                        <div key={version.id}>
                        <Card
                            key={version.id} 
                            title={version.title}
                            abstractUrl={version.abstractUrl}
                            status={version.approved}
                            uploadDate={version.uploadDate}
                            paperId={version.paperId}
                        />
                        <button onClick={handleDropdownChange} value={version.id}>View Comments</button>
                        </div>
                    ))}

                </div>
                
                


            

                <div>
                    <Comments paperId ={versionPaperId} versionId={selectedPaperVersion} />

                </div>




            
        </div>
    );
};

export default Version;