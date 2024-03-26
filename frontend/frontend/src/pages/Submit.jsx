import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';
import './Submit.css';
import emailjs from 'emailjs-com';
import Navbar from './Navbar.jsx';
import Footer from './Footer.jsx';

const Submit = () => {

    const authorId  =localStorage.getItem('authorId')
    let today = new Date();
    const uploadDate = today.getFullYear()+ "-"+ (today.getMonth()+1>=10 ? ( parseInt(today.getMonth()+1)) : ("0" + parseInt(today.getMonth()+1))) +"-"+ (today.getDate()+1>=10 ? ( parseInt(today.getDate()+1)) : ("0" + parseInt(today.getDate()+1)))
    const [submission, setSubmission] = useState({
   
        title: '',
        approved: false,
        shortdesc: '',
        abstractUrl: '',
        tags: '',
        uploadDate: uploadDate,
        authorId : authorId
      });
      const [file, setFile] = useState(null);
      const [tags, setTags] = useState([]);
      
      const handleFile = async (e) => {
        setFile(e.target.files[0]);
        console.log(e.target.files[0]);
      };
        
      const navigate = useNavigate();





    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setSubmission((prevData) => ({
          ...prevData,
          [name]: value,
        }));
      };
    
      const handleEditSubmit = async (e) => {
        e.preventDefault();
        if (
            submission.title === '' ||
            submission.shortdesc === '' ||
            (submission.abstractUrl === '' && file === null) ||
            tags === ''
        ) {
            alert('Please fill in all fields!');
            return;
        }
    
        try {
            console.log(localStorage.getItem('token'));
            // Add the paper and obtain the paper ID
            const addPaperResponse = await axios.post(
                `http://localhost:8080/paper/add`,
                submission,
                {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token'),
                    },
                }
            );
    
            console.log(addPaperResponse.data.id);

            for (const tag of tags){
              const tarResponse = await axios.post( `http://localhost:8080/tag/add`, {tagName: tag, paperId: addPaperResponse.data.id},
              {
                  headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token'),
            }
          });
          console.log(tarResponse.data);
        }
    
        
if (file) {
  const uploadFile = async () => {
      try {
          const formData = new FormData();
          formData.append('file', file);

          await axios.post(
              `http://localhost:8080/paper/upload/${addPaperResponse.data.id}`,
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

    
            
           
            alert('Abstract submitted successfully!');
        } catch (error) {
            console.error('Error submitting abstract:', error.response);
        }
        navigate('/dashboard');
    };




    const handleTags = (e) => {
      const tagString = e.target.value;
      const tagArray = tagString.split(',');
      setTags(tagArray);
  }
    
    return (
        <div className='submit-container'>
           <Navbar />
          <div className='submit-content'>
            <div className='sidebar-submit'></div>
            <div className='submit-mainbody'>
            <div className='submit-header'>
            <h1 className='submit-text'>SUBMIT</h1>
          <h1 className='abstract-text'>ABSTR<span className='submit-a'>A</span>CT</h1>
          </div>
            <form className='submit-form'>
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
        <input placeholder = 'TAGS' type="text" name="tags"  onChange={handleTags} /></span>
       
        </div>
        <button className ='submit-button' type="button" onClick={handleEditSubmit}>SUBMIT</button>
      </form>
            </div>
          <div className='sidebar-submit'></div>
          </div>
      <Footer />
        </div>
    );
};

export default Submit;