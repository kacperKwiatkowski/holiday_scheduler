import { Form, Field } from 'react-final-form'

import uploadNationalHolidays from '../actions/uploadNationalHolidays'

const NationalHolidayUpload = () => {

    const onSubmit = values => {
        uploadNationalHolidays({year: values.year, key: values.key})
    }

    var initialYear = new Date().getFullYear()

    const mapYears = () => {

        var year = new Date().getFullYear()

        var years = [];

        for(var i = 0; i<5; i++){
            years.push(year)
            year+=1;
        }

        return years;
    }

    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {
                year: initialYear
            }
        }
        render={({ handleSubmit, form, values }) => (
          <form 
            className="settingsFormWrapper"
            onSubmit={handleSubmit}
          >

            <div className="settingsFormHeader">
                Upload national holidays
            </div>
            <div className="settingsFormFieldsWrapper">
  
              <div className="settingsFormFieldWrapper">
                <label className="settingsFormLabel">Year:</label>
                <Field                  
                    className="settingsFormField" 
                    name="year"
                    component="select"
                    type="text"
                >
                    {mapYears().map(year => {
                        return(
                            <option>{year}</option>
                        )
                    })}
                </Field>
              </div>
              <div className="settingsFormFieldWrapper">
                <label className="settingsFormLabel">Key:</label>
                <Field
                    className="settingsFormField" 
                    name="key"
                    component="input"
                    type="text"
                />
              </div>

            </div>
            <div className="settingsFormButtonsWrapper">
                <button
                    className="settingsFormButton"
                    type="button"
                    onClick={form.reset}
                >
                    RESET
                </button>
                <button 
                    type="submit"
                    className="settingsFormButton"
                >
                    UPLOAD
                </button>

            </div>
          </form>
        )}
        />
            
    )


}

export default NationalHolidayUpload;