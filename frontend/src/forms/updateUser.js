import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { useHistory } from "react-router-dom";
import { updateObject } from '../actions/updateObject'

const UpdateUser = ({entity}) => {

    const dispatch = useDispatch();    
    const history = useHistory();

    const onSubmit = values => {
      history.go("/home")
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
          <form onSubmit={handleSubmit}>
            <div>
              <Field
                name="id"

                component="input"
                type="hidden"
              />
            </div>
            <div>
              <Field
                className="modalTextInput modalTextUpdateInput" 
                name="firstName"
                component="input"
                type="text"
              />
            </div>
            <div>
              <Field

                className="modalTextInput modalTextUpdateInput" 
                name="lastName"
                component="input"
                type="text"
              />
            </div>
            <div>
              <Field

                className="modalTextInput modalTextUpdateInput" 
                name="email"
                component="input"
                type="email"
              />
            </div>
            <div>
              <Field

                className="modalTextInput modalTextUpdateInput" 
                name="daysOffLeft"
                component="input"
                type="number"
                min="0"
                max="52"
              />
            </div>
            <div>
                <Field
                    className="modalTextInput modalTextUpdateInput" 
                    name="roleType"
                    component="select"
                >
                    <option value="EMPLOYEE">EMPLOYEE</option>
                    <option value="TEAM_LEADER">TEAM LEADER</option>
                    <option value="HR">HR</option>
                    <option value="ADMIN">ADMIN</option>
                </Field>
            </div>
            <div className="modalButtonsWrapper">
                <button
                    className="modalButton"
                    type="button"
                    onClick={form.reset}
                >
                    RESET
                </button>
                <button 
                    type="submit"
                    className={`modalButton modalUpdateButton`}
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