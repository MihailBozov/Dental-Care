function submitNewTreatmentForm() {
    document.getElementById('newTreatmentForm').submit();
}


document.addEventListener("DOMContentLoaded", showNewTreatmentErrors);


function showNewTreatmentErrors() {
    let element = document.querySelector('.modal_container .modal_result');
    let hasContent = element && element.textContent.trim() !== null;
    if (hasContent) {
        $('#newTreatmentModal').modal('show');
    }
}


document.addEventListener("DOMContentLoaded", showEditTreatmentErrors);


function showEditTreatmentErrors() {
    let element = document.querySelector('.modal_container .edit_modal_result');
    let hasContent = element && element.textContent.trim() !== null;
    if (hasContent) {
        $('#editTreatmentModal').modal('show');
    }
}

document.addEventListener("DOMContentLoaded", showResult);


function showResult() {
    const urlParams = new URLSearchParams(window.location.search);
    const confirmationSent = urlParams.get('confirmationSent');
    const emailConfirmed = urlParams.get('emailConfirmed')
    const success = urlParams.get('success');

    const userName = urlParams.get('name');
    const userEmail = urlParams.get('email');

    if (confirmationSent === 'true') {
        const name = document.querySelector('#confirmation_email_sent .modal_body span.name_owner');
        name.textContent = userName
        const email = document.querySelector('#confirmation_email_sent .modal_body span.email_owner')
        email.textContent = userEmail;
        $('#confirmation_email_sent').modal('show');

    } else if (emailConfirmed === 'true') {
        const name = document.querySelector('#email_confirmed .modal_body span.name_owner');
        name.textContent = userName;
        $('#email_confirmed').modal('show');

    } else if (success === 'true') {
        $('#success').modal('show');

    } else if (
        confirmationSent === 'false' ||
        emailConfirmed === 'false' ||
        success === 'false') {
        $('#failure').modal('false');
    }

    const newUrl = window.location.href.split('?')[0];
    history.replaceState(null, null, newUrl);
}


function getDeleteForm(button) {
    $('#confirmationModal').modal('show');

    let confirmButton = document.querySelector('#continueWithTheSubmitting');
    confirmButton.addEventListener('click', () => button.closest('form').submit());

}


function getEditForm(button) {
    $('#editTreatmentModal').modal('show');

    const id = button.getAttribute('data-id');

    fetch(`treatments/${id}`)
        .then(treatment => treatment.json())
        .then(data => {
            document.querySelector('#edit_treatment_name').value = data.name;
            document.querySelector('#edit_treatment_description').value = data.description;
            document.querySelector('#edit_treatment_price').value = data.price;
        })

    const editForm = document.querySelector('#editTreatmentForm');
    editForm.setAttribute('action', `/treatments/${id}`);

    const submitEdited = document.querySelector('#edit_treatment_form_button');
    submitEdited.addEventListener('click', () => editForm.submit());
}


