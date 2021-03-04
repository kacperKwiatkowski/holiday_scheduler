import { Form, Field } from 'react-final-form'
import { useDispatch,useSelector } from 'react-redux';
import { useHistory } from "react-router-dom";
import { updateCredentials } from '../actions/updateCredentials'

const ChangeEmail = () => {

  const dispatch = useDispatch();    
  const history = useHistory();

  const {id} = useSelector((state) => state.loggedUserReducer)

  const onSubmit = values => {
    dispatch(updateCredentials(
      {
        id: id, 
        object: "email", 
        oldCredential: values.oldEmail, 
        newCredential: values.newEmail, 
        newCredentialRepeated: values.repeatNewEmail
      }
    ))
  }
    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {

            }
        }
        render={({ handleSubmit, form, values }) => (
          <form 
            className="service-form-wrapper"
            onSubmit={handleSubmit}
          >
            <div className="form-header email-form-boundaries">
              CHANGE EMAIL
            </div>
            <div className="formFieldsWrapper">
              <div className="formFieldWrapper">
                <label className="formLabel">OLD EMAIL</label>
                <Field
                  name="oldEmail"

                  className="formTextInput" 
                  component="input"
                />
              </div>                   
              <div className="formFieldWrapper">
                <label className="formLabel">NEW EMAIL</label>
                <Field
                  name="newEmail"

                  className="formTextInput" 
                  component="input"
                />
              </div>                     
              <div className="formFieldWrapper">
                <label className="formLabel">REPEAT NEW EMAIL</label>
                <Field
                  name="repeatNewEmail"

                  className="formTextInput" 
                  component="input"
                />
              </div>  
            </div>   
              
            <div className="formButtonsWrapper">
                <button
                    className="formButton formServiceButton"
                    type="button"
                    onClick={form.reset}
                >
                    RESET

                </button>
                <button
                    className="formButton formServiceButton"
                    type="submit"
                >
                    CHANGE
                </button>

            </div>
          </form>
        )}
      />
    )


}
export default ChangeEmail;