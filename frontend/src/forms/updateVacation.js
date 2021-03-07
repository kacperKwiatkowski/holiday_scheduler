import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { updateObject } from '../actions/updateObject'


const UpdateUser = ({entity}) => {

    const dispatch = useDispatch();    

    const onSubmit = values => {

      values.firstDay = parseDateFormatForServer(values.firstDay)

      values.lastDay = parseDateFormatForServer(values.lastDay)

      console.log(values)

      dispatch(updateObject({object: "vacation", data: values}))
    }

    const parseDateFormatForInput = (date) => {
      const parts = date.split("/");
      return parts[2] + "-" + parts[1] + "-" + parts[0];
    }

    const parseDateFormatForServer = (date) => {
      const parts = date.split("-");
      return parts[2] + "/" + parts[1] + "/" + parts[0];
    }

    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {
                person: entity.firstName + " " + entity.lastName,
                id: entity.id,
                userID: entity.userID,
                firstName: entity.firstName,
                lastName: entity.lastName,
                email: entity.email,
                firstDay: parseDateFormatForInput(entity.firstDay).toString(),
                lastDay: parseDateFormatForInput(entity.lastDay).toString(),
                leaveType : entity.leaveType,
                accepted: entity.accepted,
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
                <label className="formLabel">Full Name:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="person"
                  component="input"
                  type="text"

                  disabled
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">First Day:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="firstDay"
                  component="input"
                  type="date"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Last Day:</label>
                <Field
                  className="formTextInput formTextUpdateInput" 
                  name="lastDay"
                  component="input"
                  type="date"
                />
              </div>
              <div className="formFieldWrapper">
              <label className="formLabel">Leave Type:</label>
                <Field
                    className="formTextInput formTextUpdateInput" 
                    name="leaveType"
                    component="select"
                >
                    <option value="PAYED">PAYED</option>
                    <option value="UNPAID">UNPAID</option>
                    <option value="SICK">SICK</option>
                    <option value="MATERNITY">MATERNITY</option>
                    <option value="BEREAVEMENT">BEREAVEMENT</option>
                    <option value="SABBATICAL">SABBATICAL</option>
                </Field>
                </div>
              <div className="formFieldWrapper">
                <label className="formLabel">Decision:</label>
                <Field
                    className="formTextInput formTextUpdateInput" 
                    name="accepted"
                    component="select"
                >
                    <option value="true">ACCEPT</option>
                    <option value="false">REJECT</option>
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