import React from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import dummy from '../dummy.json';
import { useNavigate } from 'react-router-dom';





const Reupload = () => {
    //replace with empty array instead of dummy
    const [userData,setUserData]=useState(dummy)
    localStorage.setItem('paperId', '');




    const [submission, setSubmission] = useState({
   
        title: '',
        approved: false,
        shortdesc: '',
        abstracturl: '',
        tags: '',
        uploadDate: '',
        authorId : localStorage.getItem('authorId'),
      });
    
      const navigate = useNavigate();


      useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        


        const fetchUserData = async() =>{
            try {
                const response  = await axios.get(`http://localhost:8080/paper/all`, {
                    headers: {
                        Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2h3aW5AbmFlZW0uY29tIiwiaWF0IjoxNzA2NTQzMTYwLCJleHAiOjE3MDY1NzkxNjB9.54emLy9x2lHOuOJlM9guhhp8ujhr8dbrhXrSGOn32v4`
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


    const filterdata  = () => {
        const data = userData;
        const filteredData = data.filter((item) => item.approved === false);
        setUserData(filteredData);
    }

    const [selectedOption, setSelectedOption] = useState('');

  const handleDropdownChange = (event) => {
    localStorage.setItem('paperId', event.target.id);
    setSelectedOption(event.target.value);
  };


  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setSubmission((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };



  const handleEditSubmit = async () => {
    console.log(submission);
    if (submission.title === '' || submission.shortdesc === '' || submission.abstracturl === '' || submission.tags === '') {
      alert('Please fill in all fields!');
      return;
    }
    try {
     
      //await axios.put(`http://localhost:3000/api/users/${userId}`, submission);
      
      navigate('/dashboard');
      alert('Abstract submitted successfully!')
    
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  };




   
        return (
            <div className='container'>
              <h1>Reupload Paper</h1>
                <h2>Select a paper to reupload:</h2>
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
                {selectedOption && (  
                    <form>
                    <h3>Title</h3>
                    <input type="text"  name="title"   onChange={handleInputChange} />
                    <h3>Short Description</h3>
                    <input type="text" name="shortdesc"  onChange={handleInputChange} />
                    <h3>Abstract URL</h3>
                    <input type="text" name="abstracturl"  onChange={handleInputChange} />
                    <h3>Tags</h3>
                    <input type="text" name="tags"  onChange={handleInputChange} />
                    <h3></h3>
                    <button type="button" onClick={handleEditSubmit}>Reupload Abstract</button>
                  </form>
                )  
                    }
            </div>
        );
                }
    

export default Reupload;