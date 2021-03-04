import { Form, Field } from 'react-final-form'
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from "react-router-dom";
import { postVacationRequest } from '../actions/postVacationRequest'

const RequestVacation = () => {

    const dispatch = useDispatch();    
    const history = useHistory();

    const {id} = useSelector((state) => state.loggedUserReducer)

    const onSubmit = values => {
      values.firstDay = parseDateFormatForServer(values.firstDay)

      values.lastDay = parseDateFormatForServer(values.lastDay)

      dispatch(postVacationRequest({id: id, data: values}))
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

            }
        }
        render={({ handleSubmit, form, values }) => (
          <form 
            className="service-form-wrapper"
            onSubmit={handleSubmit}
          >
            <div className="form-header email-form-boundaries">
              REQUEST VACATION
            </div>
            <div className="formFieldsWrapper">
            <div className="formFieldWrapper">
                <label className="formLabel">FIRST DAY:</label>
                <Field
                  className="formTextInput formTextServiceInput" 
                  name="firstDay"
                  component="input"
                  type="date"
                />
              </div>
              <div className="formFieldWrapper">
                <label className="formLabel">LAST DAY:</label>
                <Field
                  className="formTextInput formTextServiceInput" 
                  name="lastDay"
                  component="input"
                  type="date"
                
                />
              </div>
              <div className="formFieldWrapper">
              <label className="formLabel">Leave Type:</label>
                <Field
                    className="formTextInput formTextServiceInput" 
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
export default RequestVacation;