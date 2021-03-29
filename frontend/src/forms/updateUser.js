import {Field, Form} from 'react-final-form'
import {useDispatch} from 'react-redux';
import {updateObject} from '../actions/updateObject'

const UpdateUser = ({entity}) => {

    const dispatch = useDispatch();    

    const onSubmit = values => {
      dispatch(updateObject({object: "user", data: values}))
    }

    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {
                id: entity.id,
                firstName: entity.firstName, 
                lastName: entity.lastName,
                email: entity.email,
                daysOffLeft: entity.daysOffLeft,
                roleType: entity.roleType
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
                <label className="formLabel">First Name:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="firstName"
                  component="input"
                  type="text"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Last Name:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="lastName"
                  component="input"
                  type="text"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Email:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="email"
                  component="input"
                  type="email"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Days Off Left:</label>
                <Field

                  className="formTextInput formTextUpdateInput" 
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
                    className="formTextInput formTextUpdateInput" 
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