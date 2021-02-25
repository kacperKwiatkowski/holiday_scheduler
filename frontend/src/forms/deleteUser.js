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

    return(
        <Form 
            onSubmit={onSubmit}
            render={({ handleSubmit, form, values }) => (
                <form onSubmit={handleSubmit}>
                    <div>
                        <Field
                            name="id"
                            component="input"
                            type="hidden"
                            defaultValue={entity.id}
                        />
                    </div>

                    {
                        Object.entries(entity).map(([key, value]) => {

                            if(!key.toLowerCase().includes('id')){
                                return (
                                    <div>
                                        <Field
                                            className="modalTextInput modalTextDeleteInput" 
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
                            className={`modalButton modalDeleteButton`}

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
