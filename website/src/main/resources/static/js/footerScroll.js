document.addEventListener("DOMContentLoaded", function () {
    const smallElement = document.querySelector('#footer small:not(#newsletterHelp)');
    const hasContent = smallElement && smallElement.textContent.trim() !== "";

    if (hasContent) {
        // Smooth scroll to the footer
        document.getElementById('footer').scrollIntoView({behavior: 'instant'});
    }
});



