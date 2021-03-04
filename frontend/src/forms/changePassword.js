import { Form, Field } from 'react-final-form'
import { useDispatch,useSelector } from 'react-redux';
import { useHistory } from "react-router-dom";
import { updateCredentials } from '../actions/updateCredentials'

const ChangePassword = () => {

    const dispatch = useDispatch();    
    const history = useHistory();

    const {id} = useSelector((state) => state.loggedUserReducer)

    const onSubmit = values => {
      dispatch(updateCredentials(
        {
          id: id, 
          object: "password", 
          oldCredential: values.oldPassword, 
          newCredential: values.newPassword, 
          newCredentialRepeated: values.repeatNewPassword
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
              CHANGE PASSWORD
            </div>
            <div className="formFieldsWrapper">
              <div className="formFieldWrapper">
                <label className="formLabel">OLD PASSWORD</label>
                <Field
                  name="oldPassword"

                  className="formTextInput" 
                  component="input"
                />
              </div>                   
              <div className="formFieldWrapper">
                <label className="formLabel">NEW PASSWORD</label>
                <Field
                  name="newPassword"

                  className="formTextInput" 
                  component="input"
                />
              </div>                     
              <div className="formFieldWrapper">
                <label className="formLabel">REPEAT NEW PASSWORD</label>
                <Field
                  name="repeatNewPassword"

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
export default ChangePassword;