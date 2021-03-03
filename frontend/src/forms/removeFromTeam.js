import { Form, Field } from 'react-final-form'
import { useDispatch} from 'react-redux';
import { useHistory } from "react-router-dom";
import { removeObjectAction } from '../actions/removeObjectAction'

const DeleteUser = ({entity}) => {
    
    const dispatch = useDispatch();

    const history = useHistory();

    const onSubmit = values => {

        dispatch(removeObjectAction({genre: "team", object: "member", id: values.id}))

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
                                                className="formTextInput formTextRemoveInput" 
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
                            type="submit"
                            className={`formButton formRemoveButton`}

                        >
                            REMOVE
                        </button>

                    </div>
                </form>
            )}
        />
    )
}

export default DeleteUser;
