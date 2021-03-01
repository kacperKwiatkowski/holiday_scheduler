import Axios from 'axios'
import React, {useCallback} from 'react'
import { useSelector} from 'react-redux'
import {useDropzone} from 'react-dropzone'
import interceptor from "../interceptor/interceptor"


const Dropzone = () => {
    
    var loggedUser = useSelector((state) => state.loggedUserReducer)
  
    const onDrop = useCallback(acceptedFiles => {
      const file = acceptedFiles[0]
      console.log(file)

      const formData = new FormData();
      formData.append("file", file)
      
      Axios.post(`http://localhost:8080/api/images/${loggedUser.id}/image/upload`, formData, 
      {
        headers: {
            "Content-Type": "multipart/form-data"
        }
      }
    ).then(() => console.log("File uploaded succesfuly"))
    .catch(err => console.log(err))
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here</p> :
          <p>Drag 'n' drop some files here, or click to select files</p>
      }
      {
          loggedUser.id + " " + loggedUser.imageUrl
      }
      {
          loggedUser.imageUrl ? <img src={`http://localhost:8080/api/images/${loggedUser.id}/image/download`}/> : ''
      }
    </div>
  )
}

export default Dropzone;