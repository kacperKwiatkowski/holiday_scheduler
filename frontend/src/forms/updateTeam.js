import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { updateObject } from '../actions/updateObject'

const UpdateUser = ({entity}) => {

    const dispatch = useDispatch();   

    const onSubmit = values => {
      dispatch(updateObject({object: "team", data: values}))
    }

    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {
              leader: entity.teamLeaderFirstName + " " + entity.teamLeaderLastName,
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
                <Field
                  name="id"
                  component="input"
                  type="hidden"
                />
              </div>              
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
                  name="leader"
                  component="input"
                  type="text"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Last Name:</label>
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