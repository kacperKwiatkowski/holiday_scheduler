import Axios from 'axios'
import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import {FaAngleDoubleDown, FaAngleDown} from "react-icons/fa";


const Dropzone = ({id}) => {

    const onDrop = useCallback(acceptedFiles => {

      const file = acceptedFiles[0]

      const formData = new FormData();
      formData.append("file", file)
    
      
      Axios.post(`http://localhost:8080/api/images/${id}/image/upload`, formData, 
      {
        headers: {
            "Content-Type": "multipart/form-data"
        }
      }
    ).then(() => console.log("File uploaded succesfuly"))
    .catch(err => console.log(err))
  }, [id])

  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div className="profile-dropDown-image-wrapper" {...getRootProps()}>
      <input className="profile-dropDown-image-drag" {...getInputProps()} />
      {
        isDragActive ?
        <FaAngleDoubleDown className="dropIcon"/> :
        <FaAngleDown className="dropIcon"/>
      }
      {
          <img className="profile-dropDown-image" src={`http://localhost:8080/api/images/${id}/image/download`} alt={""}/>      }
    </div>
  )
}

export default Dropzone;