const Modal = ({modalHeader, modalContent, modalData, setModalData}) => {

    return(
        <div 
            className={modalData.active ? 'modalHiddenPosition modalBackground': 'modalVisablePosition modalBackground'} 
        >
            <div className="modalWrapper">
                <div className="modalHeader">
                    {modalHeader} 
                </div>

                <div>
                    {modalContent}
                </div>

                <button 
                        type="button"
                        className="modalDismissButton"
                        onClick={() => setModalData({active: true, data: "", task: ""})}
                    >
                    X
                </button>
            </div>
        </div>
    )
}

export default Modal