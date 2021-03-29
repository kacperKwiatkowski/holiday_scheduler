import {Field, Form} from 'react-final-form'
import {useDispatch, useSelector} from 'react-redux';
import {postEmail} from '../actions/postEmail'

const SendEmail = () => {

    const dispatch = useDispatch();   

    const {id} = useSelector((state) => state.loggedUserReducer)

    const onSubmit = values => {
      dispatch(postEmail({id: id, data: values}))
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
              <div className="formFieldWrapper">
                <label className="formLabel">E-MAIL ADDRESS:</label>
                <Field
                  name="address"

                  className="formTextInput formTextServiceInput" 
                  component="input"
                />
              </div>                   
              <div className="formFieldWrapper">

              <label className="formLabel">TITLE:</label>
                <Field
                  name="title"

                  className="formTextInput formTextServiceInput" 
                  component="input"
                />
              </div>     
              
                <div className="formFieldWrapper">
              <label className="formLabel">MESSAGE:</label>  
                  <Field
                    name="content"
                    className="formTextInput formTextServiceInput" 
                    component="textarea"
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
                    SEND
                </button>

            </div>
          </form>
        )}
      />
    )


}
export default SendEmail;