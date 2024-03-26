import React from 'react';
import axios from 'axios';
import { useState, useEffect } from 'react';
import dummy from '../dummy.json';
import { useNavigate } from 'react-router-dom';
import './Reupload.css';
import Navbar from './Navbar.jsx';
import Footer from './Footer.jsx';




const Reupload = () => {
    //replace with empty array instead of dummy
    const [userData,setUserData]=useState([])
    localStorage.setItem('paperId', '');

    const [file, setFile] = useState(null);


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

  const handleDropdownChange = (paperId) => {
    console.log(paperId);
    setSelectedOption(paperId);
  };


  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setSubmission((prevData) => ({
      ...prevData,
      [name]: value,
    }));

  console.log('the id the of the paper selected is : ' + selectedOption);
  console.log('the submission data is : ' + submission);
  };




  const handleFile = async (e) => {
    setFile(e.target.files[0]);
    console.log(e.target.files[0]);
  };

  const handleEditSubmit = async () => {
    console.log(submission);
    if(
      submission.title === '' ||
      submission.shortdesc === '' ||
      (submission.abstractUrl === '' && file === null) ||
      submission.tags === ''
    ){
      alert('Please fill in all fields!');
      return;
    }
    
   
    try {
      const paperId = selectedOption;
      await axios.put(`http://localhost:8080/paper/update/${paperId}`, submission, { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } });


      if (file) {
        const uploadFile = async () => {
            try {
                const formData = new FormData();
                formData.append('file', file);
      
                await axios.post(
                    `http://localhost:8080/paper/upload/${paperId}`,
                    formData,
                    {
                        headers: {
                          Authorization: 'Bearer ' + localStorage.getItem('token'),
                            'Content-Type': 'multipart/form-data',
                        },
                    }
                );
      
                console.log('File uploaded successfully');
            } catch (error) {
                console.error('Error uploading file:', error);
            }
        };
      
        uploadFile();
      }
      
      
      navigate('/dashboard');
      alert('Abstract submitted successfully!')








    
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  };

    console.log(selectedOption)

    const [currentOption, setCurrentOption] = useState([]); 

    useEffect(() => {
      const fetchData = async () => {
        try{
          const response =await axios.get(`http://localhost:8080/paper/${selectedOption}`, {
            headers: {
              Authorization: `Bearer ${localStorage.getItem('token')}`
            }
          });
          setCurrentOption(response.data)
          console.log('the current options info is : ' + response.data);
        }catch(error){
          console.log(error)
        }
      }
      fetchData();
      },[selectedOption]);

   
        return (
          <div className='reupload-container'>
              <Navbar />
              <div className='reupload-body'>
                
                  <div className='reupload-sidebar'></div>
                  <div className='reupload-content'>
                    <div className='reupload-header'>
                      <h1 className='reupload-text'>REUPLOAD</h1>
                      <h1 className='paper-text'>ABSTR<span className='reupload-a'>A</span>CT</h1>
                    </div>
                    <div className='reupload-bottom'>
                    <h2 className='choose-paper'>CHOOSE A PAPER</h2>
                      <div className = 'reupload-papers-container'>
                        
                        {userData.map((paper, index) => paper.approved === false && 
                          <div className='paper-option'>
                            {paper.id === selectedOption ? <img src='./tick.svg' alt = 'tick' /> : null}
                            <h2 key = {paper.id} onClick  = {() => handleDropdownChange(paper.id)}className='reupload-titles'>{paper.title}</h2>
                            </div>
                        )}
                        </div>
                              
            
                      <div className='reupload-fields'>
                      {selectedOption && (  
                            <form className='reupload-form'>
                            <div className="submit-form-top">
                            <span>
                            <h3>TITLE</h3>
                            <input placeholder = 'TITLE' type="text"  name="title"   onChange={handleInputChange} /></span>
                            <span>
                            <h3>URL</h3>

                            <div className = "fileupload"><input placeholder = 'URL' type="text" name="abstractUrl"  onChange={handleInputChange} />
                            <label><input onChange = {handleFile}  style = {{display : "none"}}type='file' accept = ".pdf, .doc, .docx" /><img src = './fileupload.svg' alt='fileupload' className='fileupload-icon'/></label>{file ? <p>{file.name}</p> : null}</div>
                            </span>
                            </div>
                            <div className="submit-form-bottom">
                            <span>
                            <h3>DESCRIPTION</h3>
                            <input placeholder = 'DESCRIPTION' type="text" name="shortdesc"  onChange={handleInputChange} /></span>
                            <span>
                            <h3>TAGS</h3>
                            <input placeholder = 'TAGS' type="text" name="tags"  onChange={handleInputChange} /></span>
                           
                            </div>
                            <button className ='submit-button' type="button" onClick={handleEditSubmit}>SUBMIT</button>
                          </form>
                      )  
                          }
                      </div>
                    </div>
                  </div> 
                  <div className='reupload-sidebar'></div>
              </div> 
              <Footer />
           
          </div>
        );
                }
    

export default Reupload;