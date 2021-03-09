import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { postObject } from '../actions/postObject'

const AddUser = () => {

    const dispatch = useDispatch();    

    const onSubmit = values => {
      dispatch(postObject({object: "user", data: values}))
    }

    return (
        <Form
        onSubmit={onSubmit}
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
                <label className="formLabel">First Name:</label>
                <Field
                  className="formTextInput formTextServiceInput" 
                  name="firstName"
                  component="input"
                  type="text"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Last Name:</label>
                <Field
                  className="formTextInput formTextServiceInput" 
                  name="lastName"
                  component="input"
                  type="text"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Email:</label>
                <Field
                  className="formTextInput formTextServiceInput" 
                  name="email"
                  component="input"
                  type="email"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Days Off Left:</label>
                <Field

                  className="formTextInput formTextServiceInput" 
                  name="daysOffLeft"
                  component="input"
                  type="number"
                  min="0"
                  max="52"
                />
              </div>
            <div className="formFieldWrapper">
              <label className="formLabel">Role Type:</label>
                <Field
                    className="formTextInput formTextServiceInput" 
                    name="roleType"
                    component="select"
                >
                    <option selected value="EMPLOYEE">EMPLOYEE</option>
                    <option value="TEAM_LEADER">TEAM LEADER</option>
                    <option value="HR">HR</option>
                    <option value="ADMIN">ADMIN</option>
                </Field>
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
                    className={`formButton formServiceButton`}
                >
                    ADD
                </button>

            </div>
          </form>
        )}
      />
    )


}
export default AddUser;