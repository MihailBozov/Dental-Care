document.addEventListener("DOMContentLoaded", () => {
    showNewTreatmentErrors()
    showEditTreatmentErrors()
    showResult()
    showForgotPasswordErrors()
    resetPasswordFormErrors()
});


function submitNewTreatmentForm() {
    document.getElementById('newTreatmentForm').submit();
}

function showNewTreatmentErrors() {
    let element = document.querySelector('.modal_container .modal_result');
    let hasContent = element && element.textContent.trim() !== null;
    if (hasContent) {
        $('#newTreatmentModal').modal('show');
    }
}

function showEditTreatmentErrors() {
    let element = document.querySelector('.modal_container .edit_modal_result');
    let hasContent = element && element.textContent.trim() !== null;
    if (hasContent) {
        $('#editTreatmentModal').modal('show');
    }
}

function showResult() {
    const urlParams = new URLSearchParams(window.location.search);
    const confirmationSent = urlParams.get('confirmationSent');
    const emailConfirmed = urlParams.get('emailConfirmed');
    const success = urlParams.get('success');
    const resetPasswordEmailSent = urlParams.get('resetPasswordEmailSent');
    const resetPassword = urlParams.get('reset-password');
    const resetPasswordDone = urlParams.get('resetPasswordDone');

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

    } else if (resetPasswordEmailSent === 'true') {
        $('#success_reset_password_email_sent').modal('show')

    } else if (resetPassword === 'true') {
        $('#reset_password_modal').modal('show');
        const  token = urlParams.get('token')
        if (token != null && token.trim() !== '') {
            const element = document.querySelector('#reset_password_form .token')
            element.value = token;
        }
        
    } else if (resetPasswordDone === 'true'){
        $('#reset_password_done_modal').modal('show');
        const name = document.querySelector('#reset_password_done_modal span.name_owner');
        name.textContent = userName;
        
    } else if (success === 'false') {
        $('#failure').modal('show');
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

function forgotPasswordFormSend() {
    const forgotPasswordForm = document.querySelector('#forgot_password_form');
    forgotPasswordForm.submit();
}

function showForgotPasswordModal() {
    $('#forgot_password_modal').modal('show')
}

function showForgotPasswordErrors() {
    let element = document.querySelector('.modal_container .forgot_password_result');
    let hasContent = element && element.textContent.trim() !== null;
    if (hasContent) {
        $('#forgot_password_modal').modal('show');
    }
}

function resetPasswordFormSend() {
    const resetPasswordForm = document.querySelector('#reset_password_form');
    resetPasswordForm.submit();
}


function resetPasswordFormErrors() {
    let element = document.querySelector('#reset_password_modal .reset_results');
    let hasContent = element && element.textContent.trim() !== '';
    if (hasContent) {
        $('#reset_password_modal').modal('show');
    }
}

