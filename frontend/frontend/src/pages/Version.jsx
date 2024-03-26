
import React,{useEffect, useState } from 'react';
import  axios from 'axios';
import dummy from '../dummy.json';
import Navbar from './Navbar.jsx';
import Comments from './Comments.jsx';
import './Version.css';
import { useNavigate } from 'react-router-dom';
import Card from '../components/Card.jsx';
import Footer from './Footer.jsx';







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
                console.log('the versions of the selected paper are: ')
                console.log(versionPapers)
                
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
    
  

    const handleDropdownChangePapers = (paperId) => {
       console.log('selected version id is :' + paperId)
        setVersionPaperId(paperId);
        setSelectedPaperVersion(paperId);
    
    } 



    return (
        <div className='version-container'>
            <Navbar />
            <div className='version-content'>
            <div className='version-sidebar'></div>
                <div className='version-body'>
                    <h1 className='version-text'>VERSI<span className='version-o'>O</span>NS</h1>
            <div className='dropdown--div'>  
                <h2 className='choose-paper'>CHOOSE A PAPER</h2>
                      <div className = 'reupload-papers-container'>
                        
                        {allPapers.map((paper, index) => paper.approved === false && 
                          <div className='paper-option'>
                            {paper.id === selectedPaperVersion ? <img src='./tick.svg' alt = 'tick' /> : null}
                            <h2 key = {paper.id} onClick  = {() => handleDropdownChangePapers(paper.id)}className='reupload-titles'>{paper.title}</h2>
                            </div>
                        )}
                        </div>
                </div>
                

                <div className='version--cards'>
                    {versionPapers.map((version,index) => (
                        <div>
                        <h3>Version {index+1}</h3>
                        <Card key={version.id} ver ={true} data={version} />
                        </div>
                    ))
                    }
                </div>
                </div>
                <div className='version-sidebar'></div>
                </div>
  

        <Footer />
        </div>
    );
};

export default Version;