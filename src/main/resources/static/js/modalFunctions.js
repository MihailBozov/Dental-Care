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
}
