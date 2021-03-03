import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { useHistory } from "react-router-dom";
import { updateObject } from '../actions/updateObject'

const SendEmail = () => {

    const dispatch = useDispatch();    
    const history = useHistory();

    const onSubmit = values => {
      dispatch(updateObject({object: "team", data: values}))
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
              SEND AN E-MAIL
            </div>
            <div className="formFieldsWrapper">
                <label className="profile-form-service-label">E-MAIL ADDRESS</label><span/>  
              <div className="formFieldWrapper">
                <Field
                  name="id"

                  className="formTextInput formTextServiceInput" 
                  component="input"
                />
              </div>       
              
              <label className="profile-form-service-label">MESSAGE</label><span/>    
              <div className="formFieldWrapper">
                <Field
                  name="id"
                  className="formTextInput formTextServiceInput" 
                  component="textarea"
                />
              </div>              
            </div>
            <div className="formButtonsWrapper">
                <button
                    className="formButton formServiceButton"
                    type="button"
                >
                    SEND
                </button>

            </div>
          </form>
        )}
      />
    )


}
export default SendEmail;