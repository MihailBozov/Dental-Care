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


document.addEventListener("DOMContentLoaded", showSuccess);

function showSuccess() {
    const urlParams = new URLSearchParams(window.location.search);
    const success = urlParams.get('success');
    if (success === 'true') {
        $('#success').modal('show');
        const newUrl = window.location.href.split('?')[0];
        history.replaceState(null, null, newUrl);
    }
    else if(success === false) {
        $('#failure').modal('show');
    }
}


function setDeleteUrl(link) {
   
    let form = document.getElementById('deleteForm');
    form.action = link.getAttribute('href');
}

function submitDeleteForm() {
    
    let form = document.getElementById('deleteForm');
    form.submit();
}


function getForm(button) {
  
    $('#confirmationModal').modal('show');
    
    let confirmButton = document.querySelector('#continueWithTheSubmitting');
    confirmButton.addEventListener('click', submitForm);

    function submitForm() {
        button.closest('form').submit();
    }
}
