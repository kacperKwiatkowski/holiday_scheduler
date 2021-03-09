import Axios from "axios";
import "../interceptor/interceptor"

import React, {useState, useEffect} from "react";
import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { updateObject } from '../actions/updateObject'

const UpdateUser = ({entity}) => {

    const dispatch = useDispatch();  

    const[availableLeaders, setAvailableLeaders] = useState([])
    
    const getAvailableLeaders = async () => {
      const response = await Axios.get("http://localhost:8080/api/team/read/leaders/available")
      const { data } = await response;
      setAvailableLeaders(data);
  }


  useEffect(() => getAvailableLeaders(), []);


    const onSubmit = values => {

      console.log(values)
      dispatch(updateObject({object: "team", data: values}))
      
    }

    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {
              name: entity.name,
              id: entity.id,
              teamLeaderId: entity.teamLeaderId, 
              teamLeaderFirstName: entity.teamLeaderFirstName,
              teamLeaderLastName: entity.teamLeaderLastName,
              teamLeaderEmail: entity.teamLeaderEmail,
              userIds: entity.userIds
            }
        }
        render={({ handleSubmit, form, values }) => (
          <form 
            onSubmit={handleSubmit}
          >
            <div className="formFieldsWrapper">
              <div className="formFieldWrapper">
                <Field
                  name="id"
                  component="input"
                  type="hidden"
                />
              </div>              
              <div className="formFieldWrapper">
                <label className="formLabel">Team leader:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="teamLeaderId"
                  component="select"
                >
                  <option selected value>{"Current: " + entity.teamLeaderId + " " + entity.teamLeaderFirstName + " " + entity.teamLeaderLastName}</option>
                {availableLeaders.map((leader) => {
                  return <option value={leader.id}>{leader.id + " " + leader.firstName + " " + leader.lastName}</option>
                })}
                </Field>
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Team's name:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="name"
                  component="input"
                  type="text"
                />
              </div>
            </div>
            <div className="formButtonsWrapper">
                <button
                    className="formButton"
                    type="button"
                    onClick={form.reset}
                >
                    RESET
                </button>
                <button 
                    type="submit"
                    className={`formButton formUpdateButton`}
                >
                    UPDATE
                </button>

            </div>
          </form>
        )}
      />
    )


}
export default UpdateUser;