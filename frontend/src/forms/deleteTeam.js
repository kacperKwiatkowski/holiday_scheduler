import {Field, Form} from 'react-final-form'
import {useDispatch} from 'react-redux';
import {deleteObject} from '../actions/deleteObjectAction'

const DeleteUser = ({entity}) => {
    
    const dispatch = useDispatch();

    const onSubmit = values => {
        dispatch(deleteObject({object: "team", id: values.id}))
    }

    const labels = ["Teams's name:", "Teams leader's first name:", "Teams leader's last name:", "Teams leader's email:"]

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

                            Object.entries(entity).filter(([key]) => !key.toLowerCase().includes('id')).map(([key, value], index) => {
                                    return (
                                        <div className="formFieldWrapper">
                                            <label className="formLabel">{labels[index]}</label>
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
                            )
                        }
                    </div>
                    <div className="formButtonsWrapper">

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
