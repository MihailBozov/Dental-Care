function submitNewTreatmentForm() {
    document.getElementById('newTreatmentForm').submit();
}


document.addEventListener("DOMContentLoaded", showErrors);

function showErrors() {
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


document.addEventListener("DOMContentLoaded", showSuccess);

function showSuccess() {
    const urlParams = new URLSearchParams(window.location.search);
    const success = urlParams.get('success');

    if (success === 'true') {
        $('#success').modal('show');
    } else if (success === 'false') {
        $('#failure').modal('show');
    }
    const newUrl = window.location.href.split('?')[0];
    history.replaceState(null, null, newUrl);
}


function setDeleteUrl(link) {

    let form = document.getElementById('deleteForm');
    form.action = link.getAttribute('href');
}

function submitDeleteForm() {

    let form = document.getElementById('deleteForm');
    form.submit();
}


function getDeleteForm(button) {

    $('#confirmationModal').modal('show');

    let confirmButton = document.querySelector('#continueWithTheSubmitting');
    confirmButton.addEventListener('click', submitForm);

    function submitForm() {
        button.closest('form').submit();
    }
}

function getEditForm(button) {
    $('#editTreatmentModal').modal('show');

    const id = button.getAttribute('data-id');
    
    console.log(id);

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


