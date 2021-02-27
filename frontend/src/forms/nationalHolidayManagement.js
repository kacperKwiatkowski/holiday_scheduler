import React, {useState, useEffect} from "react";
import { Form, Field } from 'react-final-form'
import { useDispatch, useSelector} from 'react-redux';
import { fetchNationalHolidays } from '../actions/fetchNationalHolidays'
import { deleteObject } from '../actions/deleteObjectAction'

const NationalHolidayUpload = () => {

    const dispatch = useDispatch();
    const nationalHolidays = useSelector((state) => state.nationalHolidaysReducer)



    useEffect(() => {
        dispatch(fetchNationalHolidays())
    }, [])

    const onSubmit = values => {

        console.log(values.id)
        dispatch(deleteObject({object: 'nationalholiday', id: values.id}))
    }

    return (
        <Form
        onSubmit={onSubmit}
        initialValues={
            {
                id: ''
            }
        }
        render={({ handleSubmit, form, values }) => (
          <form 
            className="settingsFormWrapper"
            onSubmit={handleSubmit}
          >

            <div className="settingsFormHeader">
                Manage national holidays
            </div>
            <div className="settingsFormFieldsWrapper">
  
              <div className="settingsFormFieldWrapper">
                <label className="settingsFormLabel">National holiday:</label>
                <Field                  
                    className="settingsFormField" 
                    name="id"
                    component="select"
                    type="text"
                >
                    {
                        nationalHolidays.map(holiday => {
                            return(

                                <option value={holiday.id}>{holiday.holidayDate + " | " + holiday.name}</option>
                            )
                        })
                    }
                </Field>
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
                    DELETE
                </button>

            </div>
          </form>
        )}
        />
            
    )


}

export default NationalHolidayUpload;