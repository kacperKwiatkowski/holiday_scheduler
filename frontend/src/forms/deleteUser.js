import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { useHistory } from "react-router-dom";
import { deleteObject } from '../actions/deleteObjectAction'

const DeleteUser = ({entity}) => {
    
    const dispatch = useDispatch();

    const history = useHistory();

    const onSubmit = values => {

        history.go("/home")
        dispatch(deleteObject({object: "user", id: values.id}))

    }

    const labels = ["First name:", "Last name:", "Email:", "Days Off Left:", "Role Type:"]

    return(
        <Form 
            onSubmit={onSubmit}
            render={({ handleSubmit, form, values }) => (
                <form onSubmit={handleSubmit}>
                    <div className="formFieldsWrapper">
                        <div>
                            <Field
                                name="id"
                                component="input"
                                type="hidden"
                                defaultValue={entity.id}
                            />
                        </div>

                        {
                            Object.entries(entity).map(([key, value], index) => {

                                if(!key.toLowerCase().includes('id')){
                                    return (
                                        <div className="formFieldWrapper">
                                            <label className="formLabel">{labels[index-1]}</label>
                                            <Field
                                                className="formTextInput formTextDeleteInput" 
                                                name={key}
                                                component="input"
                                                type="text"
                                                defaultValue={value}
                                                disabled
                                            />
                                        </div>
                                    )
                                }
                            })
                        }
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
                            className={`formButton formDeleteButton`}

                        >
                            DELETE
                        </button>

                    </div>
                </form>
            )}
        />
    )
}

export default DeleteUser;
