import React from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import dummy from '../dummy.json';
import { useNavigate } from 'react-router-dom';
import './Reupload.css';
import Navbar from './Navbar.jsx';




const Reupload = () => {
    //replace with empty array instead of dummy
    const [userData,setUserData]=useState([])
    localStorage.setItem('paperId', '');


    let today = new Date();


    const uploadDate = today.getFullYear()+ "-"+ (today.getMonth()+1>=10 ? ( parseInt(today.getMonth()+1)) : ("0" + parseInt(today.getMonth()+1))) +"-"+ (today.getDate()+1>=10 ? ( parseInt(today.getDate()+1)) : ("0" + parseInt(today.getDate()+1)))
    const authorId = localStorage.getItem('authorId');



    const [submission, setSubmission] = useState({
   
        title: '',
        approved: false,
        shortdesc: '',
        abstractUrl: '',
        tags: '',
        uploadDate: uploadDate,
        authorId : authorId
      });
    
      const navigate = useNavigate();


      useEffect( () => {

        //fetch author user id from local data (implement encryption later?)
        


        const fetchUserData = async() =>{
            try {

                const authorId = localStorage.getItem('authorId');
                const response  = await axios.get(`http://localhost:8080/paper/author/${authorId}`, {
                  headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token')
                  }
              })
                
                setUserData(response.data)
            } catch(error){
              console.log(error)
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
    console.log(event.target.value);
    setSelectedOption(event.target.value);
  };


  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setSubmission((prevData) => ({
      ...prevData,
      [name]: value,
    }));

  console.log('the id the of the paper selected is : ' + selectedOption);
  };



  const handleEditSubmit = async () => {
    console.log(submission);
    if (submission.title === '' || submission.shortdesc === '' || submission.abstractUrl === '' || submission.tags === '') {
      alert('Please fill in all fields!');
      return;
    }
    try {
      const paperId = selectedOption;
      await axios.put(`http://localhost:8080/paper/update/${paperId}`, submission, { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } });
      
      navigate('/dashboard');
      alert('Abstract submitted successfully!')
    
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  };

    console.log(selectedOption)

    const [currentOption, setCurrentOption] = useState([]); 

    useEffect(() => {
        try{
          const response = axios.get(`http://localhost:8080/paper/${selectedOption}`, {
            headers: {
              Authorization: `Bearer ${localStorage.getItem('token')}`
            }
          });
          setCurrentOption(response.data)
          console.log('the current options info is : ' + response.data);
        }catch(error){
          console.log(error)
        }
      },[selectedOption]);

   
        return (
            <div className='container'>
               <Navbar />
              <h1>Reupload Paper</h1>
                <h2>Select a paper to reupload:</h2>
                <div className='dropdown--div'>  
                    <select id="dropdown" value={selectedOption} onChange={handleDropdownChange}>
                    <option value=''>Select a paper</option>  
                        {userData.map((option, index) => option.approved === false && (
                            <option key={option.id} value={option.id}>
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
                    <input type="text" name="abstractUrl"  onChange={handleInputChange} />
                    <h3>Tags</h3>
                    <input type="text" name="tags"  onChange={handleInputChange} />
                    <h3></h3>
                    <button type="button"  onClick={handleEditSubmit} >Reupload Abstract</button>
                  </form>
                )  
                    }
            </div>
        );
                }
    

export default Reupload;